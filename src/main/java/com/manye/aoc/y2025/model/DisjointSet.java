package com.manye.aoc.y2025.model;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

  private final int[] parent;
  private final int[] size;
  private int componentCount;

  public DisjointSet(int n) {
    this.parent = new int[n];
    this.size = new int[n];
    this.componentCount = n;

    // Initially, every element is its own parent (own group)
    // and every group has size 1.
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  // Find the representative (root) of the set containing 'i'
  // Uses path compression for efficiency
  public int find(int i) {
    if (parent[i] != i) {
      parent[i] = find(parent[i]);
    }
    return parent[i];
  }

  // Returns true if a merge actually happened (sets were different)
  public boolean union(int i, int j) {
    int rootA = find(i);
    int rootB = find(j);

    if (rootA != rootB) {
      // Connect the smaller tree to the larger tree (Union by Size)
      if (size[rootA] < size[rootB]) {
        parent[rootA] = rootB;
        size[rootB] += size[rootA];
      } else {
        parent[rootB] = rootA;
        size[rootA] += size[rootB];
      }
      componentCount--;
      return true;
    }
    return false;
  }

  public int getComponentCount() {
    return componentCount;
  }

  public int getSize(int i) {
    return size[find(i)];
  }

  // Helper to get all component sizes currently in the set
  public long[] getAllComponentSizes() {
    // We iterate through all elements, find their root, and map root -> size
    // Using a map to avoid duplicates (multiple elements point to same root)
    Map<Integer, Integer> rootToSize = new HashMap<>();

    for(int i = 0; i < parent.length; i++) {
      int root = find(i);
      rootToSize.put(root, size[root]);
    }

    return rootToSize.values().stream()
        .mapToLong(Integer::longValue)
        .toArray();
  }
}
