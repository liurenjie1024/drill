package io.github.zeus.rel;

import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import io.github.zeus.rpc.ProjectNode;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusProjectNode extends ZeusSingleRelNode {
  private final ProjectNode projectNode;

  public ZeusProjectNode(ZeusRelNode input, ProjectNode projectNode) {
    super(input);
    this.projectNode = projectNode;
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.PROJECT_NODE)
      .setProjectNode(projectNode)
      .addChildren(getInput().toPlanNode())
      .build() ;
  }

  @Override
  public ScanStats getScanStats() {
    return getInput().getScanStats();
  }
}
