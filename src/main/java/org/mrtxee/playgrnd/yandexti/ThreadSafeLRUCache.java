package org.mrtxee.playgrnd.yandexti;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeLRUCache<K, V> {
  private final int capacity;
  private final Map<K, V> cache;
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  /**
   * Напишите потокобезопасную реализацию LRU‑кэша (Least Recently Used) с фиксированным размером.
   * Операции:
   *  - put(key, value) — добавить/обновить элемент;
   *  - get(key) — получить значение по ключу (или null, если нет);
   *  - при превышении размера удаляется наименее используемый элемент.
   */

  public ThreadSafeLRUCache(int capacity) {
    this.capacity = capacity;

    /*
      LinkedHashMap — оптимальный выбор для LRU‑кэша, т.к.:
      - Сохраняет порядок элементов
      - при каждом обращении через get() элементм перемещается в конец списка, что важно для LRU
      - Обеспечивает O(1) для базовых операций
     */
    this.cache = new LinkedHashMap<K, V>(16, 0.75f, true) {

      /**
       * Метод removeEldestEntry(Map.Entry < K, V> eldest) — механизм LinkedHashMap, позволяющий
       * автоматически удалять самый старый элемент при выполнении заданного условия.
       */
      @Override
      protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > ThreadSafeLRUCache.this.capacity;
      }
    };
  }

  public V get(K key) {
    lock.readLock().lock();
    try {
      return cache.get(key);
    } finally {
      lock.readLock().unlock();
    }
  }

  public void put(K key, V value) {
    lock.writeLock().lock();
    try {
      cache.put(key, value);
    } finally {
      lock.writeLock().unlock();
    }
  }
}
