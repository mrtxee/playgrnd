package org.mrtxee.playgrnd.ti.solutions.leetcode.sliding_window;

/**
 * 424. Longest Repeating Character Replacement, middle
 * <a href="https://leetcode.com/problems/longest-repeating-character-replacement/">link</a>
 * <p>
 * Найти максимальную длину подстроки, которую можно превратить в строку из одинаковых символов,
 * заменив не более k символов.
 * </p>
 */
public class LongestRepeatingCharacterReplacement {

  public static void main(String[] args) {

  }

  public static int characterReplacement(String s, int k) {
    // Частотный массив для 26 заглавных латинских букв (0 = 'A', 1 = 'B' ...)
    int[] freq = new int[26];
    // Левый указатель скользящего окна
    int left = 0;
    // Хранит максимальную частоту ОДНОГО символа внутри текущего окна
    int maxFreq = 0;
    // Хранит длину самой длинной валидной подстроки, найденной на данный момент
    int maxLen = 0;

    // Правый указатель последовательно расширяет окно до конца строки
    for (int right = 0; right < s.length(); right++) {
      // Преобразуем символ в индекс 0-25 и увеличиваем его счётчик в текущем окне
      int charIndex = s.charAt(right) - 'A';
      freq[charIndex]++;
      // Обновляем макс. частоту: нам важен только самый частый символ в окне
      maxFreq = Math.max(maxFreq, freq[charIndex]);

      // Проверяем, превышает ли необходимое количество замен лимит k:
      // (right - left + 1) — текущая длина окна
      // maxFreq — количество символов, которые УЖЕ совпадают (их менять не надо)
      // Разница = количество замен, нужных для превращения окна в однобуквенное
      if ((right - left + 1) - maxFreq > k) {
        // Замен не хватает: "сжимаем" окно слева, убирая символ из учёта
        freq[s.charAt(left) - 'A']--;
        // Сдвигаем левую границу вправо на 1 позицию
        left++;
      }

      // Обновляем глобальный максимум длины валидного окна
      // (Фактически окно либо растёт, либо сохраняет свой рекордный размер)
      maxLen = Math.max(maxLen, right - left + 1);
    }

    // Возвращаем длину самой длинной подстроки, которую можно сделать однородной за ≤ k замен
    return maxLen;
  }

}
