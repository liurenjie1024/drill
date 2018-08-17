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

package io.github.zeus;

import io.github.zeus.client.impl.AbstractZeusClient;
import io.github.zeus.rpc.QueryPlan;
import io.github.zeus.rpc.QueryResult;
import io.github.zeus.rpc.ZeusCatalog;

public class FakeZeusClient extends AbstractZeusClient {
  private final ZeusCatalog catalog;

  public FakeZeusClient(ZeusCatalog catalog) {
    this.catalog = catalog;
  }

  @Override
  public ZeusCatalog getCatalog() {
    return catalog;
  }

  @Override
  public QueryResult query(QueryPlan queryPlan) {
    throw new UnsupportedOperationException("query is not supported in fake zeus client.");
  }

  @Override
  public void close() throws Exception {
  }
}
