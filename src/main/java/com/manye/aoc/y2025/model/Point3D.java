package com.manye.aoc.y2025.model;

public record Point3D(int x, int y, int z) {

  /**
   * Calculates the straight-line (Euclidean) distance to another point.
   * Formula: sqrt(dx² + dy² + dz²)
   */
  public double distance(Point3D other) {
    long dx = this.x - other.x;
    long dy = this.y - other.y;
    long dz = this.z - other.z;

    return Math.sqrt(dx * dx + dy * dy + dz * dz);
  }

  /**
   * Calculates the Manhattan distance (taxicab geometry).
   * Often used in AoC for grid-based movement where diagonals aren't allowed.
   * Formula: |dx| + |dy| + |dz|
   */
  public int manhattanDistance(Point3D other) {
    return Math.abs(this.x - other.x)
        + Math.abs(this.y - other.y)
        + Math.abs(this.z - other.z);
  }

}
