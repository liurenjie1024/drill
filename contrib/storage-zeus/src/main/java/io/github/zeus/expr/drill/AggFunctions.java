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
import io.github.zeus.rpc.AggFuncId;
import io.github.zeus.rpc.ColumnType;

public class AggFunctions {
  public static final ZeusFunctionEntry COUNT_BOOL = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.BOOL);
  public static final ZeusFunctionEntry COUNT_INT8 = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.INT8);
  public static final ZeusFunctionEntry COUNT_INT16 = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.INT16);
  public static final ZeusFunctionEntry COUNT_INT32 = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.INT32);
  public static final ZeusFunctionEntry COUNT_INT64 = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.INT64);
  public static final ZeusFunctionEntry COUNT_FLOAT4 = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.FLOAT4);
  public static final ZeusFunctionEntry COUNT_FLOAT8 = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.FLOAT8);
  public static final ZeusFunctionEntry COUNT_STR = ZeusFunctionEntry.from(AggFuncId.COUNT,
    ColumnType.INT64, "count", ColumnType.STRING);

  public static final ZeusFunctionEntry MAX_INT32 = ZeusFunctionEntry.from(AggFuncId.MAX_INT32,
    ColumnType.INT32, "max", ColumnType.INT32);
  public static final ZeusFunctionEntry MAX_INT64 = ZeusFunctionEntry.from(AggFuncId.MAX_INT64,
    ColumnType.INT64, "max", ColumnType.INT64);
  public static final ZeusFunctionEntry MAX_FLOAT4 = ZeusFunctionEntry.from(AggFuncId.MAX_FLOAT4,
    ColumnType.FLOAT4, "max", ColumnType.FLOAT4);
  public static final ZeusFunctionEntry MAX_FLOAT8 = ZeusFunctionEntry.from(AggFuncId.MAX_FLOAT8,
    ColumnType.FLOAT8, "max", ColumnType.FLOAT8);
  public static final ZeusFunctionEntry MAX_STR = ZeusFunctionEntry.from(AggFuncId.MAX_STR,
    ColumnType.STRING, "max", ColumnType.STRING);
  
  public static final ZeusFunctionEntry MIN_INT32 = ZeusFunctionEntry.from(AggFuncId.MIN_INT32,
    ColumnType.INT32,"min", ColumnType.INT32);
  public static final ZeusFunctionEntry MIN_INT64 = ZeusFunctionEntry.from(AggFuncId.MIN_INT64,
    ColumnType.INT64,"min", ColumnType.INT64);
  public static final ZeusFunctionEntry MIN_FLOAT4 = ZeusFunctionEntry.from(AggFuncId.MIN_FLOAT4,
    ColumnType.FLOAT4, "min", ColumnType.FLOAT4);
  public static final ZeusFunctionEntry MIN_FLOAT8 = ZeusFunctionEntry.from(AggFuncId.MIN_FLOAT8,
    ColumnType.FLOAT8, "min", ColumnType.FLOAT8);
  public static final ZeusFunctionEntry MIN_STR = ZeusFunctionEntry.from(AggFuncId.MIN_STR,
    ColumnType.STRING, "min", ColumnType.STRING);

  public static final ZeusFunctionEntry SUM_INT32 = ZeusFunctionEntry.from(AggFuncId.SUM_INT32,
    ColumnType.INT32,"$sum0", ColumnType.INT32);
  public static final ZeusFunctionEntry SUM_INT64 = ZeusFunctionEntry.from(AggFuncId.SUM_INT64,
    ColumnType.INT64,"$sum0", ColumnType.INT64);
  public static final ZeusFunctionEntry SUM_FLOAT4 = ZeusFunctionEntry.from(AggFuncId.SUM_FLOAT4,
    ColumnType.FLOAT4,"$sum0", ColumnType.FLOAT4);
  public static final ZeusFunctionEntry SUM_FLOAT8 = ZeusFunctionEntry.from(AggFuncId.SUM_FLOAT8,
    ColumnType.FLOAT8, "$sum0", ColumnType.FLOAT8);
}
