package com.manye.aoc.y2025.model;

import java.util.ArrayList;
import java.util.List;

public class MathWorksheet {

  private final List<MathProblem> problems;

  // Private constructor: Use the static factories instead
  private MathWorksheet(String input, ProblemExtractor extractor) {
    this.problems = parseGrid(input, extractor);
  }

  // --- Static Factories ---

  /**
   * Part 1: Horizontal numbers, Vertical blocks.
   */
  public static MathWorksheet fromHorizontalRules(String input) {
    return new MathWorksheet(input, MathWorksheet::extractHorizontal);
  }

  /**
   * Part 2: Vertical numbers (columns), MSD at top.
   */
  public static MathWorksheet fromVerticalRules(String input) {
    return new MathWorksheet(input, MathWorksheet::extractVertical);
  }

  // --- Core Logic ---

  public long sumOfAllSolutions() {
    return problems.stream()
        .mapToLong(MathProblem::solve)
        .sum();
  }

  // --- Parsing Infrastructure ---

  @FunctionalInterface
  private interface ProblemExtractor {
    MathProblem extract(String[] lines, int startCol, int endCol);
  }

  private List<MathProblem> parseGrid(String input, ProblemExtractor extractor) {
    String[] lines = input.split("\n");
    int width = 0;
    for (String line : lines) {
      width = Math.max(width, line.length());
    }

    List<MathProblem> parsedProblems = new ArrayList<>();
    Integer startCol = null;

    for (int col = 0; col < width; col++) {
      boolean isSeparator = isColumnEmpty(lines, col);

      if (!isSeparator) {
        if (startCol == null) startCol = col;
      } else {
        if (startCol != null) {
          // Use the injected strategy to extract
          parsedProblems.add(extractor.extract(lines, startCol, col));
          startCol = null;
        }
      }
    }

    if (startCol != null) {
      parsedProblems.add(extractor.extract(lines, startCol, width));
    }

    return parsedProblems;
  }

  private boolean isColumnEmpty(String[] lines, int colIndex) {
    for (String line : lines) {
      if (getCharSafe(line, colIndex) != ' ') {
        return false;
      }
    }
    return true;
  }

  // Helper to handle variable line lengths safely (Fixes IntelliJ trimming issues too)
  private static char getCharSafe(String line, int index) {
    if (index >= line.length()) return ' ';
    return line.charAt(index);
  }

  // --- Strategy Implementations ---

  /**
   * Part 1 Logic: Rows contain the numbers.
   */
  private static MathProblem extractHorizontal(String[] lines, int startCol, int endCol) {
    List<Long> numbers = new ArrayList<>();
    Operation op = null;

    for (int row = 0; row < lines.length; row++) {
      String line = lines[row];
      // Extract segment, trim spaces
      // Note: We use Math.min to handle short lines gracefully
      int safeEnd = Math.min(endCol, line.length());
      String segment = (startCol >= line.length()) ? "" : line.substring(startCol, safeEnd).trim();

      if (segment.isEmpty()) continue;

      if (row == lines.length - 1) {
        op = Operation.fromChar(segment.charAt(0));
      } else {
        numbers.add(Long.parseLong(segment));
      }
    }
    return new MathProblem(numbers, op);
  }

  /**
   * Part 2 Logic: Columns contain the numbers (Top=MSD, Bottom=LSD).
   */
  private static MathProblem extractVertical(String[] lines, int startCol, int endCol) {
    List<Long> numbers = new ArrayList<>();
    Operation op = null;

    // 1. Find the Operator (Always on the last row)
    String lastLine = lines[lines.length - 1];
    for (int c = startCol; c < endCol; c++) {
      char ch = getCharSafe(lastLine, c);
      if (ch != ' ') {
        op = Operation.fromChar(ch);
        break;
      }
    }

    // 2. Scan Columns to build numbers
    for (int c = startCol; c < endCol; c++) {
      StringBuilder sb = new StringBuilder();

      // Iterate rows except the last one (which is the operator)
      for (int r = 0; r < lines.length - 1; r++) {
        char ch = getCharSafe(lines[r], c);
        if (Character.isDigit(ch)) {
          sb.append(ch);
        }
      }

      if (!sb.isEmpty()) {
        numbers.add(Long.parseLong(sb.toString()));
      }
    }

    return new MathProblem(numbers, op);
  }
}
