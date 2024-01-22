package com.liang.argorithm.characteristic.completablefuture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liangbingtian
 * @date 2023/10/31 下午2:40
 */
public class CompletableFutureInAction2 {

  public static void main(String[] args) {
    //不会阻塞主线程，用异步的方式，主线程会结束,这样任务没执行完的守护线程也会结束，解决方法是不要让它是守护线程
    CompletableFuture.supplyAsync(CompletableFutureInAction1::get).
        whenComplete((t, v)->{
          Optional.ofNullable(t).ifPresent(System.out::println);
          Optional.ofNullable(v).ifPresent(Throwable::printStackTrace);
        });

  }

  //如果只是这样的话运行完后主线程不会结束
  private void test1() {
    final ExecutorService service = Executors.newFixedThreadPool(2, r->{
      Thread thread = new Thread(r);
      thread.setDaemon(true);
      return thread;
    });
    service.submit(()->System.out.println("test..."));
  }

}
