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

package io.github.zeus.rule;

import io.github.zeus.ZeusGroupScan;
import io.github.zeus.rel.ZeusLimitRel;
import io.github.zeus.rpc.LimitNode;
import org.apache.calcite.plan.RelOptRule;
import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.rex.RexLiteral;
import org.apache.drill.exec.planner.logical.DrillLimitRel;
import org.apache.drill.exec.planner.logical.DrillScanRel;

public class PushLimitToScanRule extends RelOptRule {
  static final PushLimitToScanRule SINGLETON = new PushLimitToScanRule();

  private PushLimitToScanRule() {
    super(RelOptRule.operand(DrillLimitRel.class,
      RelOptRule.operand(DrillScanRel.class, RelOptRule.none())));
  }

  @Override
  public void onMatch(RelOptRuleCall call) {
    DrillLimitRel limitRel = call.rel(0);
    DrillScanRel scanRel = call.rel(1);
    int limit = RexLiteral.intValue(limitRel.getFetch());

    LimitNode limitNode = LimitNode.newBuilder()
        .setLimit(limit)
        .build();

    ZeusGroupScan groupScan = (ZeusGroupScan) scanRel.getGroupScan();

    ZeusLimitRel newRoot = new ZeusLimitRel(groupScan.getRootRelNode(), limitNode);
    ZeusGroupScan newGroupScan = groupScan.cloneWithNewRootRelNode(newRoot);

    DrillScanRel newScan = new DrillScanRel(
      scanRel.getCluster(),
      limitRel.getTraitSet(),
      scanRel.getTable(),
      newGroupScan,
      limitRel.getRowType(),
      scanRel.getColumns(),
      false);

    call.transformTo(newScan);
  }

  @Override
  public boolean matches(RelOptRuleCall call) {
    DrillScanRel scanRel = call.rel(1);

    if (!(scanRel.getGroupScan() instanceof ZeusGroupScan)) {
      return false;
    }

    DrillLimitRel limitRel = call.rel(0);
    if (limitRel.getOffset() != null) {
      return false;
    }

    return super.matches(call);
  }
}
