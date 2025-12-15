package com.manye.aoc.y2024.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;
import com.manye.aoc.y2025.model.BatteryBank;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Day02Test {

  public static final String TEST_INPUT = "/test/inputs/2024/day02.txt";
  private final Day day = new Day02();

  @Test
  void part1() {
    var expected = "2";

    var actual = day.part1(InputReader.readInput(TEST_INPUT));

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @Disabled
  void part2() {
    var expected = "31";

    var actual = day.part2(InputReader.readInput(TEST_INPUT));

    assertThat(actual).isEqualTo(expected);
  }

  private static Stream<Arguments> safeReports() {
    return Stream.of(
        Arguments.of(new int[]{7,6,4,2,1}, true),
        Arguments.of(new int[]{1,2,7,8,9}, false)
    );
  }

  @ParameterizedTest
  @MethodSource("safeReports")
  void isSafeReport(int[] input, boolean isValid) {
    assertThat(Day02.isSafe(input)).isEqualTo(isValid);
  }
}
