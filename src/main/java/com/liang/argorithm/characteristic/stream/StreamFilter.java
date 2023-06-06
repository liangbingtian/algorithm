package com.liang.argorithm.characteristic.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangbingtian
 * @date 2023/06/05 下午10:03
 */
public class StreamFilter {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 5, 6, 6, 7, 8, 8);
    List<Integer> collect = list.stream().filter(i -> i % 2 == 0)
        .collect(Collectors.toList());
    //去重
    final List<Integer> collect1 = list.stream().distinct().collect(Collectors.toList());
    //跳过
    final List<Integer> collect2 = list.stream().skip(5).collect(Collectors.toList());
    //截断
    final List<Integer> collect3 = list.stream().limit(5).collect(Collectors.toList());
  }

}
