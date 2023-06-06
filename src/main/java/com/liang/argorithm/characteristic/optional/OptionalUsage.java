package com.liang.argorithm.characteristic.optional;

import com.liang.argorithm.characteristic.lambda.Apple;
import java.util.Optional;

/**
 * @author liangbingtian
 * @date 2023/06/06 下午2:33
 */
public class OptionalUsage {

  public static void main(String[] args) {
    //创建一个包含空Apple的
    final Optional<Apple> o = Optional.empty();
    //直接创建一个带对象的
    final Optional<Apple> apple = Optional.of(new Apple());

    //上边两种方式的结合，就是如果你不知道一个对象是否为空的时候，就这么使用
    final Optional<Apple> apple1 = Optional.ofNullable(null);

    //获取值
    final Apple apple2 = apple1.orElse(new Apple());
    final Apple apple3 = apple1.orElseThrow(RuntimeException::new);
    final Apple apple4 = apple1.orElseGet(Apple::new);

    final boolean present = apple1.isPresent();
    apple1.ifPresent(System.out::println);
  }

  //一个简单的忽略空指针获取一个object的name的方法
  private String getAppleNameByOptionalMethod(Apple apple) {
    return Optional.ofNullable(apple).map(Apple::getColor).orElse("no such name");
  }


}
