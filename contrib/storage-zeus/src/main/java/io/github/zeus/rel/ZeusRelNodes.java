package io.github.zeus.rel;

import io.github.zeus.ZeusQueryPlan;
import io.github.zeus.rpc.QueryPlan;

import java.util.UUID;

public class ZeusRelNodes {
  public static ZeusQueryPlan toQueryPlan(ZeusRelNode root) {
    QueryPlan queryPlan = QueryPlan.newBuilder()
      .setPlanId(UUID.randomUUID().toString())
      .setRoot(root.toPlanNode())
      .build();

    return ZeusQueryPlan.from(queryPlan);
  }
}
