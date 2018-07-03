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

import static io.github.zeus.rpc.ScalarFuncId.NE_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.NE_INT64;
import static io.github.zeus.rpc.ScalarFuncId.GE_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.NE_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.GE_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.LT_INT32;
import static io.github.zeus.rpc.ScalarFuncId.GT_STR;
import static io.github.zeus.rpc.ScalarFuncId.GE_INT32;
import static io.github.zeus.rpc.ScalarFuncId.LE_INT64;
import static io.github.zeus.rpc.ScalarFuncId.EQ_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.GT_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.GT_INT32;
import static io.github.zeus.rpc.ScalarFuncId.LE_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.EQ_INT32;
import static io.github.zeus.rpc.ScalarFuncId.LT_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.LE_INT32;
import static io.github.zeus.rpc.ScalarFuncId.EQ_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.EQ_STR;
import static io.github.zeus.rpc.ScalarFuncId.LT_INT64;
import static io.github.zeus.rpc.ScalarFuncId.LT_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.GT_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.GE_INT64;
import static io.github.zeus.rpc.ScalarFuncId.GT_INT64;
import static io.github.zeus.rpc.ScalarFuncId.GE_STR;
import static io.github.zeus.rpc.ScalarFuncId.LT_STR;
import static io.github.zeus.rpc.ScalarFuncId.LE_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.NE_STR;
import static io.github.zeus.rpc.ScalarFuncId.EQ_INT64;
import static io.github.zeus.rpc.ScalarFuncId.NE_INT32;
import static io.github.zeus.rpc.ScalarFuncId.LE_STR;

import static io.github.zeus.rpc.ColumnType.BOOL;
import static io.github.zeus.rpc.ColumnType.INT32;
import static io.github.zeus.rpc.ColumnType.FLOAT4;
import static io.github.zeus.rpc.ColumnType.STRING;
import static io.github.zeus.rpc.ColumnType.FLOAT8;
import static io.github.zeus.rpc.ColumnType.INT64;

/**
 * This class is generated.
 */
public class ComparatorFunctions {
  
