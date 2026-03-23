package org.mrtxee.playgrnd.sandbox.alg.quicksort;

public class QuickSortByArche5 extends AbstractQuickSort {
  @Override
  void quickSort(int[] arr) {
    quickSortSegment(arr, 0, arr.length - 1);
  }

  void quickSortSegment(int[] arr, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int sortedElementIndex = getSortedElementIndex(arr, startIndex, endIndex);
      quickSortSegment(arr, startIndex, sortedElementIndex - 1);
      quickSortSegment(arr, sortedElementIndex + 1, endIndex);
    }
  }

  private int getSortedElementIndex(int[] arr, int startIndex, int endIndex) {
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
}
