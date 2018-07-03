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

package io.github.zeus.expr;

import io.github.zeus.rpc.AggFuncId;
import io.github.zeus.rpc.ColumnType;
import io.github.zeus.rpc.ScalarFuncId;

import java.util.Optional;

public class ZeusFunctionEntry {
  private final ColumnType returnType;
  private final ZeusFunctionSignature signature;
  private final Optional<ScalarFuncId > scalarFuncId;
  private final Optional<AggFuncId> aggFuncId;

  public ZeusFunctionEntry(ColumnType returnType, ZeusFunctionSignature signature, ScalarFuncId scalarFuncId) {
    this(returnType, signature, Optional.of(scalarFuncId), Optional.empty());
  }

  public ZeusFunctionEntry(ZeusFunctionSignature signature, AggFuncId aggFuncId, ColumnType returnType) {
    this(returnType, signature, Optional.empty(), Optional.of(aggFuncId));
  }

  private ZeusFunctionEntry(ColumnType returnType, ZeusFunctionSignature signature,
                            Optional<ScalarFuncId> scalarFuncId, Optional<AggFuncId> aggFuncId) {
    this.returnType = returnType;
    this.signature = signature;
    this.scalarFuncId = scalarFuncId;
    this.aggFuncId = aggFuncId;
  }


  public ZeusFunctionSignature getSignature() {
    return signature;
  }

  public Optional<ScalarFuncId> getScalarFuncId() {
    return scalarFuncId;
  }

  public Optional<AggFuncId> getAggFuncId() {
    return aggFuncId;
  }

  public ColumnType getReturnType() {
    return returnType;
  }

  public static ZeusFunctionEntry from(ScalarFuncId scalarFuncId, ColumnType returnType,
                                       String name, ColumnType... argTypes) {
    return new ZeusFunctionEntry(returnType, ZeusFunctionSignature.from(name, argTypes), scalarFuncId);
  }

  public static ZeusFunctionEntry from(AggFuncId aggFuncId, ColumnType returnType,
                                       String name, ColumnType... argTypes) {
    return new ZeusFunctionEntry(ZeusFunctionSignature.from(name, argTypes), aggFuncId, returnType);
  }
}
