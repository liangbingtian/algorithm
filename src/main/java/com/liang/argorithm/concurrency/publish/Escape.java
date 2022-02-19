package com.liang.argorithm.concurrency.publish;

import com.liang.argorithm.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 避免
 *
 * @author liangbingtian
 * @date 2022/02/19 下午2:13
 */
@Slf4j
@NotThreadSafe
public class Escape {

  private int thisCanBeEscape = 0;

  public Escape() {
    new InnerClass();
  }

  private class InnerClass{

    //在Escape对象没有初始化之前就引用到了thisCanBeEscape这个变量
    public InnerClass() {
      log.info("{}", Escape.this.thisCanBeEscape);
    }
  }

  public static void main(String[] args) {
    new Escape();
  }
}
