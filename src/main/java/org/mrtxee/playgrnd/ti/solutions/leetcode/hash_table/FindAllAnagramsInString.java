package org.mrtxee.playgrnd.ti.solutions.leetcode.hash_table;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 * 438. Find All Anagrams in a String, medium *
 * <p>
 * Найти все стартовые индексы подстрок в строке s, которые являются анаграммами строки p.
 * Анаграмма — это перестановка букв, то есть строки содержат одинаковые символы с одинаковой
 * частотой, но в любом порядке.
 * <p>
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
public class FindAllAnagramsInString {

  public static void main(String[] args) {

    System.out.println('c' - 'a');
    System.out.println(findAnagrams("cbaebabacd", "abc"));

  }

  public static List<Integer> findAnagrams(String s, String p) {
    // Создаём частотный массив для строки p (26 букв английского алфавита)
    // Индекс 0 = 'a', 1 = 'b', ..., 25 = 'z'. Значение = количество вхождений буквы.
    int[] pmap = new int[26];
    // Создаём частотный массив для текущего "окна" в строке s той же длины, что и p
    int[] smap = new int[26];
    // Список для хранения стартовых индексов найденных анаграмм
    List<Integer> result = new ArrayList<>();

    // Проходим по всей строке s, расширяя окно по одному символу
    for (int i = 0; i < p.length(); i++) {
      // Добавляем текущий символ строки s в частотную карту окна
      // используем char 'a' как основу расчета частотных карт, исходя из того, что
      //  - все следующие буквы алавита в имеют последовательный (+1) растущий
      //  индекс в алфавитном порядке, т.е. 'b'-'a'=1, 'c'-'a'=2,...
      // ограничение: только строчные буквы
      pmap[p.charAt(i) - 'a']++;
    }

    // Проходим по всей строке s, расширяя окно по одному символу
    for (int i = 0; i < s.length(); i++) {
      // Добавляем текущий символ строки s в частотную карту окна
      smap[s.charAt(i) - 'a']++;

      // Если окно стало больше, чем длина p, удаляем самый левый (старый) символ
      // Это нужно, чтобы поддерживать фиксированный размер окна = p.length()
      if (i >= p.length()) {
        // Вычисляем индекс символа, который выходит за левую границу окна
        int index = i - p.length();
        // Уменьшаем счётчик этого символа в частотной карте окна
        smap[s.charAt(index) - 'a']--;
      }

      // Начинаем проверять на анаграмму только когда окно набрало полный размер
      // Условие: обработано минимум p.length() символов (индексация с 0)
      if (i >= p.length() - 1) {
        // Сравниваем частотные массивы: если они идентичны — буквы в окне и в p совпадают
        if (Arrays.equals(pmap, smap)) {
          // Вычисляем стартовый индекс текущего окна:
          // текущий индекс - длина шаблона + 1 (потому что индекс включительный)
          // Пример: i=2, p.length()=3 → start = 2-3+1 = 0 ✓
          int start = i - p.length() + 1;
          // Добавляем найденный индекс в результат
          result.add(start);
        }
      }
    }
    return result;
  }

  /**
   * Оценка сложности алгоритма
   * Время: O(n)
   * Память: O(с)
   */

}
