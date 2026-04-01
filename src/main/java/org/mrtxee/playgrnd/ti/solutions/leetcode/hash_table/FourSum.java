package org.mrtxee.playgrnd.ti.solutions.leetcode.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * https://leetcode.com/problems/4sum/description/
 * 18. 4Sum, medium
 * <p>
 * Найти все уникальные комбинации из четырёх чисел массива, сумма которых равна target.
 * Алгоритм использует технику фиксации двух элементов + двух указателей:
 * <p>
 * Сортировка гарантирует, что одинаковые числа идут подряд, что упрощает фильтрацию дубликатов.
 * Первые два числа перебираются циклами (i, j).
 * Оставшиеся два числа ищутся за O(n) с помощью указателей left и right, что резко сокращает перебор по сравнению с brute-force O(n⁴).
 * Оптимизации (break/continue по мин/макс суммам) отбрасывают ветви поиска заранее.
 */
public class FourSum {

  public static void main(String[] args) {
    int[] nums = new int[] {1, 0, -1, 0, -2, 2};
    int target = 0;
    System.out.println(fourSum(nums, target));
  }

  public static List<List<Integer>> fourSum(int[] nums, int target) {
    // Сортируем массив: это позволяет использовать два указателя и легко пропускать дубликаты
    Arrays.sort(nums);
    // Создаём список для хранения результата (уникальных четвёрок)
    List<List<Integer>> result = new ArrayList<>();
    // Сохраняем длину массива, чтобы не вызывать length() многократно
    int n = nums.length;

    // Внешний цикл: фиксируем первый элемент четвёрки
    // Останавливаемся на n-3, так как справа должно остаться минимум 3 элемента
    for (int i = 0; i < n - 3; i++) {
      // Если текущий элемент равен предыдущему, пропускаем его, чтобы избежать дубликатов в ответе
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      // Оптимизация: минимально возможная сумма с текущим i уже больше target → дальнейший поиск бессмыслен
      if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
        break;
      }
      // Оптимизация: максимально возможная сумма с текущим i всё ещё меньше target → переходим к следующему i
      if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
        continue;
      }

      // Второй цикл: фиксируем второй элемент четвёрки
      // Начинаем с i+1, чтобы индексы не пересекались и элементы были уникальны
      for (int j = i + 1; j < n - 2; j++) {
        // Пропускаем дубликаты для второго элемента (проверка j > i+1 нужна, чтобы не отсеять первый валидный j)
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }

        // Аналогичные оптимизации для пары (i, j)
        if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
          break;
        }
        if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) {
          continue;
        }

        // Инициализируем левый указатель сразу после j и правый — в конце массива
        int left = j + 1;
        int right = n - 1;

        // Двигаем указатели навстречу, пока они не встретятся
        while (left < right) {
          // Считаем сумму четырёх чисел. Приводим к long, чтобы исключить переполнение int
          long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

          if (sum == target) {
            // Сумма совпала: добавляем найденную четвёрку в результат
            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
            // Пропускаем все одинаковые значения слева, чтобы не добавить ту же комбинацию повторно
            while (left < right && nums[left] == nums[left + 1]) {
              left++;
            }
            // Пропускаем все одинаковые значения справа по той же причине
            while (left < right && nums[right] == nums[right - 1]) {
              right--;
            }
            // Сдвигаем оба указателя для поиска следующей валидной пары
            left++;
            right--;
          } else if (sum < target) {
            // Сумма меньше target: нужно увеличить её → двигаем left вправо (к большим числам)
            left++;
          } else {
            // Сумма больше target: нужно уменьшить её → двигаем right влево (к меньшим числам)
            right--;
          }
        }
      }
    }
    // Возвращаем итоговый список уникальных четвёрок
    return result;
  }

  /**
   * Оценка сложности
   * Временная: O(n^2)
   * Пространственная: O(log(n))
   */

}
