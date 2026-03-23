package org.mrtxee.playgrnd.sandbox.hs.ex;

public class Brackets {

  private static int counter = 0;

  public static void main(String[] args) {
    int n = 5;
    generate(n, 0, 0, "");
  }

  public static void generate(int n, int openCount, int closeCount, String str) {
    if (openCount == n && closeCount == n) {
      System.out.println(++counter + ": " + str);
    } else {
      if (openCount < n) {
        generate(n, openCount + 1, closeCount, str + "(");
      }
      if (openCount > closeCount) {
        generate(n, openCount, closeCount + 1, str + ")");
      }
    }
  }

}
