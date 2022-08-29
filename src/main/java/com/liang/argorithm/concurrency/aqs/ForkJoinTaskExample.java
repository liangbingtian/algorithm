package com.liang.argorithm.concurrency.aqs;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.extern.slf4j.Slf4j;

/**
 * fork和join框架
 *
 * @author liangbingtian
 * @date 2022/04/19 下午7:59
 */
@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {

  public static final int threshold = 2;
  private final int start;
  private final int end;

  public ForkJoinTaskExample(int start, int end) {
    this.start = start;
    this.end = end;
  }

  @Override
  protected Integer compute() {
    int sum = 0;
    //如果任务足够小就去计算任务
    boolean canCompute = (end - start) <= threshold;
    if (canCompute) {
      for (int i = start; i <= end; i++) {
        sum += i;
      }
    } else {
      //如果任务量过大
      int middle = (start + end) / 2;
      ForkJoinTaskExample leftTask = new ForkJoinTaskExample(start, middle);
      ForkJoinTaskExample rightTask = new ForkJoinTaskExample(middle + 1, end);
      //执行子任务
      leftTask.fork();
      rightTask.fork();
      //任务执行结果
      int leftResult = leftTask.join();
      int rightResult = rightTask.join();

      //合并子任务
      sum = leftResult + rightResult;
    }
    return sum;
  }

  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    //生成一个计算任务，计算
    ForkJoinTaskExample task = new ForkJoinTaskExample(1, 100);
    ForkJoinTask<Integer> result = forkJoinPool.submit(task);
    try {
      log.info("result:{}", result.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
