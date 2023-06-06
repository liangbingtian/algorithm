package com.liang.argorithm.characteristic.stream;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/06 上午10:50
 */
public class NumericStream {

  public static void main(String[] args) {
    //如果单纯操作数字的话，可以把IntegerStream换成intStream，这样会大大的减少空间
    final IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4});
    int a = 9;
    final Stream<Integer[]> stream1 = IntStream.rangeClosed(1, 100)
        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
        .map(b -> new Integer[]{a * a, b * b, (int) Math.sqrt(a * a + b * b)});
    final Stream<Integer> stream2 = Arrays.stream(new Integer[]{1, 2, 3, 3});
    final IntStream intStream = stream2.mapToInt(Integer::intValue);
  }

}
