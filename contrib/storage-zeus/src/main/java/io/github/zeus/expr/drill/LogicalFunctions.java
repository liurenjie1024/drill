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
