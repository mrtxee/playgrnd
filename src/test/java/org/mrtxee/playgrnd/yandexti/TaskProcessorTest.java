package org.mrtxee.playgrnd.yandexti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class TaskProcessorTest {

  private TaskProcessor taskProcessor;

  @BeforeEach
  void setUp() {
    taskProcessor = new TaskProcessor();
  }

  @AfterEach
  void tearDown() {
    taskProcessor.shutdown();
  }

  @Test
  void testSuccessfulTaskProcessing() throws Exception {
    // Given
    Callable<String> successfulTask = () -> "result";

    // When
    String result = taskProcessor.processTask(successfulTask);

    // Then
    assertEquals("result", result);
    TaskProcessor.Stats stats = taskProcessor.getStats();
    assertEquals(1, stats.getTotal());
    assertEquals(1, stats.getSuccess());
    assertEquals(0, stats.getFailure());
  }

  @Test
  void testTaskTimeout() {
    // Given
    Callable<String> slowTask = () -> {
      Thread.sleep(6000); // больше 5 секунд
      return "should not reach here";
    };

    // When & Then
    assertThrows(TimeoutException.class, () -> taskProcessor.processTask(slowTask));

    TaskProcessor.Stats stats = taskProcessor.getStats();
    assertEquals(1, stats.getTotal());
    assertEquals(0, stats.getSuccess());
    assertEquals(1, stats.getFailure());
  }

  @Test
  void testTaskExecutionException() {
    // Given
    Callable<String> failingTask = () -> {
      throw new RuntimeException("Task failed");
    };

    // When & Then
    assertThrows(ExecutionException.class, () -> taskProcessor.processTask(failingTask));

    TaskProcessor.Stats stats = taskProcessor.getStats();
    assertEquals(1, stats.getTotal());
    assertEquals(0, stats.getSuccess());
    assertEquals(1, stats.getFailure());
  }

  @Test
  void testMultipleTasksProcessing() throws Exception {
    // Given
    Callable<Integer> task1 = () -> 42;
    Callable<String> task2 = () -> "hello";
    Callable<Double> task3 = () -> 3.14;

    // When
    Integer result1 = taskProcessor.processTask(task1);
    String result2 = taskProcessor.processTask(task2);
    Double result3 = taskProcessor.processTask(task3);

    // Then
    assertEquals(42, result1);
    assertEquals("hello", result2);
    assertEquals(3.14, result3);

    TaskProcessor.Stats stats = taskProcessor.getStats();
    assertEquals(3, stats.getTotal());
    assertEquals(3, stats.getSuccess());
    assertEquals(0, stats.getFailure());
  }

  @Test
  void testConcurrentTaskProcessing() throws Exception {
    // Given
    int taskCount = 20;
    List<Callable<Integer>> tasks = new ArrayList<>();
    for (int i = 0; i < taskCount; i++) {
      final int finalI = i;
      tasks.add(() -> {
        Thread.sleep(100); // имитация работы
        return finalI * 2;
      });
    }

    // When: запускаем все задачи параллельно
    List<Future<Integer>> futures = new ArrayList<>();
    for (Callable<Integer> task : tasks) {
      futures.add(Executors.newSingleThreadExecutor().submit(() ->
          taskProcessor.processTask(task)
      ));
    }

    // Then: проверяем результаты
    for (int i = 0; i < taskCount; i++) {
      Integer result = futures.get(i).get();
      assertEquals(i * 2, result);
    }

    TaskProcessor.Stats stats = taskProcessor.getStats();
    assertEquals(taskCount, stats.getTotal());
    assertEquals(taskCount, stats.getSuccess());
    assertEquals(0, stats.getFailure());
  }

  @Test
  void testStatisticsConsistencyAfterShutdown() throws Exception {
    // Given: выполняем несколько задач
    Callable<String> task1 = () -> "first";
    Callable<String> task2 = () -> "second";

    taskProcessor.processTask(task1);
    taskProcessor.processTask(task2);

    // When: останавливаем процессор
    taskProcessor.shutdown();

    // Then: статистика должна остаться неизменной
    TaskProcessor.Stats stats = taskProcessor.getStats();
    assertEquals(2, stats.getTotal());
    assertEquals(2, stats.getSuccess());
    assertEquals(0, stats.getFailure());
  }

  @Test
  void testGetStatsReturnsImmutableSnapshot() throws Exception {
    // Given
    Callable<String> task = () -> "test";

    // When
    TaskProcessor.Stats before = taskProcessor.getStats();
    taskProcessor.processTask(task);
    TaskProcessor.Stats after = taskProcessor.getStats();

    // Then: снимки должны отличаться
    assertNotEquals(before.getTotal(), after.getTotal());
    assertEquals(0, before.getTotal());
    assertEquals(1, after.getTotal());
  }

  @Test
  void testShutdownDoesNotAffectExistingStats() throws Exception {
    // Given
    taskProcessor.processTask(() -> "test");
    TaskProcessor.Stats initialStats = taskProcessor.getStats();

    // When
    taskProcessor.shutdown();

    // Then: статистика не должна измениться после shutdown
    TaskProcessor.Stats finalStats = taskProcessor.getStats();
    assertEquals(initialStats.getTotal(), finalStats.getTotal());
    assertEquals(initialStats.getSuccess(), finalStats.getSuccess());
    assertEquals(initialStats.getFailure(), finalStats.getFailure());
  }
}