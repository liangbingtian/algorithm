package com.liang.argorithm.concurrency.commonUnsafe;

import com.liang.argorithm.concurrency.annoations.NotThreadSafe;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/02/20 下午6:41
 */
@Slf4j
@NotThreadSafe
public class DateFormatExample {

  //多线程共享使用SimpleDateFormat的时候，会出现异常。
  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

  //同时请求的线程总数
  private static final int clientTotal = 5000;

  //最大可同时并发处理的线程数
  private static final int threadTotal = 200;


  private static void update() {
    try {
      simpleDateFormat.parse("20180208");
    } catch (ParseException e) {
      log.error("parse exception");
    }

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
  }

}
