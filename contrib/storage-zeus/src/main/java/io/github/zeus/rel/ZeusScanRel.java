/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.zeus.rel;

import io.github.zeus.rpc.Expression;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import io.github.zeus.rpc.ScanNode;
import org.apache.drill.exec.physical.base.ScanStats;

import java.util.Collections;
import java.util.List;

public class ZeusScanRel implements ZeusRel {
  private final long rowCount;
  private final ScanNode scanNode;

  public ZeusScanRel(long rowCount, ScanNode scanNode) {
    this.rowCount = rowCount;
    this.scanNode = scanNode;
  }

  @Override
  public Iterable<ZeusRel> getChildren() {
    return Collections.emptyList();
  }

  public ZeusScanRel cloneWithColumnIds(List<Integer> columnIds) {
    ScanNode newScanNode = ScanNode.newBuilder(scanNode)
      .clearColumns()
      .addAllColumns(columnIds)
      .build();

    return new ZeusScanRel(rowCount, newScanNode);
  }

  public ZeusScanRel cloneWithFilters(Expression filterExpression) {
    ScanNode newScanNode = ScanNode.newBuilder(scanNode)
      .clearFilters()
      .addFilters(filterExpression)
      .build();

    return new ZeusScanRel(rowCount, newScanNode);
  }

  public boolean containsFilter() {
    return scanNode.getFiltersCount() > 0;
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
