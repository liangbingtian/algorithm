package com.liang.argorithm.characteristic.collector;

import com.liang.argorithm.characteristic.lambda.Apple;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author liangbingtian
 * @date 2023/06/08 上午10:15
 */
public class CollectorAction2 {

  private static List<Apple> appleList = Arrays.asList(new Apple(), new Apple(), new Apple());

  public static void main(String[] args) {
    //可以指定一个list去聚合
    final LinkedList<String> collect = appleList.stream().map(Apple::getColor)
        .collect(Collectors.toCollection(LinkedList::new));
    //生成map有group by的方式。比如有个需求是group by后计算平均值
    final Map<String, Double> collect1 = appleList.stream().collect(
        Collectors.groupingBy(Apple::getColor, Collectors.averagingLong(Apple::getWeight)));
    //还有直接把对象拉平的，这个比较常用
    final Map<String, Apple> collect2 = appleList.stream()
        .collect(Collectors.toMap(Apple::getColor, Function.identity()));
    //转换成concurrentHashMap
    final ConcurrentMap<String, Apple> collect3 = appleList.stream()
        .collect(Collectors.toConcurrentMap(Apple::getColor, Function.identity()));
    //转换成concurrentHashMap并对key值一样的结果进行处理，之前不处理应该是覆盖
    final ConcurrentMap<String, Long> collect4 = appleList.stream()
        .collect(Collectors.toConcurrentMap(Apple::getColor, Apple::getWeight, Long::sum));
    final ConcurrentMap<String, Apple> collect5 = appleList.stream()
        .collect(Collectors.toConcurrentMap(Apple::getColor, Function.identity(), (a, b)->b));
    //同时还可以指定数据结构
    final ConcurrentSkipListMap<String, Apple> collect6 = appleList.stream()
        .collect(Collectors.toConcurrentMap(Apple::getColor, Function.identity(), (a, b) -> b,
            ConcurrentSkipListMap::new));

    //活学活用，制定成map，并且将其改造为线程安全
//    final Collector<Apple, Object, Map<String, Apple>> collector = Collectors
//        .collectingAndThen(Collectors.toMap(Apple::getColor, Function.identity(), (a, b) -> a),
//            Collections::synchronizedMap);
  }

}
