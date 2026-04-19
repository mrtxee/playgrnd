package org.mrtxee.playgrnd.ti.solutions.leetcode.queue_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 20. Valid Parentheses, easy
 * <a href="https://leetcode.com/problems/valid-parentheses/">link</a>
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if
 * the input string is valid.
 * Input: s = "([])"
 * Output: true
 * Input: s = "([)]"
 * Output: false
 * </p>
 */
public class ValidParentheses {

  public static void main(String[] args) {
    System.out.println("([]) :: " + isValid("([])"));
    System.out.println("([)] :: " + isValid("([)]"));
  }

  /**
   * Скобки закрываются в порядке, обратном открытию (последняя открытая → первая закрытая).
   * Через стэк с LIFO проверяем, что закрывающие скобки приходят в правильном порядке
   */
  public static boolean isValid(String s) {

    // Фиксируем правила закрытия скобок
    final Map<Character, Character> versaBracket = new HashMap<>();
    versaBracket.put('(', ')');
    versaBracket.put('[', ']');
    versaBracket.put('{', '}');

    // Инициализируем стек (LIFO). Ёмкость = длина/2, т.к. максимум половина символов может быть открывающими.
    final Deque<Character> openingBracketsSequence = new ArrayDeque<>(s.length() / 2);


    // Последовательно проходим по каждому символу строки слева направо
    for (int i = 0; i < s.length(); i++) {
      Character curChar = s.charAt(i);
      // Если символ открывающий, его нужно запомнить и позже найти пару
      if (curChar.equals('(') || curChar.equals('[') || curChar.equals('{')) {
        openingBracketsSequence.push(curChar);
        // Если символ закрывающий, сверяем его с последней открытой
      } else if (curChar.equals(')') || curChar.equals(']') || curChar.equals('}')) {
        if (openingBracketsSequence.isEmpty() ||
            !versaBracket.get(openingBracketsSequence.pop()).equals(curChar)) {
          return false;
        }
      } else {
        // Строка содержит недопустимые символы, сразу отвергаем
        return false;

      }
    }

    // Если не пуст, остались незакрытые скобки, метод вернёт false
    return openingBracketsSequence.isEmpty();
  }

  /**
   * Оценка сложности:
   *
   * Пусть N — длина обоих слов, а K - число уникальных символов
   *
   * Time  = O(N)
   * Space = O(K)
   */
}
