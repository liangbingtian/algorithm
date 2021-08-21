package com.liang.argorithm.jvm.classloading;

/**
 * @author liangbingtian
 * @date 2021/08/11 下午5:47
 */
public class SubClass extends SuperClass {

  public static int value = 123;

  static {
    System.out.println("SubClass init!");
  }
}
