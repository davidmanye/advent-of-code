package com.manye.aoc.y2025.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day11Test {

  private final Day day = new Day11();

  @Test
  void part1() {
    var expected = "5";

    var actual = day.part1(InputReader.readInput("/test/inputs/day11_part1.txt"));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "2";

    var actual = day.part2(InputReader.readInput("/test/inputs/day11_part2.txt"));

    assertThat(actual).isEqualTo(expected);
  }
}
