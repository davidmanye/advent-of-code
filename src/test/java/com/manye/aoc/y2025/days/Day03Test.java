package com.manye.aoc.y2025.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day03Test {

  public static final String INPUT = "/test/inputs/2025/day03.txt";
  private final Day day = new Day03();

  @Test
  void part1() {
    var expected = "357";

    var actual = day.part1(InputReader.readInput(INPUT));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "3121910778619";

    var actual = day.part2(InputReader.readInput(INPUT));

    assertThat(actual).isEqualTo(expected);
  }
}
