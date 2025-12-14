package com.manye.aoc.y2024.days;

import com.manye.aoc.Day;
import com.manye.aoc.InputReader;

public class Main {

  private static final String PACKAGE = "com.manye.aoc.y2024.days";
  public static final int YEAR = 2024;

  public static void main(String[] args) {
    // SET THIS TO 0 TO AUTOMATICALLY RUN THE LATEST IMPLEMENTED DAY
    int dayToRun = 0;

    try {
      // 1. RESOLVE THE DAY
      int dayNumber = (dayToRun == 0) ? findLatestDay() : dayToRun;
      System.out.printf("--- Advent of Code %s - Day %02d ---%n", YEAR, dayNumber);

      // 2. DYNAMICALLY LOAD THE CLASS
      Day dayImpl = loadDay(dayNumber);

      // 3. READ INPUT
      String input = InputReader.readInput(YEAR, dayNumber);
      if (input == null || input.isEmpty()) {
        System.err.println("Input is empty or missing!");
        return;
      }

      // 4. EXECUTE
      measureAndRun("Part 1", () -> dayImpl.part1(input));
      measureAndRun("Part 2", () -> dayImpl.part2(input));

    } catch (ClassNotFoundException e) {
      System.err.printf("Error: Day %d is not implemented yet (Class not found).%n", dayToRun);
    } catch (Exception e) {
      System.err.println("Unexpected error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Dynamically loads the class com.manye.aoc.days.DayXX using Reflection
   */
  private static Day loadDay(int day) throws Exception {
    String className = String.format("%s.Day%02d", PACKAGE, day);

    // 1. Get the Class Object
    Class<?> clazz = Class.forName(className);

    // 2. Create a new instance (calls the empty constructor)
    return (Day) clazz.getDeclaredConstructor().newInstance();
  }

  /**
   * Helper to time the execution cleanly
   */
  private static void measureAndRun(String label, java.util.function.Supplier<String> logic) {
    long start = System.nanoTime();
    String result = logic.get();
    long end = System.nanoTime();
    System.out.printf("%s: %s (Time: %.3f ms)%n", label, result, (end - start) / 1_000_000.0);
  }

  /**
   * Looks for Day25 down to Day01. Returns the first one that exists.
   */
  private static int findLatestDay() {
    for (int i = 25; i >= 1; i--) {
      try {
        String className = String.format("%s.Day%02d", PACKAGE, i);
        Class.forName(className);
        return i; // Found it!
      } catch (ClassNotFoundException ignored) {
        // Class doesn't exist, keep checking backwards
      }
    }
    throw new RuntimeException("No Day implementations found!");
  }
}
