package com.manye.aoc.y2025.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day04Test {

  private final Day day = new Day04();

  @Test
  void part1() {
    var expected = "13";

    var actual = day.part1(InputReader.readInput("/test/inputs/2025/day04.txt"));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "43";

    var actual = day.part2(InputReader.readInput("/test/inputs/2025/day04.txt"));

    assertThat(actual).isEqualTo(expected);
  }
}
