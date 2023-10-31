package com.liang.argorithm.jvm;

/**
 * @author liangbingtian
 * @date 2023/02/27 下午5:09
 */
public class GCTest {

  public static void main(String[] args) throws InterruptedException {
    Thread.sleep(30000);
    while (true) {
      loadData();
    }
  }

  private static void loadData() throws InterruptedException {
    byte[] data = null;
    for (int i=0;i<50;i++) {
      data = new byte[100 * 1024];
    }
    data = null;
    Thread.sleep(1000);
  }

}
