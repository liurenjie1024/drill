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

import org.apache.drill.PlanTestBase;
import org.apache.drill.common.exceptions.ExecutionSetupException;
import org.apache.drill.exec.store.StoragePluginRegistry;
import org.junit.BeforeClass;

public class ZeusTestBase extends PlanTestBase {

  @BeforeClass
  public static void setUpBeforeClass() throws ExecutionSetupException {
    initZeusStoragePlugin();
  }

  protected static void initZeusStoragePlugin() throws ExecutionSetupException {
    StoragePluginRegistry storagePluginRegistry = getDrillbitContext().getStorage();

    ZeusStoragePluginConfig config = new ZeusStoragePluginConfig("/home/liurenjie-sal/Downloads/test/logs.schema", "localhost", 8899);
    config.setEnabled(true);

    storagePluginRegistry.createOrUpdate("logs", config, true);
  }
}
