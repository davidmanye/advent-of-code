package com.manye.aoc.y2025.model;

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
