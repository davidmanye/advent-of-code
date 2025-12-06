package com.manye.aoc;

import com.manye.aoc.days.Day01;
import com.manye.aoc.days.Day02;
import com.manye.aoc.days.Day03;
import com.manye.aoc.days.Day04;
import com.manye.aoc.days.Day05;

public class Main {

  public static void main(String[] args) {
    // 1. CHOOSE THE DAY TO RUN
    int dayToRun = 5;

    // 2. INITIALIZE THE DAY IMPLEMENTATION
    Day dayImpl;
    switch (dayToRun) {
      case 1:
        dayImpl = new Day01();
        break;
       case 2:
           dayImpl = new Day02();
           break;
      case 3:
        dayImpl = new Day03();
        break;
      case 4:
        dayImpl = new Day04();
        break;
      case 5:
        dayImpl = new Day05();
        break;
      default:
        System.err.printf("Day %d not implemented.%n", dayToRun);
        return;
    }

    // 3. READ THE INPUT
    String input = InputReader.readInput(dayToRun);
    if (input.isEmpty()) {
      return; // Stop if input reading failed
    }

    // 4. EXECUTE AND PRINT RESULTS
    System.out.printf("--- Advent of Code 2025 - Day %02d ---%n", dayToRun);

    // Part 1
    long startTime1 = System.nanoTime();
    String result1 = dayImpl.part1(input);
    long endTime1 = System.nanoTime();
    System.out.printf("Part 1: %s (Time: %.3f ms)%n", result1, (endTime1 - startTime1) / 1_000_000.0);

    // Part 2
    long startTime2 = System.nanoTime();
    String result2 = dayImpl.part2(input);
    long endTime2 = System.nanoTime();
    System.out.printf("Part 2: %s (Time: %.3f ms)%n", result2, (endTime2 - startTime2) / 1_000_000.0);
  }
}
