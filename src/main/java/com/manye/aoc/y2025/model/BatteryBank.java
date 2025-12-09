package com.manye.aoc.y2025.model;

public class BatteryBank {

  private final String input;

  public BatteryBank(String input) {
    this.input = input;
  }

  public long getMaxJoltageOfDigitsLength(int digitsLength) {
    int currentIndex = 0;
    long value = 0;

    for (int i = 0; i < digitsLength; i++) {
      // Calculate how many digits we still need to find AFTER this one
      int remainingNeeded = digitsLength - 1 - i;

      // The search window starts at 'currentIndex'.
      // It ends at a point that leaves exactly 'remainingNeeded' characters
      // at the end of the string.
      int searchEndIndexInclusive = input.length() - 1 - remainingNeeded;

      Max max = findMaxIn(currentIndex, searchEndIndexInclusive);

      value = (value * 10L) + max.value;

      // Move our current index past the digit we just found
      currentIndex = max.index + 1;
    }
    return value;
  }

  // Optimized to search by index rather than creating new String objects
  private Max findMaxIn(int start, int endInclusive) {
    int maxVal = -1;
    int maxIdx = -1;

    for (int i = start; i <= endInclusive; i++) {
      int digit = input.charAt(i) - '0';

      // Optimization: If we find a 9, we can stop immediately
      // because we can't find anything higher.
      if (digit == 9) {
        return new Max(i, 9);
      }

      if (digit > maxVal) {
        maxVal = digit;
        maxIdx = i;
      }
    }
    return new Max(maxIdx, maxVal);
  }

  public record Max(int index, int value) {}
}
