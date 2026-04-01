package org.mrtxee.playgrnd.ti.solutions.sort;

import java.util.Arrays;

public class QuickSort {

  public static void main(String[] args) {
    int[] arr = new int[] {3, 5, 8, 1, 2, 9, 4, 7, 6};
    System.out.println(Arrays.toString(arr));
    quickSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  private static void quickSort(int[] arr) {
    quickSortSegment(arr, 0, arr.length - 1);
  }

  private static void quickSortSegment(int[] arr, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int sortedElementIndex = getSortedElementIndex(arr, startIndex, endIndex);
      quickSortSegment(arr, startIndex, sortedElementIndex - 1);
      quickSortSegment(arr, sortedElementIndex + 1, endIndex);
    }
  }

  private static int getSortedElementIndex(int[] arr, int startIndex, int endIndex) {
    int pivot = arr[endIndex];
    int wallIndex = startIndex - 1;
    for (int currentIndex = startIndex; currentIndex <= endIndex; currentIndex++) {
      if (arr[currentIndex] <= pivot) {
        wallIndex++;
        swap(arr, currentIndex, wallIndex);
      }
    }
    return wallIndex;
  }

  private static void swap(int[] arr, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}

