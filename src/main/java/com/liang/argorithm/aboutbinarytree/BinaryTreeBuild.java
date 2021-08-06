package com.liang.argorithm.aboutbinarytree;

import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
import org.springframework.stereotype.Component;

/**
 * 二叉树构建
 *
 * @author liangbingtian
 * @date 2021/07/12 下午9:19
 */
@Component
public class BinaryTreeBuild {

  /**
   * 根据中序遍历和后续遍历构建二叉树
   */
  public TreeNode buildTreeNodeFromMidAndBack(int[] inOrder, int[] postOrder, int inOrderStart,
      int inOrderEnd, int postOrderStart, int postOrderEnd) {
    //后续遍历数组如果为空的话返回null
    if (postOrderStart > postOrderEnd) {
      return null;
    }
    //后续遍历的最后一个元素为分隔点元素
    int rootNumber = postOrder[postOrderEnd];
    TreeNode treeNode = new TreeNode(rootNumber, null, null);
    //如果后续遍历数组为一个的话，那么直接返回这个
    if (postOrderEnd == postOrderStart) {
      return treeNode;
    }
    //分隔中序遍历数组
    int delimiterIndex = inOrderStart;
    for (; delimiterIndex <= inOrderEnd; ++delimiterIndex) {
      if (inOrder[delimiterIndex] == rootNumber) {
        break;
      }
    }
    treeNode.setLeft(
        buildTreeNodeFromMidAndBack(inOrder, postOrder, inOrderStart, delimiterIndex - 1,
            postOrderStart, postOrderStart + (delimiterIndex - 1 - inOrderStart)));
    treeNode.setRight(
        buildTreeNodeFromMidAndBack(inOrder, postOrder, delimiterIndex + 1, inOrderEnd,
            postOrderStart + (delimiterIndex - inOrderStart), postOrderEnd - 1));
    return treeNode;
  }

  /**
   * 根据中序遍历和先序遍历生成二叉树
   */
  public TreeNode buildTreeNodeFromMidAndFront(int[] midOrder, int midOrderBegin, int midOrderEnd,
      int[] frontOrder, int frontOrderBegin, int frontOrderEnd) {
    //找到返回的条件
    if (frontOrderBegin > frontOrderEnd) {
      return null;
    }
    TreeNode root = new TreeNode(frontOrder[frontOrderBegin], null, null);
    if (frontOrderBegin == frontOrderEnd) {
      return root;
    }
    //找到切分点
    int delimitIndex = 0;
    for (; delimitIndex <= midOrderEnd; delimitIndex++) {
      if (midOrder[delimitIndex] == frontOrder[frontOrderBegin]) {
        break;
      }
    }
    //再分别切分中序和先序数组
    root.setLeft(buildTreeNodeFromMidAndFront(midOrder, midOrderBegin, delimitIndex - 1, frontOrder,
        frontOrderBegin + 1, frontOrderBegin + delimitIndex - midOrderBegin));
    root.setRight(buildTreeNodeFromMidAndFront(midOrder, delimitIndex + 1, midOrderEnd, frontOrder,
        frontOrderBegin + delimitIndex - midOrderBegin + 1, frontOrderEnd));
    return root;
  }

  /**
   * 合并二叉树 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
   * <p>
   * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
   */
  TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    //终止条件，如果其中一个为空，那么返回第二个
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }
    t1.setValue(t1.getValue() + t2.getValue());
    t1.setLeft(mergeTrees(t1.getLeft(), t2.getLeft()));
    t1.setRight(mergeTrees(t1.getRight(), t2.getRight()));
    return t1;
  }

  /**
   * 合并二叉树二 使用迭代
   */
  TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(t1);
    deque.offerLast(t2);
    while (!deque.isEmpty()) {
      TreeNode first = deque.pollFirst();
      TreeNode second = deque.pollFirst();
      first.setValue(first.getValue() + second.getValue());
      if (first.getLeft() != null && second.getLeft() != null) {
        deque.offerLast(first);
        deque.offerLast(second);
      }
      if (first.getRight() != null && second.getRight() != null) {
        deque.offerLast(first.getRight());
        deque.offerLast(second.getRight());
      }
      if (first.getLeft() == null && second.getLeft() != null) {
        first.setLeft(second.getLeft());
      }
      if (first.getRight() == null && second.getRight() != null) {
        first.setRight(second.getRight());
      }
    }
    return t1;
  }


}
