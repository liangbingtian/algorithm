package com.liang.argorithm.concurrency.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用枚举类来保证单例模式
 *
 * @author liangbingtian
 * @date 2022/02/19 下午4:51
 */
@Slf4j
public class SingletonExample4 {

  private SingletonExample4(){

  }

  public static SingletonExample4 getInstance() {
    return Singleton.INSTANCE.getInstance();
  }

  private enum Singleton {
    INSTANCE;

    private SingletonExample4 singleton;

    /**
     * JVM保证该构造方法只会被调用一次。
     */
    Singleton() {
      singleton = new SingletonExample4();
    }

    public SingletonExample4 getInstance() {
      return singleton;
    }
  }
}
