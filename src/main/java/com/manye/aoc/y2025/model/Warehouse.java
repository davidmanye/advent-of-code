package com.manye.aoc.y2025.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

  public static final char PAPER_ROLL = '@';
  private final char[][] grid;
  private final int height;
  private final int width;

  // --- Transformation Logic (Parsing) ---
  public Warehouse(String input) {
    // Java 21 stream logic to convert String -> char[][]
    this.grid = input.lines()
        .map(String::toCharArray)
        .toArray(char[][]::new);

    this.height = grid.length;
    // Handle empty input case safely
    this.width = height > 0 ? grid[0].length : 0;
  }

  private Warehouse(char[][] grid) {
    this.grid = grid;
    this.height = grid.length;
    // Handle empty input case safely
    this.width = height > 0 ? grid[0].length : 0;
  }

  // --- Core Logic ---

  public List<Point> getAccessibleRolls() {
    List<Point> accessibleRolls = new ArrayList<>();
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Point p = new Point(x, y);

        // The problem asks to check rolls ('@'), not empty space.
        if (isPaperRoll(p) && isAccessible(p)) {
          accessibleRolls.add(p);
        }
      }
    }
    return accessibleRolls;
  }

  public long countAccessibleRolls() {
    return getAccessibleRolls().size();
  }

  private boolean isAccessible(Point p) {
    long neighborCount = p.neighbors().stream()
        .filter(this::isPaperRoll) // Count how many neighbors are also paper
        .count();

    // Condition: fewer than 4 adjacent paper rolls
    return neighborCount < 4;
  }

  // --- Helper / Bounds Checking ---

  private boolean isPaperRoll(Point p) {
    if (!isInBounds(p)) {
      return false; // Out of bounds is not a paper roll
    }
    return grid[p.y()][p.x()] == PAPER_ROLL;
  }

  private boolean isInBounds(Point p) {
    return p.y() >= 0 && p.y() < height &&
        p.x() >= 0 && p.x() < width;
  }

  public Warehouse removePoints(List<Point> accessibleRolls) {
    char[][] newGrid = copy(grid);
    for (Point p : accessibleRolls) {
      newGrid[p.y()][p.x()] = 'x';
    }
    return new Warehouse(newGrid);
  }

  public static char[][] copy(char[][] source) {
    char[][] copy = new char[source.length][];

    for (int i = 0; i < source.length; i++) {
      // .clone() on a 1D primitive array is a true deep copy
      copy[i] = source[i].clone();
    }
    return copy;
  }
}
