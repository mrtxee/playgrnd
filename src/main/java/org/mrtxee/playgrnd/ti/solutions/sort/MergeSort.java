package org.mrtxee.playgrnd.ti.solutions.sort;

import java.util.Arrays;

public class MergeSort {

  public static void main(String[] args) {
    int[] arr = new int[] {3, 5, 8, 1, 2, 9, 4, 7, 6};
    System.out.println(Arrays.toString(arr));
    mergeSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  /**
   * Отсортировать массив по возрастанию, используя стратегию «разделяй и властвуй»:
   *
   * Рекурсивно разбиваем массив на две половины, пока размер подмассивов не станет 1.
   * Последовательно сливаем соседние отсортированные подмассивы, сравнивая их элементы и
   * формируя новую упорядоченную последовательность.
   * Гарантируем стабильность сортировки (равные элементы сохраняют исходный порядок) и
   * предсказуемую производительность на любых данных.
   */
  public static void mergeSort(int[] arr) {
    // Базовый случай рекурсии: массив из 0 или 1 элемента уже отсортирован
    if (arr == null || arr.length <= 1) {
      return;
    }
    // Создаём вспомогательный массив один раз, чтобы не пересоздавать его на каждом шаге рекурсии
    int[] temp = new int[arr.length];
    // Запускаем рекурсивную сортировку для всего диапазона индексов [0, length-1]
    sort(arr, temp, 0, arr.length - 1);
  }

  /**
   * 🌳 Визуализация рекурсии на примере [3,1,4,2]:
   *
   *                    [3,1,4,2]  ← sort(0,3)
   *                   /          \
   *           [3,1] sort(0,1)   [4,2] sort(2,3)
   *           /    \            /    \
   *      [3] sort(0,0) [1] sort(1,1)  [4] sort(2,2) [2] sort(3,3)
   *           \    /            \    /
   *        merge → [1,3]      merge → [2,4]
   *                   \        /
   *                 merge → [1,2,3,4] ✅
   */
  private static void sort(int[] arr, int[] temp, int left, int right) {
    // Если левая граница >= правой, подмассив пуст или содержит 1 элемент → сортировать нечего
    if (left >= right) {
      return;
    }
    // Находим середину текущего подмассива, безопасно избегая переполнения при (left + right)
    int mid = left + (right - left) / 2;
    // Рекурсивно делим и сортируем левую половину массива
    sort(arr, temp, left, mid);
    // Рекурсивно делим и сортируем правую половину массива
    sort(arr, temp, mid + 1, right);
    // Сливаем две уже отсортированные половины в один упорядоченный подмассив
    merge(arr, temp, left, mid, right);
  }

  private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
    // Копируем текущий рабочий диапазон во вспомогательный массив,
    // чтобы не перезаписать данные до слияния
    for (int i = left; i <= right; i++) {
      temp[i] = arr[i];
    }
    // Указатель на текущий элемент левой отсортированной половины
    int leftSortedIndex = left;
    // Указатель на текущий элемент правой отсортированной половины
    int rightSortedIndex = mid + 1;
    // Указатель на позицию записи результата в исходный массив
    int leftBound = left;
    // Пока оба указателя не вышли за границы своих половин, сравниваем и записываем меньший
    while (leftSortedIndex <= mid && rightSortedIndex <= right) {
      // Если элемент из левой половины меньше или равен, записываем его и двигаем оба указателя
      if (temp[leftSortedIndex] <= temp[rightSortedIndex]) {
        arr[leftBound++] = temp[leftSortedIndex++];
      }
      // Иначе записываем элемент из правой половины (он меньше)
      else {
        arr[leftBound++] = temp[rightSortedIndex++];
      }
    }
    // Если в левой половине остались элементы, дописываем их в конец (правая уже закончилась)
    while (leftSortedIndex <= mid) {
      arr[leftBound++] = temp[leftSortedIndex++];
    }
    // Если в правой остались элементы, дописываем их (нужен для корректности, хотя часто не срабатывает)
    while (rightSortedIndex <= right) {
      arr[leftBound++] = temp[rightSortedIndex++];
    }
  }

}
