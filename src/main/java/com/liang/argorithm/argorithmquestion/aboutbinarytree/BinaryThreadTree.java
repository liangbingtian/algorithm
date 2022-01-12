package com.liang.argorithm.argorithmquestion.aboutbinarytree;

import com.liang.argorithm.argorithmquestion.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 线索二叉树相关的内容
 *
 * @author liangbingtian
 * @date 2021/09/12 下午8:07
 */
public class BinaryThreadTree {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class ThreadNode {

    private int value;
    private ThreadNode left;
    private ThreadNode right;
    //0表示左孩子，1表示左前驱
    private int lTag;
    //0表示右孩子，1表示右前驱
    private int rTag;
  }

  //-----------------中序线索二叉树-----------------------

  /**
   * 中序线索二叉树的构造
   */
  public void buildMidThreadBinaryTree(ThreadNode root) {
    if (root == null) {
      return;
    }
    ThreadNode prev = null;
    ThreadNode curr = root;
    Deque<ThreadNode> deque = new LinkedList<>();
    while (curr != null || !deque.isEmpty()) {
      if (curr != null) {
        deque.offerFirst(curr);
        curr = curr.left;
      } else {
        curr = deque.pollFirst();
        if (curr.getLeft() == null) {
          curr.setLeft(prev);
          curr.setLTag(1);
        }
        if (prev != null && prev.getRight() == null) {
          prev.setRight(curr);
          prev.setLTag(1);
        }
        prev = curr;
        curr = curr.getRight();
      }
    }
  }

  /**
   * 查找中序线索二叉树的第一个节点
   */
  public ThreadNode findFirstNodeInMidThread(ThreadNode root) {
    while (root.getLTag() == 0) {
      root = root.getLeft();
    }
    return root;
  }

  /**
   * 查找中序线索二叉树的最后一个节点
   */
  public ThreadNode findLastNodeInMidThread(ThreadNode root) {
    while (root.getRTag() == 0) {
      root = root.getRight();
    }
    return root;
  }

  /**
   * 查找中序线索二叉树中的节点p在中序序列下的后继
   */
  public ThreadNode findNextInMidThread(ThreadNode root) {
    if (root.getRTag() == 0) {
      return findFirstNodeInMidThread(root.getRight());
    }
    return root.getRight();
  }

  /**
   * 查找中序线索二叉树节点p在中序序列下的前驱
   */
  public ThreadNode findPrevInMidThread(ThreadNode root) {
    if (root.getLTag() == 0) {
      return findLastNodeInMidThread(root.getLeft());
    }
    return root.getLeft();
  }

  //--------------------先序线索二叉树-----------------------

  /**
   * 先序线索二叉树的创建
   * @param root
   */
  public void buildFrontThreadBinaryTree(ThreadNode root) {
    if (root == null) {
      return;
    }
    ThreadNode prev = null;
    Deque<ThreadNode> deque = new LinkedList<>();
    deque.offerFirst(root);
    ThreadNode curr = root;
    while (!deque.isEmpty()) {
      curr = deque.pollFirst();
      if (curr.getLeft() == null) {
        curr.setLeft(prev);
        curr.setLTag(1);
      }
      if (prev != null && prev.getRight() == null) {
        prev.setRight(curr);
        prev.setRTag(1);
      }
      prev = curr;
    }
  }

  /**
   * 先序线索二叉树需要节点的后继
   */
  public ThreadNode findNextNodeFrontThreadBinaryTree(ThreadNode root) {
    if (root.getRTag()==1) {
      return root.getRight();
    }
    if (root.getLTag()==0) {
      return root.getLeft();
    }else {
      return root.getRight();
    }
  }

  /**
   * 在中序线索二叉树中查找指定节点p在后续的前驱节点
   */
  public ThreadNode inPostPre(ThreadNode p) {
    //1.在后续序列中，如果p有右子女，则右子女是其前驱，若无右子女而有左子女，则左子女是其前驱
    if (p.getRTag()==0) {
      return p.getRight();
    }else if (p.getLTag()==0) {
      return p.getLeft();
    }else if (p.getLeft()==null) {
      //如果p是中序遍历下的第一个节点，则其后序遍历也无前驱
      return null;
    }else {
      //查找p的祖先，若祖先存在，查找祖先的左子女
      while (p.getLTag()==1&&p.getLeft()!=null) {
        p = p.getLeft();
      }
      //要么就走到了有左子女的节点，要么就走到了单支树的根节点
      if (p.getLTag()==0) {
        return p.getLeft();
      }else {
        //单枝树的话，那节点p在后续遍历中没有前驱
        return null;
      }
    }
  }


}
