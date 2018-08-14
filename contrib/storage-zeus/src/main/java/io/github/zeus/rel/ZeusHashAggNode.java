package io.github.zeus.rel;

import io.github.zeus.rpc.AggregationNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusHashAggNode extends ZeusSingleRelNode {
  private final AggregationNode aggNode;

  public ZeusHashAggNode(ZeusRelNode input, AggregationNode aggNode) {
    super(input);
    this.aggNode = aggNode;
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.AGGREGATE_NODE)
      .setAggNode(aggNode)
      .addChildren(getInput().toPlanNode())
      .build() ;
  }

  @Override
  public ScanStats getScanStats() {
    ScanStats inputScanStats = getInput().getScanStats();
    double ratio = 1 - Math.pow(0.5, aggNode.getGroupByCount());
    return new ScanStats(ScanStats.GroupScanProperty.NO_EXACT_ROW_COUNT,
      (long)(inputScanStats.getRecordCount() * ratio),
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost());
  }
}
