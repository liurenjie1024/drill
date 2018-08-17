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

import com.google.common.collect.ImmutableList;
import io.github.zeus.ZeusGroupScan;
import io.github.zeus.expr.ZeusExprBuilder;
import io.github.zeus.rel.ZeusProjectRel;
import io.github.zeus.rpc.Expression;
import io.github.zeus.rpc.ProjectNode;
import io.github.zeus.rpc.ProjectNode.ProjectItem;
import org.apache.calcite.plan.RelOptRule;
import org.apache.calcite.plan.RelOptRuleCall;
import org.apache.calcite.rex.RexNode;
import org.apache.calcite.util.Pair;
import org.apache.drill.common.expression.LogicalExpression;
import org.apache.drill.exec.physical.base.GroupScan;
import org.apache.drill.exec.planner.logical.DrillOptiq;
import org.apache.drill.exec.planner.logical.DrillParseContext;
import org.apache.drill.exec.planner.logical.DrillProjectRel;
import org.apache.drill.exec.planner.logical.DrillScanRel;
import org.apache.drill.exec.planner.physical.PrelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PushProjectToScanRule extends RelOptRule {
  public static final PushProjectToScanRule SINGLETON = new PushProjectToScanRule();

  private PushProjectToScanRule() {
    super(RelOptRule.operand(DrillProjectRel.class,
      RelOptRule.operand(DrillScanRel.class, RelOptRule.none())));
  }

  @Override
  public void onMatch(RelOptRuleCall call) {
    DrillProjectRel projectRel = call.rel(0);
    DrillScanRel scanRel = call.rel(1);

    ZeusGroupScan groupScan = (ZeusGroupScan)scanRel.getGroupScan();

    List<ProjectItem> projects = new ArrayList<>(projectRel.getProjects().size());
    boolean allConverted = true;

    for (Pair<RexNode, String> namedProject: projectRel.getNamedProjects()) {
      LogicalExpression logicalExpr = DrillOptiq.toDrill(
        new DrillParseContext(PrelUtil.getPlannerSettings(call.getPlanner())),
        scanRel, namedProject.left);

      Optional<Expression> zeusExprOpt = logicalExpr.accept(
        new ZeusExprBuilder(scanRel),
        null);

      if (!zeusExprOpt.isPresent()) {
        allConverted = false;
        break;
      }

      Expression zeusExpr = Expression.newBuilder(zeusExprOpt.get())
        .setAlias(namedProject.right)
        .build();

      ProjectItem projectItem = ProjectItem.newBuilder()
        .setAlias(namedProject.right)
        .setExpression(zeusExpr)
        .build();

      projects.add(projectItem);
    }

    if (allConverted) {
      ProjectNode projectNode = ProjectNode.newBuilder()
        .addAllItems(projects)
        .build();

      ZeusProjectRel newRoot = new ZeusProjectRel(groupScan.getRootRelNode(), projectNode);
      ZeusGroupScan newGroupScan = groupScan.cloneWithNewRootRelNode(newRoot)
        .setRulePushedDown(PushedDownRule.PROJECT);

      DrillScanRel newScan = new DrillScanRel(
        scanRel.getCluster(),
        projectRel.getTraitSet(),
        scanRel.getTable(),
        newGroupScan,
        projectRel.getRowType(),
        scanRel.getColumns(),
        false);

      call.transformTo(newScan);
    } else {
      ZeusGroupScan newGroupScan = groupScan.copy()
        .setRulePushedDown(PushedDownRule.PROJECT);

      DrillScanRel newScan = new DrillScanRel(
        scanRel.getCluster(),
        projectRel.getTraitSet(),
        scanRel.getTable(),
        newGroupScan,
        projectRel.getRowType(),
        scanRel.getColumns(),
        false);

      call.transformTo(projectRel.copy(projectRel.getTraitSet(), ImmutableList.of(newScan)));
    }


  }

  @Override
  public boolean matches(RelOptRuleCall call) {
    DrillScanRel scanRel = call.rel(1);
    GroupScan groupScan = scanRel.getGroupScan();

    if (!(groupScan instanceof ZeusGroupScan)) {
      return false;
    }

    ZeusGroupScan zeusGroupScan = (ZeusGroupScan) groupScan;
    if (zeusGroupScan.isRulePushedDown(PushedDownRule.PROJECT)) {
      return false;
    }

    return super.matches(call);
  }
}
