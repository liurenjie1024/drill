package io.github.zeus.rel;

import io.github.zeus.rpc.PlanNode;
import org.apache.drill.exec.physical.base.ScanStats;

public interface ZeusRelNode {
  Iterable<ZeusRelNode>  getChildren();
  PlanNode toPlanNode();
  ScanStats getScanStats();
}
