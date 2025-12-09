package com.manye.aoc.y2020.days;

import com.manye.aoc.Day;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 implements Day {

  @Override
  public String part1(String input) {
    Map<Long, Long> memory = new HashMap<>();
    long orMask = 0L;
    long andMask = 0L;

    for (String line : input.lines().toList()) {
      if (line.startsWith("mask = ")) {
        String maskStr = line.substring(7);
        // Create OR mask: X becomes 0 (no change), 1 becomes 1 (force set), 0 becomes 0
        orMask = Long.parseLong(maskStr.replace('X', '0'), 2);
        // Create AND mask: X becomes 1 (no change), 1 becomes 1, 0 becomes 0 (force clear)
        andMask = Long.parseLong(maskStr.replace('X', '1'), 2);
      } else {
        // Parse "mem[address] = value"
        String[] parts = line.split("] = ");
        long address = Long.parseLong(parts[0].substring(4));
        long value = Long.parseLong(parts[1]);

        // Apply bitmasks
        long result = (value | orMask) & andMask;
        memory.put(address, result);
      }
    }

    return String.valueOf(memory.values().stream().mapToLong(Long::longValue).sum());
  }

  @Override
  public String part2(String input) {
    Map<Long, Long> memory = new HashMap<>();
    String currentMask = "";

    for (String line : input.lines().toList()) {
      if (line.startsWith("mask = ")) {
        currentMask = line.substring(7);
      } else {
        String[] parts = line.split("] = ");
        long rawAddress = Long.parseLong(parts[0].substring(4));
        long value = Long.parseLong(parts[1]);

        // 1. Apply the '1's from the mask (0s leave address unchanged)
        long baseAddress = rawAddress | Long.parseLong(currentMask.replace('X', '0'), 2);

        // 2. Identify floating bit positions (indices of 'X')
        List<Integer> floatingBits = new ArrayList<>();
        for (int i = 0; i < currentMask.length(); i++) {
          if (currentMask.charAt(i) == 'X') {
            // Convert string index (0=MSB) to bit position (35=MSB, 0=LSB)
            floatingBits.add(35 - i);
          }
        }

        // 3. Clear all floating bits in baseAddress to 0 so we can toggle them cleanly
        for (int bit : floatingBits) {
          baseAddress &= ~(1L << bit);
        }

        // 4. Recursively write to all floating address permutations
        writeFloating(memory, baseAddress, value, floatingBits, 0);
      }
    }

    return String.valueOf(memory.values().stream().mapToLong(Long::longValue).sum());
  }

  /**
   * Recursive helper to generate all address combinations for floating bits.
   */
  private void writeFloating(Map<Long, Long> memory, long address, long value, List<Integer> floatingBits, int index) {
    // Base case: processed all floating bits
    if (index == floatingBits.size()) {
      memory.put(address, value);
      return;
    }

    int bitPos = floatingBits.get(index);

    // Case 1: Leave the floating bit as 0 (it was pre-cleared in baseAddress)
    writeFloating(memory, address, value, floatingBits, index + 1);

    // Case 2: Set the floating bit to 1
    long addressWithOne = address | (1L << bitPos);
    writeFloating(memory, addressWithOne, value, floatingBits, index + 1);
  }
}
