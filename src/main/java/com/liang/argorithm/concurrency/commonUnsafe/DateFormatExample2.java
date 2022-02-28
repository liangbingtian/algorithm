package com.liang.argorithm.concurrency.commonUnsafe;

import com.liang.argorithm.concurrency.annoations.NotThreadSafe;
import com.liang.argorithm.concurrency.annoations.ThreadSafe;
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
@ThreadSafe
public class DateFormatExample2 {


  //同时请求的线程总数
  private static final int clientTotal = 5000;

  //最大可同时并发处理的线程数
  private static final int threadTotal = 200;

  //使用堆栈封闭的方式，声明局部变量的话，就能成功，这样属于线程封闭
  private static void update() {
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
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
