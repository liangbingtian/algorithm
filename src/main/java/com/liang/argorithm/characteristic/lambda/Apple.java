package com.liang.argorithm.characteristic.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author liangbingtian
 * @date 2023/05/31 上午11:47
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Apple {

  private String color;

  private Long weight;

  private Double weightDouble;

  @FunctionalInterface
  public interface AppleFilter{
    boolean filter(Apple apple);
  }

  public static List<Apple> findApples(List<Apple> appleList, AppleFilter filter) {
    List<Apple> filteredApples = new ArrayList<>();
    for (Apple apple : appleList) {
      if (filter.filter(apple)){
        filteredApples.add(apple);
      }
    }
    return filteredApples;
  }

  public static class GreenAppleFilter implements AppleFilter{

    @Override
    public boolean filter(Apple apple) {
      return false;
    }
  }


  public static void aboutSortWithoutLambda() {
    Comparator<Apple> comparator = new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o1.getColor().compareTo(o2.getColor());
      }
    };
    List<Apple> list = new ArrayList<>(Collections.emptyList());
    list.sort(comparator);
    list.sort((o1, o2)-> o1.getColor().compareTo(o2.getColor()));
  }

  public static void main(String[] args) {
    //直接使用创造类的写法
    findApples(Arrays.asList(new Apple(), new Apple()), new GreenAppleFilter());
    //创造的类太多了，使用匿名内部类的写法
    findApples(Arrays.asList(new Apple(), new Apple()), new AppleFilter() {
      @Override
      public boolean filter(Apple apple) {
        return false;
      }
    });
    findApples(Arrays.asList(new Apple(), new Apple()), apple->apple.getColor().equals("红色"));

    //预测相关的代码
    Predicate<Apple> predicate = a->a.getColor().equals("1243");

    //function是得到一个东西然后返回一个东西
    Function<Apple, Boolean> function = a->a.getColor().equals("123");
    //Supplier是直接得到一个东西
    Supplier<Apple> supplier = Apple::new;
  }



}
