package io.github.zeus.rule;

import io.github.zeus.ZeusGroupScan;
import io.github.zeus.rel.ZeusGetRowNumRel;
import io.github.zeus.rel.ZeusRel;
import io.github.zeus.rel.ZeusScanRel;
import io.github.zeus.rpc.GetRowNumNode;
import org.apache.calcite.plan.RelOptRule;
import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.rel.core.AggregateCall;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.util.Pair;
import org.apache.drill.exec.planner.logical.DrillAggregateRel;
import org.apache.drill.exec.planner.logical.DrillProjectRel;
import org.apache.drill.exec.planner.logical.DrillScanRel;

import java.util.List;

public class PushCountToScanRule extends RelOptRule {
  public static final PushCountToScanRule SINGLETON = new PushCountToScanRule();

  private PushCountToScanRule() {
    super(RelOptRule.operand(DrillAggregateRel.class, RelOptRule.operand(
      DrillProjectRel.class, RelOptRule.operand(DrillScanRel.class, RelOptRule.none()))));
  }

  @Override
  public void onMatch(RelOptRuleCall call) {
    DrillScanRel scanRel = call.rel(2);
    DrillAggregateRel aggRel = call.rel(0);

    Pair<AggregateCall, String> namedCall = aggRel.getNamedAggCalls().get(0);

    ZeusGroupScan groupScan = (ZeusGroupScan) scanRel.getGroupScan();

    GetRowNumNode node = GetRowNumNode.newBuilder()
      .setDbId(groupScan.getTable().getDBId())
      .setTableId(groupScan.getTable().getId())
      .setAlias(namedCall.getValue())
      .build();

    ZeusGetRowNumRel newRoot = new ZeusGetRowNumRel(node);

    ZeusGroupScan newGroupScan = groupScan.cloneWithNewRootRelNode(newRoot);

    DrillScanRel newScanRel = new DrillScanRel(scanRel.getCluster(),
      aggRel.getTraitSet(),
      scanRel.getTable(),
      newGroupScan,
      aggRel.getRowType(),
      scanRel.getColumns(),
      false);

    call.transformTo(newScanRel);
  }

  @Override
  public boolean matches(RelOptRuleCall call) {
    DrillScanRel scanRel = call.rel(2);
    DrillProjectRel projectRel = call.rel(1);
    DrillAggregateRel aggRel = call.rel(0);

    if (!(scanRel.getGroupScan() instanceof ZeusGroupScan)) {
      return false;
    }

    ZeusGroupScan groupScan = (ZeusGroupScan) scanRel.getGroupScan();

    ZeusRel rootRel = groupScan.getRootRelNode();
    if (!(rootRel instanceof ZeusScanRel)) {
      return false;
    }

    ZeusScanRel zeusScanRel = (ZeusScanRel) rootRel;
    if (zeusScanRel.containsFilter()) {
      return false;
    }

    List<RexNode> projectExprs = projectRel.getProjects();
    if (projectExprs.size() > 1) {
      return false;
    }

    if (aggRel.getGroupCount() > 0) {
      return false;
    }

    if (aggRel.getAggCallList().size() != 1) {
      return false;
    }

    AggregateCall aggCall = aggRel.getAggCallList().get(0);
    if (!aggCall.getAggregation().getName().equalsIgnoreCase("count")) {
      return false;
    }

    return super.matches(call);
  }
}
