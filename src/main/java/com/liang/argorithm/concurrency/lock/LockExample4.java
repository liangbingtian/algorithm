package com.liang.argorithm.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * 适用于Condition的例子
 *
 * @author liangbingtian
 * @date 2022/04/15 下午3:49
 */
@Slf4j
public class LockExample4 {

  public static void main(String[] args) {
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();

    new Thread(() -> {
      try {
        reentrantLock.lock();
        log.info("wait signal");
        condition.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("get signal");
      reentrantLock.unlock();
    }).start();

    new Thread(() -> {
      reentrantLock.lock();
      log.info("get lock");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      condition.signalAll();
      log.info("send signal ~");
      reentrantLock.unlock();
    }).start();


  }


}
