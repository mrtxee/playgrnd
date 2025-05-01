package org.mrtxee.playgrnd.sandbox.alg;

import java.math.BigInteger;

public class Factorial {
  public static void main(String[] args) {
    System.out.println(calcFactorial(5));
    System.out.println(calcFactorial(6) - calcFactorial(4)); // 696
    System.out.println(calcFactorialBig(50));
  }

  private static long calcFactorial(int n) {
    return (n == 1)
      ? n
      : n * calcFactorial(n - 1);
  }

  /**
   * Для n > 20
   */
  private static BigInteger calcFactorialBig(int n) {
    return (n == 1)
      ? BigInteger.ONE
      : BigInteger.valueOf(n).multiply(calcFactorialBig(n - 1));
  }
}
