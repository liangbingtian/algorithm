package com.liang.argorithm.argorithmquestion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 左孩子右兄弟节点
 *
 * @author liangbingtian
 * @date 2022/01/14 下午8:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PareBroNode {

  private Integer val;
  private PareBroNode lChild;
  private PareBroNode rBrother;
}
