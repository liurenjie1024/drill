package io.github.zeus.rel;

import io.github.zeus.rpc.AggregationNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusHashAggNode extends ZeusSingleRelNode {
  private final long rowCount;
  private final AggregationNode aggNode;

  public ZeusHashAggNode(ZeusRelNode input, AggregationNode aggNode) {
    this(input, aggNode, -1);
  }

  public ZeusHashAggNode(ZeusRelNode input, AggregationNode aggNode, long rowCount) {
    super(input);
    this.aggNode = aggNode;
    this.rowCount = rowCount;
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
    long rowCount = this.rowCount;
    if (rowCount < 0 ){
      double ratio = 1 - Math.pow(0.5, aggNode.getGroupByCount());
      rowCount = (long)(inputScanStats.getRecordCount() * ratio);
    }
    return new ScanStats(ScanStats.GroupScanProperty.NO_EXACT_ROW_COUNT,
      rowCount,
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost());
  }
}
