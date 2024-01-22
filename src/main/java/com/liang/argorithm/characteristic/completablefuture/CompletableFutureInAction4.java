package com.liang.argorithm.characteristic.completablefuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author liangbingtian
 * @date 2023/11/02 下午1:40
 */
public class CompletableFutureInAction4 {

  public static void main(String[] args) throws InterruptedException {
//    CompletableFuture.supplyAsync(()->1).thenApply(a->{
//      throw new RuntimeException();
//    }).whenComplete((v, t)->{
//      Optional.ofNullable(v).ifPresent(System.out::println);
//      Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
//    });

    CompletableFuture.supplyAsync(() -> 1).thenApply(a -> {
      throw new RuntimeException();
    }).handle((v, t) -> {
      if (t instanceof RuntimeException) {
        return v;
      }
      return 2;
    }).whenComplete((v, t) -> {
      Optional.ofNullable(v).ifPresent(System.out::println);
      Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
    });

    CompletableFuture.supplyAsync(() -> 1)
        .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10 * i))
        .thenAccept(System.out::println);

    CompletableFuture.supplyAsync(() -> 1)
        .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), Double::sum)
        .thenAccept(System.out::println);

    Thread.sleep(10000);
  }

}
