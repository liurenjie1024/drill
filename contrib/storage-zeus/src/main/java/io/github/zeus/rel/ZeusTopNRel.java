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

import io.github.zeus.rpc.PlanNode;
import io.github.zeus.rpc.PlanNodeType;
import io.github.zeus.rpc.TopNNode;
import org.apache.drill.exec.physical.base.ScanStats;

public class ZeusTopNRel extends ZeusAbstractSingleRel {
  private final TopNNode topNNode;

  public ZeusTopNRel(ZeusRel input, TopNNode topNNode) {
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
