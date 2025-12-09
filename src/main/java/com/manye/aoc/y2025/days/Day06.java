package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.MathWorksheet;
import com.manye.aoc.y2025.model.Range;

public class Day06 implements Day {

  @Override
  public String part1(String input) {
    final var mathWorksheet = MathWorksheet.fromHorizontalRules(input);
    return mathWorksheet.sumOfAllSolutions() + "";
  }

  @Override
  public String part2(String input) {
    final var mathWorksheet = MathWorksheet.fromVerticalRules(input);
    return mathWorksheet.sumOfAllSolutions() + "";
  }
}
