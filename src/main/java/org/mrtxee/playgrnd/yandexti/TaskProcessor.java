package org.mrtxee.playgrnd.yandexti;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;

public class TaskProcessor {
  private final ExecutorService executor;
  private final AtomicInteger totalTasks = new AtomicInteger();
  private final AtomicInteger successCount = new AtomicInteger();
  private final AtomicInteger failureCount = new AtomicInteger();

  public TaskProcessor() {
    this.executor = Executors.newFixedThreadPool(10);
  }

  public <T> T processTask(Callable<T> task)
      throws InterruptedException, ExecutionException, TimeoutException {
    totalTasks.incrementAndGet();
    Future<T> future = executor.submit(task);
    try {
      T result = future.get(5, TimeUnit.SECONDS);
      successCount.incrementAndGet();
      return result;
    } catch (TimeoutException | ExecutionException e) {
      failureCount.incrementAndGet();
      throw e;
    }
  }

  public Stats getStats() {
    return new Stats(totalTasks.get(), successCount.get(), failureCount.get());
  }

  public void shutdown() {
    executor.shutdown();
  }

  @Data
  public static class Stats {
    private final int total;
    private final int success;
    private final int failure;

    public Stats(int total, int success, int failure) {
      this.total = total;
      this.success = success;
      this.failure = failure;
    }

    @Override
    public String toString() {
      return String.format("Total: %d, Success: %d, Failure: %d", total, success, failure);
    }
  }
}
