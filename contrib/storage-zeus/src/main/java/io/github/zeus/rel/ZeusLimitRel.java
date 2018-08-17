package io.github.zeus.rel;

import io.github.zeus.rpc.LimitNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusLimitRel extends ZeusAbstractSingleRel {
  private final LimitNode limitNode;

  public ZeusLimitRel(ZeusRel input, LimitNode limitNode) {
    super(input);
    this.limitNode = limitNode;
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.LIMIT_NODE)
      .setLimitNode(limitNode)
      .addChildren(getInput().toPlanNode())
      .build() ;
  }

  @Override
  public ScanStats getScanStats() {
    ScanStats inputScanStats = getInput().getScanStats();

    return new ScanStats(ScanStats.GroupScanProperty.NO_EXACT_ROW_COUNT,
      limitNode.getLimit(),
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost()) ;
  }
}
