package com.liang.argorithm.characteristic.defaulttest;

/**
 * @author liangbingtian
 * @date 2023/10/31 上午10:17
 */
public class DefaultInAction {

  public static void main(String[] args) {
    A a = ()->10;
    System.out.println(a.size());
    System.out.println(a.isEmpty());
  }

  private interface A {

    int size();

    default boolean isEmpty() {
      return size() == 0;
    }
  }

}
