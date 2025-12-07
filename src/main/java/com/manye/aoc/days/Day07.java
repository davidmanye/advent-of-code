package com.manye.aoc.days;

import com.manye.aoc.Day;
import com.manye.aoc.model.TachyonManifold;

public class Day07 implements Day {

  @Override
  public String part1(String input) {
    var manifold = new TachyonManifold(input);
    return String.valueOf(manifold.countSplits());
  }

  @Override
  public String part2(String input) {
    var manifold = new TachyonManifold(input);
    return String.valueOf(manifold.countTimelines());
  }
}
