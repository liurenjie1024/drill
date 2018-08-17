/*
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

package io.github.zeus;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.github.zeus.client.exception.CatalogNotFoundException;
import io.github.zeus.rel.ZeusRel;
import io.github.zeus.rel.ZeusRels;
import io.github.zeus.rel.ZeusScanRel;
import io.github.zeus.rule.PushedDownRule;
import io.github.zeus.schema.ZeusTable;
import org.apache.drill.common.exceptions.ExecutionSetupException;
import org.apache.drill.common.expression.SchemaPath;
import org.apache.drill.common.logical.StoragePluginConfig;
import org.apache.drill.exec.physical.PhysicalOperatorSetupException;
import org.apache.drill.exec.physical.base.AbstractGroupScan;
import org.apache.drill.exec.physical.base.PhysicalOperator;
import org.apache.drill.exec.physical.base.ScanStats;
import org.apache.drill.exec.physical.base.SubScan;
import org.apache.drill.exec.proto.CoordinationProtos.DrillbitEndpoint;
import org.apache.drill.exec.store.StoragePluginRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.List;

@JsonTypeName("zeus-scan")
public class ZeusGroupScan extends AbstractGroupScan {
  private static final Logger logger = LoggerFactory.getLogger(ZeusGroupScan.class);

  private final int tableId;
  private final ZeusQueryPlan queryPlan;
  private final ZeusRel rootRelNode;
  private final ZeusStoragePluginConfig config;
  private final ZeusStoragePlugin plugin;
  private final BitSet pushedDownRules;

  @JsonCreator
  public ZeusGroupScan(
      @JsonProperty("tableId") int tableId,
      @JsonProperty("queryPlan") ZeusQueryPlan queryPlan,
      @JsonProperty("config") StoragePluginConfig config,
      @JsonProperty("pushedDownRules") byte[] pushedDownRules,
      @JacksonInject StoragePluginRegistry registry) throws ExecutionSetupException {
    this(tableId, queryPlan, null, (ZeusStoragePluginConfig)config,
      (ZeusStoragePlugin) registry.getPlugin(config), BitSet.valueOf(pushedDownRules));
  }

  public ZeusGroupScan(
    int tableId,
    ZeusRel rootRelNode,
    ZeusStoragePluginConfig config,
    ZeusStoragePlugin plugin) {
    this(tableId, ZeusRels.toQueryPlan(rootRelNode), rootRelNode, config, plugin, new BitSet());
  }

  public ZeusGroupScan(
    int tableId,
    ZeusRel rootRelNode,
    ZeusStoragePluginConfig config,
    ZeusStoragePlugin plugin,
    BitSet pushedDownRules) {
    this(tableId, ZeusRels.toQueryPlan(rootRelNode), rootRelNode, config, plugin,
      pushedDownRules);
  }


  private ZeusGroupScan(
      int tableId,
      ZeusQueryPlan queryPlan,
      ZeusRel rootNode,
      ZeusStoragePluginConfig config,
      ZeusStoragePlugin plugin,
      BitSet pushedDownRules) {
    super("");
    this.tableId = tableId;
    this.queryPlan = queryPlan;
    this.config = config;
    this.plugin = plugin;
    this.rootRelNode = rootNode;
    this.pushedDownRules = BitSet.valueOf(pushedDownRules.toByteArray());
  }


  @Override
  public void applyAssignments(List<DrillbitEndpoint> endpoints) throws PhysicalOperatorSetupException {
  }

  @Override
  public SubScan getSpecificScan(int minorFragmentId) throws ExecutionSetupException {
    return new ZeusSubScan(queryPlan, config, plugin);
  }

  @Override
  public ScanStats getScanStats() {
    return rootRelNode.getScanStats();
  }

  @Override
  public int getMaxParallelizationWidth() {
    return 1;
  }

  @JsonProperty
  public ZeusStoragePluginConfig getConfig() {
    return config;
  }

  @JsonProperty
  public byte[] getPushedDownRules() {
    return pushedDownRules.toByteArray();
  }

  public boolean isRulePushedDown(PushedDownRule rule) {
    return pushedDownRules.get(rule.ordinal());
  }

  public ZeusGroupScan setRulePushedDown(PushedDownRule rule) {
    pushedDownRules.set(rule.ordinal());
    return this;
  }

  @Override
  public ZeusGroupScan clone(List<SchemaPath> columns) {
    ZeusScanRel zeusScanRel = (ZeusScanRel) rootRelNode;

    List<Integer> columnIds = plugin.getDbSchema()
      .getTable(tableId)
      .map(t -> t.getColumnIds(columns))
      .orElseThrow(() -> CatalogNotFoundException.tableIdNotFound(plugin.getDbSchema()
        .getId(), tableId));

    ZeusRel newRoot = zeusScanRel.cloneWithColumnIds(columnIds);

    return new ZeusGroupScan(tableId, newRoot, config, plugin, pushedDownRules);
  }

  @Override
  public boolean canPushdownProjects(List<SchemaPath> columns) {
    return true;
  }

  public ZeusGroupScan cloneWithNewRootRelNode(ZeusRel newRoot) {
    return new ZeusGroupScan(tableId, newRoot, config, plugin, pushedDownRules);
  }

  public ZeusGroupScan copy() {
    return new ZeusGroupScan(tableId, queryPlan, rootRelNode, config, plugin,
      pushedDownRules);
  }

  @Override
  public String getDigest() {
    return String.format("ZeusGroupScan[plan=%s]", queryPlan);
  }

  @Override
  public PhysicalOperator getNewWithChildren(List<PhysicalOperator> children) throws ExecutionSetupException {
    return this;
  }

  @JsonIgnore
  public ZeusTable getTable() {
    return plugin.getDbSchema().getTable(tableId).get();
  }

  @JsonProperty
  public ZeusQueryPlan getQueryPlan() {
    return queryPlan;
  }

  @JsonIgnore
  public ZeusRel getRootRelNode() {
    return rootRelNode;
  }
}
