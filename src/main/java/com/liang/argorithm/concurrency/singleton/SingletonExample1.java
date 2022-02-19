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
@NotRecommend("加锁保证同一时刻只能有一个线程来访问，这样虽然线程安全，但是有性能开销，所以要用lazyDoubleCheck的方法")
public class SingletonExample1 {

  private SingletonExample1(){

  }

  private static volatile SingletonExample1 instance = null;

//  public static synchronized SingletonExample1 getInstance() {
//    if (instance==null) {
//      //当两个线程同时运行到上一行的时候，会同时进入下面的初始化代码，两个线程会分别初始化两个对象。这就不满足单例模式。
//      instance = new SingletonExample1();
//    }
//    return instance;
//  }


  public static SingletonExample1 getInstance() {
    if (instance == null) {
      synchronized (SingletonExample1.class) {
        if (instance==null) {
          //1. 分配对象内存空间，2.调用构造方法实例化对象。3，设置instance指向刚分配的内存。
          //由于指令重排序，线程1 执行了1，3。线程2在最外层的if条件判断处。看到instance不为null。就把未完全初始化的对象返回给调用方了，就会出现问题。
          //用volatile修饰instance的时候，解决了对instance进行volatile写时候与其他指令进行指令重排序的问题。
          instance = new SingletonExample1();
        }
      }
    }
    return instance;
  }

}
