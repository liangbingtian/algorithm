package com.liang.argorithm.aboutbinarytree;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 关于二叉树的前中后续遍历
 *
 * @author liangbingtian
 * @date 2021/06/15 下午7:18
 */
@Component
public class BinaryTreeTraversal {

  /**
   * 前序遍历的递归方式
   * @param root
   * @param result
   */
  void preorderTraversal(TreeNode root, List<Integer> result) {
   if (root==null) {
     return;
   }
   result.add(root.getValue());
   preorderTraversal(root.getLeft(), result);
   preorderTraversal(root.getRight(), result);
  }

  /**
   * 前序遍历的迭代方式
   * 根 左 右
   * @param root
   * @param result
   */
  void preorderTraversal2(TreeNode root, List<Integer> result) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root==null) {
      return;
    }
    deque.offerFirst(root);
    TreeNode curr;
    while (!deque.isEmpty()) {
      curr = deque.pollFirst();
      result.add(curr.getValue());
      if (curr.getRight()!=null) {
        deque.offerFirst(curr.getRight());
      }
      if (curr.getLeft()!=null) {
        deque.offerFirst(curr.getLeft());
      }
    }
  }

  /**
   * 中序遍历的递归写法
   */
  void midOrderTraversal(TreeNode root, List<Integer> result) {
    if (root==null){
      return;
    }
    midOrderTraversal(root.getLeft(), result);
    result.add(root.getValue());
    midOrderTraversal(root.getRight(), result);
  }

  /**
   * 中序遍历的迭代写法
   */
  void midOrderTraversal2(TreeNode root, List<Integer> result) {
    if (root == null) {
      return;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    while (curr!=null||!deque.isEmpty()) {
      if (curr!=null) {
        deque.offerFirst(curr);
        curr = curr.getLeft();
      }else {
        curr = deque.pollFirst();
        result.add(curr.getValue());
        curr = curr.getRight();
      }
    }
  }

  /**
   * 后续遍历的递归写法
   */
  void backOrderTraversal(TreeNode root, List<Integer> result) {
    if (root==null) {
      return;
    }
    backOrderTraversal(root.getLeft(), result);
    backOrderTraversal(root.getRight(), result);
    result.add(root.getValue());

  }

  Boolean findRoad(TreeNode m, StringBuilder result, int n) {
    //如果节点是空，表示没找到n节点，返回false
    if (m==null) {
      return false;
    }
    //如果找到了，返回true
    if (m.getValue()==n) {
      result.append(m.getValue());
      return true;
    }
    //递归查找左子树，如果已经找到,将路径加入进去
    if (findRoad(m.getLeft(), result, n)) {
      result.append(m.getValue());
    }
    //递归查找右子树，如果已经找到,将路径加入进去
    if (findRoad(m.getRight(), result, n)) {
      result.append(m.getValue());
    }
    return false;
  }

  /**
   * 后续遍历的迭代写法
   *
   * 根 左 右
   *
   * 左 右 跟
   *
   * 右 左 跟
   */
  void backOrderTraversal2(TreeNode root, List<Integer> result) {
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerFirst(root);
    while(!deque.isEmpty()) {
      TreeNode curr = deque.pollFirst();
      result.add(curr.getValue());
      if (curr.getLeft()!=null) {
        deque.offerFirst(curr.getLeft());
      }
      if (curr.getRight()!=null) {
        deque.offerFirst(curr.getRight());
      }
    }
    for (int i=0, j=result.size()-1;i<j;++i,--j){
      int tmp = result.get(i);
      result.set(i, result.get(j));
      result.set(j, tmp);
    }
  }

  /**
   * 二叉树的层序遍历
   * 类似题目。层序遍历的倒置，二叉树的右视图
   */
  void levelOrder(TreeNode root, List<Integer> result) {
    Deque<TreeNode> deque = new LinkedList<>();
    if (root == null) {
      return;
    }
    deque.offerLast(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 1;i<=size;++i) {
        TreeNode curr = deque.pollFirst();
        assert curr != null;
        result.add(curr.getValue());
        if (curr.getLeft()!=null) {
          deque.offerLast(curr.getLeft());
        }
        if (curr.getRight()!=null) {
          deque.offerLast(curr.getRight());
        }
      }
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class TreeNode {

    private Integer value;
    private TreeNode left;
    private TreeNode right;
  }

}
