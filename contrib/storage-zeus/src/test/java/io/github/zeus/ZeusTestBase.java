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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zeus.client.ZeusClient;
import io.github.zeus.com.google.protobuf.util.JsonFormat;
import io.github.zeus.rpc.QueryPlan;
import io.github.zeus.rpc.ZeusCatalog;
import org.apache.drill.PlanTestBase;
import org.apache.drill.common.exceptions.ExecutionSetupException;
import org.apache.drill.exec.store.StoragePluginRegistry;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.Assert.assertEquals;

public class ZeusTestBase extends PlanTestBase {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    initZeusStoragePlugin();
  }

  protected static void initZeusStoragePlugin() throws ExecutionSetupException, IOException {
    StoragePluginRegistry storagePluginRegistry = getDrillbitContext().getStorage();

    ZeusStoragePluginConfig config = new ZeusStoragePluginConfig("/home/liurenjie-sal/Downloads/test/logs.schema", "localhost", 8899);
    config.setEnabled(true);
    config.setClient(createFakeClient());

    storagePluginRegistry.createOrUpdate("logs", config, true);
  }

  protected static ZeusClient createFakeClient() throws IOException {
    return new FakeZeusClient(readCatalog("test_catalog.json"));
  }

  protected static ZeusCatalog readCatalog(String resourcePath) throws IOException {
    try(InputStream is = ZeusTestBase.class.getClassLoader().getResourceAsStream(resourcePath);
        Reader reader = new InputStreamReader(is)) {
      ZeusCatalog.Builder catalog = ZeusCatalog.newBuilder();
      JsonFormat.parser()
        .ignoringUnknownFields()
        .merge(reader, catalog);

      return catalog.build();
    }
  }

  protected static QueryPlan readQueryPlan(String resourcePath) throws IOException {
    try(InputStream is = ZeusTestBase.class.getClassLoader().getResourceAsStream(resourcePath);
        Reader reader = new InputStreamReader(is)) {
      QueryPlan.Builder plan = QueryPlan.newBuilder();
      JsonFormat.parser()
        .ignoringUnknownFields()
        .merge(reader, plan);

      return plan.build();
    }
  }

  protected static void comparePlanIgnoringPlanId(QueryPlan expectedPlan, QueryPlan actualPlan) {
    QueryPlan expectedQueryPlan = QueryPlan.newBuilder(expectedPlan)
      .clearPlanId()
      .build();

    QueryPlan actualQueryPlan = QueryPlan.newBuilder(actualPlan)
      .clearPlanId()
      .build();


    assertEquals(expectedQueryPlan, actualQueryPlan);
  }

  protected static void comparePlanWithStoredPlan(String resourcePath, QueryPlan actualPlan)
    throws IOException {
    comparePlanIgnoringPlanId(readQueryPlan(resourcePath), actualPlan);
  }

  protected static QueryPlan getZeusScanPlanFromSql(String sql) throws Exception {
    String physicalPlan = getPlanInString("EXPLAIN PLAN FOR " + sql,
      JSON_FORMAT);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootNode = mapper.readTree(physicalPlan);
    JsonNode jsonPlan = rootNode.at("/graph/0/queryPlan/jsonPlan");

    QueryPlan.Builder queryPlanBuilder  = QueryPlan.newBuilder();
    JsonFormat.parser().merge(jsonPlan.textValue(), queryPlanBuilder);

    return queryPlanBuilder.build();
  }

  protected static void verifyZeusPlanForSql(String sql, String resourcePathForPlan) throws Exception {
    comparePlanWithStoredPlan(resourcePathForPlan, getZeusScanPlanFromSql(sql));
  }
}
