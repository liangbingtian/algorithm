package com.liang.argorithm.concurrency.aqs;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程池框架
 *
 * @author liangbingtian
 * @date 2022/04/20 上午10:56
 */
@Slf4j
public class ThreadPoolExample {

  private static final ScheduledExecutorService executorService = Executors
      .newScheduledThreadPool(5);

  private static final ExecutorService executorService2 = Executors.newFixedThreadPool(5);

  public static void main(String[] args) {
    for (int i = 0; i < 10; ++i) {
      final int index = i;
      //延迟1s。每隔3s执行一次任务。
      executorService.scheduleAtFixedRate(() -> {
        log.info("task:{}", index);
      }, 1, 3, TimeUnit.SECONDS);
    }
    for (int i=0;i<10;++i) {
      final int index  = i;
      executorService2.execute(()->{
        log.info("task:{}", index);
      });
    }
//    executorService.shutdown();
//    Timer timer = new Timer();
//    timer.schedule(new TimerTask() {
//      @Override
//      public void run() {
//        log.warn("timer run");
//      }
//    }, new Date(), 5 * 1000);
  }
}
