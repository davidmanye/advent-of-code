package com.manye.aoc.y2025.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day08Test {

  private final Day day = new Day08(10);

  @Test
  void part1() {
    var expected = "40";

    var actual = day.part1(InputReader.readInput("/test/inputs/2025/day08.txt"));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "25272";

    var actual = day.part2(InputReader.readInput("/test/inputs/2025/day08.txt"));

    assertThat(actual).isEqualTo(expected);
  }
}
