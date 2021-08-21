package com.liang.argorithm.jvm.classloading;

/**
 * 被动使用类字段1
 *
 * @author liangbingtian
 * @date 2021/08/11 下午5:46
 */
public class SuperClass {
  public static int value = 123;
  static {
    value = 889;
    System.out.println("SuperClass init!");
  }

  public static final String HELLO_WORLD = "hello_world";

  public static void main(String[] args) {
    //1. 被动引用，子类引用父类的字段
//    System.out.println(SubClass.value);
    //2. 被动引用，通过数组定义来引用类，不会触发此类的初始化。
//    SuperClass[] sca = new SuperClass[10];
    //3. 被动引用，常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类。
//    System.out.println(SuperClass.HELLO_WORLD);
    System.out.println(SuperClass.value);
  }
}
