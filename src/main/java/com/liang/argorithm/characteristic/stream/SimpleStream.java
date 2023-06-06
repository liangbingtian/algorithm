package com.liang.argorithm.characteristic.stream;

import com.liang.argorithm.characteristic.lambda.Apple;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/04 下午11:19
 */
public class SimpleStream {

  public static void main(String[] args) {
    List<Apple> apples = Arrays.asList(new Apple("123", 123L), new Apple("456", 456L));
    //使用stream的简单排序
    List<String> collect = apples.stream().filter(a -> a.getWeight() > 300L)
        .sorted(Comparator.comparing(Apple::getWeight)).map(Apple::getColor).collect(
            Collectors.toList());
    List<String> collect1 = apples.parallelStream().filter(a -> {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return a.getWeight()>300L;
    }).sorted(Comparator.comparing(Apple::getWeight)).map(Apple::getColor).limit(3).collect(
            Collectors.toList());
    //mapToInt或者mapToLong这些然后再用推导方式
    LongStream longStream = apples.stream().mapToLong(Apple::getWeight);
    //skip是跳过前面几个
    Stream<Apple> skip = apples.stream().skip(3);
  }

}
