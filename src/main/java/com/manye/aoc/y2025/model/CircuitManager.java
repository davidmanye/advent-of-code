package com.manye.aoc.y2025.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CircuitManager {

  private final List<Point3D> boxes;
  // Cache the connections so we don't recalculate them for Part 2
  private List<Connection> sortedConnections = null;

  public CircuitManager(String input) {
    this.boxes = input.lines()
        .map(this::parseLine)
        .toList();
  }

  private Point3D parseLine(String line) {
    String[] parts = line.split(",");
    return new Point3D(
        Integer.parseInt(parts[0]),
        Integer.parseInt(parts[1]),
        Integer.parseInt(parts[2])
    );
  }

  private List<Connection> getSortedConnections() {
    if (this.sortedConnections != null) {
      return this.sortedConnections;
    }

    List<Connection> connections = new ArrayList<>();
    for (int i = 0; i < boxes.size(); i++) {
      for (int j = i + 1; j < boxes.size(); j++) {
        double dist = boxes.get(i).distance(boxes.get(j));
        connections.add(new Connection(i, j, dist));
      }
    }
    connections.sort(Comparator.comparingDouble(Connection::distance));
    this.sortedConnections = connections;
    return connections;
  }

  public long solvePart1(int limit) {
    List<Connection> connections = getSortedConnections();
    DisjointSet dsu = new DisjointSet(boxes.size());

    int actualLimit = Math.min(limit, connections.size());
    for (int k = 0; k < actualLimit; k++) {
      Connection c = connections.get(k);
      dsu.union(c.node1(), c.node2());
    }

    // (Same logic as before for calculating result...)
    long[] sizes = dsu.getAllComponentSizes();
    java.util.Arrays.sort(sizes);

    long result = 1;
    int count = 0;
    for(int i = sizes.length - 1; i >= 0 && count < 3; i--) {
      result *= sizes[i];
      count++;
    }
    return result;
  }



  // --- Part 2 ---
  public long solvePart2() {
    List<Connection> connections = getSortedConnections();
    DisjointSet dsu = new DisjointSet(boxes.size());

    for (Connection c : connections) {
      // Try to connect the two boxes
      boolean merged = dsu.union(c.node1(), c.node2());

      // If they were merged (meaning they weren't connected before)...
      if (merged) {
        // ...check if the whole world is now one big happy circuit.
        if (dsu.getComponentCount() == 1) {
          Point3D boxA = boxes.get(c.node1());
          Point3D boxB = boxes.get(c.node2());

          // Return product of X coordinates
          return (long) boxA.x() * boxB.x();
        }
      }
    }

    throw new IllegalStateException("All connections exhausted but graph is not connected!");
  }

  // Local record to hold edge info
  private record Connection(int node1, int node2, double distance) {}
}
