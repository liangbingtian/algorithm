package com.liang.argorithm.argorithmquestion.aboutbinarytree;

import com.liang.argorithm.argorithmquestion.entity.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

  private Integer INT_MIN = Integer.MAX_VALUE;

  private Integer maxCount = 0;

  private Integer count = 0;

  private TreeNode prev = null;

  private Integer prevNumber;

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
   * 判断是否是二叉搜索树的递归方式的另一种，写法，不需要找最小值，每次把前一个节点记录下来就行
   *
   * @param root
   * @return
   */
  public boolean isBST3(TreeNode root) {
    if (root == null) {
      return true;
    }
    boolean left = isBST3(root.getLeft());
    if (prev == null || prev.getValue() < root.getValue()) {
      prev = root;
    } else {
      return false;
    }
    boolean right = isBST3(root.getRight());
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
   * 二叉搜索树任意两个节点的绝对值最小值，进行递归的方式 进行 中序遍历
   */
  public void getMinimumDifference(TreeNode root) {
    if (root == null) {
      return;
    }
    getMinimumDifference(root.getLeft());
    if (prev != null) {
      INT_MIN = Math.min(INT_MIN, root.getValue() - prev.getValue());
    }
    prev = root;
    getMinimumDifference(root.getRight());
  }

  /**
   * 二叉树搜索任意两个节点的最小值，进行迭代的方式 进行 中序遍历
   */
  public int getMinimumDifference1(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    while (curr != null || !deque.isEmpty()) {
      if (curr != null) {
        deque.offerFirst(curr);
        curr = curr.getLeft();
      } else {
        TreeNode tmp = deque.removeFirst();
        if (prev != null) {
          INT_MIN = Math.max(INT_MIN, tmp.getValue() - prev.getValue());
        }
        prev = tmp;
        curr = tmp.getRight();
      }
    }
    return INT_MIN;
  }

  /**
   * 如果是求二叉树的众数，那么将二叉树进行节点遍历，并将节点的以及节点的个数放到map里，将map里的value进行排序。 如果是二叉搜索树的话。就可以使用中序遍历。
   */
  public List<Integer> searchMaxValueInBinaryTree(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    //1. 遍历树。将节点出现的频次存到map里
    Map<Integer, Integer> map = new HashMap<>();
    traversalTree(map, root);
    //2. 对map进行排序
    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort(Entry.comparingByValue());
    //3. 遍历取出高频
    list.add(list.get(list.size() - 1));
    for (int i = list.size() - 2; i >= 0; --i) {
      if (list.get(i).getValue().equals(list.get(list.size() - 1).getValue())) {
        result.add(list.get(i).getKey());
      } else {
        break;
      }
    }
    return result;
  }

  private void traversalTree(Map<Integer, Integer> map, TreeNode root) {
    if (root == null) {
      return;
    }
    map.put(root.getValue(), map.getOrDefault(root.getValue(), 0) + 1);
    traversalTree(map, root.getLeft());
    traversalTree(map, root.getRight());
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
   * <p>
   * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
   * <p>
   * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
   */
  TreeNode traversal(TreeNode cur, TreeNode p, TreeNode q) {
    if (cur == null) {
      return null;
    }
    if (p.getValue() < cur.getValue() && q.getValue() < cur.getValue()) {
      TreeNode left = traversal(cur.getLeft(), p, q);
      if (left != null) {
        return left;
      }
    }
    if (p.getValue() > cur.getValue() && q.getValue() > cur.getValue()) {
      TreeNode right = traversal(cur.getRight(), p, q);
      if (right != null) {
        return right;
      }
    }
    return cur;
  }

  /**
   * 二叉搜索树的插入。第一种带有返回值的递归写法
   */
  TreeNode insertIntoBST(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val, null, null);
    }
    if (val < root.getValue()) {
      root.setLeft(insertIntoBST(root.getLeft(), val));
    }
    if (val > root.getValue()) {
      root.setRight(insertIntoBST(root.getRight(), val));
    }
    return root;
  }

  /**
   * 递归无返回值的写法
   */
  void insertIntoBST2(TreeNode root, int val) {
    if (root == null) {
      if (prev.getValue() > val) {
        prev.setLeft(new TreeNode(val, null, null));
      } else {
        prev.setRight(new TreeNode(val, null, null));
      }
      return;
    }
    prev = root;
    if (root.getValue() > val) {
      insertIntoBST2(root.getLeft(), val);
    }
    if (root.getValue() < val) {
      insertIntoBST2(root.getRight(), val);
    }
  }

  /**
   * 迭代的写法
   */
  TreeNode insertIntoBST3(TreeNode root, int val) {
    if (root == null) {
      return new TreeNode(val, null, null);
    }
    TreeNode curr = root;
    while (curr != null) {
      prev = curr;
      if (val < curr.getValue()) {
        curr = curr.getLeft();
      } else if (val > curr.getValue()) {
        curr = curr.getRight();
      }
    }
    if (val < prev.getValue()) {
      prev.setLeft(new TreeNode(val, null, null));
    } else if (val > prev.getValue()) {
      prev.setRight(new TreeNode(val, null, null));
    }
    return root;
  }

  /**
   * 二叉搜索树的节点删除，第一种是递归的写法,对于左子树和右子树节点都存在的情况，方法一，将左子树放到，右子树1的最左边的节点（右子树中序遍历的第一个节点） 的左子树,并且返回右子树1
   */
  TreeNode deleteBSTNode(TreeNode root, int val) {
    if (root == null) {
      return null;
    }
    if (val == root.getValue()) {
      //1.如果左右子树都为空，返回NULL。如果左不为空返回左，右不为空返回右
      if (root.getLeft() == null) {
        return root.getRight();
      } else if (root.getRight() == null) {
        return root.getLeft();
      } else {
        //2.如果左右子树都不为空，将左子树放到右子树的最左边节点的左孩子处,并返回右子树
        TreeNode newRoot = root.getRight();
        TreeNode curr = newRoot;
        while (curr.getLeft() != null) {
          curr = curr.getLeft();
        }
        curr.setLeft(root.getLeft());
        return newRoot;
      }
    }
    //递归逻辑
    if (val < root.getValue()) {
      root.setLeft(deleteBSTNode(root.getLeft(), val));
    } else if (val > root.getValue()) {
      root.setRight(deleteBSTNode(root.getRight(), val));
    }
    return root;
  }

  /**
   * 第二种方法，找到直接后继，并用直接后继的值代替要删除的值，而且转换为删除直接后继（直接后继即为右子树中序遍历的第一个节点）
   */
  TreeNode deleteBSTNodeAnother(TreeNode root, int val) {
    if (root == null) {
      return null;
    }
    if (val==root.getValue()) {
      if (root.getLeft()==null) {
        return root.getRight();
      }else if (root.getRight()==null) {
        return root.getLeft();
      }else {
        TreeNode curr = root.getRight();
        TreeNode prev = root;
        while (curr.getLeft()!=null) {
          prev = curr;
          curr = curr.getLeft();
        }
        root.setValue(curr.getValue());
        if (prev.getLeft()==curr) {
          prev.setLeft(deleteBSTNodeAnother(curr, val));
        }else {
          prev.setRight(deleteBSTNodeAnother(curr, val));
        }
      }
    }
    if (val<root.getValue()) {
      root.setLeft(deleteBSTNodeAnother(root.getLeft(),val));
    }else {
      root.setRight(deleteBSTNodeAnother(root.getRight(), val));
    }
    return root;
  }

  /**
   * 二叉搜索树的节点删除，迭代写法
   */
  TreeNode deleteBSTNode2(TreeNode root, int val) {
    if (root == null) {
      return null;
    }
    //记录父节点，用来删除当前节点的时候使用
    TreeNode prev = null;
    TreeNode curr = root;
    //递归的时候是找到的需要删除的节点，然后对该节点进行操作，然后返回给它的父亲节点，迭代写法的时候就需要记录下它的父亲节点
    while (curr != null) {
      if (curr.getValue().equals(val)) {
        break;
      }
      prev = curr;
      if (val < curr.getValue()) {
        curr = curr.getLeft();
      } else if (val > curr.getValue()) {
        curr = curr.getRight();
      }
    }
    //退出之后找到了要删除的节点，以及它的父亲节点。
    //如果父亲节点为空证明没有找到val，直接返回
    if (prev == null) {
      return deleteOneNode(curr);
    }
    //如果没找到，直接返回root
    if (curr == null) {
      return root;
    }
    //如果找到的话，再确定是左节点还是右节点，进行相应的调整，这里不能直接用等于，因为curr有可能是空，curr是空的时候证明没找到
    //不能直接删除节点
    if (prev.getLeft().equals(curr)) {
      prev.setLeft(deleteOneNode(curr));
    } else if (prev.getRight().equals(curr)) {
      prev.setRight(deleteOneNode(curr));
    }
    return root;
  }

  private TreeNode deleteOneNode(TreeNode node) {
    if (node.getLeft() == null) {
      return node.getRight();
    } else if (node.getRight() == null) {
      return node.getLeft();
    } else {
      TreeNode newRoot = node.getRight();
      TreeNode curr = newRoot;
      while (curr.getLeft() != null) {
        curr = curr.getLeft();
      }
      curr.setLeft(node.getLeft());
      return newRoot;
    }
  }

  /**
   * 修剪一颗二叉树，给定节点的区间范围[a, b]
   */
  public TreeNode trimBST(TreeNode node, int a, int b) {
    if (node == null) {
      return null;
    }
    //根据二叉搜索树的性质，只需要修剪一边就行。如果范围只在一一个子树
    if (b < node.getValue()) {
      return trimBST(node.getLeft(), a, b);
    }
    if (a > node.getValue()) {
      return trimBST(node.getRight(), a, b);
    }
    //如果节点在该区间范围内，则继续修剪其左子树和右子树
    node.setLeft(trimBST(node.getLeft(), a, b));
    node.setRight(trimBST(node.getRight(), a, b));
    return node;
  }

  /**
   * 使用迭代的方式进行二叉树的修剪
   */
  public TreeNode trimBST1(TreeNode root, int a, int b) {
    //1.如果root是空的话，返回null
    if (root == null) {
      return null;
    }
    //2. 然后搜索root,找到[a,b]范围内的root
    while (root != null && (root.getValue() < a || root.getValue() > b)) {
      if (b < root.getValue()) {
        root = root.getLeft();
      } else {
        root = root.getRight();
      }
    }
    //3. 将当前节点设置为root，进行处理
    TreeNode curr = root;
    //4. 如果curr是空，那么当前树中没有这个范围内的节点，应该全部删掉
    //5. 不是空的话进行修剪，先修剪curr的左孩子不在范围内的。
    while (curr != null) {
      while (curr.getLeft() != null && curr.getLeft().getValue() < a) {
        curr.setLeft(curr.getLeft().getRight());
      }
      //6. 不需管左孩子的右子树，因为左孩子的右子树必然是在满足的条件内的。
      curr = curr.getLeft();
    }
    curr = root;
    while (curr != null) {
      while (curr.getRight() != null && curr.getRight().getValue() > b) {
        curr.setRight(curr.getRight().getLeft());
      }
      curr = curr.getRight();
    }
    return root;
  }

  /**
   * 将有序数组转化为二叉搜索树，递归写法
   */
  public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
    //1.递归终止的条件
    if (left > right) {
      return null;
    }
    int middleIndex = left + (right - left) / 2;
    TreeNode root = new TreeNode(nums[middleIndex], null, null);
    root.setLeft(sortedArrayToBST(nums, left, middleIndex - 1));
    root.setRight(sortedArrayToBST(nums, middleIndex + 1, right));
    return root;
  }

  /**
   * 将有序数组转化为二叉搜索树，迭代写法
   */
  public TreeNode sortedArrayToBST(int[] nums) {
    Deque<TreeNode> nodeQue = new LinkedList<>();
    Deque<Integer> leftQue = new LinkedList<>();
    Deque<Integer> rightQue = new LinkedList<>();
    TreeNode root = new TreeNode();
    nodeQue.offerLast(root);
    leftQue.offerLast(0);
    rightQue.offerLast(nums.length - 1);
    while (!nodeQue.isEmpty()) {
      TreeNode tmpRoot = nodeQue.pollFirst();
      int left = leftQue.pollFirst();
      int right = rightQue.pollLast();
      int middle = left + (right - left) / 2;
      //相当于递归的结束条件
      if (left <= middle - 1) {
        tmpRoot.setLeft(new TreeNode());
        nodeQue.offerLast(tmpRoot.getLeft());
        leftQue.offerLast(left);
        rightQue.offerLast(middle - 1);
      }
      if (right >= middle + 1) {
        tmpRoot.setRight(new TreeNode());
        nodeQue.offerLast(tmpRoot.getRight());
        leftQue.offerLast(middle + 1);
        rightQue.offerLast(right);
      }
    }
    return root;
  }

  /**
   * 将二叉搜索树转化为累加树，递归写法
   */
  public void convertBST(TreeNode curr) {
    if (curr == null) {
      return;
    }
    convertBST(curr.getRight());
    prevNumber += curr.getValue();
    curr.setValue(prevNumber);
    convertBST(curr.getLeft());
  }

  /**
   * 把二叉树转化为累加树，迭代写法
   */
  public TreeNode convertBST2(TreeNode root) {
    if (root == null) {
      return null;
    }
    prevNumber = 0;
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    while (curr != null || !deque.isEmpty()) {
      if (curr != null) {
        deque.offerFirst(curr);
        curr = curr.getRight();
      } else {
        curr = deque.pollFirst();
        curr.setValue(prevNumber + curr.getValue());
        prevNumber = curr.getValue();
        curr = curr.getLeft();
      }
    }
    return root;
  }
}
