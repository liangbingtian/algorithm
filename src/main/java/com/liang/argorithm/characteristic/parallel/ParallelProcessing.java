package com.liang.argorithm.characteristic.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/10/27 下午1:43
 */
public class ParallelProcessing {

  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
    final long time1 = measureSumPerformance(ParallelProcessing::normalAdd, 1000_0000);
    final long time2 = measureSumPerformance(ParallelProcessing::parallelIterateStream2, 1000_0000);
//    final long time3 = measureSumPerformance(ParallelProcessing::parallelIterateStream, 1000_0000);
    System.out.println("normal add the best timer is: " + time1);
    System.out.println("iterateStream the best timer is: " + time2);
//    System.out.println("parallelIterateStream the best timer is: " + time3);

  }

  private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 10; i++) {
      final long currentTimeMillis = System.currentTimeMillis();
      final Long result = adder.apply(limit);
      final long duration = System.currentTimeMillis() - currentTimeMillis;
//      System.out.println("this result of sum is:" + result);
      fastest = Math.min(duration, fastest);
    }
    return fastest;
  }

  private static long iterateStream(long limit) {
    return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
  }

  private static long parallelIterateStream(long limit) {
    return Stream.iterate(1L, i -> i + 1).limit(limit).parallel().reduce(0L, Long::sum);
  }

  private static long parallelIterateStream2(long limit) {
   return LongStream.rangeClosed(1L, limit).parallel().reduce(0L, Long::sum);
  }

  private static long normalAdd(long limit) {
    long result = 0L;
    for (int i = 1; i < limit; i++) {
      result += i;
    }
    return result;
  }

}
