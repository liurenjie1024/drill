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
package io.github.zeus.expr.drill;

import io.github.zeus.expr.ZeusFunctionEntry;

import static io.github.zeus.rpc.ColumnType.BOOL;
import static io.github.zeus.rpc.ScalarFuncId.AND;
import static io.github.zeus.rpc.ScalarFuncId.NOT;
import static io.github.zeus.rpc.ScalarFuncId.OR;

public class LogicalFunctions {
  public static final ZeusFunctionEntry AND_BOOLEAN = ZeusFunctionEntry.from(AND, "booleanAnd", BOOL, BOOL);
  public static final ZeusFunctionEntry OR_BOOLEAN = ZeusFunctionEntry.from(OR, "booleanOr", BOOL, BOOL);
  public static final ZeusFunctionEntry NOT_BOOLEAN = ZeusFunctionEntry.from(NOT, "booleanNot", BOOL, BOOL);
}
