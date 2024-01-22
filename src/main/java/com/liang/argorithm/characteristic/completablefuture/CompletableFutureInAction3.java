package com.liang.argorithm.characteristic.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author liangbingtian
 * @date 2023/10/31 下午11:44
 */
public class CompletableFutureInAction3 {

  public static void main(String[] args) {
    final ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
      Thread thread = new Thread(r);
      thread.setDaemon(false);
      return thread;
    });
    CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executorService)
        .thenApply(CompletableFutureInAction3::multiply)
        .whenComplete((v, t)->{
          Optional.ofNullable(v).ifPresent(System.out::println);
          Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
        });

    //多任务执行
    final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    final List<Double> collect = list.stream()
        .map(i -> CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executorService))
        .map(i -> i.thenApply(CompletableFutureInAction3::multiply))
        .map(CompletableFuture::join).collect(Collectors.toList());
    System.out.println(collect);
  }

  private static double multiply(double d) {
    try {
      Thread.sleep(10000);
      return d*100;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 0d;
  }

}
