package org.mrtxee.playgrnd.sandbox.quicksort;

public class QuickSortByArche3 extends AbstractQuickSort {


  @Override
  void quickSort(int[] arr) {
    quickSortPartition(arr, 0, arr.length - 1);
  }

  private void quickSortPartition(int[] arr, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int sortedIndex = quickSortPartitionAndReturnSortedElementIndex(arr, startIndex, endIndex);
      quickSortPartition(arr, startIndex, sortedIndex - 1);
      quickSortPartition(arr, sortedIndex + 1, endIndex);
    }
  }

  private int quickSortPartitionAndReturnSortedElementIndex(int[] arr, int startIndex, int endIndex) {
    int pivot = arr[endIndex];
    /*
     начальный индекс стены должен быть меньше стартового индекса,
     т.к. мы всегда инкриментим индекс стены и он может выйти на границы массива
     */
    int wallIndex = startIndex - 1;

    for (int currentIndex = startIndex; currentIndex <= endIndex; currentIndex++) {
      if (arr[currentIndex] <= pivot) {
        wallIndex++;
        if (wallIndex < currentIndex) {
          swapWithInfo(arr, wallIndex, currentIndex, pivot);
        }
      }
    }

    return wallIndex;
  }
}
