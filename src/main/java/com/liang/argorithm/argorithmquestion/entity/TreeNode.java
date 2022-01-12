package com.liang.argorithm.argorithmquestion.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 二叉树节点
 *
 * @author liangbingtian
 * @date 2022/01/12 下午1:42
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class TreeNode {

  private Integer value;
  private TreeNode left;
  private TreeNode right;
}

