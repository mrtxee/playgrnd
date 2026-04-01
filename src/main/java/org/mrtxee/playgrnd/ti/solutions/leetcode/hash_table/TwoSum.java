package org.mrtxee.playgrnd.ti.solutions.leetcode.hash_table;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/description/
 * 1. Two Sum, easy
 * <p>
 * Найти два разных индекса в массиве nums, значения которых в сумме дают target.
 *
 */
public class TwoSum {

  public static void main(String[] args) {
    int[] nums = new int[] {2, 7, 11, 15};
    int target = 9;
    System.out.println(Arrays.toString(twoSum(nums, target)));
  }

  public static int[] twoSum(int[] nums, int target) {
    // Создаём HashMap для хранения уже встреченных чисел и их индексов.
    // Ключ = само число, Значение = его индекс в исходном массиве.
    Map<Integer, Integer> map = new HashMap<>();

    // Проходим по массиву ровно один раз, получая текущий индекс i.
    for (int i = 0; i < nums.length; i++) {
      // Вычисляем "дополнение" (complement): число, которое нужно к текущему, чтобы сумма стала равна target.
      int complement = target - nums[i];

      // Проверяем, встречали ли мы уже это дополнение ранее.
      // Если да, значит нужная пара найдена: один индекс лежит в map, второй — текущий i.
      if (map.containsKey(complement)) {
        return new int[] { map.get(complement), i };
      }

      // Сохраняем текущее число и его индекс в map для будущих итераций.
      // Это делается ПОСЛЕ проверки, чтобы случайно не использовать один элемент дважды.
      map.put(nums[i], i);
    }

    // По условию задачи решение гарантированно существует, поэтому эта строка не выполнится.
    // Бросаем исключение для корректного завершения метода при нарушении условий.
    throw new IllegalArgumentException("No two sum solution");
  }

  /**
   * Оценка сложности
   * Временная: O(n)
   * Пространственная: O(n)
   */

}
