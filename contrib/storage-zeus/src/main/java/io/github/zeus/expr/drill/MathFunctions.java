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

import static io.github.zeus.rpc.ScalarFuncId.ADD_INT32;
import static io.github.zeus.rpc.ScalarFuncId.MINUS_INT32;
import static io.github.zeus.rpc.ScalarFuncId.DIV_INT64;
import static io.github.zeus.rpc.ScalarFuncId.MINUS_INT64;
import static io.github.zeus.rpc.ScalarFuncId.MUL_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.ADD_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.ADD_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.MINUS_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.MUL_FLOAT8;
import static io.github.zeus.rpc.ScalarFuncId.MINUS_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.MUL_INT32;
import static io.github.zeus.rpc.ScalarFuncId.MUL_INT64;
import static io.github.zeus.rpc.ScalarFuncId.DIV_INT32;
import static io.github.zeus.rpc.ScalarFuncId.ADD_INT64;
import static io.github.zeus.rpc.ScalarFuncId.DIV_FLOAT4;
import static io.github.zeus.rpc.ScalarFuncId.DIV_FLOAT8;
  
import static io.github.zeus.rpc.ColumnType.INT32;
import static io.github.zeus.rpc.ColumnType.FLOAT8;
import static io.github.zeus.rpc.ColumnType.INT64;
import static io.github.zeus.rpc.ColumnType.FLOAT4;

/**
 * This class is generated.
 */
public class MathFunctions {
  
