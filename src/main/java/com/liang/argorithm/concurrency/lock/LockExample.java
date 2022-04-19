package com.liang.argorithm.concurrency.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/04/14 下午6:13
 */
@Slf4j
public class LockExample {

  private static final int threadCount = 5000;

  private static final Semaphore semaphore = new Semaphore(3);

  private static final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  private static int count;

  private final static Lock lock = new ReentrantLock();

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < threadCount; ++i) {
      executorService.execute(() -> {
        try {
          add();
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    System.out.println(count);
    executorService.shutdown();
  }

  private static void add() {
    lock.lock();
    try {
      count++;
    } finally {
      lock.unlock();
    }
  }


}
