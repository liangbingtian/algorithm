package com.liang.argorithm.concurrency.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/02/17 上午10:27
 */
@Slf4j
public class SynchronizedExample1 {

  //修饰一个代码块
  public void test1(int j) {
    synchronized (this) {
      for (int i = 0; i < 10; ++i) {
        log.info("test1 {} - {}", j, i);
      }
    }
  }

  //修饰一个方法.
  public synchronized void test2(int j) {
    for (int i = 0; i < 10; ++i) {
      log.info("test2 {} - {}", j, i);
    }
  }

  //修饰一个类
  public void test3(int j) {
    synchronized (SynchronizedExample1.class) {
      for (int i = 0; i < 10; ++i) {
        log.info("test3 {}-{}", j, i);
      }
    }
  }

  //修饰一个静态方法
  public synchronized static void test4(int j) {
    for (int i = 0; i < 10; ++i) {
      log.info("test2 {} - {}", j, i);
    }
  }

  public static void main(String[] args) {
    SynchronizedExample1 example1 = new SynchronizedExample1();
    SynchronizedExample1 example2 = new SynchronizedExample1();
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> {
      example1.test4(1);
    });
    executorService.execute(() -> {
      example2.test4(2);
    });
  }

}
