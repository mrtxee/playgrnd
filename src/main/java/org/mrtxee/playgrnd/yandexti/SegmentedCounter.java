package org.mrtxee.playgrnd.yandexti;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * Условие: Реализуйте потокобезопасный счётчик, который минимизирует конкуренцию между потоками.
 * Используйте сегментирование: вместо одного атомарного счётчика — массив из 16 счётчиков. При
 * инкременте выбирается случайный сегмент.
 */
public class SegmentedCounter {
  private static final int SEGMENTS = 16;
  private final AtomicLongArray segments = new AtomicLongArray(SEGMENTS);

  public SegmentedCounter() {
    for (int i = 0; i < SEGMENTS; i++) {
      segments.set(i, 0);
    }
  }

  public void increment() {
    int segment = ThreadLocalRandom.current().nextInt(SEGMENTS);
    segments.incrementAndGet(segment);
  }

  public long getValue() {
    long sum = 0;
    for (int i = 0; i < SEGMENTS; i++) {
      sum += segments.get(i);
    }
    return sum;
  }
}
