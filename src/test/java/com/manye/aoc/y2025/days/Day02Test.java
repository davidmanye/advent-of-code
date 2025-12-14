package com.manye.aoc.y2025.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day02Test {

  private final Day day = new Day02();

  @Test
  void part1() {
    var expected = "1227775554";

    var actual = day.part1(InputReader.readInput("/test/inputs/2025/day02.txt"));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "4174379265";

    var actual = day.part2(InputReader.readInput("/test/inputs/2025/day02.txt"));

    assertThat(actual).isEqualTo(expected);
  }
}
