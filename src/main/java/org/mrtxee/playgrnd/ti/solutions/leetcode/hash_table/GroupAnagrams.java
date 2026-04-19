package org.mrtxee.playgrnd.ti.solutions.leetcode.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams, medium
 * <a href="https://leetcode.com/problems/group-anagrams/description/">link</a>
 * <p>
 * Сгруппировать все слова из входного массива так, чтобы в одном подсписке оказались анаграммы
 * друг друга. Анаграммы — это слова, состоящие из одинаковых букв, но в разном порядке.
 * </p>
 */
public class GroupAnagrams {

  public static void main(String[] args) {
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(groupAnagrams(strs));
  }

  /**
   * Алгоритм использует сортировку отсортированное представление анаграммы как ключ мапы.
   * Все анаграммы становятся одинаковым словом, что позволяет распределять их по группам в HashMap.
   */
  public static List<List<String>> groupAnagrams(String[] strs) {
    // Создаём хэш-таблицу: ключ = отсортированная форма слова,
    // значение = список исходных анаграмм
    Map<String, List<String>> groupedAnagrams = new HashMap<>();

    // Перебираем каждое слово из входного массива по одному
    for (String word : strs) {
      // Преобразуем строку в массив символов, для возможности сортировки
      char[] chars = word.toCharArray();
      // Сортируем символы по возрастанию, все анаграммы станут идентичными массивами
      Arrays.sort(chars);
      // Преобразуем отсортированный массива к строке для использования в качестве ключа мапы
      String sortedWord = new String(chars);

      // Если группы нет, создаём её, пустой ArrayList и исходное слово в список
      groupedAnagrams.computeIfAbsent(sortedWord, s -> new ArrayList<>()).add(word);

    }

    System.out.println(groupedAnagrams);

    // Извлекаем все значения мапы (списки анаграмм) и оборачиваем их в новый ArrayList, так как groupedAnagrams.values() возвращает коллекцию
    return new ArrayList<>(groupedAnagrams.values());
  }

  /**
   * Оценка сложности:
   *
   * Пусть N — количество строк в массиве, K — максимальная длина одной строки.
   *
   * Time Complexity	O(N * K log K)
   *    -  Для каждой из N строк
   *        -  выполняется сортировка символов длиной K :: O(K log K)
   *        -  Операции с HashMap (поиск/вставка) занимают в среднем :: O(K)
   * Space Complexity	O(N * K)
   */
}
