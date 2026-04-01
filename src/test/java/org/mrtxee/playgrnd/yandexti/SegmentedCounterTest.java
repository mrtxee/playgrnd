package org.mrtxee.playgrnd.yandexti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mrtxee.playgrnd.ti.solutions.yandexti.SegmentedCounter;

@DisplayName("SegmentedCounter Tests")
class SegmentedCounterTest {

  private SegmentedCounter counter;

  @BeforeEach
  void setUp() {
    counter = new SegmentedCounter();
  }

  // ─────────────────────────────────────────────────────────────
  // 1. Базовые тесты
  // ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("Initial value should be 0")
  void testInitialValue() {
    assertEquals(0, counter.getValue());
  }

  @Test
  @DisplayName("Single increment should increase value by 1")
  void testSingleIncrement() {
    counter.increment();
    assertEquals(1, counter.getValue());
  }

  @Test
  @DisplayName("Multiple increments should accumulate correctly")
  void testMultipleIncrements() {
    int increments = 100;
    for (int i = 0; i < increments; i++) {
      counter.increment();
    }
    assertEquals(increments, counter.getValue());
  }

  // ─────────────────────────────────────────────────────────────
  // 2. Тесты на многопоточность
  // ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("Concurrent increments from multiple threads")
  void testConcurrentIncrements() throws InterruptedException {
    int threadCount = 10;
    int incrementsPerThread = 1000;
    int expectedTotal = threadCount * incrementsPerThread;

    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount); // ← Счётчик на 10

    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        try {
          for (int j = 0; j < incrementsPerThread; j++) {
            counter.increment();
          }
        } finally {
          latch.countDown(); // ← Уменьшаем счётчик после завершения
        }
      });
    }

    latch.await(); // ← Ждём, пока все 10 потоков завершат работу
    executor.shutdown();

    assertEquals(expectedTotal, counter.getValue());
  }

  @Test
  @DisplayName("High contention concurrent test")
  void testHighContention() throws InterruptedException {
    int threadCount = 50;
    int incrementsPerThread = 100;
    int expectedTotal = threadCount * incrementsPerThread;

    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      executor.submit(() -> {
        try {
          for (int j = 0; j < incrementsPerThread; j++) {
            counter.increment();
          }
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await();
    executor.shutdown();

    assertEquals(expectedTotal, counter.getValue());
  }

  // ─────────────────────────────────────────────────────────────
  // 3. Тесты на производительность
  // ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("Performance test - should complete in reasonable time")
  void testPerformance() {
    int increments = 1_000_000;

    long startTime = System.currentTimeMillis();

    for (int i = 0; i < increments; i++) {
      counter.increment();
    }

    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;

    assertEquals(increments, counter.getValue());

    // Должно выполниться быстрее 5 секунд
    assertTrue(duration < 5000,
        "Performance test took too long: " + duration + "ms");
  }

  // ─────────────────────────────────────────────────────────────
  // 4. Тесты на корректность сегментов
  // ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("All segments should be initialized to 0")
  void testSegmentsInitialization() {
    // Проверяем, что начальное значение 0
    assertEquals(0, counter.getValue());

    // После инкрементов сумма должна быть корректной
    counter.increment();
    counter.increment();
    counter.increment();

    assertEquals(3, counter.getValue());
  }

  @Test
  @DisplayName("getValue should be thread-safe read")
  void testConcurrentReads() throws InterruptedException {
    int increments = 10000;

    ExecutorService executor = Executors.newFixedThreadPool(10);
    CountDownLatch latch = new CountDownLatch(1);

    // Один поток пишет
    executor.submit(() -> {
      try {
        latch.await();
        for (int i = 0; i < increments; i++) {
          counter.increment();
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    });

    // Несколько потоков читают
    for (int i = 0; i < 5; i++) {
      executor.submit(() -> {
        try {
          latch.await();
          while (counter.getValue() <= increments) {
            // Чтение не должно вызывать исключений
            long value = counter.getValue();
            assertTrue(value >= 0 && value <= increments);
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      });
    }

    latch.countDown();
    Thread.sleep(100);
    executor.shutdown();
  }

  // ─────────────────────────────────────────────────────────────
  // 6. Тест на повторяемость
  // ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("Multiple test runs should be consistent")
  void testConsistency() {
    for (int run = 0; run < 5; run++) {
      SegmentedCounter testCounter = new SegmentedCounter();

      for (int i = 0; i < 1000; i++) {
        testCounter.increment();
      }

      assertEquals(1000, testCounter.getValue(),
          "Run " + run + " failed");
    }
  }
}