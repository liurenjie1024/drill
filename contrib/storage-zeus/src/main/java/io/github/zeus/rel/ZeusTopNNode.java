package io.github.zeus.rel;

import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import io.github.zeus.rpc.TopNNode;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusTopNNode extends ZeusSingleRelNode {
  private final TopNNode topNNode;

  public ZeusTopNNode(ZeusRelNode input, TopNNode topNNode) {
    super(input);
    this.topNNode = topNNode;
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.TOPN_NODE)
      .setTopnNode(topNNode)
      .addChildren(getInput().toPlanNode())
      .build();
  }

  @Override
  public ScanStats getScanStats() {
    ScanStats inputScanStats = getInput().getScanStats();
    return new ScanStats(ScanStats.GroupScanProperty.EXACT_ROW_COUNT,
      topNNode.getLimit(),
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost());
  }
}
