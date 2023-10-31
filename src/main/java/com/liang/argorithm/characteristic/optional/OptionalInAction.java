package com.liang.argorithm.characteristic.optional;

import java.util.Optional;

/**
 * @author liangbingtian
 * @date 2023/06/06 下午2:56
 */
public class OptionalInAction {

  public static void main(String[] args) {

  }

  //首先是用map，那么不需要传递封装的值
  private String getInsuranceNameByPersonOptional(Person person) {
    return Optional.ofNullable(person).map(Person::getCar).map(Car::getInsurance)
        .map(Insurance::getName).orElse("已经获得了");
  }

  //如果里面都是Optional的话，则另一种方式
  private String getInsuranceNameByPersonOptional2(Person person) {
    return Optional.ofNullable(person).flatMap(Person::getOptionalCar)
        .flatMap(Car::getOptionalInsurance)
        .map(Insurance::getName).orElse("123456");
  }

}
