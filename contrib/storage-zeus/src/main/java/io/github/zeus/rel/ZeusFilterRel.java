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

import io.github.zeus.rpc.FilterNode;
import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusFilterRel extends ZeusAbstractSingleRel {
  private final FilterNode filterNode;

  public ZeusFilterRel(ZeusRel input, FilterNode filterNode) {
    super(input);
    this.filterNode = filterNode;
  }

  @Override
  public PlanNode toPlanNode() {
    return PlanNode.newBuilder()
      .setPlanNodeType(PlanNodeType.FILTER_NODE)
      .setFilterNode(filterNode)
      .addChildren(getInput().toPlanNode())
      .build();
  }

  @Override
  public ScanStats getScanStats() {
    ScanStats inputScanStats = getInput().getScanStats();
    double selectivity = Math.pow(0.1, filterNode.getConditionsCount());
    return new ScanStats(ScanStats.GroupScanProperty.NO_EXACT_ROW_COUNT,
      (long)(inputScanStats.getRecordCount() * selectivity),
      inputScanStats.getCpuCost(),
      inputScanStats.getDiskCost());
  }

  public ZeusFilterRel cloneWithNewInput(ZeusRel newInput) {
    return new ZeusFilterRel(newInput, filterNode);
  }
}
