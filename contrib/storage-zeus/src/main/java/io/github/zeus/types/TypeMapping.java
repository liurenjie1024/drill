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

package io.github.zeus.types;

import com.google.common.collect.ImmutableBiMap;
import io.github.zeus.rpc.ColumnType;
import org.apache.drill.common.types.TypeProtos.MinorType;

import java.util.Optional;

public enum TypeMapping {
  BOOL(ColumnType.BOOL, MinorType.BIT),
  INT8(ColumnType.INT8, MinorType.TINYINT),
  INT16(ColumnType.INT16, MinorType.SMALLINT),
  INT32(ColumnType.INT32, MinorType.INT),
  INT64(ColumnType.INT64, MinorType.BIGINT),
  FLOAT4(ColumnType.FLOAT4, MinorType.FLOAT4),
  FLOAT8(ColumnType.FLOAT8, MinorType.FLOAT8),
  TIMESTAMP(ColumnType.TIMESTAMP, MinorType.TIMESTAMP),
  UTF8(ColumnType.STRING, MinorType.VARCHAR);
  private final ColumnType zeusType;
  private final MinorType drillType;

  static final ImmutableBiMap<ColumnType, MinorType> MAPPING;

  static {
    ImmutableBiMap.Builder builder = ImmutableBiMap.builder();

    for (TypeMapping typeMapping: TypeMapping.values()) {
      builder.put(typeMapping.zeusType, typeMapping.drillType);
    }

    MAPPING = builder.build();
  }

  TypeMapping(ColumnType zeusType, MinorType drillType) {
    this.zeusType = zeusType;
    this.drillType = drillType;
  }

  public static Optional<MinorType> drillTypeOf(ColumnType zeusType) {
    return Optional.ofNullable(MAPPING.get(zeusType));
  }

  public static Optional<ColumnType> zeusTypeOf(MinorType drillType) {
    return Optional.ofNullable(MAPPING.inverse().get(drillType));
  }
}
