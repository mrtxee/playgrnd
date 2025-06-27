package org.mrtxee.playgrnd.sandbox.quicksort;

public class QuickSortByArche2 extends AbstractQuickSort {
  @Override
  void quickSort(int[] arr) {
    quickSortPartition(arr, 0, arr.length - 1);
  }

  /**
   * Если партиция массива содержит хотя бы 1 элемент, то
   *   1. получаем индекс отсортированного элемента
   *   2. делаем рекурсивный вызов для партиций справа и слева от отсортированного
   */
  void quickSortPartition(int[] arr, int arrStartIndex, int arrEndIndex) {
    if (arrStartIndex < arrEndIndex) {
      int sortedElementIndex = quickSortPartitionAndReturnSortedElementIndex(arr, arrStartIndex, arrEndIndex);
      quickSortPartition(arr, arrStartIndex, sortedElementIndex - 1);
      quickSortPartition(arr, sortedElementIndex + 1, arrEndIndex);
    }
  }

  /**
   * Идем по массиву. Все отсортированные элементы отгораживаем стеной. В конце индекс стены станет индексом
   * отсортированного элемента.
   *
   * На каждом шаге
   *  - если значение текущего элемента <= значению опорного, то
   *      1. сдвигаем стену
   *      2. если индекс стены меньше индекса текущего элемента, то меняем местами, таким образом значения больше
   *      опорного всегда будут справа от стены.
   *
   */
  int quickSortPartitionAndReturnSortedElementIndex(int[] arr, int arrStartIndex, int arrEndIndex) {
    int pivot = arr[arrEndIndex];
    int wallIndex = arrStartIndex - 1; // индекс отсортированных элементов т.е. которые <= опорному

    for (int currentIndex = arrStartIndex; currentIndex <= arrEndIndex; currentIndex++) {
      if (arr[currentIndex] <= pivot) {
        wallIndex++;
        if (wallIndex < currentIndex) {
          swapWithInfo(arr, wallIndex, currentIndex);
        }
      }
    }

    return wallIndex;
  }
}
