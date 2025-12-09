package com.manye.aoc.y2025.model;

public record Rotation(Direction direction, int distance) {

  public static Rotation parse(String line) {
    char dirCode = line.charAt(0);
    int dist = Integer.parseInt(line.substring(1));
    return new Rotation(Direction.fromCode(dirCode), dist);
  }
}
