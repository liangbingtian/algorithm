package com.liang.argorithm.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liangbingtian
 * @date 2023/03/23 下午2:54
 */
public class BeanLIfeCycle {

  public static void main(String[] args) {
    System.out.println("现在开始初始化容器");
    ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext(
        "test/beans.xml");
    System.out.println("容器初始化成功");
    Person person = factory.getBean("person", Person.class);
    System.out.println(person);
    System.out.println("现在开始关闭容器");
    factory.registerShutdownHook();

  }

}
