package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.Rotation;
import com.manye.aoc.y2025.model.SafeDial;
import java.util.List;

public class Day01 implements Day {

  @Override
  public String part1(String input) {
    final var rotations = buildRotations(input);
    var dial = new SafeDial(50);
    int targetHits = 0;
    for (Rotation rotation : rotations) {
      var currentValue = dial.apply(rotation);
      if (currentValue == 0) {
        targetHits++;
      }
    }
    return String.valueOf(targetHits);
  }

  @Override
  public String part2(String input) {
    var rotations = buildRotations(input);
    var dial = new SafeDial(50);
    long zerosReached = 0;
    for (var rotation : rotations) {
      zerosReached += dial.applyAndCountZeros(rotation);
    }
    return String.valueOf(zerosReached);
  }

  private static List<Rotation> buildRotations(String input) {
    return input.lines()
        .map(Rotation::parse)
        .toList();
  }
}
