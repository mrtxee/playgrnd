package org.mrtxee.playgrnd.ti.solutions.leetcode.hash_table;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram, easy
 * <a href="https://leetcode.com/problems/valid-anagram/">link</a>
 * <p>
 * Проверить, являются ли две строки анаграммами (содержат ли они ровно одинаковый набор символов
 * с одинаковой частотой).
 * </p>
 */
public class ValidAnagram {

  public static void main(String[] args) {
    String s = "anagramВ!", t = "nagaraВm;";
    System.out.println(format("isAnagram: %s. For words: %s, %s.", isAnagram(s, t), s, t));
  }

  /**
   * Используем массив фиксированного размера как частотную таблицу вхождений символов.
   * Символы из первой строки добавляют +1, из второй -1. Если в итоге все ячейки равны 0, строки
   * сбалансированы и являются анаграммами.
   */
  public static boolean isAnagram(String s, String t) {
    // Если длины строк различаются, они не могут содержать одинаковый набор символов
    if (s.length() != t.length()) {
      return false;
    }

    final Map<Character, Integer> frequencies = new HashMap<>();

    // Проходим по обеим строкам одновременно, так как их длины гарантированно равны
    for (int i = 0; i < s.length(); i++) {

      // инкрементируем по ключу из первой строки
      char incrementChar = s.charAt(i);
      int incrementCharFrequency = frequencies.getOrDefault(incrementChar, 0);
      frequencies.put(incrementChar, ++incrementCharFrequency);

      // декрементируем по ключу из первой строки
      char decrementChar = t.charAt(i);
      int decrementCharFrequency = frequencies.getOrDefault(decrementChar, 0);
      frequencies.put(decrementChar, --decrementCharFrequency);
    }

    // если хотя бы 1 частота отличается от 0, значит не анаграммы
    for (int indicator : frequencies.values()) {
      if (indicator != 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * Оценка сложности:
   *
   * Пусть N — длина обоих слов, а K - число уникальных символов
   *
   * Time  = O(N + K)
   * Space = O(K)
   */
}
