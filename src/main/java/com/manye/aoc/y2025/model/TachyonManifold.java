package com.manye.aoc.y2025.model;

import com.manye.aoc.GridUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TachyonManifold {

  public static final char SPLITTER = '^';
  public static final char WAY = '|';
  private final char[][] grid;
  private final int height;
  private final int width;
  private Point start = null;

  // Cache for Part 2: memo[y][x] stores the timeline count from that point
  private long[][] memo;

  public TachyonManifold(String input) {
    String[] lines = input.split("\n");
    this.height = lines.length;
    this.width = lines[0].length();
    this.grid = new char[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // Handle varying line lengths just in case
        if (x < lines[y].length()) {
          char c = lines[y].charAt(x);
          grid[y][x] = c;
          if (c == 'S') {
            this.start = new Point(x, y);
          }
        } else {
          grid[y][x] = '.'; // Treat missing chars as empty space
        }
      }
    }
  }

  // --- Part 1: BFS ---
  public long countSplits() {
    if (start == null) return 0;

    // Queue for BFS: Stores the current location of active beam heads
    Queue<Point> queue = new LinkedList<>();

    // Visited set: Prevents processing the same coordinate twice.
    // This effectively handles "beam merging".
    Set<Point> visited = new HashSet<>();

    // Initialize
    queue.add(start);
    visited.add(start);

    long splitCount = 0;

    while (!queue.isEmpty()) {
      Point current = queue.poll();

      // Calculate the coordinate directly below
      Point next = new Point(current.x(), current.y() + 1);

      // If the beam goes off the bottom of the grid, it's gone
      if (!isInBounds(next)) {
        continue;
      }

      char nextCell = grid[next.y()][next.x()];

      if (nextCell == SPLITTER) {
        // --- SPLITTER HIT ---
        splitCount++;

        // Spawn LEFT beam (at the same row as the splitter)
        Point left = new Point(next.x() - 1, next.y());
        if (isInBounds(left) && visited.add(left)) {
          queue.add(left);
          grid[left.y()][left.x()] = WAY;
        }

        // Spawn RIGHT beam (at the same row as the splitter)
        Point right = new Point(next.x() + 1, next.y());
        if (isInBounds(right) && visited.add(right)) {
          queue.add(right);
          grid[right.y()][right.x()] = WAY;
        }

      } else {
        // --- EMPTY SPACE / CONTINUE ---
        // Beam continues downward
        if (visited.add(next)) {
          queue.add(next);
          grid[next.y()][next.x()] = WAY;
        }
      }
    }

    return splitCount;
  }

  // --- Part 2: DFS with Memoization ---
  public long countTimelines() {
    if (start == null) return 0;

    // Initialize memoization table with -1 (meaning "not calculated yet")
    this.memo = new long[height][width];
    for (long[] row : memo) {
      Arrays.fill(row, -1);
    }

    return solve(start.x(), start.y());
  }

  private long solve(int x, int y) {
    // 1. Check Bounds (Base Case: Exit)
    // If we fall off the bottom OR the sides, that is 1 finished timeline.
    if (!isInBounds(new Point(x, y))) {
      return 1;
    }

    // 2. Check Memo
    if (memo[y][x] != -1) {
      return memo[y][x];
    }

    long result;
    char current = grid[y][x];

    // 3. Recursive Steps
    // Note: The beam is AT (x,y). We calculate where it goes NEXT.

    if (current == SPLITTER) {
      // Splitter: Creates two timelines.
      // Physics: They appear Left/Right and immediately fall.
      // Next positions: (x-1, y+1) and (x+1, y+1)
      long leftPath = solve(x - 1, y + 1);
      long rightPath = solve(x + 1, y + 1);
      result = leftPath + rightPath;
    } else {
      // Empty space (or Start): Continues downward.
      // Next position: (x, y+1)
      result = solve(x, y + 1);
    }

    // 4. Store and Return
    memo[y][x] = result;
    return result;
  }

  private boolean isInBounds(Point p) {
    return p.x() >= 0 && p.x() < width &&
        p.y() >= 0 && p.y() < height;
  }

  public void printGrid() {
    GridUtils.printGridPretty(grid);
  }

  public void printMemo() {
    GridUtils.printGridPretty(memo);
  }
}
