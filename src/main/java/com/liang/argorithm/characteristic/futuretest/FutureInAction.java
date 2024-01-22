package com.liang.argorithm.characteristic.futuretest;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author liangbingtian
 * @date 2023/10/31 下午12:04
 */
public class FutureInAction {

  public static void main(String[] args) {
    final Future<String> future = invoke(() -> {
      try {
        Thread.sleep(10000);
        return "finish";
      } catch (InterruptedException e) {
        e.printStackTrace();
        return "error";
      }
    });
    System.out.println(future.get());
  }

  /**
   * 加上future与callable的区别就是一个是两个线程处理，一个是一个线程处理。
   */
  private static <T> T block(Callable<T> callable) {
    return callable.action();
  }

  private static <T> Future<T> invoke(Callable<T> callable) {
    final AtomicReference<T> result = new AtomicReference<>();
    final AtomicBoolean finished = new AtomicBoolean(false);
    Thread t = new Thread(()->{
      final T action = callable.action();
      result.set(action);
      finished.set(true);
    });
    t.start();

    return new Future<T>() {
      @Override
      public T get() {
        return result.get();
      }

      @Override
      public boolean isDown() {
        return finished.get();
      }
    };
  }

  private interface Future<T> {
    T get();
    boolean isDown();
  }

  private interface Callable<T> {
    T action();
  }

}
