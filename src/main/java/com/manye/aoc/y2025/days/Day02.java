package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.IDValidator;
import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day02 implements Day {

  private final IDValidator validator = new IDValidator();

  @Override
  public String part1(String input) {
    final var ranges = buildRanges(input);
    var result = ranges.flatMapToLong(range -> range.filter(id -> !validator.isRepeatedTwice(id))).sum();
    return String.valueOf(result);
  }

  @Override
  public String part2(String input) {
    final var ranges = buildRanges(input);
    var result = ranges.flatMapToLong(range -> range.filter(id -> !validator.isRepeatedAsLeastTwice(id))).sum();
    return String.valueOf(result);
  }

  private static Stream<LongStream> buildRanges(String input) {
    return Arrays.stream(input.split(",")).map(numbers -> numbers.split("-")).map(
        numbers -> LongStream.rangeClosed(Long.parseLong(numbers[0].trim()),
            Long.parseLong(numbers[1].trim())));
  }

}
