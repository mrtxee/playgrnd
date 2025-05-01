package org.mrtxee.playgrnd.sandbox.runtime;

import org.apache.commons.io.FileUtils;

public class Main {
  public static void main(String[] args) {
    Runtime currentRuntime = Runtime.getRuntime();
    String text = """
      Runtime.getRuntime : %s
      availableProcessors: %d
      totalMemory: %s
      freeMemory : %s
      maxMemory  : %s
      """
      .formatted(
        currentRuntime.toString(),
        currentRuntime.availableProcessors(),
        FileUtils.byteCountToDisplaySize(currentRuntime.totalMemory()),
        FileUtils.byteCountToDisplaySize(currentRuntime.freeMemory()),
        FileUtils.byteCountToDisplaySize(currentRuntime.maxMemory())
      );
    System.out.println(text);
  }
}
