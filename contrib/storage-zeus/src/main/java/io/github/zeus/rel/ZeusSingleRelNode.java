package io.github.zeus.rel;

import java.util.Collections;

public abstract class ZeusSingleRelNode implements ZeusRelNode {
  private final ZeusRelNode input;

  protected ZeusSingleRelNode(ZeusRelNode input) {
    this.input = input;
  }

  @Override
  public Iterable<ZeusRelNode> getChildren() {
    return Collections.singletonList(input);
  }

  public ZeusRelNode getInput() {
    return input;
  }
}
