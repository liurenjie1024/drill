package io.github.zeus.rel;

import io.github.zeus.rpc.FilterNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusFilterRel extends ZeusAbstractSingleRel {
  private final FilterNode filterNode;

  public ZeusFilterRel(ZeusRel input, FilterNode filterNode) {
    super(input);
    this.filterNode = filterNode;
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.FILTER_NODE)
      .setFilterNode(filterNode)
      .addChildren(getInput().toPlanNode())
      .build();
  }

  @Override
  public ScanStats getScanStats() {
    ScanStats inputScanStats = getInput().getScanStats();
    double selectivity = Math.pow(0.1, filterNode.getConditionsCount());
    return new ScanStats(ScanStats.GroupScanProperty.NO_EXACT_ROW_COUNT,
      (long)(inputScanStats.getRecordCount() * selectivity),
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost());
  }

  public ZeusFilterRel cloneWithNewInput(ZeusRel newInput) {
    return new ZeusFilterRel(newInput, filterNode);
  }
}
