package com.liang.argorithm.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用例子2
 *
 * @author liangbingtian
 * @date 2022/02/14 下午10:02
 */
@Slf4j
public class AtomicExample2 {

  private static AtomicReference<Integer> count = new AtomicReference<>(0);

  @Getter
  public volatile int volCount = 100;

  /**
   * 用volatile修饰并且非static修饰的
   */
  private static AtomicIntegerFieldUpdater<AtomicExample2> updater = AtomicIntegerFieldUpdater
      .newUpdater(AtomicExample2.class, "volCount");

  private static AtomicExample2 example2 = new AtomicExample2();

  public static void main(String[] args) {
    count.compareAndSet(0, 2);
    count.compareAndSet(0, 1);
    count.compareAndSet(2, 3);
    if (updater.compareAndSet(new AtomicExample2(), 100, 120)){
      log.info("update success, {}", example2.getVolCount());
    }
  }
}
