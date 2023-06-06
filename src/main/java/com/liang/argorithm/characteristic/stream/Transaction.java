package com.liang.argorithm.characteristic.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author liangbingtian
 * @date 2023/06/06 上午11:30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  private Trader trader;

  private int year;

  private int value;

}
