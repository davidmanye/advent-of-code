package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.Point;
import com.manye.aoc.y2025.model.Warehouse;

public class Day04 implements Day {

  @Override
  public String part1(String input) {
    // 1. Transform String to Model
    var warehouse = new Warehouse(input);

    // 2. Execute Logic
    long result = warehouse.countAccessibleRolls();

    // 3. Return Output
    return String.valueOf(result);
  }

  @Override
  public String part2(String input) {
    // 1. Transform String to Model
    var warehouse = new Warehouse(input);
    var accessibleRolls = warehouse.getAccessibleRolls();
    long total = 0;
    while (!accessibleRolls.isEmpty()) {
      total += accessibleRolls.size();
      warehouse = warehouse.removePoints(accessibleRolls);
      accessibleRolls = warehouse.getAccessibleRolls();
    }
    return String.valueOf(total);
  }
}
