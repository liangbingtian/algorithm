package com.liang.argorithm.aboutbinarytree;

import apple.laf.JRSUIUtils.Tree;
import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * BST二叉搜索树
 *
 * @author liangbingtian
 * @date 2021/07/23 下午1:03
 */
@Component
public class BinarySearchTree {

  private Long MIN_VALUE = Long.MIN_VALUE;

  private Integer maxCount = 0;

  private Integer count = 0;

  private TreeNode prev = null;

  private List<Integer> result = new ArrayList<>();

  /**
   * 二叉搜索树中的搜索 递归写法，递归需要返回值的都是搜索单边，及不需要遍历整棵树
   */
  public TreeNode searchBST(TreeNode root, int value) {
    //1. 如果找到了，或者根节点为空，那么return
    if (root == null || root.getValue().equals(value)) {
      return root;
    }
    //2. 递归遍历
    if (value < root.getValue()) {
      return searchBST(root.getLeft(), value);
    }
    if (value > root.getValue()) {
      return searchBST(root.getRight(), value);
    }
    return null;
  }

  /**
   * 二叉搜索树中的迭代写法 一路找到底
   */
  public TreeNode searchBST2(TreeNode root, int value) {
    while (root != null) {
      if (root.getValue().equals(value)) {
        return root;
      }
      if (value < root.getValue()) {
        root = root.getLeft();
      }
      if (value > root.getValue()) {
        root = root.getRight();
      }
    }
    return null;
  }

  /**
   * 判断是否是二叉搜索树。其实就是判断中序遍历，后的元素是否是按照从小到大排列的
   */
  public boolean isBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    boolean left = isBST(root);
    if (MIN_VALUE < root.getValue()) {
      MIN_VALUE = root.getValue().longValue();
    } else {
      return false;
    }
    boolean right = isBST(root);
    return left && right;
  }

  /**
   * 判断是否是二叉搜索树的迭代写法
   */
  public boolean isBST2(TreeNode root) {
    if (root == null) {
      return true;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    TreeNode prev = null;
    while (curr != null || !deque.isEmpty()) {
      if (curr != null) {
        deque.offerFirst(curr);
        curr = curr.getLeft();
      } else {
        TreeNode node = deque.pollFirst();
        if (prev != null && prev.getValue() >= node.getValue()) {
          return false;
        }
        prev = node;
        curr = node.getRight();
      }
    }
    return true;
  }

  /**
   * 二叉搜索树中的众数。反正就是要中序遍历，这样就从小到大排序了 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
   * <p>
   * 假定 BST 有如下定义：
   * <p>
   * 结点左子树中所含结点的值小于等于当前结点的值 结点右子树中所含结点的值大于等于当前结点的值 左子树和右子树都是二叉搜索树 例如：
   * <p>
   * 给定 BST [1,null,2,2],
   */
  void searchBST(TreeNode root) {
    if (root == null) {
      return;
    }
    searchBST(root.getLeft());
    if (prev == null) {
      count = 1;
    } else if (prev.getValue().equals(root.getValue())) {
      count++;
    } else {
      count = 1;
    }
    prev = root;
    if (maxCount.equals(count)) {
      result.add(root.getValue());
    }
    if (count > maxCount) {
      maxCount = count;
      result.clear();
      result.add(root.getValue());
    }
    searchBST(root.getRight());
  }

  /**
   * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
   * <p>
   * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
   * <p>
   * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
   */
  TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == p || root == q || root == null) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
    TreeNode right = lowestCommonAncestor(root.getRight(), p, q);
    if (left != null && right != null) {
      return root;
    }
    if (left == null && right != null) {
      return right;
    } else if (left != null && right == null) {
      return left;
    } else {
      return null;
    }
  }

  /**
   * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
   *
   * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
   *
   * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
   */
  TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q) {
    if (cur==null) {
      return null;
    }
    if (p.getValue()<cur.getValue()&&q.getValue()<cur.getValue()) {
      TreeNode left = traversal(cur.getLeft(), p, q);
      if (left!=null) {
        return left;
      }
    }
    if (p.getValue()>cur.getValue()&&q.getValue()>cur.getValue()) {
      TreeNode right = traversal(cur.getRight(), p, q);
      if (right!=null) {
        return right;
      }
    }
    return cur;
  }

}
