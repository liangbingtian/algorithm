package com.liang.argorithm.argorithmquestion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 线索二叉树的节点
 *
 * @author liangbingtian
 * @date 2022/01/14 下午8:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadNode {

  private int value;
  private ThreadNode left;
  private ThreadNode right;
  //0表示左孩子，1表示左前驱
  private int lTag;
  //0表示右孩子，1表示右前驱
  private int rTag;
}
