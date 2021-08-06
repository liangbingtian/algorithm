package com.liang.argorithm.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 线程死循环
 *
 * @author liangbingtian
 * @date 2021/07/29 下午9:23
 */
public class PracticeThread {

  public static void createBusyThread() {
    Thread thread = new Thread(() -> {
      while(true)
        ;
    }, "testBusyThread");
    thread.start();
  }

  /**
   * 线程锁等待演示
   */
  public static void createLockThread(final Object lock) {
    Thread thread = new Thread(()->{
      synchronized (lock) {
        try {
          lock.wait();
        }catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, "testLockThread");
    thread.start();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    br.readLine();
//    createBusyThread();
    br.readLine();
    Object obj = new Object();
    createLockThread(obj);
  }
}
