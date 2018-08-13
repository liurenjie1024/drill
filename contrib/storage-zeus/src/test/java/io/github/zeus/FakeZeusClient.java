package io.github.zeus;

import io.github.zeus.client.impl.AbstractZeusClient;
import io.github.zeus.rpc.QueryPlan;
import io.github.zeus.rpc.QueryResult;
import io.github.zeus.rpc.ZeusCatalog;

public class FakeZeusClient extends AbstractZeusClient {
  private final ZeusCatalog catalog;

  public FakeZeusClient(ZeusCatalog catalog) {
    this.catalog = catalog;
  }

  @Override
  public ZeusCatalog getCatalog() {
    return catalog;
  }

  @Override
  public QueryResult query(QueryPlan queryPlan) {
    throw new UnsupportedOperationException("query is not supported in fake zeus client.");
  }

  @Override
  public void close() throws Exception {
  }
}
