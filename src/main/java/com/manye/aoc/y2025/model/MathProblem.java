package com.manye.aoc.y2025.model;

import java.util.List;

public record MathProblem(List<Long> numbers, Operation operation) {

  public long solve() {
    if (numbers.isEmpty()) return 0;

    // Reduce the list using the operator's logic
    // We use the first number as the identity/start
    return numbers.stream()
        .reduce(this.operation::apply)
        .orElse(0L);
  }
}
