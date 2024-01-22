package com.liang.argorithm.characteristic.completablefuture;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author liangbingtian
 * @date 2023/10/31 下午2:14
 */
public class CompletableFutureInAction1 {

  public static final Random random = new Random(System.currentTimeMillis());

  public static void main(String[] args) {
    final CompletableFuture<Double> future = new CompletableFuture<>();
    new Thread(()->{
      final double v = get();
      future.complete(v);
    }).start();
    System.out.println("123");
    //没有显示的去拿，通过流式的方式去拿，但是主线程不会结束。
    future.whenComplete((v, t)->{
      Optional.ofNullable(v).ifPresent(System.out::println);
      Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
    });
  }

  static double get() {
    try {
      Thread.sleep(random.nextInt());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return random.nextDouble();
  }

}
