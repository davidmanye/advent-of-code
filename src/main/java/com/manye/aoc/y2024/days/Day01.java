package com.manye.aoc.y2024.days;

import com.manye.aoc.Day;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 implements Day {

  @Override
  public String part1(String input) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    input.lines().forEach(line -> {
      var numbers = line.split("   ");
      list1.add(Integer.parseInt(numbers[0]));
      list2.add(Integer.parseInt(numbers[1]));
    });
    list1.sort(Comparator.comparingInt(Integer::intValue));
    list2.sort(Comparator.comparingInt(Integer::intValue));
    long result = 0;
    for (int i = 0; i < list1.size(); i++) {
      result += Math.abs(list1.get(i) - list2.get(i));
    }
    return "" + result;
  }

  @Override
  public String part2(String input) {
    List<Integer> list1 = new ArrayList<>();
    Map<Integer, Integer> counters = new HashMap<>();
    input.lines().forEach(line -> {
      var numbers = line.split("   ");
      list1.add(Integer.parseInt(numbers[0]));
      counters.compute(Integer.parseInt(numbers[1]), (k, v) -> v == null ? 1 : v + 1);
    });
    return "" + list1.stream().mapToLong(value -> {
      var times = counters.getOrDefault(value, 0);
      return (long) value * times;
    }).sum();
  }
}
