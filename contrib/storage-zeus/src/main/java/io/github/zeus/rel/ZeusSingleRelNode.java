package io.github.zeus.rel;

import java.util.Collections;
import java.util.Objects;

public abstract class ZeusSingleRelNode implements ZeusRelNode {
  private final ZeusRelNode input;

  protected ZeusSingleRelNode(ZeusRelNode input) {
    Objects.requireNonNull(input, "input can't be null.");
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
