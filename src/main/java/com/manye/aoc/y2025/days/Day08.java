package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.CircuitManager;

public class Day08 implements Day {

  private final int connections;

  public Day08() {
    this.connections = 1000;
  }

  public Day08(int connections) {
    this.connections = connections;
  }

  @Override
  public String part1(String input) {
    var manager = new CircuitManager(input);
    // The problem asks to connect the 1000 closest pairs
    return String.valueOf(manager.solvePart1(connections));
  }

  @Override
  public String part2(String input) {
    var manager = new CircuitManager(input);
    return String.valueOf(manager.solvePart2());
  }
}
