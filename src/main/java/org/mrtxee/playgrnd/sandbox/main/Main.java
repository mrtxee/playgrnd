package org.mrtxee.playgrnd.sandbox.main;

public class Main {
  public static void main(String[] args) {
    Runtime currentRuntime = Runtime.getRuntime();
    System.out.println(currentRuntime.toString());
    System.out.println(currentRuntime.totalMemory());
    System.out.println(currentRuntime.availableProcessors());
//    System.out.println(FileUtils.byteCountToDisplaySize(currentRuntime.freeMemory()));
//    System.out.println(FileUtils.byteCountToDisplaySize(currentRuntime.maxMemory()));

  }
}