    public static final ZeusFunctionEntry GREATER_THAN_INT32_INT32 = ZeusFunctionEntry.from(GT_INT32, BOOL, "greater_than", INT32, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_INT32_INT64 = ZeusFunctionEntry.from(GT_INT64, BOOL, "greater_than", INT32, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_INT32_FLOAT4 = ZeusFunctionEntry.from(GT_FLOAT4, BOOL, "greater_than", INT32, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_INT32_FLOAT8 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", INT32, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_INT64_INT32 = ZeusFunctionEntry.from(GT_INT64, BOOL, "greater_than", INT64, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_INT64_INT64 = ZeusFunctionEntry.from(GT_INT64, BOOL, "greater_than", INT64, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_INT64_FLOAT4 = ZeusFunctionEntry.from(GT_FLOAT4, BOOL, "greater_than", INT64, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_INT64_FLOAT8 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", INT64, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT4_INT32 = ZeusFunctionEntry.from(GT_FLOAT4, BOOL, "greater_than", FLOAT4, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT4_INT64 = ZeusFunctionEntry.from(GT_FLOAT4, BOOL, "greater_than", FLOAT4, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(GT_FLOAT4, BOOL, "greater_than", FLOAT4, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", FLOAT4, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT8_INT32 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", FLOAT8, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT8_INT64 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", FLOAT8, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", FLOAT8, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(GT_FLOAT8, BOOL, "greater_than", FLOAT8, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_STRING_STRING = ZeusFunctionEntry.from(GT_STR, BOOL, "greater_than", STRING, STRING);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT32_INT32 = ZeusFunctionEntry.from(GE_INT32, BOOL, "greater_than_or_equal_to", INT32, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT32_INT64 = ZeusFunctionEntry.from(GE_INT64, BOOL, "greater_than_or_equal_to", INT32, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT32_FLOAT4 = ZeusFunctionEntry.from(GE_FLOAT4, BOOL, "greater_than_or_equal_to", INT32, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT32_FLOAT8 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", INT32, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT64_INT32 = ZeusFunctionEntry.from(GE_INT64, BOOL, "greater_than_or_equal_to", INT64, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT64_INT64 = ZeusFunctionEntry.from(GE_INT64, BOOL, "greater_than_or_equal_to", INT64, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT64_FLOAT4 = ZeusFunctionEntry.from(GE_FLOAT4, BOOL, "greater_than_or_equal_to", INT64, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_INT64_FLOAT8 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", INT64, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT4_INT32 = ZeusFunctionEntry.from(GE_FLOAT4, BOOL, "greater_than_or_equal_to", FLOAT4, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT4_INT64 = ZeusFunctionEntry.from(GE_FLOAT4, BOOL, "greater_than_or_equal_to", FLOAT4, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(GE_FLOAT4, BOOL, "greater_than_or_equal_to", FLOAT4, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", FLOAT4, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT8_INT32 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", FLOAT8, INT32);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT8_INT64 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", FLOAT8, INT64);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", FLOAT8, FLOAT4);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(GE_FLOAT8, BOOL, "greater_than_or_equal_to", FLOAT8, FLOAT8);
    public static final ZeusFunctionEntry GREATER_THAN_OR_EQUAL_TO_STRING_STRING = ZeusFunctionEntry.from(GE_STR, BOOL, "greater_than_or_equal_to", STRING, STRING);
    public static final ZeusFunctionEntry LESS_THAN_INT32_INT32 = ZeusFunctionEntry.from(LT_INT32, BOOL, "less_than", INT32, INT32);
    public static final ZeusFunctionEntry LESS_THAN_INT32_INT64 = ZeusFunctionEntry.from(LT_INT64, BOOL, "less_than", INT32, INT64);
    public static final ZeusFunctionEntry LESS_THAN_INT32_FLOAT4 = ZeusFunctionEntry.from(LT_FLOAT4, BOOL, "less_than", INT32, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_INT32_FLOAT8 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", INT32, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_INT64_INT32 = ZeusFunctionEntry.from(LT_INT64, BOOL, "less_than", INT64, INT32);
    public static final ZeusFunctionEntry LESS_THAN_INT64_INT64 = ZeusFunctionEntry.from(LT_INT64, BOOL, "less_than", INT64, INT64);
    public static final ZeusFunctionEntry LESS_THAN_INT64_FLOAT4 = ZeusFunctionEntry.from(LT_FLOAT4, BOOL, "less_than", INT64, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_INT64_FLOAT8 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", INT64, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT4_INT32 = ZeusFunctionEntry.from(LT_FLOAT4, BOOL, "less_than", FLOAT4, INT32);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT4_INT64 = ZeusFunctionEntry.from(LT_FLOAT4, BOOL, "less_than", FLOAT4, INT64);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(LT_FLOAT4, BOOL, "less_than", FLOAT4, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", FLOAT4, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT8_INT32 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", FLOAT8, INT32);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT8_INT64 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", FLOAT8, INT64);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", FLOAT8, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(LT_FLOAT8, BOOL, "less_than", FLOAT8, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_STRING_STRING = ZeusFunctionEntry.from(LT_STR, BOOL, "less_than", STRING, STRING);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT32_INT32 = ZeusFunctionEntry.from(LE_INT32, BOOL, "less_than_or_equal_to", INT32, INT32);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT32_INT64 = ZeusFunctionEntry.from(LE_INT64, BOOL, "less_than_or_equal_to", INT32, INT64);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT32_FLOAT4 = ZeusFunctionEntry.from(LE_FLOAT4, BOOL, "less_than_or_equal_to", INT32, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT32_FLOAT8 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", INT32, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT64_INT32 = ZeusFunctionEntry.from(LE_INT64, BOOL, "less_than_or_equal_to", INT64, INT32);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT64_INT64 = ZeusFunctionEntry.from(LE_INT64, BOOL, "less_than_or_equal_to", INT64, INT64);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT64_FLOAT4 = ZeusFunctionEntry.from(LE_FLOAT4, BOOL, "less_than_or_equal_to", INT64, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_INT64_FLOAT8 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", INT64, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT4_INT32 = ZeusFunctionEntry.from(LE_FLOAT4, BOOL, "less_than_or_equal_to", FLOAT4, INT32);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT4_INT64 = ZeusFunctionEntry.from(LE_FLOAT4, BOOL, "less_than_or_equal_to", FLOAT4, INT64);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(LE_FLOAT4, BOOL, "less_than_or_equal_to", FLOAT4, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", FLOAT4, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT8_INT32 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", FLOAT8, INT32);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT8_INT64 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", FLOAT8, INT64);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", FLOAT8, FLOAT4);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(LE_FLOAT8, BOOL, "less_than_or_equal_to", FLOAT8, FLOAT8);
    public static final ZeusFunctionEntry LESS_THAN_OR_EQUAL_TO_STRING_STRING = ZeusFunctionEntry.from(LE_STR, BOOL, "less_than_or_equal_to", STRING, STRING);
    public static final ZeusFunctionEntry EQUAL_INT32_INT32 = ZeusFunctionEntry.from(EQ_INT32, BOOL, "equal", INT32, INT32);
    public static final ZeusFunctionEntry EQUAL_INT32_INT64 = ZeusFunctionEntry.from(EQ_INT64, BOOL, "equal", INT32, INT64);
    public static final ZeusFunctionEntry EQUAL_INT32_FLOAT4 = ZeusFunctionEntry.from(EQ_FLOAT4, BOOL, "equal", INT32, FLOAT4);
    public static final ZeusFunctionEntry EQUAL_INT32_FLOAT8 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", INT32, FLOAT8);
    public static final ZeusFunctionEntry EQUAL_INT64_INT32 = ZeusFunctionEntry.from(EQ_INT64, BOOL, "equal", INT64, INT32);
    public static final ZeusFunctionEntry EQUAL_INT64_INT64 = ZeusFunctionEntry.from(EQ_INT64, BOOL, "equal", INT64, INT64);
    public static final ZeusFunctionEntry EQUAL_INT64_FLOAT4 = ZeusFunctionEntry.from(EQ_FLOAT4, BOOL, "equal", INT64, FLOAT4);
    public static final ZeusFunctionEntry EQUAL_INT64_FLOAT8 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", INT64, FLOAT8);
    public static final ZeusFunctionEntry EQUAL_FLOAT4_INT32 = ZeusFunctionEntry.from(EQ_FLOAT4, BOOL, "equal", FLOAT4, INT32);
    public static final ZeusFunctionEntry EQUAL_FLOAT4_INT64 = ZeusFunctionEntry.from(EQ_FLOAT4, BOOL, "equal", FLOAT4, INT64);
    public static final ZeusFunctionEntry EQUAL_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(EQ_FLOAT4, BOOL, "equal", FLOAT4, FLOAT4);
    public static final ZeusFunctionEntry EQUAL_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", FLOAT4, FLOAT8);
    public static final ZeusFunctionEntry EQUAL_FLOAT8_INT32 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", FLOAT8, INT32);
    public static final ZeusFunctionEntry EQUAL_FLOAT8_INT64 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", FLOAT8, INT64);
    public static final ZeusFunctionEntry EQUAL_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", FLOAT8, FLOAT4);
    public static final ZeusFunctionEntry EQUAL_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(EQ_FLOAT8, BOOL, "equal", FLOAT8, FLOAT8);
    public static final ZeusFunctionEntry EQUAL_STRING_STRING = ZeusFunctionEntry.from(EQ_STR, BOOL, "equal", STRING, STRING);
    public static final ZeusFunctionEntry NOT_EQUAL_INT32_INT32 = ZeusFunctionEntry.from(NE_INT32, BOOL, "not_equal", INT32, INT32);
    public static final ZeusFunctionEntry NOT_EQUAL_INT32_INT64 = ZeusFunctionEntry.from(NE_INT64, BOOL, "not_equal", INT32, INT64);
    public static final ZeusFunctionEntry NOT_EQUAL_INT32_FLOAT4 = ZeusFunctionEntry.from(NE_FLOAT4, BOOL, "not_equal", INT32, FLOAT4);
    public static final ZeusFunctionEntry NOT_EQUAL_INT32_FLOAT8 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", INT32, FLOAT8);
    public static final ZeusFunctionEntry NOT_EQUAL_INT64_INT32 = ZeusFunctionEntry.from(NE_INT64, BOOL, "not_equal", INT64, INT32);
    public static final ZeusFunctionEntry NOT_EQUAL_INT64_INT64 = ZeusFunctionEntry.from(NE_INT64, BOOL, "not_equal", INT64, INT64);
    public static final ZeusFunctionEntry NOT_EQUAL_INT64_FLOAT4 = ZeusFunctionEntry.from(NE_FLOAT4, BOOL, "not_equal", INT64, FLOAT4);
    public static final ZeusFunctionEntry NOT_EQUAL_INT64_FLOAT8 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", INT64, FLOAT8);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT4_INT32 = ZeusFunctionEntry.from(NE_FLOAT4, BOOL, "not_equal", FLOAT4, INT32);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT4_INT64 = ZeusFunctionEntry.from(NE_FLOAT4, BOOL, "not_equal", FLOAT4, INT64);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(NE_FLOAT4, BOOL, "not_equal", FLOAT4, FLOAT4);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", FLOAT4, FLOAT8);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT8_INT32 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", FLOAT8, INT32);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT8_INT64 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", FLOAT8, INT64);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", FLOAT8, FLOAT4);
    public static final ZeusFunctionEntry NOT_EQUAL_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(NE_FLOAT8, BOOL, "not_equal", FLOAT8, FLOAT8);
    public static final ZeusFunctionEntry NOT_EQUAL_STRING_STRING = ZeusFunctionEntry.from(NE_STR, BOOL, "not_equal", STRING, STRING);
}