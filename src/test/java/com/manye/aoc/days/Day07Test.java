package com.manye.aoc.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day07Test {

  private final Day day = new Day07();

  @Test
  void part1() {
    var expected = "21";

    var actual = day.part1(InputReader.readInput("/test/inputs/day07.txt"));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "40";

    var actual = day.part2(InputReader.readInput("/test/inputs/day07.txt"));

    assertThat(actual).isEqualTo(expected);
  }
}
