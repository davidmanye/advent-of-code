package com.manye.aoc.y2024.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day01Test {

  public static final String TEST_INPUT = "/test/inputs/2024/day01.txt";
  private final Day day = new Day01();

  @Test
  void part1() {
    var expected = "11";

    var actual = day.part1(InputReader.readInput(TEST_INPUT));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "31";

    var actual = day.part2(InputReader.readInput(TEST_INPUT));

    assertThat(actual).isEqualTo(expected);
  }
}

