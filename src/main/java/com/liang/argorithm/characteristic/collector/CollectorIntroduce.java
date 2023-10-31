package com.liang.argorithm.characteristic.collector;

import static java.util.stream.Collectors.groupingBy;

import com.liang.argorithm.characteristic.lambda.Apple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections4.map.HashedMap;

/**
 * @author liangbingtian
 * @date 2023/06/07 上午9:29
 */
public class CollectorIntroduce {

  public static void main(String[] args) {
    final List<Apple> apples = Arrays.asList(new Apple(), new Apple(), new Apple());
    final List<Apple> greenApples = apples.stream().filter(a -> a.getColor().equals("绿色"))
        .collect(Collectors.toList());
  }

  private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
    Map<String, List<Apple>> result = new HashedMap<>();
    apples.forEach(a-> Optional.ofNullable(result.get(a.getColor())).orElseGet(()->{
      List<Apple> appleList = new ArrayList<>();
      result.put(a.getColor(), appleList);
      appleList.add(a);
      return appleList;
    }));
    return result;
  }

  private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
    return apples.stream().collect(groupingBy(Apple::getColor));
  }

}