  public static final ZeusFunctionEntry ADD_INT32_INT32 = ZeusFunctionEntry.from(ADD_INT32, INT32, "add", INT32, INT32);
  public static final ZeusFunctionEntry ADD_INT32_INT64 = ZeusFunctionEntry.from(ADD_INT64, INT64, "add", INT32, INT64);
  public static final ZeusFunctionEntry ADD_INT32_FLOAT4 = ZeusFunctionEntry.from(ADD_FLOAT4, FLOAT4, "add", INT32, FLOAT4);
  public static final ZeusFunctionEntry ADD_INT32_FLOAT8 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", INT32, FLOAT8);
  public static final ZeusFunctionEntry ADD_INT64_INT32 = ZeusFunctionEntry.from(ADD_INT64, INT64, "add", INT64, INT32);
  public static final ZeusFunctionEntry ADD_INT64_INT64 = ZeusFunctionEntry.from(ADD_INT64, INT64, "add", INT64, INT64);
  public static final ZeusFunctionEntry ADD_INT64_FLOAT4 = ZeusFunctionEntry.from(ADD_FLOAT4, FLOAT4, "add", INT64, FLOAT4);
  public static final ZeusFunctionEntry ADD_INT64_FLOAT8 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", INT64, FLOAT8);
  public static final ZeusFunctionEntry ADD_FLOAT4_INT32 = ZeusFunctionEntry.from(ADD_FLOAT4, FLOAT4, "add", FLOAT4, INT32);
  public static final ZeusFunctionEntry ADD_FLOAT4_INT64 = ZeusFunctionEntry.from(ADD_FLOAT4, FLOAT4, "add", FLOAT4, INT64);
  public static final ZeusFunctionEntry ADD_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(ADD_FLOAT4, FLOAT4, "add", FLOAT4, FLOAT4);
  public static final ZeusFunctionEntry ADD_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", FLOAT4, FLOAT8);
  public static final ZeusFunctionEntry ADD_FLOAT8_INT32 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", FLOAT8, INT32);
  public static final ZeusFunctionEntry ADD_FLOAT8_INT64 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", FLOAT8, INT64);
  public static final ZeusFunctionEntry ADD_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", FLOAT8, FLOAT4);
  public static final ZeusFunctionEntry ADD_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(ADD_FLOAT8, FLOAT8, "add", FLOAT8, FLOAT8);
  public static final ZeusFunctionEntry SUBTRACT_INT32_INT32 = ZeusFunctionEntry.from(MINUS_INT32, INT32, "subtract", INT32, INT32);
  public static final ZeusFunctionEntry SUBTRACT_INT32_INT64 = ZeusFunctionEntry.from(MINUS_INT64, INT64, "subtract", INT32, INT64);
  public static final ZeusFunctionEntry SUBTRACT_INT32_FLOAT4 = ZeusFunctionEntry.from(MINUS_FLOAT4, FLOAT4, "subtract", INT32, FLOAT4);
  public static final ZeusFunctionEntry SUBTRACT_INT32_FLOAT8 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", INT32, FLOAT8);
  public static final ZeusFunctionEntry SUBTRACT_INT64_INT32 = ZeusFunctionEntry.from(MINUS_INT64, INT64, "subtract", INT64, INT32);
  public static final ZeusFunctionEntry SUBTRACT_INT64_INT64 = ZeusFunctionEntry.from(MINUS_INT64, INT64, "subtract", INT64, INT64);
  public static final ZeusFunctionEntry SUBTRACT_INT64_FLOAT4 = ZeusFunctionEntry.from(MINUS_FLOAT4, FLOAT4, "subtract", INT64, FLOAT4);
  public static final ZeusFunctionEntry SUBTRACT_INT64_FLOAT8 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", INT64, FLOAT8);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT4_INT32 = ZeusFunctionEntry.from(MINUS_FLOAT4, FLOAT4, "subtract", FLOAT4, INT32);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT4_INT64 = ZeusFunctionEntry.from(MINUS_FLOAT4, FLOAT4, "subtract", FLOAT4, INT64);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(MINUS_FLOAT4, FLOAT4, "subtract", FLOAT4, FLOAT4);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", FLOAT4, FLOAT8);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT8_INT32 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", FLOAT8, INT32);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT8_INT64 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", FLOAT8, INT64);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", FLOAT8, FLOAT4);
  public static final ZeusFunctionEntry SUBTRACT_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(MINUS_FLOAT8, FLOAT8, "subtract", FLOAT8, FLOAT8);
  public static final ZeusFunctionEntry MULTIPLY_INT32_INT32 = ZeusFunctionEntry.from(MUL_INT32, INT32, "multiply", INT32, INT32);
  public static final ZeusFunctionEntry MULTIPLY_INT32_INT64 = ZeusFunctionEntry.from(MUL_INT64, INT64, "multiply", INT32, INT64);
  public static final ZeusFunctionEntry MULTIPLY_INT32_FLOAT4 = ZeusFunctionEntry.from(MUL_FLOAT4, FLOAT4, "multiply", INT32, FLOAT4);
  public static final ZeusFunctionEntry MULTIPLY_INT32_FLOAT8 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", INT32, FLOAT8);
  public static final ZeusFunctionEntry MULTIPLY_INT64_INT32 = ZeusFunctionEntry.from(MUL_INT64, INT64, "multiply", INT64, INT32);
  public static final ZeusFunctionEntry MULTIPLY_INT64_INT64 = ZeusFunctionEntry.from(MUL_INT64, INT64, "multiply", INT64, INT64);
  public static final ZeusFunctionEntry MULTIPLY_INT64_FLOAT4 = ZeusFunctionEntry.from(MUL_FLOAT4, FLOAT4, "multiply", INT64, FLOAT4);
  public static final ZeusFunctionEntry MULTIPLY_INT64_FLOAT8 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", INT64, FLOAT8);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT4_INT32 = ZeusFunctionEntry.from(MUL_FLOAT4, FLOAT4, "multiply", FLOAT4, INT32);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT4_INT64 = ZeusFunctionEntry.from(MUL_FLOAT4, FLOAT4, "multiply", FLOAT4, INT64);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(MUL_FLOAT4, FLOAT4, "multiply", FLOAT4, FLOAT4);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", FLOAT4, FLOAT8);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT8_INT32 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", FLOAT8, INT32);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT8_INT64 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", FLOAT8, INT64);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", FLOAT8, FLOAT4);
  public static final ZeusFunctionEntry MULTIPLY_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(MUL_FLOAT8, FLOAT8, "multiply", FLOAT8, FLOAT8);
  public static final ZeusFunctionEntry DIVIDE_INT32_INT32 = ZeusFunctionEntry.from(DIV_INT32, INT32, "divide", INT32, INT32);
  public static final ZeusFunctionEntry DIVIDE_INT32_INT64 = ZeusFunctionEntry.from(DIV_INT64, INT64, "divide", INT32, INT64);
  public static final ZeusFunctionEntry DIVIDE_INT32_FLOAT4 = ZeusFunctionEntry.from(DIV_FLOAT4, FLOAT4, "divide", INT32, FLOAT4);
  public static final ZeusFunctionEntry DIVIDE_INT32_FLOAT8 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", INT32, FLOAT8);
  public static final ZeusFunctionEntry DIVIDE_INT64_INT32 = ZeusFunctionEntry.from(DIV_INT64, INT64, "divide", INT64, INT32);
  public static final ZeusFunctionEntry DIVIDE_INT64_INT64 = ZeusFunctionEntry.from(DIV_INT64, INT64, "divide", INT64, INT64);
  public static final ZeusFunctionEntry DIVIDE_INT64_FLOAT4 = ZeusFunctionEntry.from(DIV_FLOAT4, FLOAT4, "divide", INT64, FLOAT4);
  public static final ZeusFunctionEntry DIVIDE_INT64_FLOAT8 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", INT64, FLOAT8);
  public static final ZeusFunctionEntry DIVIDE_FLOAT4_INT32 = ZeusFunctionEntry.from(DIV_FLOAT4, FLOAT4, "divide", FLOAT4, INT32);
  public static final ZeusFunctionEntry DIVIDE_FLOAT4_INT64 = ZeusFunctionEntry.from(DIV_FLOAT4, FLOAT4, "divide", FLOAT4, INT64);
  public static final ZeusFunctionEntry DIVIDE_FLOAT4_FLOAT4 = ZeusFunctionEntry.from(DIV_FLOAT4, FLOAT4, "divide", FLOAT4, FLOAT4);
  public static final ZeusFunctionEntry DIVIDE_FLOAT4_FLOAT8 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", FLOAT4, FLOAT8);
  public static final ZeusFunctionEntry DIVIDE_FLOAT8_INT32 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", FLOAT8, INT32);
  public static final ZeusFunctionEntry DIVIDE_FLOAT8_INT64 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", FLOAT8, INT64);
  public static final ZeusFunctionEntry DIVIDE_FLOAT8_FLOAT4 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", FLOAT8, FLOAT4);
  public static final ZeusFunctionEntry DIVIDE_FLOAT8_FLOAT8 = ZeusFunctionEntry.from(DIV_FLOAT8, FLOAT8, "divide", FLOAT8, FLOAT8);
}