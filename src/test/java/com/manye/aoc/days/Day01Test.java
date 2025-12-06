package com.manye.aoc.days;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day01Test {

  private final Day day = new Day01();

  @Test
  void part1() {
    var expected = "3";

    var actual = day.part1(InputReader.readInput("/test/inputs/day01.txt"));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "6";

    var actual = day.part2(InputReader.readInput("/test/inputs/day01.txt"));

    assertThat(actual).isEqualTo(expected);
  }
}
