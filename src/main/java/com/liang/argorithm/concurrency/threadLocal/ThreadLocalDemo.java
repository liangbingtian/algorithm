package com.liang.argorithm.concurrency.threadLocal;

import java.lang.reflect.Field;

/**
 * @author liangbingtian
 * @date 2023/03/20 下午4:18
 */
public class ThreadLocalDemo {

  public static void main(String[] args) throws InterruptedException {
    Thread t = new Thread(()->test("abc", false));
    t.start();
    t.join();
    System.out.println("--gc后--");
    Thread t1 = new Thread(()->test("def", true));
    t1.start();
    t1.join();
  }


  private static void test(String s, boolean isGC) {
    try {
      new ThreadLocal<>().set(s);
      if (isGC) {
        System.gc();
      }
      Thread thread = Thread.currentThread();
      Class<? extends Thread> clz = thread.getClass();
      Field threadLocals = clz.getDeclaredField("threadLocals");
      threadLocals.setAccessible(true);
      Object threadLocalMap = threadLocals.get(thread);
      Class<?> clzTLM = threadLocalMap.getClass();
      Field table = clzTLM.getDeclaredField("table");
      table.setAccessible(true);
      Object[] entryArray = (Object[]) table.get(threadLocalMap);
      for (Object obj : entryArray) {
        if (obj!=null) {
          Class<?> clzEntry = obj.getClass();
          Class<?> clzReference = obj.getClass().getSuperclass().getSuperclass();
          Field value = clzEntry.getDeclaredField("value");
          Field referent = clzReference.getDeclaredField("referent");
          value.setAccessible(true);
          referent.setAccessible(true);
          System.out
              .println(String.format("弱引用的key:%s, value:%s", referent.get(obj), value.get(obj)));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
