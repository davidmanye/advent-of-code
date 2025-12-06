package com.manye.aoc.model;

public class IDValidator {

  public boolean isRepeatedTwice(String id) {
    return isRepeatedTwice(Long.parseLong(id));
  }

  public boolean isRepeatedTwice(long number) {
    String id = String.valueOf(number);

    int length = id.length();

    // 1. If length is odd, it cannot be a sequence repeated twice.
    // Example: "101" (length 3) -> Valid
    if (length % 2 != 0) {
      return true;
    }

    // 2. If length is even, split into two equal halves.
    int mid = length / 2;
    String firstHalf = id.substring(0, mid);
    String secondHalf = id.substring(mid);

    // 3. The ID is Invalid (return false) if the halves are identical.
    // The ID is Valid (return true) if the halves are different.
    return !firstHalf.equals(secondHalf);
  }

  public boolean isRepeatedAsLeastTwice(long number) {
    String id = String.valueOf(number);
    int length = id.length();

    // We look for a pattern of length 'i'.
    // The pattern length must be at most half the string (to repeat at least twice).
    for (int i = 1; i <= length / 2; i++) {

      // Optimization: A pattern of length 'i' can only fit perfectly
      // if 'i' is a divisor of the total length.
      if (length % i == 0) {

        String pattern = id.substring(0, i);
        int timesToRepeat = length / i;

        // Java 11+ convenient method to rebuild the string
        if (pattern.repeat(timesToRepeat).equals(id)) {
          return false; // It IS a repetition, therefore INVALID
        }
      }
    }

    return true; // No repetition pattern found, therefore VALID
  }

}
