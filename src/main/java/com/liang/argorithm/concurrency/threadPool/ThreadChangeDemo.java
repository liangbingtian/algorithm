package com.liang.argorithm.concurrency.threadPool;

import cn.hutool.core.thread.NamedThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liangbingtian
 * @date 2023/03/20 上午10:47
 */
public class ThreadChangeDemo {

  public static void main(String[] args) throws InterruptedException {
    dynamicModifyExecutor();
  }

  /**
   * 自定义线程池
   *
   * @return
   */
  private static ThreadPoolExecutor buildThreadPoolExecutor() {
    return new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(10),
        new NamedThreadFactory("lbt", true));
  }

  /**
   * 先提交任务给线程池，并修改线程池参数
   */
  private static void dynamicModifyExecutor() throws InterruptedException {
    ThreadPoolExecutor threadPoolExecutor = buildThreadPoolExecutor();
    for (int i = 0; i < 15; ++i) {
      threadPoolExecutor.submit(() -> {
        threadPoolStatus(threadPoolExecutor, "创建任务");
        try {
          TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    threadPoolStatus(threadPoolExecutor, "改变之前");
    threadPoolExecutor.setCorePoolSize(10);
    threadPoolExecutor.setMaximumPoolSize(10);
    threadPoolStatus(threadPoolExecutor, "改变之后");
    Thread.currentThread().join();
  }

  /**
   * 打印线程池状态
   */
  private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
    BlockingQueue<Runnable> queue = executor.getQueue();
    String builder = Thread.currentThread().getName() + "-" + name + "-:"
        + "核心线程数为:" + executor.getCorePoolSize()
        + "活动线程数为:" + executor.getActiveCount()
        + "最大线程数为:" + executor.getMaximumPoolSize()
        + "线程活跃度为:" + divide(executor.getActiveCount(), executor.getMaximumPoolSize())
        + "任务完成数:" + executor.getCompletedTaskCount()
        + "队列大小:" + (queue.size() + queue.remainingCapacity())
        + "当前排队线程数:" + queue.size()
        + "队列剩余大小:" + queue.remainingCapacity()
        + "队列使用度:" + divide(queue.size(), (queue.size() + queue.remainingCapacity()));
    System.out.println(builder);
  }

  /**
   * 保留两位小数
   */
  private static String divide(int num1, int num2) {
    return String
        .format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
  }

}
