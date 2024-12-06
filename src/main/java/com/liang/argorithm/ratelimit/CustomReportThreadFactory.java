package com.liang.argorithm.ratelimit;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangbingtian
 * @date 2023/11/28 上午10:44
 */
public class CustomReportThreadFactory implements ThreadFactory {

  private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
  private final ThreadGroup group;
  private final AtomicInteger threadNumber = new AtomicInteger(1);
  private final String namePrefix;

  private CustomReportThreadFactory(String poolNamePrefix) {
    SecurityManager s = System.getSecurityManager();
    group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
    namePrefix = poolNamePrefix + POOL_NUMBER.getAndIncrement() + "-";
  }
  public static ThreadFactory newFactory(String poolName) {
    return new CustomReportThreadFactory(poolName);
  }

  @Override
  public Thread newThread(Runnable r) {
    Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
    if (t.isDaemon()) {
      t.setDaemon(false);
    }
    if (t.getPriority() != Thread.NORM_PRIORITY) {
      t.setPriority(Thread.NORM_PRIORITY);
    }
    return t;
  }
}
