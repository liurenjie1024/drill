package io.github.zeus.rel;

import io.github.zeus.rpc.GetRowNumNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

import java.util.Collections;

public class ZeusGetRowNumRel implements ZeusRel {
  private final GetRowNumNode node;

  public ZeusGetRowNumRel(GetRowNumNode node) {
    this.node = node;
  }

  @Override
  public Iterable<ZeusRel> getChildren() {
    return Collections.emptyList();
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.GET_ROW_NUM_NODE)
      .setGetRowNumNode(node)
      .build();
  }

  @Override
  public ScanStats getScanStats() {
    return new ScanStats(ScanStats.GroupScanProperty.EXACT_ROW_COUNT, 1, 0, 0);
  }
}
