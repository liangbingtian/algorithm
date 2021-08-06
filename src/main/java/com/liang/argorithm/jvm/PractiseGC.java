package com.liang.argorithm.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 练习1
 *
 * @author liangbingtian
 * @date 2021/07/29 下午8:57
 */
public class PractiseGC {

  private static class OOMObject{
    public byte[] placeholder = new byte[64*1024];
  }

  public static void fillHeap(int num) throws InterruptedException {
    List<OOMObject> list = new ArrayList<>();
    for (int i=0;i<num;++i) {
      Thread.sleep(50);
      list.add(new OOMObject());
    }
    System.gc();
  }

  public static void main(String[] args) throws InterruptedException {
    fillHeap(10000);
  }

}
