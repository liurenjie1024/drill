package io.github.zeus.rule;

import io.github.zeus.ZeusGroupScan;
import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.InvalidRelException;
import org.apache.drill.exec.physical.base.GroupScan;
import org.apache.drill.exec.planner.logical.DrillAggregateRel;
import org.apache.drill.exec.planner.logical.DrillScanRel;
import org.apache.drill.exec.planner.logical.RelOptHelper;
import org.apache.drill.exec.planner.physical.AggPrelBase;
import org.apache.drill.exec.planner.physical.AggPruleBase;
import org.apache.drill.exec.planner.physical.DrillDistributionTraitDef;
import org.apache.drill.exec.planner.physical.HashAggPrel;
import org.apache.drill.exec.planner.physical.Prel;
import org.apache.drill.exec.planner.physical.ScanPrel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.zeus.rule.PushHashAggregateToScanRule.pushAggToScan;

public class PushZeroGroupByAggToScanRule extends AggPruleBase {
  public static final PushZeroGroupByAggToScanRule SINGLETON = new PushZeroGroupByAggToScanRule();

  private static final Logger LOG = LoggerFactory.getLogger(PushHashAggregateToScanRule.class);

  private PushZeroGroupByAggToScanRule() {
    super(RelOptHelper.some(DrillAggregateRel.class, RelOptHelper.any(DrillScanRel.class)),
      "PushZeroGroupByAggToScanRule");
  }

  @Override
  public void onMatch(RelOptRuleCall call) {
    final DrillAggregateRel aggregate = call.rel(0);
    final DrillScanRel scan = call.rel(1);


    RelTraitSet traits = call.getPlanner().emptyTraitSet()
      .plus(Prel.DRILL_PHYSICAL)
      .plus(scan.getTraitSet().getTrait(DrillDistributionTraitDef.INSTANCE));

    try {
      HashAggPrel phase1Agg = new HashAggPrel(
        aggregate.getCluster(),
        traits,
        scan,
        aggregate.indicator,
        aggregate.getGroupSet(),
        aggregate.getGroupSets(),
        aggregate.getAggCallList(),
        AggPrelBase.OperatorPhase.PHASE_1of1);


      ScanPrel newScan = pushAggToScan(phase1Agg, scan);

      if (newScan == null) {
        return;
      }

      call.transformTo(newScan);
    } catch (InvalidRelException e) {
      LOG.error("Failed to push aggregate down to scan.", e);
    }

  }

  @Override
  public boolean matches(RelOptRuleCall call) {
    final DrillAggregateRel aggregate = call.rel(0);
    final DrillScanRel scanRel = call.rel(1);

    GroupScan groupScan = scanRel.getGroupScan();
    if (!(groupScan instanceof ZeusGroupScan)) {
      return false;
    }

    if (aggregate.containsDistinctCall() || aggregate.getGroupCount() != 0) {
      // currently, don't use HashAggregate if any of the logical aggrs contains DISTINCT
      return false;
    }

    return super.matches(call);
  }
}
