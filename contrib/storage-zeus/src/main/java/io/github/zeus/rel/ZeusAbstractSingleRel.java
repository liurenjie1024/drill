package io.github.zeus.rel;

import java.util.Collections;
import java.util.Objects;

public abstract class ZeusAbstractSingleRel implements ZeusRel {
  private final ZeusRel input;

  protected ZeusAbstractSingleRel(ZeusRel input) {
    Objects.requireNonNull(input, "input can't be null.");
    this.input = input;
  }

  @Override
  public Iterable<ZeusRel> getChildren() {
    return Collections.singletonList(input);
  }

  public ZeusRel getInput() {
    return input;
  }
}
