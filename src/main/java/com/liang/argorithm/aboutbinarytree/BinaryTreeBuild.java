package com.liang.argorithm.aboutbinarytree;

import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
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
    if (postOrderStart>postOrderEnd) {
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
            postOrderStart + (delimiterIndex - inOrderStart), postOrderEnd-1));
    return treeNode;
  }

  /**
   * 根据中序遍历和先序遍历生成二叉树
   */
  public TreeNode buildTreeNodeFromMidAndFront(int[] midOrder, int midOrderBegin, int midOrderEnd,
                                                int[] frontOrder, int frontOrderBegin, int frontOrderEnd) {
    //找到返回的条件
    if (frontOrderBegin>frontOrderEnd) {
      return null;
    }
    TreeNode root = new TreeNode(frontOrder[frontOrderBegin], null, null);
    if (frontOrderBegin==frontOrderEnd) {
      return root;
    }
    //找到切分点
    int delimitIndex = 0;
    for (;delimitIndex<=midOrderEnd;delimitIndex++) {
      if (midOrder[delimitIndex]==frontOrder[frontOrderBegin]) {
        break;
      }
    }
    //再分别切分中序和先序数组
    root.setLeft(buildTreeNodeFromMidAndFront(midOrder, midOrderBegin, delimitIndex-1, frontOrder, frontOrderBegin+1, frontOrderBegin+delimitIndex-midOrderBegin));
    root.setRight(buildTreeNodeFromMidAndFront(midOrder, delimitIndex+1, midOrderEnd, frontOrder, frontOrderBegin+delimitIndex-midOrderBegin+1, frontOrderEnd));
    return root;
  }
}
