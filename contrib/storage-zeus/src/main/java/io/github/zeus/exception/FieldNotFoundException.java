package io.github.zeus.exception;

public class FieldNotFoundException extends RuntimeException {
  public FieldNotFoundException(String name) {
    super(name + " not found!");
  }
}
