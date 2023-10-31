package com.liang.argorithm.characteristic.collector;

import cn.hutool.core.lang.func.Func1;
import com.google.common.base.Function;
import com.liang.argorithm.characteristic.lambda.Apple;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * @author liangbingtian
 * @date 2023/06/07 上午10:12
 */
public class CollectorAction {

  private static List<Apple> appleList = Arrays.asList(new Apple(), new Apple(), new Apple());

  public static void main(String[] args) {

    List<Apple> appleList = Arrays.asList(new Apple(), new Apple(), new Apple());
    //1. 直接分组
    final Map<String, List<Apple>> stringListMap = Optional
        .of(appleList.stream().collect(Collectors.groupingBy(Apple::getColor))).get();
    //2. 分组后做一些事情
    final Map<String, Long> stringLongMap = Optional.of(appleList.stream()
        .collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()))).get();

    final TreeMap<String, Double> stringDoubleTreeMap = Optional.of(appleList.stream()
        .collect(Collectors
            .groupingBy(Apple::getColor, TreeMap::new, Collectors.averagingLong(Apple::getWeight))))
        .get();
    Optional.ofNullable(stringDoubleTreeMap.getClass()).ifPresent(System.out::println);

    //强制性的转化为跳表
    final ConcurrentSkipListMap<String, Double> collect = appleList.stream()
        .collect(Collectors.groupingByConcurrent(Apple::getColor,
            ConcurrentSkipListMap::new, Collectors.averagingLong(Apple::getWeight)));
  }

  //查找平均数，好像是可以自动拆箱和装箱
  private static void toAverageDouble() {
    final Double d = appleList.stream()
        .collect(Collectors.averagingDouble(Apple::getWeightDouble));
  }

  private static void toAverageInt() {
    final Double collect = appleList.stream().collect(Collectors.averagingLong(Apple::getWeight));
    //把这些最大最小还有平均值这些东西都统计了出来
    final LongSummaryStatistics collect1 = appleList.stream()
        .collect(Collectors.summarizingLong(Apple::getWeight));
  }

  //collectionAndThen收集一个值，然后转换成另一个值
  private static void collectAndThen() {
    final String collect = appleList.stream().collect(
        Collectors.collectingAndThen(Collectors.averagingLong(Apple::getWeight), String::valueOf));
  }

  //收集完集合后使他无法修改
  private static void collectAndThen1() {
    final List<String> collect = appleList.stream().map(Apple::getColor).collect(
        Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
  }

  //使用join进行连接，并且使用delimiter分隔符
  private static void joinAndDelimiter() {
    final String collect = appleList.stream().map(Apple::getColor).collect(Collectors.joining(","));
    //还可以加前缀和后缀
    final String collect1 = appleList.stream().map(Apple::getColor)
        .collect(Collectors.joining(",", "name:[", "]"));
  }

  private static void testMaxBy() {
    final Optional<Long> reduce = appleList.stream().map(Apple::getWeight).reduce(Long::max);
    final Optional<Apple> collect = appleList.stream()
        .collect(Collectors.maxBy(Comparator.comparingLong(Apple::getWeight)));
    final Optional<Apple> collect1 = appleList.stream()
        .max(Comparator.comparingLong(Apple::getWeight));
  }


}
