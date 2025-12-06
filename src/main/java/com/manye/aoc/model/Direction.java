package com.manye.aoc.model;

public enum Direction {
  LEFT,
  RIGHT;

  public static Direction fromCode(char code) {
    return switch (code) {
      case 'L' -> LEFT;
      case 'R' -> RIGHT;
      default -> throw new IllegalArgumentException("Unknown direction: " + code);
    };
  }
}
