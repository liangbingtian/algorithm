package com.liang.argorithm.characteristic.optional;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author liangbingtian
 * @date 2023/06/06 下午3:01
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  private Car car;

  private Optional<Car> optionalCar;

}
