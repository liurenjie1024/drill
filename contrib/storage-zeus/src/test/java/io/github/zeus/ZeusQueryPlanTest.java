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

import org.apache.drill.categories.ZeusStorageTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({ZeusStorageTest.class})
public class ZeusQueryPlanTest extends ZeusTestBase {
  @Test
  public void testSelectAllWithFilterPlan() throws Exception {
    verifyZeusPlanForSql("select * from logs.realtimelog where `timestamp` > 10000 and `timestamp` <= 20000",
      "select_with_filter_plan.json");
  }

  @Test
  public void testProjectWithAliasPlan() throws Exception {
    verifyZeusPlanForSql("select (fee+realFee) as money, advertiserId as advId from logs.realtimelog",
      "project_with_alias_plan.json");
  }

  @Test
  public void testPushTopNPlan() throws Exception {
    verifyZeusPlanForSql(
      "select realFee + fee, `timestamp` " +
        "from logs.realtimelog " +
        "where advertiserId > 100 " +
        "order by `timestamp`, advertiserId " +
        "limit 3",
      "push_topn_plan.json"
    );
  }

  @Test
  public void testPushZeroGroupByAggPlan() throws Exception {
    verifyZeusPlanForSql(
      "select " +
        "min(fee) as minFee, " +
        "max(realFee) as maxRealFee, " +
        "sum(numOfShow) as showSum " +
        "from logs.realtimelog ",

      "push_zero_group_by_hash_agg_plan.json"
    );
  }

  @Test
  public void testPushHashAggregationPlan() throws Exception {
    verifyZeusPlanForSql(
      "select advertiserId, " +
        "min(fee) as minFee, " +
        "max(realFee) as maxRealFee, " +
        "sum(numOfShow) as showSum " +
        "from logs.realtimelog " +
        "group by advertiserId " +
        "having sum(numOfClick) > 0",

      "push_hash_agg_plan.json"
    );
  }

  @Test
  public void testPushCountToScanPlan() throws Exception {
    verifyZeusPlanForSql(
      "select count(*) as cnt from logs.realtimelog",
      "push_count_plan.json"
    );
  }
}
