package com.liang.argorithm.characteristic.lambda;

import cn.hutool.core.lang.copier.Copier;
import com.baomidou.mybatisplus.core.toolkit.support.BiIntFunction;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author liangbingtian
 * 方法推导
 * @date 2023/06/03 上午8:44
 */
public class MethodReference {


  public static void main(String[] args) {
    Consumer<String> s = System.out::println;
    useConsumer(s, "123");

    int a = Integer.parseInt("123");
    Function<String, Integer> function = Integer::parseInt;
    function.apply("123");
    BiIntFunction<String, Character> function1 = String::charAt;
    Character apply = function1.apply("123", 0);
    BiFunction<String, Integer, Character> function2 = String::charAt;
    Character apply1 = function2.apply("456", 2);


    //如果指定了对象的话，也可以使用
    String s3 = "abcdefg";
    Function<Integer, Character> characterFunc1 = s3::charAt;
    characterFunc1.apply(3);

    //构造函数的推导
    Supplier<String> supplier4 = String::new;
    Function<String, String> function3 = String::new;

    //多参数推导的话需要按照顺序传递
//    BiFunction<String, Long, Apple> appleCopier = Apple::new;
    ThreeFunction<String, Long, String, Apple> threeFunction = ComplexApple::new;
    Comparator<Apple> comparing = Comparator.comparing(Apple::getColor);
    Function<Apple, String> getColor = Apple::getColor;
  }


  private static <T> void useConsumer(Consumer<T> consumer, T t) {
    consumer.accept(t);
  }
}
