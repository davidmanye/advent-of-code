package com.manye.aoc.days;

import com.manye.aoc.Day;
import com.manye.aoc.model.Range;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day05 implements Day {

  @Override
  public String part1(String input) {
    final var inputParts = input.split("\n\n");
    final List<Range> ranges = inputParts[0].lines().map(Range::parse).toList();
    final List<Long> availableIds = inputParts[1].lines().mapToLong(Long::parseLong).boxed()
        .toList();
    return availableIds.stream().filter(id -> ranges.stream().anyMatch(range -> range.isBetween(id))).count() + "";
  }

  @Override
  public String part2(String input) {
    final var inputParts = input.split("\n\n");
    // 1. Parse and create a MUTABLE list for sorting
    List<Range> ranges = new ArrayList<>(
        inputParts[0].lines().map(Range::parse).toList()
    );

    // 2. Sort by the start (min) of the range
    ranges.sort(java.util.Comparator.comparingLong(Range::min));

    long totalFreshIDs = 0;

    // Initialize with the first range
    Range current = ranges.getFirst();

    for (int i = 1; i < ranges.size(); i++) {
      Range next = ranges.get(i);

      // 3. Check for overlap
      // Since they are sorted, we only need to check if the next one starts
      // before (or exactly when) the current one ends.
      if (next.min() <= current.max()) {
        // MERGE: Extend the current 'max' to cover the next range if needed
        long newMax = Math.max(current.max(), next.max());
        current = new Range(current.min(), newMax);
      } else {
        // NO OVERLAP: The 'current' range is complete.
        totalFreshIDs += current.size();
        // Move to the next one
        current = next;
      }
    }
    // 4. Don't forget to add the final range remaining in 'current'
    totalFreshIDs += current.size();

    return String.valueOf(totalFreshIDs);
  }
}
