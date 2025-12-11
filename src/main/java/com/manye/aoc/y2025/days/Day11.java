package com.manye.aoc.y2025.days;

import com.manye.aoc.Day;
import com.manye.aoc.y2025.model.ReactorNetwork;

public class Day11 implements Day {

  @Override
  public String part1(String input) {
    var network = new ReactorNetwork(input);
    // The problem asks for paths from 'you' to 'out'
    return String.valueOf(network.countPaths("you", "out"));
  }

  @Override
  public String part2(String input) {
    var network = new ReactorNetwork(input);

    String start = "svr";
    String end = "out";
    String mid1 = "dac";
    String mid2 = "fft";

    // Scenario 1: svr -> dac -> fft -> out
    long viaDacThenFft = network.countPaths(start, mid1)
        * network.countPaths(mid1, mid2)
        * network.countPaths(mid2, end);

    // Scenario 2: svr -> fft -> dac -> out
    long viaFftThenDac = network.countPaths(start, mid2)
        * network.countPaths(mid2, mid1)
        * network.countPaths(mid1, end);

    return String.valueOf(viaDacThenFft + viaFftThenDac);
  }
}
