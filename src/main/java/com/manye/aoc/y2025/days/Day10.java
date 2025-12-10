package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.FactorySolver;

public class Day10 implements Day {

  @Override
  public String part1(String input) {
    return String.valueOf(FactorySolver.solvePart1All(input));
  }

  @Override
  public String part2(String input) {
    return String.valueOf(FactorySolver.solvePart2All(input));
  }
}
