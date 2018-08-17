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
