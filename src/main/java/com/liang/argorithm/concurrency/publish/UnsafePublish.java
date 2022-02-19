package com.liang.argorithm.concurrency.publish;

import com.liang.argorithm.concurrency.annoations.NotThreadSafe;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * 不安全的发布对象
 *
 * @author liangbingtian
 * @date 2022/02/19 上午11:32
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

  /**
   * 其他线程可以随意修改该对象中的域
   */
  private String[] state = {"a", "b", "c"};

  private String[] getStates() {
    return state;
  }

  public static void main(String[] args) {
    UnsafePublish unsafePublish = new UnsafePublish();
    log.info("{}", Arrays.toString(unsafePublish.getStates()));

    unsafePublish.getStates()[0] = "d";
    log.info("{}", Arrays.toString(unsafePublish.getStates()));
  }
}
