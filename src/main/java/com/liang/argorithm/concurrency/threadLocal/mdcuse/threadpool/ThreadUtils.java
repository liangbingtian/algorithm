package com.liang.argorithm.concurrency.threadLocal.mdcuse.threadpool;

import com.liang.argorithm.common.Constants;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * 线程池创建
 *
 * @author liangbingtian
 * @date 2023/03/21 下午5:32
 */
@Slf4j
public class ThreadUtils {

  private static volatile ThreadPoolExecutor executor;


  public static ThreadPoolExecutor getInstance() {
    if (executor == null) {
      synchronized (ThreadUtils.class) {
        if (executor == null) {
          executor = createThreadPool();
        }
      }
    }
    return executor;
  }

  private static ThreadPoolExecutor createThreadPool() {
    log.info("初始化线程池");
    return new MDCThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS);
  }

  public static Runnable wrap(final Runnable runnable, final Map<String, String> threadContext) {
    return () -> {
      if (threadContext == null) {
        MDC.clear();
      } else {
        MDC.setContextMap(threadContext);
      }
      setTraceIdIfAbsent();
      try {
        runnable.run();
      } finally {
        MDC.clear();
      }
    };
  }

  public static <T> Callable<T> wrap(final Callable<T> callable,
      final Map<String, String> threadContext) {
    return () -> {
      if (threadContext == null) {
        MDC.clear();
      } else {
        MDC.setContextMap(threadContext);
      }
      setTraceIdIfAbsent();
      try {
        return callable.call();
      } finally {
        MDC.clear();
      }
    };
  }


  public static void setTraceIdIfAbsent() {
    if (MDC.get(Constants.TRACE_ID) == null) {
      MDC.put(Constants.TRACE_ID, getRandomTraceId());
    }
  }

  /**
   * 生成随机的traceId
   *
   * @return
   */
  public static String getRandomTraceId() {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase();
  }

}
