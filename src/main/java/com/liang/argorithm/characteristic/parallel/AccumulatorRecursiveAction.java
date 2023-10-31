package com.liang.argorithm.characteristic.parallel;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangbingtian
 * @date 2023/10/31 上午12:12
 */
public class AccumulatorRecursiveAction extends RecursiveAction {

  private final int start;

  private final int end;

  private final int[] data;

  public static final int LIMIT = 3;

  public AccumulatorRecursiveAction(int start, int end, int[] data) {
    this.start = start;
    this.end = end;
    this.data = data;
  }

  @Override
  protected void compute() {
    if ((end - start) <= LIMIT) {
      for (int i = start; i < end; i++) {
        AccumulatorHelper.accumulate(data[i]);
      }
    } else {
      int mid = (start + end) / 2;
      final AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start,
          mid, data);
      final AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid,
          end, data);
      left.fork();
      right.fork();
      left.join();
      right.join();
    }
  }

  static class AccumulatorHelper {

    private static final AtomicInteger result = new AtomicInteger(0);

    static void accumulate(int value) {
      result.getAndAdd(value);
    }

    static int getResult() {
      return result.get();
    }

    static void reset() {
      result.set(0);
    }
  }
}
