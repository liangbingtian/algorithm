package com.liang.argorithm.argorithmquestion.aboutbinarytree;

import com.liang.argorithm.argorithmquestion.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
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
    if (curr.getLeft() != null) {
      if (traversal(curr.getLeft(), count - curr.getLeft().getValue())) {
        return true;
      }
    }
    if (curr.getRight() != null) {
      return traversal(curr.getRight(), count - curr.getRight().getValue());
    }
    return false;
  }

  /**
   * 使用迭代法做
   */
  boolean traversal2(TreeNode curr, int count) {
    if (curr == null) {
      return false;
    }
    Deque<RecordPair> deque = new LinkedList<>();
    deque.addFirst(new RecordPair(curr, curr.getValue()));
    while (!deque.isEmpty()) {
      RecordPair tmp = deque.pollFirst();
      TreeNode node = tmp.getCurr();
      if (node.getLeft() == null && node.getRight() == null && tmp.getRoadValue() == count) {
        return true;
      }
      if (node.getRight() != null) {
        deque.addFirst(
            new RecordPair(curr.getRight(), curr.getValue() + curr.getRight().getValue()));
      }
      if (node.getLeft() != null) {
        deque.addFirst(new RecordPair(curr.getLeft(), curr.getValue() + curr.getLeft().getValue()));
      }
    }
    return false;
  }

  /**
   * 求普通二叉树的节点个数，先用递归做
   */
  public int normalBinaryTreeNodesNumber(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftNodeNumbers = normalBinaryTreeNodesNumber(root.getLeft());
    int rightNodeNumbers = normalBinaryTreeNodesNumber(root.getRight());
    return leftNodeNumbers + rightNodeNumbers + 1;
  }

  /**
   * 求普通二叉树的个数，再用迭代层序遍历去做
   */
  public int normalBinaryTreeNodesNumber2(TreeNode root) {
    int result = 0;
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      result += size;
      for (int i = 1; i <= size; ++i) {
        TreeNode tmpNode = deque.pollFirst();
        if (tmpNode.getLeft() != null) {
          deque.offerLast(tmpNode.getLeft());
        }
        if (tmpNode.getRight() != null) {
          deque.offerLast(tmpNode.getRight());
        }
      }
    }
    return result;
  }

  /**
   * 完全二叉树的情况，可以使用完全二叉树的性质
   */
  public int countMaxBinaryTreeNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    TreeNode leftNode = root.getLeft();
    TreeNode rightNode = root.getRight();
    int leftHeight = 0, rightHeight = 0;
    while (leftNode != null) {
      leftNode = leftNode.getLeft();
      leftHeight++;
    }
    while (rightNode != null) {
      rightNode = rightNode.getRight();
      rightHeight++;
    }
    //看最左边节点和最右边节点的深度是否相等，相等则用公式计算
    if (leftHeight == rightHeight) {
      return (int) Math.pow(2, leftHeight + 1) - 1;
    }
    return countMaxBinaryTreeNodes(root.getLeft()) + countMaxBinaryTreeNodes(root.getRight()) + 1;
  }

  /**
   * 完全二叉树的节点个数.用另一种递归的方式去做.
   */
  public int countMaxBinaryTreeNodes2(TreeNode root) {
    if (root==null) {
      return 0;
    }
    int result = 1;
    int leftDepth = getDepthOfTree(root.getLeft());
    int rightDepth = getDepthOfTree(root.getRight());
    if (leftDepth==rightDepth) {
      result = result + (int)(Math.pow(2, leftDepth)-1) + countMaxBinaryTreeNodes2(root.getRight());
    }else {
      result = result + (int)(Math.pow(2, rightDepth)-1) + countMaxBinaryTreeNodes2(root.getLeft());
    }
    return result;
  }

  private int getDepthOfTree(TreeNode root) {
    int result = 0;
    while (root!=null) {
      root = root.getLeft();
      result++;
    }
    return result;
  }

  /**
   * 判断二叉树是否是对称,采用后续遍历的做法
   */
  private boolean compare(TreeNode leftNode, TreeNode rightNode) {
    if (leftNode==null&&rightNode!=null) {
      return false;
    }else if (leftNode!=null&&rightNode==null) {
      return false;
    }else if (leftNode == null&&rightNode==null) {
      return true;
    }else if (!leftNode.getValue().equals(rightNode.getValue())) {
      return false;
    }
    boolean left = compare(leftNode.getLeft(), rightNode.getRight());
    boolean right = compare(leftNode.getRight(), rightNode.getLeft());
    return left&&right;
  }

  /**
   * 判断二叉树是否对称，采用迭代法
   */
  private boolean compare2(TreeNode root) {
    if (root==null) {
      return true;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(root.getLeft());
    deque.offerLast(root.getRight());
    while (!deque.isEmpty()) {
      TreeNode leftNode = deque.pollFirst();
      TreeNode rightNode = deque.pollFirst();
      if (leftNode==null&&rightNode==null) {
        continue;
      }
      if (leftNode==null||rightNode==null||leftNode.getValue().equals(rightNode.getValue())) {
        return false;
      }
      deque.offerLast(leftNode.getLeft());
      deque.offerLast(rightNode.getRight());
      deque.offerLast(leftNode.getRight());
      deque.offerLast(rightNode.getLeft());
    }
    return true;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class RecordPair {

    private TreeNode curr;
    private Integer roadValue;
  }

}
