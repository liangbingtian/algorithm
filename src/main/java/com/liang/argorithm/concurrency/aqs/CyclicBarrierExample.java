package com.liang.argorithm.concurrency.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * 循环屏障测试类
 *
 * @author liangbingtian
 * @date 2022/03/22 下午4:05
 */
@Slf4j
public class CyclicBarrierExample {

  private static final ExecutorService executor = Executors.newCachedThreadPool();

  private static CyclicBarrier barrier = new CyclicBarrier(5);

  public static void main(String[] args) throws InterruptedException {
    for (int i=0;i<10;++i) {
      final int threadNum = i;
      Thread.sleep(1000);
      executor.execute(()->{
        try {
          race(threadNum);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    }
  }

  private static void race(int threadNum) throws Exception {
    Thread.sleep(1000);
    log.info("{} is ready", threadNum);
    barrier.await();
    log.info("{} is wait", threadNum);
  }
}
