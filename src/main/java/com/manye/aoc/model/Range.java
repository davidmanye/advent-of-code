package com.manye.aoc.model;

public record Range(long min, long max) {

  public long size() {
    return max - min + 1;
  }

  public boolean isBetween(long number) {
    return number >= min && number <= max;
  }

  public static Range parse(String input) {
    String[] parts = input.split("-");
    return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
  }
}
