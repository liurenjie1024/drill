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
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.drill.common.types.TypeProtos.MinorType;

import java.util.Optional;

public enum TypeMapping {
  BOOL(ColumnType.BOOL, MinorType.BIT, SqlTypeName.BOOLEAN),
  INT8(ColumnType.INT8, MinorType.TINYINT, SqlTypeName.TINYINT),
  INT16(ColumnType.INT16, MinorType.SMALLINT, SqlTypeName.SMALLINT),
  INT32(ColumnType.INT32, MinorType.INT, SqlTypeName.INTEGER),
  INT64(ColumnType.INT64, MinorType.BIGINT, SqlTypeName.BIGINT),
  FLOAT4(ColumnType.FLOAT4, MinorType.FLOAT4, SqlTypeName.FLOAT),
  FLOAT8(ColumnType.FLOAT8, MinorType.FLOAT8, SqlTypeName.DOUBLE),
  TIMESTAMP(ColumnType.TIMESTAMP, MinorType.TIMESTAMP, SqlTypeName.TIMESTAMP),
  UTF8(ColumnType.STRING, MinorType.VARCHAR, SqlTypeName.VARCHAR);
  private final ColumnType zeusType;
  private final MinorType drillType;
  private final SqlTypeName sqlType;

  static final ImmutableBiMap<ColumnType, MinorType> DRILL_MAPPING;
  static final ImmutableBiMap<ColumnType, SqlTypeName> SQL_MAPPING;

  static {
    ImmutableBiMap.Builder drillBuilder = ImmutableBiMap.builder();
    ImmutableBiMap.Builder sqlBuilder = ImmutableBiMap.builder();

    for (TypeMapping typeMapping: TypeMapping.values()) {
      drillBuilder.put(typeMapping.zeusType, typeMapping.drillType);
      sqlBuilder.put(typeMapping.zeusType, typeMapping.sqlType);
    }


    DRILL_MAPPING = drillBuilder.build();
    SQL_MAPPING = sqlBuilder.build();
  }

  TypeMapping(ColumnType zeusType, MinorType drillType, SqlTypeName sqlType) {
    this.zeusType = zeusType;
    this.drillType = drillType;
    this.sqlType = sqlType;
  }

  public static Optional<MinorType> drillTypeOf(ColumnType zeusType) {
    return Optional.ofNullable(DRILL_MAPPING.get(zeusType));
  }

  public static Optional<SqlTypeName> sqlTypeOf(ColumnType zeusType) {
    return Optional.ofNullable(SQL_MAPPING.get(zeusType));
  }

  public static Optional<ColumnType> zeusTypeOf(MinorType drillType) {
    return Optional.ofNullable(DRILL_MAPPING.inverse().get(drillType));
  }

  public static Optional<ColumnType> zeusTypeOf(SqlTypeName sqlType) {
    return Optional.ofNullable(SQL_MAPPING.inverse().get(sqlType));
  }
}
