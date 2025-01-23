package org.mrtxee.playgrnd.sandbox.quicksort;

public class QuickSortBaeldung extends AbstractQuickSort {


  @Override
  void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  public void quickSort(int[] arr, int begin, int end) {
    if (begin < end) {
      int pivotIndex = partition(arr, begin, end);
      quickSort(arr, begin, pivotIndex - 1);
      quickSort(arr, pivotIndex + 1, end);
    }
  }

  private int partition(int[] arr, int begin, int end) {
    int pivot = arr[end];
    int wallIndex = begin - 1;

    for (int currentIndex = begin; currentIndex < end; currentIndex++) {
      final int current = arr[currentIndex];
      if (current <= pivot) {
        wallIndex++;
        if(wallIndex != currentIndex){
          swapWithInfo(arr, wallIndex, currentIndex);
        }
      }
    }

    wallIndex++;
    if(wallIndex != end){
      swap(arr, wallIndex, end);
    }
    return wallIndex;
  }

}
