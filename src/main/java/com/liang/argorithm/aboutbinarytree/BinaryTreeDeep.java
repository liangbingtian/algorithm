package com.liang.argorithm.aboutbinarytree;

import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author liangbingtian
 * @date 2021/06/26 下午10:23
 */
@Component
public class BinaryTreeDeep {

  private int result = 1;

  private int wplResult = 0;

  /**
   * 二叉树的最大深度，递归方式
   */
  int binaryTreeMaxDeep(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    int leftDepth = binaryTreeMaxDeep(treeNode.getLeft());
    int rightDepth = binaryTreeMaxDeep(treeNode.getRight());
    return Math.max(leftDepth, rightDepth) + 1;
  }

  /**
   * 二叉树的最小深度，递归方式，注意，只有从根节点到叶子节点才是一个二叉树的深度
   */
  int binaryTreeMinDeep(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    int leftDeep = binaryTreeMinDeep(treeNode.getLeft());
    int rightDeep = binaryTreeMinDeep(treeNode.getRight());
    if (treeNode.getLeft() == null && treeNode.getRight() != null) {
      return rightDeep + 1;
    }
    if (treeNode.getRight() == null && treeNode.getLeft() != null) {
      return leftDeep + 1;
    }
    return Math.min(leftDeep, rightDeep) + 1;
  }


  /**
   * 二叉树最大深度，层序遍历
   */
  int binaryTreeMaxDeep2(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    int depth = 0;
    deque.offerLast(treeNode);
    while (!deque.isEmpty()) {
      depth++;
      int size = deque.size();
      for (int i = 0; i < size; ++i) {
        TreeNode curr = deque.pollFirst();
        assert curr != null;
        if (curr.getLeft() != null) {
          deque.offerLast(curr.getLeft());
        }
        if (curr.getRight() != null) {
          deque.offerLast(curr.getRight());
        }
      }
    }
    return depth;
  }

  /**
   * 二叉树最小深度，层序遍历
   */
  int binaryTreeMinDeep2(TreeNode treeNode) {
    if (treeNode == null) {
      return 0;
    }
    Deque<TreeNode> queue = new LinkedList<>();
    int minDeep = 0;
    queue.offerLast(treeNode);
    while (!queue.isEmpty()) {
      minDeep++;
      int size = queue.size();
      for (int i = 0; i < size; ++i) {
        TreeNode curr = queue.pollFirst();
        assert curr != null;
        if (curr.getLeft() == null && curr.getRight() == null) {
          return minDeep;
        }
        if (curr.getLeft() != null) {
          queue.offerLast(curr.getLeft());
        }
        if (curr.getRight() != null) {
          queue.offerLast(curr.getRight());
        }
      }
    }
    return minDeep;
  }

  /**
   * 使用递归回溯法求二叉树的最高度
   *
   * @param root
   * @return
   */
  public int BinaryTreeMaxDeep2(TreeNode root) {
    boolean isBalance = true;
    if (root == null) {
      return 0;
    }
    getMaxDeep(root, 1);
    return result;
  }


  private void getMaxDeep(TreeNode node, int deep) {
    result = Math.max(result, deep);
    if (node.getLeft() != null) {
      getMaxDeep(node.getLeft(), deep + 1);
    }
    if (node.getRight() != null) {
      getMaxDeep(node.getRight(), deep + 1);
    }
  }

  /**
   * 二叉树的带权路径的和，二叉树所有的叶子节点乘以叶子节点的深度，先序遍历递归法
   */
  public void getWpl(TreeNode root, int depth) {
    //先序遍历计算查找所有叶子节点的深度，因为先序遍历可以一层一层的记录。后续遍历回溯只能统计总和
    if (root == null) {
      return;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      wplResult += depth * root.getValue();
    }
    getWpl(root.getLeft(), depth + 1);
    getWpl(root.getRight(), depth + 1);
  }

  /**
   * 求二叉树带权路径和，层序遍历法
   */
  public int getWpl2(TreeNode root) {
    int result = 0;
    int depth = 0;
    if (root == null) {
      return result;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      depth++;
      for (int i = 1; i <= size; ++i) {
        TreeNode curr = deque.pollFirst();
        if (curr.getLeft() != null && curr.getRight() != null) {
          result += depth * curr.getValue();
        }
        if (curr.getLeft() != null) {
          deque.offerLast(curr.getLeft());
        }
        if (curr.getRight() != null) {
          deque.offerLast(curr.getRight());
        }
      }
    }
    return result;
  }


  /**
   * 使用回溯法求解二叉树的所有路径
   */
  private void allPathInBinaryTree(TreeNode treeNode, List<Integer> pathValue,
      List<String> result) {
    if (treeNode == null) {
      return;
    }
    pathValue.add(treeNode.getValue());
    if (treeNode.getLeft() == null && treeNode.getRight() == null) {
      StringBuilder builder = new StringBuilder();
      int i = 0;
      for (; i < pathValue.size() - 1; ++i) {
        builder.append(pathValue.get(i));
        builder.append("->");
      }
      builder.append(pathValue.get(i));
      result.add(builder.toString());
    }
    if (treeNode.getLeft() != null) {
      allPathInBinaryTree(treeNode.getLeft(), pathValue, result);
      pathValue.remove(pathValue.size() - 1);
    }
    if (treeNode.getRight() != null) {
      allPathInBinaryTree(treeNode.getRight(), pathValue, result);
      pathValue.remove(pathValue.size() - 1);
    }
  }

  /**
   * 或者直接使用先序遍历法。求二叉树的所有路径
   */
  private void allPathInBinaryTree(TreeNode treeNode, String path, List<String> result) {
    if (treeNode == null) {
      return;
    }
    StringBuilder sb = new StringBuilder(path);
    sb.append(treeNode.getValue());
    if (treeNode.getLeft() == null && treeNode.getRight() == null) {
      result.add(sb.toString());
      return;
    }
    sb.append("->");
    if (treeNode.getLeft() != null) {
      allPathInBinaryTree(treeNode.getLeft(), sb.toString(), result);
    }
    if (treeNode.getRight() != null) {
      allPathInBinaryTree(treeNode.getRight(), sb.toString(), result);
    }
  }

  /**
   * 判断是否是平衡二叉树
   */
  public boolean isBalanceBinaryTree(TreeNode root) {
    return getDifferenceOfLeftAndRight(root) != -1;
  }


  /**
   * 一个节点左右子树高度的差值,并返回该节点的高度
   *
   * @param root
   * @return
   */
  private int getDifferenceOfLeftAndRight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftDeep = 0;
    int rightDeep = 0;
    leftDeep = getDifferenceOfLeftAndRight(root.getLeft());
    if (leftDeep == -1) {
      return -1;
    }
    rightDeep = getDifferenceOfLeftAndRight(root.getRight());
    if (rightDeep == -1) {
      return -1;
    }
    return Math.abs(leftDeep - rightDeep) > 1 ? -1 : Math.max(leftDeep, rightDeep) + 1;
  }
}
