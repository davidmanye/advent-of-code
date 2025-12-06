package com.manye.aoc.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class IDValidatorTest {

  private final IDValidator validator = new IDValidator();

  // Static method providing the test data
  private static Stream<Arguments> repeatedTwiceTestSet() {
    return Stream.of(
        Arguments.of("11", false),
        Arguments.of("22", false),
        Arguments.of("1010", false),
        Arguments.of("101", true),
        Arguments.of("1188511885", false),
        Arguments.of("222222", false),
        Arguments.of("446446", false),
        Arguments.of("38593859", false),
        Arguments.of("115", true),
        Arguments.of("1188511890", true),
        Arguments.of("446449", true)
    );
  }

  private static Stream<Arguments> repeatedAtLeastTwiceTestSet() {
    return Stream.of(
        Arguments.of("11", false),
        Arguments.of("22", false),
        Arguments.of("111", false),
        Arguments.of("999", false),
        Arguments.of("565656", false),
        Arguments.of("824824824", false),
        Arguments.of("1010", false),
        Arguments.of("101", true),
        Arguments.of("1188511885", false),
        Arguments.of("222222", false),
        Arguments.of("446446", false),
        Arguments.of("38593859", false),
        Arguments.of("115", true),
        Arguments.of("1188511890", true),
        Arguments.of("446449", true)
    );
  }

  @ParameterizedTest(name = "[{index}] ID: {0} -> Expected: {1}")
  @MethodSource("repeatedTwiceTestSet")
  void validateIsTwice(String input, boolean valid) {
    assertThat(validator.isRepeatedTwice(input)).isEqualTo(valid);
  }

  @ParameterizedTest(name = "[{index}] ID: {0} -> Expected: {1}")
  @MethodSource("repeatedTwiceTestSet")
  void validateIsAtLeastTwice(String input, boolean valid) {
    assertThat(validator.isRepeatedAsLeastTwice(Long.parseLong(input))).isEqualTo(valid);
  }
}
