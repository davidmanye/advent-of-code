package com.manye.aoc.y2025.model;

import java.util.List;

public record FactoryMachine(
    long targetState,
    int numLights,
    List<Long> buttons,
    List<Integer> joltageTargets
) {

  public int findMinPresses() {
    return FactorySolver.solvePart1(this);
  }

  public long findMinJoltagePresses() {
    return FactorySolver.solvePart2(this);
  }
}
