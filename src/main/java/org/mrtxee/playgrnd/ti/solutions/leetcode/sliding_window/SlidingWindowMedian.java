package org.mrtxee.playgrnd.ti.solutions.leetcode.sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 480. Sliding Window Median, Hard
 * <a href="https://leetcode.com/problems/sliding-window-median/">link</a>
 * <p>
 * Для каждого положения скользящего окна размера k в массиве nums найти медиану (среднее
 * значение в отсортированном подмножестве) и вернуть массив этих медиан.
 *
 * You are given an integer array nums and an integer k. There is a sliding window of size k
 * which is moving from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one position.
 * Eg.:
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 *
 * Как найти медиану для набора нечетного набора [1, 3, -1]:
 * 1. Упорядочьте числа по возрастанию:
 *    [−1,1,3]
 * 2. Определите количество элементов:
 *    3 числа — нечётное количество.
 * 3. Найдите элемент посередине: 1
 *
 * Как найти медиану для набора четного набора [1, 3, 5, -1]:
 * 1. Упорядочьте числа по возрастанию:
 *    [-1, 1, 3, 5]
 * 2. Определите количество элементов:
 *    4 числа — чётное количество.
 * 3. Найдите среднее арифметическое 2 элементов посередине: (1+3)/2 = 2 *
 */

public class SlidingWindowMedian {

  public static void main(String[] args) {
    int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
    int k = 3;
    System.out.println(Arrays.toString(medianSlidingWindow(nums, k)));
    System.out.println("[1.0, -1.0, -1.0, 3.0, 5.0, 6.0]");
  }

  /**
   * Алгоритм поддерживает текущее окно всегда отсортированным, что позволяет получать медиану за
   * O(1) просто обращением к центру списка.
   */
  public static double[] medianSlidingWindow(int[] nums, int widowSize) {
    // Создаём динамический список для последовательного сохранения найденных медиан
    List<Double> resList = new ArrayList<>();
    // Запоминаем длину массива, чтобы контролировать границы цикла сдвига окна
    int arrayLength = nums.length;
    // Левый указатель: индекс элемента, который покинет окно при сдвиге вправо
    int windowStartIndex = 0;
    // Правый указатель: индекс нового элемента, который войдёт в окно
    int windowEndIndex = widowSize - 1;
    // Список для хранения элементов текущего окна в строго отсортированном порядке
    List<Integer> sortedNums = new ArrayList<>();
    // Загружаем первые widowSize-1 элементов окна
    // (последний добавим внутри цикла для удобства сдвига)
    for (int x = 0; x < widowSize - 1; x++) {
      sortedNums.add(nums[x]);
    }

    // Сортируем начальные элементы, чтобы сразу получить упорядоченную структуру для поиска медианы
    Collections.sort(sortedNums);
    // Цикл выполняется, пока правый указатель не выйдет за пределы массива
    while (windowEndIndex < arrayLength) {
      // Захватываем новый элемент, входящий в окно справа
      int num = nums[windowEndIndex];
      // Ищем позицию для вставки нового элемента через бинарный поиск
      // работает только с отсортированным списком
      int insertIdx = Collections.binarySearch(sortedNums, num);
      // Если элемента нет в списке, binarySearch возвращает (-(точка вставки) - 1)
      if (insertIdx < 0) {
        // Преобразуем код возврата в реальный индекс, куда нужно вставить элемент для сохранения
        // сортировки insertIdx = -insertIdx - 1;
      }
      // Вставляем элемент по найденному индексу: ArrayList автоматически сдвигает элементы вправо
      sortedNums.add(insertIdx, num);

      // Вычисляем медиану текущего окна в зависимости от чётности widowSize
      if (widowSize % 2 == 1) {
        // При нечётном widowSize медиана — ровно центральный элемент
        double median = (double) sortedNums.get(widowSize / 2);
        resList.add(median);
      } else {
        // При чётном widowSize медиана — среднее арифметическое двух центральных элементов
        int idx = widowSize / 2;
        double median1 = (double) sortedNums.get(idx);
        double median2 = (double) sortedNums.get(idx - 1);
        resList.add((median1 + median2) / 2);
      }

      // Ищем индекс элемента, который должен покинуть окно слева (старый элемент nums[windowStartIndex])
      int removeIdx = Collections.binarySearch(sortedNums, nums[windowStartIndex]);
      // Страховка на случай, если элемент не найден (теоретически невозможно при корректном вводе)
      if (removeIdx < 0) {
        removeIdx = -removeIdx - 1;
      }
      // Удаляем элемент по индексу: ArrayList сдвигает все последующие элементы влево, закрывая дыру
      sortedNums.remove(removeIdx);

      // Сдвигаем окно на одну позицию вправо: левый указатель++, правый указатель++
      windowStartIndex++;
      windowEndIndex++;
    }

    // Создаём примитивный массив double нужного размера для возврата результата
    double[] resArray = new double[resList.size()];
    // Копируем элементы из List в примитивный массив (Java не позволяет привести List<Double> в double[] напрямую)
    for (int x = 0; x < resList.size(); x++) {
      resArray[x] = resList.get(x);
    }

    // Возвращаем итоговый массив медиан для каждого положения окна
    return resArray;
  }
}
