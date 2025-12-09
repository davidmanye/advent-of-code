package com.manye.aoc.y2025.model;

import java.util.List;
import java.util.stream.Stream;

public record Point(int x, int y) {

  // Generates the 8 adjacent coordinates (diagonals included)
  public List<Point> neighbors() {
    return Stream.of(
        new Point(x - 1, y - 1), new Point(x, y - 1), new Point(x + 1, y - 1),
        new Point(x - 1, y), new Point(x + 1, y),
        new Point(x - 1, y + 1), new Point(x, y + 1), new Point(x + 1, y + 1)
    ).toList();
  }
}
