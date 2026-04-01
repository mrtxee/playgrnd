package org.mrtxee.playgrnd.ti.solutions.leetcode.two_pointer;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/container-with-most-water/description/
 * Medium
 *
 * Найти пару вертикальных линий в массиве, которые вместе с осью X образуют контейнер
 * с максимальной площадью (объёмом воды).
 *
 */
public class ContainerWithMostWater {

  public static void main(String[] args) {

    int[] input = new int[] {1,8,6,2,5,4,8,3,7};
    System.out.println(Arrays.toString(input));
    System.out.println(maxArea(input));

  }

  public static int maxArea(int[] heights) {

    // указатель на начало контейнера
    int left = 0;
    // указатель на конец контейнера
    int right = heights.length - 1;
    // Инициализируем переменную для хранения максимальной найденной площади,
    int maxArea = 0;

    // Используем 2 движущихся указателя чтобы перебрать все варианты площадей
    // и фиксируем наибольшую площадь
    while (left < right) {
      // Считаем площадь текущего контейнера
      int currentArea = Math.min(heights[left], heights[right]) * (right - left);
      // Если площадь текущего контейнера увеличилась, записываем её
      maxArea = Math.max(maxArea, currentArea);

      // Сдвигаем указатель с той линии которая короче.
      // Таким образом мы отсекаем варианты без шанса на улучшение.
      if (heights[left] < heights[right]) {
        left++;
      } else {
        right--;
      }
    }

    return maxArea;
  }

  /**
   * Оценка сложности алгоритма
   *
   * Время: O(n)
   * - На каждой итерации цикла один из указателей сдвигается на 1 позицию.
   * - Указатели проходят массив один раз, выполняется n-1 итераций.
   *
   * Память: O(с)
   * - Алгоритм использует фиксированное количество переменных (left, right, maxArea, currentArea)
   * - память не зависит от размера входного массива.
   */


}
