package com.liang.argorithm.characteristic.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/05 下午10:45
 */
public class StreamMap {

  public static void main(String[] args) {
    final List<Integer> integers = Arrays.asList(1, 2, 3, 3, 4, 4, 5, 5, 6);
    final List<Integer> collect = integers.stream().map(i -> i * 2).collect(Collectors.toList());
    //flatmap扁平化处理,即多个stream合并成一个stream
    String[] strings = new String[]{"Hello", "World"};
    final Stream<String> stringStream = Arrays.stream(strings).map(s -> s.split(""))
        .flatMap(Arrays::stream);
    final List<String> collect1 = stringStream.distinct().collect(Collectors.toList());

    final Optional<Integer> reduce = integers.stream().reduce(Integer::sum);
  }
}
