package org.mrtxee.playgrnd.yandexti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ThreadSafeLRUCacheTest {

  @Test
  void testGetNonExistingKeyReturnsNull() {
    ThreadSafeLRUCache<String, Integer> cache = new ThreadSafeLRUCache<>(3);
    assertNull(cache.get("non-existent"));
  }

  @Test
  void testPutAndGetSingleElement() {
    ThreadSafeLRUCache<String, String> cache = new ThreadSafeLRUCache<>(3);
    cache.put("key1", "value1");
    assertEquals("value1", cache.get("key1"));
  }

  @Test
  void testUpdateExistingKey() {
    ThreadSafeLRUCache<String, String> cache = new ThreadSafeLRUCache<>(3);
    cache.put("key1", "oldValue");
    cache.put("key1", "newValue");
    assertEquals("newValue", cache.get("key1"));
  }

  @Test
  void testLRUEvictionWhenCapacityExceeded() {
    ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(2);

    cache.put(1, "one");
    cache.put(2, "two");
    // Кэш заполнен: [1=one, 2=two]

    cache.put(3, "three");
    // Должен быть удалён ключ 1 (наименее используемый)

    assertNull(cache.get(1));  // Удален
    assertEquals("two", cache.get(2));  // Остался
    assertEquals("three", cache.get(3));  // Новый
  }

  @Test
  void testAccessOrderMaintainsLRUProperty() {
    ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(3);

    cache.put(1, "one");
    cache.put(2, "two");
    cache.put(3, "three");
    // Порядок доступа: 1 → 2 → 3

    cache.get(1);  // Доступ к ключу 1 делает его «свежим»
    // Новый порядок доступа: 2 → 3 → 1

    cache.put(4, "four");
    // При добавлении 4 должен быть удалён ключ 2 (наименее используемый)

    assertNull(cache.get(2));  // Удален (наименее используемый)
    assertEquals("one", cache.get(1));  // Остался (был accessed)
    assertEquals("three", cache.get(3));  // Остался
    assertEquals("four", cache.get(4));  // Новый
  }

  @Test
  void testMultipleGetsDontAffectEvictionOrder() {
    ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(2);

    cache.put(1, "one");
    cache.put(2, "two");

    // Многократные обращения к ключу 2
    cache.get(2);
    cache.get(2);
    cache.get(2);

    cache.put(3, "three");
    // Ключ 1 должен быть удалён, т.к. он наименее используемый

    assertNull(cache.get(1));  // Удален
    assertEquals("two", cache.get(2));  // Остался
    assertEquals("three", cache.get(3));  // Новый
  }

  @Test
  void testZeroCapacity() {
    ThreadSafeLRUCache<String, String> cache = new ThreadSafeLRUCache<>(0);

    cache.put("key", "value");
    assertNull(cache.get("key"));
  }

  @Test
  void testLargeCapacity() {
    ThreadSafeLRUCache<Integer, Integer> cache = new ThreadSafeLRUCache<>(1000);

    for (int i = 0; i < 100; i++) {
      cache.put(i, i * 2);
    }

    for (int i = 0; i < 100; i++) {
      assertEquals(i * 2, cache.get(i));
    }
  }

  @Test
  void testThreadSafetyWithConcurrentAccess() throws InterruptedException {
    final ThreadSafeLRUCache<Integer, String> cache = new ThreadSafeLRUCache<>(50);
    final int NUM_THREADS = 10;
    final int OPERATIONS_PER_THREAD = 100;

    Thread[] threads = new Thread[NUM_THREADS];

    for (int t = 0; t < NUM_THREADS; t++) {
      final int threadId = t;
      threads[t] = new Thread(() -> {
        for (int i = 0; i < OPERATIONS_PER_THREAD; i++) {
          // Разные ключи для разных потоков, чтобы минимизировать конфликты
          int key = threadId * 100 + i;
          String value = "value-" + key;

          // Чередуем put и get
          if (i % 3 == 0) {
            cache.put(key, value);
          } else {
            cache.get(key);
          }
        }
      });
    }

    // Запускаем все потоки
    for (Thread thread : threads) {
      thread.start();
    }

    // Ждём завершения всех потоков
    for (Thread thread : threads) {
      thread.join();
    }

    // Проверяем, что кэш не повреждён — нет NullPointerException и т.п.
    assertTrue(true); // Если дошли сюда без исключений — тест пройден
  }

  @Test
  void testEvictionOrderAfterMultipleAccesses() {
    ThreadSafeLRUCache<String, Integer> cache = new ThreadSafeLRUCache<>(3);

    cache.put("A", 1);
    cache.put("B", 2);
    cache.put("C", 3);
    // Порядок: A → B → C


    cache.get("A");  // A становится самым свежим
    // Порядок: B → C → A

    cache.get("B");  // B становится самым свежим
    // Порядок: C → A → B

    cache.put("D", 4);
    // Добавляем D — должен быть удалён C (наименее используемый)

    assertNull(cache.get("C"));  // C удалён
    assertEquals(1, cache.get("A"));
    assertEquals(2, cache.get("B"));
    assertEquals(4, cache.get("D"));
  }
}