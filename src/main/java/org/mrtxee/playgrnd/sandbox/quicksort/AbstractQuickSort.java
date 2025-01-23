package org.mrtxee.playgrnd.sandbox.quicksort;

import java.util.Arrays;

public abstract class AbstractQuickSort {

  abstract void quickSort(int[] arr);

  public static void main(String[] args) {
    System.out.println("sandbox");
    swapTest();
  }

  protected static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  protected static void swapWithInfo(int[] arr, int i, int j) {
    swap(arr, i, j);
    System.out.printf("%s swapped: [%s]%s, [%s]%s%n", Arrays.toString(arr), i, arr[i], j, arr[j]);
  }

  private static void swapTest() {
    int[] arr = new int[]{1, 2, 3};
    swap(arr, 0, 1);
    System.out.println(arr[0] == 2);
    System.out.println(arr[1] == 1);
    System.out.println(arr[2] == 3);
  }


}
