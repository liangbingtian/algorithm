package com.liang.argorithm.concurrency.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/03/15 上午9:46
 */
@Slf4j
public class CountDownLatchExample {

  private static final int threadCount = 200;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    for (int i=0;i<threadCount;++i) {
      final int threadNum = i;
      executorService.execute(()->{
        try {
          test(threadNum);
        } catch (Exception e) {
          log.error("exception", e);
        } finally {
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await(10, TimeUnit.MILLISECONDS);
    log.info("finish");
    executorService.shutdown();
  }

  private static void test(int threadNum) throws Exception{
    Thread.sleep(100);
    log.info("{}", threadNum);
  }

}
