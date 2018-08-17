package io.github.zeus.rel;

import io.github.zeus.rpc.AggregationNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusHashAggRel extends ZeusAbstractSingleRel {
  private final AggregationNode aggNode;

  public ZeusHashAggRel(ZeusRel input, AggregationNode aggNode) {
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
    long rowCount;
    if (aggNode.getGroupByCount() > 0){
      double ratio = 1 - Math.pow(0.5, aggNode.getGroupByCount());
      rowCount = (long)(inputScanStats.getRecordCount() * ratio);
    } else {
      rowCount = 1;
    }

    return new ScanStats(ScanStats.GroupScanProperty.EXACT_ROW_COUNT,
      rowCount,
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost());
  }
}
