package com.liang.argorithm.concurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试Future
 *
 * @author liangbingtian
 * @date 2022/04/19 下午5:19
 */
@Slf4j
public class FutureExample {

  static class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
      log.info("do something in callable");
      Thread.sleep(5000);
      return "OK";
    }
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<String> future = executorService.submit(new MyCallable());
    log.info("do something in main");
    Thread.sleep(1000);
    //如果方法执行没有结束的话，则会一直阻塞在获取结果那块
    String result = future.get();
    log.info("result:{}", result);
  }

}
