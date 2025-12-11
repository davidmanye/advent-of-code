package com.manye.aoc.y2025.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactorNetwork {

  // Adjacency List: "node" -> ["neighborA", "neighborB"]
  private final Map<String, List<String>> adjacencyList = new HashMap<>();

  // Memoization Cache: "node" -> number of paths to target
  private final Map<String, Long> memo = new HashMap<>();

  public ReactorNetwork(String input) {
    input.lines().forEach(this::parseLine);
  }

  private void parseLine(String line) {
    // Format: "aaa: you hhh"
    String[] parts = line.split(": ");
    String node = parts[0];
    String[] targets = parts[1].split(" ");

    adjacencyList.put(node, List.of(targets));
  }

  public long countPaths(String startNode, String endNode) {
    // Clear cache in case we reuse the object
    memo.clear();
    return countRecursive(startNode, endNode);
  }

  private long countRecursive(String current, String target) {
    // 1. Base Case: We reached the target
    if (current.equals(target)) {
      return 1;
    }

    // 2. Check Cache
    if (memo.containsKey(current)) {
      return memo.get(current);
    }

    // 3. Recursive Step: Sum paths from all neighbors
    long totalPaths = 0;
    List<String> neighbors = adjacencyList.getOrDefault(current, new ArrayList<>());

    for (String neighbor : neighbors) {
      totalPaths += countRecursive(neighbor, target);
    }

    // 4. Store in Cache and Return
    memo.put(current, totalPaths);
    return totalPaths;
  }
}
