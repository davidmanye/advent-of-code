package com.manye.aoc.y2025.model;

import java.util.Arrays;

public enum Operation {
  ADD('+') {
    @Override
    public long apply(long a, long b) { return a + b; }
  },
  MULTIPLY('*') {
    @Override
    public long apply(long a, long b) { return a * b; }
  };

  private final char symbol;

  Operation(char symbol) {
    this.symbol = symbol;
  }

  public abstract long apply(long a, long b);

  public static Operation fromChar(char c) {
    return Arrays.stream(values())
        .filter(op -> op.symbol == c)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + c));
  }
}
