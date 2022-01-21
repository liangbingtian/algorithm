package com.liang.argorithm.argorithmquestion.wangdao;

import com.liang.argorithm.argorithmquestion.entity.TreeNode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import org.springframework.stereotype.Component;

/**
 * 第五章的第二部分
 *
 * p184页的课后题
 *
 * @author liangbingtian
 * @date 2021/09/23 下午8:00
 */
@Component
public class ChapterFive2 {
  /**
   * 给定一个关键字的集合，查找各关键字的概率相同，画出其最佳二叉排序树
   */
   public TreeNode questionFix(Integer[] nums) {
     //1.先将数组从小到大排序
     Arrays.sort(nums, Comparator.naturalOrder());
     return genBinarySearchTree1(nums, 0, nums.length-1);
   }

  /**
   * 生成二叉搜索树，用二分查找的方式生成，先使用递归写法
   */
   private TreeNode genBinarySearchTree1(Integer[] nums, int left, int right) {
     if (left>right) {
       return null;
     }
     int mid = left + (right-left)/2;
     TreeNode root = new TreeNode(nums[mid], null, null);
     root.setLeft(genBinarySearchTree1(nums, left, mid-1));
     root.setRight(genBinarySearchTree1(nums, mid+1, right));
     return root;
   }

  /**
   * 判断一个二叉树是否是二叉排序树，将其后续遍历，看是否是升序
   */
  public boolean questionSix(TreeNode root) {
    TreeNode prev = null;
    if (root==null) {
      return false;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    while(!deque.isEmpty()||curr!=null) {
      if (curr!=null) {
        deque.offerFirst(curr);
        curr = curr.getLeft();
      }else {
        curr = deque.pollFirst();
        if (prev!=null&&prev.getValue()>=curr.getValue()) {
          return false;
        }
        prev = curr;
        curr = curr.getRight();
      }
    }
    return true;
  }

  /**
   * 判断指定节点在二叉搜索树中的层次。就相当于二叉搜索树的搜索
   */
  public int questionSeven(TreeNode root, TreeNode node) {
    if (root==null) {
      return 0;
    }
    int depth = 0;
    while(root!=null) {
      depth++;
      if (root==node) {
        return depth;
      }else if (node.getValue()<root.getValue()) {
        root = root.getLeft();
      }else {
        root = root.getRight();
      }
    }
    return -1;
  }

  /**
   * 判断二叉树是否是平衡二叉树。后续遍历看高度
   */
  public boolean questionEight(TreeNode root) {
    return balance(root)!=-1;
  }

  private int balance(TreeNode curr) {
    if (curr==null) {
      return 0;
    }
    int left = balance(curr.getLeft());
    if (left==-1) {
      return left;
    }
    int right = balance(curr.getRight());
    if (right==-1) {
      return right;
    }
    return Math.abs(left-right)>1?-1:Math.max(left, right)+1;
  }

  /**
   * 找出二叉树最小最大关键字，因为是后续遍历是升序，所以最小关键字在最左边，最大关键字在最右边
   */
  public int[] questionNine(TreeNode root) {
    int[] result = new int[2];
    if (root==null) {
      return result;
    }
    TreeNode curr = root;
    while (curr.getLeft()!=null) {
      curr = curr.getLeft();
    }
    result[0] = curr.getValue();
    while (curr.getRight()!=null) {
      curr = curr.getRight();
    }
    result[1] = curr.getValue();
    return result;
  }

}
