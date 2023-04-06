package com.liang.argorithm.concurrency.threadLocal.mdcuse.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

/**
 * MDC线程池
 *
 * @author liangbingtian
 * @date 2023/03/21 下午5:14
 */
@Slf4j
public class MDCThreadPoolExecutor extends ThreadPoolExecutor {

  private static final String THREAD_NAME_PREFIX = "employee_thread";

  public MDCThreadPoolExecutor(int corePoolSize,
      int maximumPoolSize,
      long keepAliveTime,
      TimeUnit unit) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>(32),
        new EmployeeThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
  }

  @Override
  public void execute(Runnable command) {
    super.execute(ThreadUtils.wrap(command, MDC.getCopyOfContextMap()));
  }

  @NotNull
  @Override
  public Future<?> submit(Runnable task) {
    return super.submit(ThreadUtils.wrap(task, MDC.getCopyOfContextMap()));
  }

  @NotNull
  @Override
  public <T> Future<T> submit(Runnable task, T result) {
    return super.submit(ThreadUtils.wrap(task, MDC.getCopyOfContextMap()), result);
  }

  @NotNull
  @Override
  public <T> Future<T> submit(Callable<T> task) {
    return super.submit(ThreadUtils.wrap(task, MDC.getCopyOfContextMap()));
  }

  private static class EmployeeThreadFactory implements ThreadFactory {

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(@NotNull Runnable r) {
      Thread thread = new Thread(r);
      String threadName = THREAD_NAME_PREFIX + count.addAndGet(1);
      thread.setName(threadName);
      return thread;
    }
  }
}
