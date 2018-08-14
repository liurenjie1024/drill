package io.github.zeus.rel;

import io.github.zeus.rpc.Expression;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import io.github.zeus.rpc.ScanNode;
import org.apache.drill.exec.physical.base.ScanStats;

import java.util.Collections;
import java.util.List;

public class ZeusScanNode implements ZeusRelNode {
  private final long rowCount;
  private final ScanNode scanNode;

  public ZeusScanNode(long rowCount, ScanNode scanNode) {
    this.rowCount = rowCount;
    this.scanNode = scanNode;
  }

  @Override
  public Iterable<ZeusRelNode> getChildren() {
    return Collections.emptyList();
  }

  public ZeusScanNode cloneWithColumnIds(List<Integer> columnIds) {
    ScanNode newScanNode = ScanNode.newBuilder(scanNode)
      .clearColumns()
      .addAllColumns(columnIds)
      .build();

    return new ZeusScanNode(rowCount, newScanNode);
  }

  public ZeusScanNode cloneWithFilters(Expression filterExpression) {
    ScanNode newScanNode = ScanNode.newBuilder(scanNode)
      .clearFilters()
      .addFilters(filterExpression)
      .build();

    return new ZeusScanNode(rowCount, newScanNode);
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.SCAN_NODE)
      .setScanNode(scanNode)
      .build();
  }

  @Override
  public ScanStats getScanStats() {
    return new ScanStats(ScanStats.GroupScanProperty.NO_EXACT_ROW_COUNT, rowCount, rowCount,
      rowCount*scanNode.getColumnsCount());
  }
}
