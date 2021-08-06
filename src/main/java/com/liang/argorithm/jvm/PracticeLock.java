package com.liang.argorithm.jvm;

import org.springframework.stereotype.Component;

/**
 * 测试线程锁
 *
 * @author liangbingtian
 * @date 2021/08/05 下午6:07
 */
@Component
public class PracticeLock implements Runnable {

  int a, b;

  public PracticeLock() {
  }

  public PracticeLock(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public void run() {
    synchronized (Integer.valueOf(a)) {
      synchronized (Integer.valueOf(b)) {
        System.out.println(a+b);
      }
    }
  }

  //Integer.valueOf()方法会出于减少对象的创建次数和节省内存考虑，会对数值为-128~127之间的Integer对象进行缓存。其实就导致是一个对象
  public static void main(String[] args) {
    for (int i=0;i<100;++i) {
      new Thread(new PracticeLock(1, 2)).start();
      new Thread(new PracticeLock(2, 1)).start();
    }
  }
}
