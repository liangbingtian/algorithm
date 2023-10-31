package com.liang.argorithm.characteristic.lambda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author liangbingtian
 * @date 2023/06/03 上午9:12
 */
@Getter
@Setter
@NoArgsConstructor
public class ComplexApple extends Apple{

  private String name;

  public ComplexApple(String color, Long weight, String name) {
    super(color, weight, 0d);
    this.name = name;
  }
}
