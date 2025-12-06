package com.manye.aoc.model;

public class SafeDial {

  private static final int DIAL_SIZE = 100;

  private int currentPosition;

  public SafeDial(int startPosition) {
    this.currentPosition = startPosition;
  }

  public int apply(Rotation rotation) {
    currentPosition = switch (rotation.direction()) {
      case LEFT -> currentPosition - rotation.distance();
      case RIGHT -> currentPosition + rotation.distance();
    };
    currentPosition = Math.floorMod(currentPosition, DIAL_SIZE);
    return currentPosition;
  }

  public long applyAndCountZeros(Rotation rotation) {
    // 1. Calculate distance to the *first* zero occurrence
    int distToFirstZero = getDistanceToNextZero(rotation.direction());

    // 2. If we don't even reach the first zero, return 0
    if (rotation.distance() < distToFirstZero) {
      apply(rotation);
      return 0;
    }

    // 3. Math: Count first zero + how many full circles (100s) fit in remaining steps
    long count = 1 + (rotation.distance() - distToFirstZero) / DIAL_SIZE;

    // 4. Update the state
    apply(rotation);

    return count;
  }

  private int getDistanceToNextZero(Direction direction) {
    return switch (direction) {
      case RIGHT -> {
        // Moving up: Distance is (100 - current).
        // If at 0, dist is 100 (must do full circle to hit 0 again).
        int diff = DIAL_SIZE - currentPosition;
        yield (diff == 0) ? DIAL_SIZE : diff;
      }
      case LEFT -> {
        // Moving down: Distance is current position.
        // If at 0, dist is 100.
        yield (currentPosition == 0) ? DIAL_SIZE : currentPosition;
      }
    };
  }

  public int getCurrentPosition() {
    return currentPosition;
  }
}
