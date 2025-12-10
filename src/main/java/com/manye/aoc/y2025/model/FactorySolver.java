package com.manye.aoc.y2025.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FactorySolver {

  public static long solvePart1All(String input) {
    return input.lines()
        .map(FactorySolver::parseLine)
        .mapToLong(FactoryMachine::findMinPresses)
        .sum();
  }

  public static long solvePart2All(String input) {
    return input.lines()
        .map(FactorySolver::parseLine)
        .mapToLong(FactoryMachine::findMinJoltagePresses)
        .sum();
  }

  // --- Part 1: Bitmask BFS ---
  public static int solvePart1(FactoryMachine machine) {
    long target = machine.targetState();
    if (target == 0) return 0;

    Queue<StateP1> queue = new ArrayDeque<>();
    Set<Long> visited = new HashSet<>();

    queue.add(new StateP1(0L, 0));
    visited.add(0L);

    while (!queue.isEmpty()) {
      StateP1 current = queue.poll();

      for (long buttonMask : machine.buttons()) {
        long nextStateVal = current.lights ^ buttonMask;

        if (nextStateVal == target) {
          return current.presses + 1;
        }

        if (visited.add(nextStateVal)) {
          queue.add(new StateP1(nextStateVal, current.presses + 1));
        }
      }
    }
    throw new IllegalStateException("Solution not found for machine (Part 1)!");
  }

  public static long solvePart2(FactoryMachine machine) {
    return GaussianSolver.solve(machine);
  }

// --- Helpers & Inner Classes ---

  private static int estimateRemaining(int[] counts, int[] maxReduction) {
    int maxNeeded = 0;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] == 0) continue;
      // If a column needs to be reduced but NO button affects it, it's impossible.
      // (We treat this as high cost, effectively ignoring the path)
      if (maxReduction[i] == 0) return 999999;

      // We need at least 'counts[i] / 1' presses (since max reduction is usually 1)
      int neededForThisIndex = (counts[i] + maxReduction[i] - 1) / maxReduction[i]; // Ceiling division
      maxNeeded = Math.max(maxNeeded, neededForThisIndex);
    }
    return maxNeeded;
  }

  private static int[] subtractArray(int[] a, int[] b) {
    int[] res = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      int val = a[i] - b[i];
      if (val < 0) return null; // Prune invalid states
      res[i] = val;
    }
    return res;
  }

  private static boolean isAllZero(int[] arr) {
    for (int i : arr) if (i != 0) return false;
    return true;
  }

  // Wrapper for int[] to make it usable in HashMap/HashSet
  private record StateKey(int[] data) {
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      StateKey stateKey = (StateKey) o;
      return Arrays.equals(data, stateKey.data);
    }

    @Override
    public int hashCode() {
      return Arrays.hashCode(data);
    }
  }

  // Comparable State for PriorityQueue
  private record AStarState(int[] counts, int presses, int heuristic) implements Comparable<AStarState> {
    // f(n) = g(n) + h(n) => presses + heuristic
    public int priority() {
      return presses + heuristic;
    }

    @Override
    public int compareTo(AStarState other) {
      return Integer.compare(this.priority(), other.priority());
    }
  }

  private record StateP1(long lights, int presses) {}
  private record StateP2(List<Integer> counts, int presses) {}

  // --- Parsing Logic ---
  private static final Pattern LIGHTS_PATTERN = Pattern.compile("\\[([.#]+)]");
  private static final Pattern BUTTON_PATTERN = Pattern.compile("\\(([^)]+)\\)");
  private static final Pattern JOLTAGE_PATTERN = Pattern.compile("\\{([\\d,]+)\\}"); // New Regex

  private static FactoryMachine parseLine(String line) {
    // 1. Parse Lights Target (Bitmask)
    Matcher m = LIGHTS_PATTERN.matcher(line);
    if (!m.find()) throw new IllegalArgumentException("Invalid format: " + line);

    String lightsStr = m.group(1);
    int numLights = lightsStr.length();
    long targetMask = 0;
    for (int i = 0; i < numLights; i++) {
      if (lightsStr.charAt(i) == '#') targetMask |= (1L << i);
    }

    // 2. Parse Buttons (Bitmasks)
    List<Long> buttons = new ArrayList<>();
    Matcher bm = BUTTON_PATTERN.matcher(line);
    while (bm.find()) {
      String content = bm.group(1);
      long mask = 0;
      if (!content.isBlank()) {
        for (String idx : content.split(",")) {
          mask |= (1L << Integer.parseInt(idx.trim()));
        }
      }
      buttons.add(mask);
    }

    // 3. Parse Joltage Requirements (Integer List)
    List<Integer> joltageTargets = new ArrayList<>();
    Matcher jm = JOLTAGE_PATTERN.matcher(line);
    if (jm.find()) {
      String content = jm.group(1);
      for (String val : content.split(",")) {
        joltageTargets.add(Integer.parseInt(val.trim()));
      }
    }

    return new FactoryMachine(targetMask, numLights, buttons, joltageTargets);
  }
}
