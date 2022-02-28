package com.liang.argorithm.concurrency.commonUnsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/02/20 下午4:55
 */
@Slf4j
public class StringExample1 {

  //同时请求的线程总数
  private static final int clientTotal = 5000;

  //最大可同时并发处理的线程数
  private static final int threadTotal = 200;

  public static StringBuffer stringBuffer = new StringBuffer();

  private static void update() {
    stringBuffer.append("1");
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    final Semaphore semaphore = new Semaphore(threadTotal);
    final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
    for (int i = 0; i < clientTotal; ++i) {
      executorService.execute(() -> {
        try {
          semaphore.acquire();
          //执行操作
          update();
          semaphore.release();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        countDownLatch.countDown();
      });
    }
    countDownLatch.await();
    executorService.shutdown();
    log.info("stringBuilder.size = {}", stringBuffer.length());
  }

}
