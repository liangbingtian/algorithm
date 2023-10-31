package com.liang.argorithm.characteristic.parallel;

import com.liang.argorithm.characteristic.parallel.AccumulatorRecursiveAction.AccumulatorHelper;
import java.util.concurrent.ForkJoinPool;

/**
 * @author liangbingtian
 * @date 2023/10/27 下午3:08
 */
public class ForkJoinPoolTest {

  private static final int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

  public static void main(String[] args) {
    System.out.println("result=>" + calc());
    AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, a.length, a);
    final ForkJoinPool forkJoinPool = new ForkJoinPool();
    final Integer result = forkJoinPool.invoke(task);
    System.out.println("AccumulatorRecursiveTask>>" + result);
    AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0, a.length, a);
    forkJoinPool.invoke(action);
    System.out.println("AccumulatorRecursiveAction>>" + AccumulatorHelper.getResult());
  }

  private static int calc() {
    int sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += a[i];
    }
    return sum;
  }

}
