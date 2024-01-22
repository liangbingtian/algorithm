package com.liang.argorithm.characteristic.futuretest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author liangbingtian
 * @date 2023/10/31 下午1:53
 */
public class JDKFutureAction {

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, TimeoutException {
    final ExecutorService executorService = Executors.newSingleThreadExecutor();
    final Future<String> submit = executorService.submit(() -> {
      try {
        Thread.sleep(10000);
        return "success";
      } catch (InterruptedException e) {
        return "error";
      }
    });
    //阻塞直到返回结果
    final String s = submit.get(10, TimeUnit.SECONDS);
    executorService.shutdown();
  }

}
