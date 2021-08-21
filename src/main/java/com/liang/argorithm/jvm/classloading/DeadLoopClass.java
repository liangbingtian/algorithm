package com.liang.argorithm.jvm.classloading;

/**
 * 类加载阶段的初始化阶段，多线程同时进行一个类的初始化，如果其中一个阻塞，则会导致线程线程阻塞
 *
 * @author liangbingtian
 * @date 2021/08/11 下午7:53
 */
public  class DeadLoopClass {
  static class DeadLoopClass1 {
    static {
      if (true) {
        System.out.println(Thread.currentThread()+"init DeepLoopClass");
        while (true) {

        }
      }
    }
  }


  public static void main(String[] args) {
    Runnable script = () -> {
      System.out.println(Thread.currentThread()+"start");
      DeadLoopClass1 dlc = new DeadLoopClass1();
      System.out.println(Thread.currentThread()+"end");
    };
    Thread thread1 = new Thread(script);
    Thread thread2 = new Thread(script);
    thread1.start();
    thread2.start();;
  }

}
