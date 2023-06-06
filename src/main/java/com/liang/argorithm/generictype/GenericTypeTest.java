package com.liang.argorithm.generictype;

/**
 * @author liangbingtian
 * @date 2023/05/12 上午10:31
 */
public class GenericTypeTest {

  GenericType<Integer> a = new GenericType<>(1);

  //泛型方法 静态
  public static <E> E printArray(E[] inputArray) {
    for (E element : inputArray) {
      System.out.printf("%s", element);
    }
    return inputArray[0];
  }


  //泛型类
  private static class GenericType<T> {
    private T key;

    //非静态，使用类上声明的T
    public GenericType(T key) {
      this.key = key;
    }

    public T getKey() {
      return key;
    }
  }

}
