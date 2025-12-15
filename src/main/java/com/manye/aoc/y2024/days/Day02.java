package com.manye.aoc.y2024.days;

import com.manye.aoc.Day;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 implements Day {

  @Override
  public String part1(String input) {
    return ""
        + input
            .lines()
            .map(
                line -> {
                  return Arrays.stream(line.split(" "))
                      .mapToInt(Integer::parseInt)
                      .toArray();
                })
            .filter(Day02::isSafe)
            .count();
  }

  public static boolean isSafe(int[] levels) {
    boolean asc = levels[0] < levels[1];
    for (int i = 0; i < levels.length - 1; i++) {
      var current = levels[i];
      var next = levels[i + 1];
      var value = current - next;
      if (value == 0 || Math.abs(value) > 3) {
        return false;
      } else if (value > 0 && asc) {
        return false;
      } else if (value < 0 && !asc) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String part2(String input) {
    return "";
  }
}
