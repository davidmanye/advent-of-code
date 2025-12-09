package com.manye.aoc; // <-- Updated package name

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class InputReader {

  // The base path inside the 'resources' directory
  private static final String INPUT_DIR = "/inputs/";

  /**
   * Reads the entire content of the input file for a specific day.
   * @param dayNumber The day number (1, 2, 3, etc.).
   * @return The file content as a String, or an empty string upon error.
   */
  public static String readInput(int year, int dayNumber) {
    // Generates the formatted file name (e.g., "day01.txt")
    String fileName = String.format("%s/day%02d.txt", year, dayNumber);
    String fullPath = INPUT_DIR + fileName;

    return readInput(fullPath);
  }

  /**
   * Reads the entire content of the file using a full path within 'resources'.
   * @param path The full path of the resource (e.g., "/inputs/day01.txt").
   * @return The file content as a String, or an empty string upon error.
   */
  public static String readInput(String path) {
    // Use the ClassLoader to locate the resource relative to the classpath root
    try (InputStream inputStream = InputReader.class.getResourceAsStream(path)) {

      // 1. Check if the resource exists
      if (inputStream == null) {
        System.err.println("Error: Resource not found at path: " + path);
        return "";
      }

      return IOUtils.toString(inputStream, StandardCharsets.UTF_8);

    } catch (IOException e) {
      System.err.println("I/O error reading file: " + path);
      e.printStackTrace();
      return "";
    }
  }
}
