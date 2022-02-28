package com.liang.argorithm.concurrency.threadLocal;

/**
 * @author liangbingtian
 * @date 2022/02/20 上午11:41
 */
public class RequestHolder {

  private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

  public static void add(Long id) {
    requestHolder.set(id);
  }

  public static Long getId() {
    return requestHolder.get();
  }

  public static void remove() {
    requestHolder.remove();
  }

}
