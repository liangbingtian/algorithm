package com.liang.argorithm.springtest.annotation.model;

import com.liang.argorithm.springtest.annotation.MyComponent;

/**
 * @author liangbingtian
 * @date 2023/05/04 上午10:16
 */
@MyComponent
public class Student {
  private String name;
  private String age;

  public void eat() {
    System.out.println("这个是eat方法");
  }

}
