package org.mrtxee.playgrnd.ti.solutions.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals, medium
 * <a href="https://leetcode.com/problems/merge-intervals/">link</a>
 * <p>
 * Объединить все пересекающиеся интервалы во входном массиве в непересекающиеся.
 * Eg.:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 */
public class MergeIntervals {

  public static void main(String[] args) {
    int[][] intervals = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

    for (int[] current : merge(intervals)) {
      System.out.println(Arrays.toString(current));
    }

  }

  /**
   * Алгоритм использует жадный подход: после сортировки по началу интервала мы последовательно
   * "сливаем" соседние интервалы, если они пересекаются. Это гарантирует, что каждый интервал
   * обрабатывается ровно один раз, и результат содержит минимальное количество непересекающихся
   * отрезков, покрывающих все исходные данные.
   */
  public static int[][] merge(int[][] intervals) {

    // Сортируем интервалы в порядке возрастания начал (a[0]) интервалов
    // Это необходимо чтобы каждый интервал сравнивать с соседним, а не со всеми
    // a -> a[0] — лямбда-выражение, которое из интервала a (массива из двух чисел) извлекает
    // первый элемент для сравнения.
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    // Создаём динамический список для хранения результата (непересекающихся интервалов)
    List<int[]> mergedIntervals = new ArrayList<>();
    // Инициализируем "текущий" интервал первым элементом отсортированного массива
    int[] prev = intervals[0];

    // Проходим по всем оставшимся интервалам, начиная со второго (индекс 1)
    for (int i = 1; i < intervals.length; i++) {
      // Проверяем условие пересечения: если начало текущего интервала <= конца предыдущего
      // Это работает благодаря сортировке: мы сравниваем только соседние интервалы
      if (intervals[i][0] <= prev[1]) {
        // Интервалы пересекаются: расширяем конец текущего "слитого" интервала
        // Берём максимум из двух концов, чтобы покрыть оба исходных интервала
        prev[1] = Math.max(prev[1], intervals[i][1]);
      } else {
        // Пересечения нет: предыдущий интервал окончательно сформирован
        // Добавляем его в результат и начинаем новый "текущий" интервал
        mergedIntervals.add(prev);
        prev = intervals[i];
      }
    }

    // Добавляем последний "текущий" интервал, который не был добавлен в цикле
    mergedIntervals.add(prev);
    // Конвертируем ArrayList<int[]> в двумерный массив int[][] для возврата результата
    return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
  }

  /**
   * Оценка сложности:
   *
   * Пусть N — число интервалов
   *
   * Time  = O(N log N)
   *    - сортировка массива: O(N log N)
   *    - однопроходный цикл слияния занимает O(N): Итого: O(N log N) + O(N) = O(N log N).

   * Space = O(N)
   */
}
