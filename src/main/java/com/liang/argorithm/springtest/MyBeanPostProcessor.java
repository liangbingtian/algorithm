package com.liang.argorithm.springtest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author liangbingtian
 * @date 2023/03/23 下午2:49
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

  public MyBeanPostProcessor() {
    super();
    System.out.println("这是BeanPostProcessor实现类构造器！！");
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object postProcessAfterInitialization(Object arg0, String arg1)
      throws BeansException {
    System.out
        .println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
    return arg0;
  }

  @Override
  public Object postProcessBeforeInitialization(Object arg0, String arg1)
      throws BeansException {
    System.out
        .println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
    return arg0;
  }
}