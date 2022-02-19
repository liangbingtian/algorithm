package com.liang.argorithm.concurrency.atomic;

import com.liang.argorithm.concurrency.annoations.ThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试并发
 *
 * @author liangbingtian
 * @date 2022/02/13 下午5:14
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {

  //同时的请求总数
  public static int clientTotal = 5000;

  //同时并发执行的线程数
  public static int threadTotal = 200;

  //使用下面这种方法用synchronized也可以
//  public static int count2 = 0;
//
//  private synchronized static void add2() {
//    count2++;
//  }

  public static AtomicInteger count = new AtomicInteger();

  private static void add() {
    count.incrementAndGet();
  }

  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newCachedThreadPool();
    final Semaphore semaphore = new Semaphore(threadTotal);
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

    for (int i = 0; i < clientTotal; ++i) {
      executorService.execute(() -> {
        try {
          semaphore.acquire();
          add();
          semaphore.release();
          countDownLatch.countDown();
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      countDownLatch.await();
    }
  }
}
