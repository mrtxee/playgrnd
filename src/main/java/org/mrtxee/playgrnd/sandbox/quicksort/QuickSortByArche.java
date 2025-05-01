package org.mrtxee.playgrnd.sandbox.quicksort;

/**
 * Пусть стена - индекс левее которого все элементы меньше оси
 * Суть сортировки: делить массив на сегменты, выделя каждый раз индекс стены пока все сегменты не окажутся
 * отсортированными
 */

public class QuickSortByArche extends AbstractQuickSort {

  /**
   * Первичный вызов рекурсивного метода
   */
  @Override
  void quickSort(int[] arr) {
    quickSortRecursive(arr, 0, arr.length - 1);
  }

  /**
   * Получить индекс отсортированного элемента и рекурсивно вызывать для сегментов слева и справа
   */
  void quickSortRecursive(int[] arr, int startIndex, int endIndex) {
    if (startIndex >= endIndex) {
      return;
    }
    int sortedElementIndex = quickSortSegmentAndGetSortedElementIndex(arr, startIndex, endIndex);
    quickSortRecursive(arr, startIndex, sortedElementIndex - 1);
    quickSortRecursive(arr, sortedElementIndex + 1, endIndex);
  }

  /**
   * Бежим по массиву, сдвигаем стену только тогда, когда текущее значение массива <= опорного элемента
   * При этом меняем значения стены и текущего элемента местами, чтобы если будет элемент со значением выше опорного
   * элемента, то он сдвигался бы вправо.
   * В конце возвращаем индекс стены, так как он и станет отсортированным элементом.
   */
  private int quickSortSegmentAndGetSortedElementIndex(int[] arr, int startIndex, int endIndex) {
    final int pivot = arr[endIndex];
    int wallIndex = startIndex - 1;

    for (int currentIndex = startIndex; currentIndex <= endIndex; currentIndex++) {
      final int current = arr[currentIndex];
      if (current <= pivot) {
        wallIndex++;
        if (wallIndex < currentIndex) {
          swapWithInfo(arr, wallIndex, currentIndex);
        }
      }
    }

    return wallIndex;
  }
}
