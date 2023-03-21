package com.liang.argorithm.concurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import lombok.extern.slf4j.Slf4j;

/**
 * 目标任务
 *
 * @author liangbingtian
 * @date 2022/04/19 下午5:32
 */
@Slf4j
public class FutureTaskExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    FutureTask<String> futureTask = new FutureTask<>(() -> {
      log.info("do something in callable");
      Thread.sleep(5000);
      return "OK";
    });

    new Thread(futureTask).start();
    log.info("do something in main");
    Thread.sleep(1000);
    String result = futureTask.get();
    log.info("result:{}", result);
  }
}
