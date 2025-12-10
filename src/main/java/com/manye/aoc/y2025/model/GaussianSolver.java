package com.manye.aoc.y2025.model;

import java.util.ArrayList;
import java.util.List;

public class GaussianSolver {

  public static long solve(FactoryMachine machine) {
    int numRows = machine.numLights(); // Equations
    int numCols = machine.buttons().size(); // Variables (Buttons)

    // 1. Build Augmented Matrix [A | b]
    // using Double for calculation, but we check for integer validity later
    double[][] matrix = new double[numRows][numCols + 1];
    List<Integer> targets = machine.joltageTargets();
    List<Long> buttonMasks = machine.buttons();

    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        long mask = buttonMasks.get(c);
        boolean affects = ((mask >> r) & 1) == 1;
        matrix[r][c] = affects ? 1.0 : 0.0;
      }
      matrix[r][numCols] = targets.get(r);
    }

    // 2. Convert to Reduced Row Echelon Form (RREF)
    int lead = 0;
    int[] pivotRowForCol = new int[numCols];
    java.util.Arrays.fill(pivotRowForCol, -1);

    for (int r = 0; r < numRows; r++) {
      if (numCols <= lead) break;

      int i = r;
      // Find pivot
      while (Math.abs(matrix[i][lead]) < 1e-9) {
        i++;
        if (i == numRows) {
          i = r;
          lead++;
          if (numCols == lead) break;
        }
      }

      if (numCols <= lead) break; // Should be covered by inner break but safety first

      // Swap rows
      double[] temp = matrix[i];
      matrix[i] = matrix[r];
      matrix[r] = temp;

      // Scale row to make pivot 1
      double div = matrix[r][lead];
      for (int j = 0; j <= numCols; j++) matrix[r][j] /= div;

      // Eliminate other rows
      for (int k = 0; k < numRows; k++) {
        if (k != r) {
          double factor = matrix[k][lead];
          for (int j = 0; j <= numCols; j++) matrix[k][j] -= factor * matrix[r][j];
        }
      }

      pivotRowForCol[lead] = r;
      lead++;
    }

    // 3. Identify Free Variables
    List<Integer> freeCols = new ArrayList<>();
    List<Integer> pivotCols = new ArrayList<>();

    for (int c = 0; c < numCols; c++) {
      if (pivotRowForCol[c] != -1) {
        pivotCols.add(c);
      } else {
        freeCols.add(c);
      }
    }

    // 4. Check for Inconsistency (0 = non-zero)
    // If a row is all zeros in A but non-zero in b, impossible.
    for (int r = 0; r < numRows; r++) {
      boolean allZero = true;
      for (int c = 0; c < numCols; c++) {
        if (Math.abs(matrix[r][c]) > 1e-9) {
          allZero = false;
          break;
        }
      }
      if (allZero && Math.abs(matrix[r][numCols]) > 1e-9) {
        return 0; // Impossible
      }
    }

    // 5. Search Free Variables
    // If no free variables, direct solution.
    // If free variables exist, we iterate them.
    // Upper bound estimate: roughly max(target) since coeffs are usually 1.
    // Optimization: The recursion depth is small (few free vars).

    long result = searchRecursive(matrix, pivotRowForCol, freeCols, 0, new long[numCols]);
    return result == Long.MAX_VALUE ? 0 : result;
  }

  private static long searchRecursive(double[][] rref, int[] pivotRowForCol,
      List<Integer> freeCols, int freeIdx,
      long[] solution) {

    // Base Case: All free variables assigned
    if (freeIdx == freeCols.size()) {
      long currentPresses = 0;
      int numCols = solution.length;
      int numRows = rref.length;

      // Calculate Pivot Variables based on Free Variables
      for (int c = 0; c < numCols; c++) {
        int r = pivotRowForCol[c];
        if (r != -1) {
          // x_pivot = Constant - sum(coeff_free * val_free)
          double val = rref[r][numCols]; // Start with constant
          for (int fCol : freeCols) {
            val -= rref[r][fCol] * solution[fCol];
          }

          // Check Integrity
          long rounded = Math.round(val);
          if (Math.abs(rounded - val) > 1e-4 || rounded < 0) {
            return Long.MAX_VALUE; // Invalid solution (fraction or negative)
          }
          solution[c] = rounded;
        }
        currentPresses += solution[c];
      }
      return currentPresses;
    }

    // Recursive Step
    // Try values for this free variable.
    // Heuristic Bound: Look at the raw targets. No single button press count
    // should likely exceed the maximum target value in the input row.
    // For safety in Day 10 part 2, 0..100 is usually enough for "free" adjustments.
    // For specific "big inputs", logic bounds it by (Constant / Coeff).

    int col = freeCols.get(freeIdx);
    long minResult = Long.MAX_VALUE;

    // We try a range. In "Underdetermined" systems for AoC, the free variable usually
    // accounts for "loops" or alternative ways. 0 to 50 is a safe bet for speed/coverage.
    // If targets are huge (200), we might need dynamic bounding.
    // Let's implement a dynamic bound check based on the first row that uses this free var.
    int limit = 200;

    for (int val = 0; val <= limit; val++) {
      solution[col] = val;
      long res = searchRecursive(rref, pivotRowForCol, freeCols, freeIdx + 1, solution);
      if (res < minResult) minResult = res;

      // Optimization: If simple heuristics suggest we are going too high, break.
      // (omitted for safety)
    }

    return minResult;
  }
}
