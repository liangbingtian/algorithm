package com.liang.argorithm.argorithmquestion.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 值为字符串的二叉树
 *
 * @author liangbingtian
 * @date 2022/01/12 下午1:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class TreeNodeCh {

  private Character value;
  private TreeNodeCh left;
  private TreeNodeCh right;
}



