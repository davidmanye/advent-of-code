package com.manye.aoc.model;

public record Rotation(Direction direction, int distance) {

  public static Rotation parse(String line) {
    char dirCode = line.charAt(0);
    int dist = Integer.parseInt(line.substring(1));
    return new Rotation(Direction.fromCode(dirCode), dist);
  }
}
