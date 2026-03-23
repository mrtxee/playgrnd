package org.mrtxee.playgrnd.sandbox.hs;

import java.util.Arrays;

public class FindUniqueNumberProblem {
  public static int getUniqueNumberDepricated(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int count = 0;
      for (int j = 0; j < array.length; j++) {
        if (array[i] == array[j]) {
          count++;
        }
      }
      if (count == 1) {
        return array[i];
      }
    }
    return 0;
  }

  public static void main(String[] args) {

    FindUniqueNumberProblem me = new FindUniqueNumberProblem();
    int[] arr = {1, 5, 1, 2, 2, 7, 5};
    System.out.println(me.getUniqueNumber(arr));

    //        System.out.println("512 is "+me.isPowerOfTwo(512));
    //        System.out.println("513 is "+me.isPowerOfTwo(513));
    //        System.out.println("1 is "+me.isPowerOfTwo(1));
    //        System.out.println("2 is "+me.isPowerOfTwo(2));
    //        System.out.println("512 is "+me.isPowerOfTwo(512));
  }

  public boolean isPowerOfTwoDeprecated(int number) {
    int twoInPower = 1;
    while (twoInPower <= number) {
      if (twoInPower == number) {
        return true;
      }
      twoInPower *= 2;
    }
    return false;
  }

  public boolean isPowerOfTwoDeprecated2(int number) {
    int twoInPower = 1;
    while (twoInPower <= number) {
      if (twoInPower == number) {
        return true;
      }
      twoInPower <<= 1;
    }
    return false;
  }

  public boolean isPowerOfTwo(int number) {
    return (number > 0 && (number & number - 1) == 0);
  }

  public int getUniqueNumber(int[] nums) {
    System.out.println(Arrays.toString(nums));
    int result = 0;
    for (int i : nums) {
      System.out.print(
          Integer.toBinaryString(i) + " (" + i + ") ^ " + Integer.toBinaryString(result) + " ("
              + result + ")");
      result = result ^ i;
      System.out.println(" = " + Integer.toBinaryString(result) + " (" + result + ")");
    }
    return result;
  }
}
