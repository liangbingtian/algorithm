package com.liang.argorithm.characteristic.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/06 上午9:59
 */
public class StreamMatch {

  public static void main(String[] args) {
     Stream<Integer> stream = Arrays
        .stream(new Integer[]{1, 2, 2, 3, 10, 4, 5, 9, 6, 7, 7, 8});
     //这个表示的是全大于
     final boolean b = stream.allMatch(i -> i > 5);
     //这个表示的是只有一个大于
    final boolean b1 = stream.anyMatch(i -> i >= 10);
    final boolean b2 = stream.noneMatch(i -> i < 0);
  }

}
