package org.mrtxee.playgrnd.yandexti;

/**
 * Поиск цикла в связном списке
 * Условие: Дан односвязный список. Определите, есть ли в нём цикл. Если есть — найдите начало
 * цикла. Алгоритм должен работать за O(n) времени и O(1) памяти.
 */
public class CycleDetection {

  public ListNode detectCycle(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;

    // Шаг 1: находим точку встречи (если есть цикл)
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      // Если slow == fast — цикл есть (встреча внутри цикла).
      if (slow == fast) {
        break;
      }
    }

    // Если fast достиг конца — цикла нет
    if (fast == null || fast.next == null) {
      return null;
    }

    // Шаг 2: находим начало цикла
    // Расстояние от head до начала цикла математически равно расстоянию от точки встречи до
    // начала цикла (по ходу движения).
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow; // начало цикла
  }

  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}

