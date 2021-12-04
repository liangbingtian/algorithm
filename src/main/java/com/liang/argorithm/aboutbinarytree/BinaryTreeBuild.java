package com.liang.argorithm.aboutbinarytree;

import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNodeCh;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
    //中序遍历数组如果为空的话返回null，这种情况是左子树或右子树为NULL的情况，比如中序遍历是[a,b],在中序遍历的右端点处也可以。后续遍历是[b,a]
    if (inOrderStart > inOrderEnd) {
      return null;
    }
    //后续遍历的最后一个元素为分隔点元素
    int rootNumber = postOrder[postOrderEnd];
    TreeNode treeNode = new TreeNode(rootNumber, null, null);
    //如果中序遍历数组为一个的话，那么直接返回这个。这种情况是根节点只有一个元素。比如中序遍历是[b,a,c],后续遍历是[b,c,a]
    if (inOrderStart == inOrderEnd) {
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
    if (midOrderBegin > midOrderEnd) {
      return null;
    }
    TreeNode root = new TreeNode(frontOrder[frontOrderBegin], null, null);
    if (midOrderBegin == midOrderEnd) {
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

  /**
   * 根据数组构建最大二叉树
   */
  TreeNode constructMaximumBinaryTree(int[] nums) {
    if (nums.length == 0) {
      return null;
    }
    return constructMaximumBinaryTree(nums, 0, nums.length - 1);
  }

  private TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
    //1.递归的终止条件
    if (left > right) {
      return null;
    }
    int maxValue = Integer.MIN_VALUE;
    int maxIndex = left;
    for (int i = left; i <= right; ++i) {
      if (nums[i] > maxValue) {
        maxIndex = i;
        maxValue = nums[i];
      }
    }
    TreeNode root = new TreeNode(maxValue, null, null);
    root.setLeft(constructMaximumBinaryTree(nums, left, maxIndex - 1));
    root.setRight(constructMaximumBinaryTree(nums, maxIndex + 1, right));
    return root;
  }

  /**
   * 求二叉树的WPL，所有叶子的带权路径之和,由于是算分叉，那么深度可从1开始，但是最后乘的时候要减一
   */
  int wplResult;

  public void wpl(TreeNode root, int depth) {
    if (root == null) {
      return;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      wplResult += (root.getValue() * (depth - 1));
      return;
    }
    wpl(root.getLeft(), depth + 1);
    wpl(root.getRight(), depth + 1);
  }

  /**
   * 二叉树转化为中缀表达式
   */
  StringBuilder expSb;

  public void treeToExp(TreeNode root, int depth) {
    if (root == null) {
      return;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      expSb.append(root.getValue());
      return;
    }
    if (depth > 1) {
      expSb.append("(");
    }
    treeToExp(root.getLeft(), depth);
    expSb.append(root.getValue());
    treeToExp(root.getRight(), depth);
    if (depth > 1) {
      expSb.append(")");
    }
  }

  /**
   * 中缀表达式转化为二叉树，先是迭代的写法
   */
  public TreeNodeCh ExpToTree(String str) {
    if (StringUtils.isEmpty(str)) {
      return null;
    }
    char[] chars = str.toCharArray();
    Deque<Character> ops = new ArrayDeque<>();
    Deque<TreeNodeCh> nums = new ArrayDeque<>();
    for (char ch : chars) {
      //1. 首先如果是非符号，则放入数据组
      if (opsPriority(ch) == 0) {
        nums.offerFirst(new TreeNodeCh(ch, null, null));
      } else {
        //如果是符号,如果符号栈顶为空，或者栈顶的优先级大于等于当前优先级，则弹出
        if (ops.isEmpty() || opsPriority(ops.peekFirst()) < opsPriority(ch)) {
          ops.offerFirst(ch);
        } else {
          //如若不然，将栈顶优先级大于等于当前符号的，都弹出
          while (!ops.isEmpty() && ops.peekFirst() != '('
              && opsPriority(ops.peekFirst()) <= opsPriority(ch)) {
            transform(ops, nums);
          }
          if (ch == ')') {
            ops.pollFirst();
          } else {
            ops.offerFirst(ch);
          }
        }
      }
    }
    while (!ops.isEmpty()) {
      transform(ops, nums);
    }
    return nums.peekFirst();
  }

  /**
   * 定义出符号优先级，数字越大优先级越高，把需要插入的非符号定义优先级为0
   *
   * @param c
   * @return
   */
  private int opsPriority(char c) {
    if (c == '(') {
      return 4;
    } else if (c == '*' || c == '/') {
      return 3;
    } else if (c == '+' || c == '-') {
      return 2;
    } else if (c == ')') {
      return 1;
    } else {
      return 0;
    }
  }

  private void transform(Deque<Character> ops, Deque<TreeNodeCh> nums) {
    TreeNodeCh right = new TreeNodeCh(Objects.requireNonNull(nums.pollFirst()).getValue(), null,
        null);
    TreeNodeCh left = new TreeNodeCh(Objects.requireNonNull(nums.pollFirst()).getValue(), null,
        null);
    TreeNodeCh root = new TreeNodeCh(ops.pollFirst(), null, null);
    root.setLeft(left);
    root.setRight(right);
    nums.offerFirst(root);
  }

  /**
   * 表达式树，使用后续遍历计算就转化为了逆波兰式求值
   */
  public int calculateEvalTree(TreeNodeCh root) {
    if (root == null) {
      return 0;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      return root.getValue() - '0';
    }
    int leftNum = calculateEvalTree(root.getLeft());
    int rightNum = calculateEvalTree(root.getRight());
    int resultValue = 0;
    switch (root.getValue()) {
      case '+':
        resultValue = leftNum + rightNum;
        break;
      case '-':
        resultValue = leftNum - rightNum;
        break;
      case '*':
        resultValue = leftNum * rightNum;
        break;
      case '/':
        resultValue = leftNum / rightNum;
        break;
    }
    return resultValue;
  }

  /**
   * 前序遍历构造二叉搜索树，由于二叉搜索树的前序遍历，所以第一个为根节点，
   * 且数组中肯定可以找到一部分小于根另一部分大于根的
   */
   public TreeNode dfs(int[] preorder, int left, int right) {
     if (left>right) {
       return null;
     }
     TreeNode root = new TreeNode(preorder[0], null, null);
     //找到最后一个左节点小于又节点的位置，这时候二分派上用场
     int l = left;
     int r = right;
     while (l<r) {
       int mid = l+(r-l+1)/2;
       if (preorder[l]<preorder[mid]) {
         l = mid;
       }else {
         r = mid-1;
       }
     }
     root.setLeft(dfs(preorder, left+1, l));
     root.setRight(dfs(preorder, l+1, right));
     return root;
   }

}
