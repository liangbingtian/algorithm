package com.liang.argorithm.concurrency.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * ThreadLocal相关的样例
 *
 * @author liangbingtian
 * @date 2023/03/18 上午11:44
 */
public class ThreadLocalExample implements Runnable{

  //SimpleDateFormat 不是线程安全的，所以每个线程都要有自己独立的副本
  private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyyMMdd HHmm"));

  @Override
  public void run() {
    System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter ="+formatter.get().toPattern());
    try {
      Thread.sleep(new Random().nextInt(1000));
    }catch (InterruptedException e){
      e.printStackTrace();
    }
    formatter.set(new SimpleDateFormat());
    System.out.println("Thread Name= " + Thread.currentThread().getName() + " default Formatter ="+formatter.get().toPattern());
  }
}
