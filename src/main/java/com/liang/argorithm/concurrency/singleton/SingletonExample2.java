package com.liang.argorithm.concurrency.singleton;

import com.liang.argorithm.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * 饿汉模式，类装载的时候就实现对象的声明，是线程安全的，缺点是如果私有构造函数中的东西很多，
 * 声明对象的时间就会很久，会导致类装载的性能问题。
 * @date 2022/02/19 下午2:48
 */
@Slf4j
@ThreadSafe
public class SingletonExample2 {
  private static SingletonExample2 instance = new SingletonExample2();

  private SingletonExample2(){

  }

  public static SingletonExample2 getInstance() {
    return instance;
  }
}
