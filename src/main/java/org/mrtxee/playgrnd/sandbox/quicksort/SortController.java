package org.mrtxee.playgrnd.sandbox.quicksort;

import java.util.Arrays;

public class SortController {

  public static void main(String[] args) {

    AbstractQuickSort sorter = new QuickSortByArche3();

    //int[] arr = new int[]{1, 3, 6, 8, 4, 1, 9, 5, 2};
    int[] arr = new int[]{3, 5, 8, 1, 2, 9, 4, 7, 6};
    System.out.println(Arrays.toString(arr));
    sorter.quickSort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
