package com.liang.argorithm.jvm;

/**
 * 分配对象
 *
 * @author liangbingtian
 * @date 2021/07/25 下午10:07
 */
public class PrepareObject {

  private static final int _1MB = 1024 * 1024;

  /**
   * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
   */
  public static void main(String[] args) {
    byte[] allocation1, allocation2, allocation3, allocation4;
    allocation1 = new byte[2 * _1MB];
    allocation2 = new byte[2 * _1MB];
    allocation3 = new byte[2 * _1MB];
    allocation4 = new byte[4 * _1MB]; //出现一次Minor GC
  }
}
