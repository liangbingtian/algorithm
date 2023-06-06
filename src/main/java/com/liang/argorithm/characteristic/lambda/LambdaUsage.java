package com.liang.argorithm.characteristic.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author liangbingtian
 * @date 2023/06/02 上午11:27
 */
public class LambdaUsage {

  private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
      if (predicate.test(apple)) {
        result.add(apple);
      }
    }
    //或者
    return source.stream().filter(predicate).collect(Collectors.toList());
  }

  private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
      if (predicate.test(apple.getWeight())) {
        result.add(apple);
      }
    }
    return result;
  }

  private static List<Apple> filterByBi(List<Apple> source, BiPredicate<String, Long> predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
      if (predicate.test(apple.getColor(), apple.getWeight())) {
        result.add(apple);
      }
    }
    return result;
  }

  private static List<Apple> filterByConsumer(List<Apple> source, Consumer<Apple> consumer) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
     consumer.accept(apple);
    }
    return result;
  }


  public static void main(String[] args) {
    List<Apple> apples = Collections.singletonList(new Apple());
    List<Apple> greenApples = filter(apples, a -> a.getColor().equals("绿色"));
    List<Apple> greenApples1 = filterByWeight(apples, a->a>123L);
    List<Apple> greenApples2 = filterByBi(apples, (a, b)->a.equals("绿色")&&b>123);
    List<Apple> greenApples3 = filterByConsumer(apples, System.out::println);
    IntFunction<Double>  doubleIntFunction = i->i*100.0d;
    Supplier<String> s = String::new;
  }

}
