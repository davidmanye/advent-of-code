package com.manye.aoc.y2025.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import org.junit.jupiter.api.Test;

class Day09Test {

  public static final String FILE = "day09.txt";
  private final Day day = new Day09();

  @Test
  void part1() {
    var expected = "50";

    var actual = day.part1(InputReader.readInput("/test/inputs/2025/" + FILE));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void part2() {
    var expected = "24";

    var actual = day.part2(InputReader.readInput("/test/inputs/2025/" + FILE));

    assertThat(actual).isEqualTo(expected);
  }
}
