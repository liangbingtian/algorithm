package com.liang.argorithm.characteristic.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/06 上午10:08
 */
public class StreamFind {

  public static void main(String[] args) {
    Stream<Integer> stream = Arrays
        .stream(new Integer[]{1, 2, 2, 3, 10, 4, 5, 9, 6, 7, 7, 8});
    //随便拿出一个
    final Optional<Integer> any = stream.filter(i -> i % 2 == 0).findAny();
    final Optional<Integer> first = stream.filter(i -> i % 2 == 0).findFirst();
    final Integer integer = first.orElseThrow(RuntimeException::new);
    final Integer integer1 = first.get();
    final Integer integer2 = first.orElse(-1);
    first.ifPresent(System.out::println);
    final Optional<Integer> max = stream.max(Integer::compareTo);
    final Optional<Integer> reduce = stream.reduce(Integer::max);
  }

}
