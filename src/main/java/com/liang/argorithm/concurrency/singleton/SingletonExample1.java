package com.liang.argorithm.concurrency.singleton;

import com.liang.argorithm.concurrency.annoations.NotRecommend;
import com.liang.argorithm.concurrency.annoations.NotThreadSafe;
import com.liang.argorithm.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式的例子1
 * 懒汉模式，在类初始化的时候初始化单例对象
 * @author liangbingtian
 * @date 2022/02/19 下午2:37
 */
@Slf4j
@ThreadSafe
@NotRecommend("加锁保证同一时刻只能有一个线程来访问，这样虽然线程安全，但是有性能开销")
public class SingletonExample1 {


  private SingletonExample1(){

  }

  private static SingletonExample1 instance = null;

  public static synchronized SingletonExample1 getInstance() {
    if (instance==null) {
      //当两个线程同时运行到上一行的时候，会同时进入下面的初始化代码，两个线程会分别初始化两个对象。这就不满足单例模式。
      instance = new SingletonExample1();
    }
    return instance;
  }

}
