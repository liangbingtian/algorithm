package com.liang.argorithm.aboutbinarytree;

import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 关于二叉树节点的相关试题
 *
 * @author liangbingtian
 * @date 2021/07/11 下午12:06
 */
@Component
public class BinaryTreeNode {

  /**
   * 计算二叉树所有的左叶子的和
   */
  public int sumOfLeftLeaves(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    int leftSumLeavesValue = sumOfLeftLeaves(treeNode.getLeft());
    int rightSumLeavesValue = sumOfLeftLeaves(treeNode.getRight());
    int sumValue = 0;
    if (treeNode.getLeft() != null && treeNode.getLeft().getLeft() == null
        && treeNode.getLeft().getRight() == null) {
      sumValue += treeNode.getLeft().getValue();
    }
    sumValue += leftSumLeavesValue + rightSumLeavesValue;
    return sumValue;
  }

  /**
   * 计算所有二叉树的叶子节点，迭代法
   */
  public int sumOfLeftLeaves2(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerFirst(treeNode);
    int sumValue = 0;
    while (!deque.isEmpty()) {
      TreeNode curr = deque.pollFirst();
      if (curr.getLeft() != null && curr.getLeft().getLeft() == null
          && curr.getLeft().getRight() == null) {
        sumValue += curr.getLeft().getValue();
      }
      if (treeNode.getRight() != null) {
        deque.addFirst(treeNode.getRight());
      }
      if (treeNode.getLeft() != null) {
        deque.addFirst(treeNode.getLeft());
      }
    }
    return sumValue;
  }

  /**
   * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
   * <p>
   * 说明: 叶子节点是指没有子节点的节点。
   */
  boolean traversal(TreeNode curr, int count) {
    if (curr == null) {
      return false;
    }
    if (curr.getLeft() == null && curr.getRight() == null && count == 0) {
      return true;
    }
    if (curr.getLeft() == null && curr.getRight() == null) {
      return false;
    }
    if (curr.getLeft()!=null) {
      if (traversal(curr.getLeft(), count-curr.getLeft().getValue())){
        return true;
      }
    }
    if (curr.getRight()!=null) {
      return traversal(curr.getRight(), count - curr.getRight().getValue());
    }
    return false;
  }

  /**
   * 使用迭代法做
   */
  boolean traversal2(TreeNode curr, int count) {
    if (curr==null) {
      return false;
    }
    Deque<RecordPair> deque = new LinkedList<>();
    deque.addFirst(new RecordPair(curr, curr.getValue()));
    while (!deque.isEmpty()) {
      RecordPair tmp = deque.pollFirst();
      TreeNode node = tmp.getCurr();
      if (node.getLeft()==null&&node.getRight()==null&&tmp.getRoadValue()==count) {
        return true;
      }
      if (node.getRight()!=null) {
        deque.addFirst(new RecordPair(curr.getRight(), curr.getValue()+curr.getRight().getValue()));
      }
      if (node.getLeft()!=null) {
        deque.addFirst(new RecordPair(curr.getLeft(), curr.getValue()+curr.getLeft().getValue()));
      }
    }
    return false;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class RecordPair{
    private TreeNode curr;
    private Integer roadValue;
  }

}
