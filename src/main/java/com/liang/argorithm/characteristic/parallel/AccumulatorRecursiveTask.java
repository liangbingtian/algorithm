package com.liang.argorithm.characteristic.parallel;

import java.util.concurrent.RecursiveTask;

/**
 * @author liangbingtian
 * @date 2023/10/27 下午3:17
 */
public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

  private final int start;

  private final int end;

  private int[] data;

  public static final int LIMIT = 3;

  public AccumulatorRecursiveTask(int start, int end, int[] data) {
    this.start = start;
    this.end = end;
    this.data = data;
  }

  @Override
  protected Integer compute() {
    if ((end - start) <= LIMIT) {
      int result = 0;
      for (int i = start; i < end; i++) {
        result += data[i];
      }
      return result;
    }
    int mid = (start + end) / 2;
    final AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start,
        mid, data);
    final AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid,
        end, data);
    left.fork();
    final Integer subResult1 = right.compute();
    final Integer subResult2 = left.join();
    return subResult1 + subResult2;
  }
}
