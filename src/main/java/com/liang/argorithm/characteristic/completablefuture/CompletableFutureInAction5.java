package com.liang.argorithm.characteristic.completablefuture;

import cn.hutool.core.thread.ThreadUtil;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2023/11/02 下午2:14
 */
@Slf4j
public class CompletableFutureInAction5 {

  public static void i() {
    Integer i = null;
    try {
      Preconditions.checkArgument(i != null, "值为空");
    }catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static void main(String[] args) {

    final ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(4, 4);

    final CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
        i();
        System.out.println("future1执行到了这一步");
    });

    final CompletableFuture<Object> future2 = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1;
    }, threadPoolExecutor);

    CompletableFuture.allOf(future1, future2).join();
//    log.info("线程池任务开始执行");
    threadPoolExecutor.shutdown();
  }



}
