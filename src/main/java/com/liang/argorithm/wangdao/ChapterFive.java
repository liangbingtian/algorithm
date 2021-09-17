package com.liang.argorithm.wangdao;

import com.liang.argorithm.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 树与二叉树相关
 *
 * @author liangbingtian
 * @date 2021/09/05 下午5:19
 */
public class ChapterFive {

  public static List<Integer> result;

  public StringBuilder stringBuilder = new StringBuilder();

  public int depth = 0;

  /**
   * 线性表示的二叉树的最近公共祖先问题
   *
   * @param a
   * @param i
   * @param j
   * @return
   */
  int commonAncestor(int[] a, int i, int j) {
    if (i >= 0 && j >= 0) {
      while (i != j) {
        if (i > j) {
          i = i / 2;
        } else {
          j = j / 2;
        }
      }
      return a[i];
    }
    return -1;
  }

  /**
   * p141。第三题。用迭代法写后序遍历
   */
  public List<Integer> backTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    deque.offerFirst(curr);
    while (!deque.isEmpty()) {
      curr = deque.pollFirst();
      result.add(curr.getValue());
      if (curr.getLeft() != null) {
        deque.offerFirst(curr.getLeft());
      }
      if (curr.getRight() != null) {
        deque.offerFirst(curr.getRight());
      }
    }
    //再将结果逆转即可
    for (int i = 0, j = result.size() - 1; i < j; ++i, --j) {
      int tmp = result.get(i);
      result.set(i, result.get(j));
      result.set(j, tmp);
    }
    return result;
  }

  /**
   * 第四题。二叉树的层序遍历,从上到下，从右到左（往队列里放节点的时候先放右节点再放左节点就行了)。
   */
  public List<Integer> questionFour(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    deque.offerLast(curr);
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 1; i <= size; ++i) {
        curr = deque.pollFirst();
        result.add(curr.getValue());
        if (curr.getRight() != null) {
          deque.offerLast(curr.getRight());
        }
        if (curr.getLeft() != null) {
          deque.offerLast(curr.getLeft());
        }
      }
    }
    return result;
  }

  /**
   * 第五题，用非递归算法求二叉树的高度。就用层序遍历的做法就行
   */
  public int questionFive(TreeNode root) {
    int result = 0;
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    deque.offerLast(curr);
    while (!deque.isEmpty()) {
      int size = deque.size();
      result++;
      for (int i = 1; i <= size; ++i) {
        curr = deque.pollFirst();
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
   * 根据前序遍历和中序遍历构造二叉树
   *
   * @return
   */
  public TreeNode questionSix(int[] front, int frontFirst, int frontLast,
      int[] mid, int midFirst, int midLast) {
    //1.递归返回条件
    if (midFirst > midLast) {
      return null;
    }
    //2. 根节点
    int rootValue = front[frontFirst];
    TreeNode root = new TreeNode(rootValue, null, null);
    if (midFirst == midLast) {
      return root;
    }
    //3. 在中序中查找分隔点
    int i = midFirst;
    for (; i <= midLast; ++i) {
      if (mid[i] == rootValue) {
        break;
      }
    }
    root.setLeft(
        questionSix(front, frontFirst + 1, frontFirst + i - midFirst, mid, midFirst, i - 1));
    root.setRight(
        questionSix(front, frontFirst + i - midFirst + 1, frontLast, mid, i + 1, midLast));
    return root;
  }

  /**
   * 判断一个二叉树是否是完全二叉树
   */
  public boolean questionSeven(TreeNode root) {
    Deque<TreeNode> deque = new LinkedList<>();
    TreeNode curr = root;
    deque.offerLast(curr);
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 1; i <= size; ++i) {
        curr = deque.pollFirst();
        //如果当前的curr是空，那么查看队列之后的元素，如果都是空，则是完全二叉树，否则不是
        if (curr == null) {
          while (!deque.isEmpty()) {
            if (deque.pollFirst() != null) {
              return false;
            }
          }
        }
        deque.offerLast(curr.getLeft());
        deque.offerLast(curr.getRight());
      }
    }
    return true;
  }

  /**
   * 计算一颗普通二叉树的双分支节点的个数
   */
  public int questionEight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftNumber = questionEight(root.getLeft());
    int rightNumber = questionEight(root.getRight());
    return (leftNumber + rightNumber) + 1;
  }

  /**
   * 交换一颗二叉树的左右子树
   */
  public void questionNine(TreeNode root) {
    if (root == null) {
      return;
    }
    questionNine(root.getLeft());
    questionNine(root.getRight());
    TreeNode tmp = root.getLeft();
    root.setLeft(root.getRight());
    root.setRight(tmp);
  }

  /**
   * 先序遍历第k个节点的值
   */
  public TreeNode questionTen(TreeNode root, int num) {
    if (root == null) {
      return null;
    }
    int k = 0;
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerFirst(root);
    while (!deque.isEmpty()) {
      TreeNode curr = deque.pollFirst();
      if (k++ == num) {
        return curr;
      }
      if (curr.getRight() != null) {
        deque.offerFirst(curr.getRight());
      }
      if (curr.getLeft() != null) {
        deque.offerFirst(curr.getLeft());
      }
    }
    return null;
  }

  /**
   * 删除以它为根节点的子树，采用后续遍历的做法
   */
  public void questionEleven(TreeNode root) {

  }

  /**
   * 找到节点x，并打印节点x的所有祖先，才用后序遍历的做法 后续遍历，先找左子树，再找右子树，如果找到了就返回true，并根据true和false进行打印
   */
  public boolean questionTwelve(TreeNode root, int num) {
    result = new ArrayList<>();
    if (root == null) {
      return false;
    }
    if (num == root.getValue()) {
      return true;
    }
    //先找左子树
    if (questionTwelve(root.getLeft(), num)) {
      result.add(root.getValue());
      return true;
    }
    //左子树找不到再找右子树
    if (questionTwelve(root.getRight(), num)) {
      result.add(root.getValue());
      return true;
    }
    //左右子树都没找到
    return false;
  }

  /**
   * 求二叉树p，q的最近公共祖先
   *
   * @param root
   * @param p
   * @param q
   * @return
   */
  public TreeNode questionThirteen(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode leftNode = questionThirteen(root.getLeft(), p, q);
    TreeNode rightNode = questionThirteen(root.getRight(), p, q);
    if (leftNode != null && rightNode != null) {
      return root;
    } else if (leftNode != null) {
      return leftNode;
    } else {
      return rightNode;
    }
  }

  /**
   * 求二叉树的宽度
   */
  public int questionFourteen(TreeNode root) {
    int width = 0;
    if (root == null) {
      return 0;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(root);
    TreeNode curr = root;
    while (!deque.isEmpty()) {
      int size = deque.size();
      width = Math.max(width, size);
      for (int i = 1; i <= size; ++i) {
        curr = deque.pollFirst();
        width++;
        if (curr.getLeft() != null) {
          deque.offerLast(curr.getLeft());
        }
        if (curr.getRight() != null) {
          deque.offerLast(curr.getRight());
        }
      }
    }
    return width;
  }

  /**
   * 将满二叉树的先序遍历转化为后续遍历。这个没太闹明白
   */
  public int[] questionFifteen(int[] pre, int preLeft, int preRight, int[] post) {
    return null;
  }

  /**
   * 将二叉树的叶子节点链接成一个单链表，用所有的遍历都能做，这里选层序遍历
   */
  public void questionSixTeen(TreeNode root) {
    if (root == null) {
      return;
    }
    TreeNode pre = null;
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(root);
    TreeNode curr;
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 1; i <= size; ++i) {
        curr = deque.pollFirst();
        if (curr.getLeft() == null && curr.getRight() == null) {
          if (pre != null) {
            pre.setRight(curr);
          }
          pre = curr;
        }
        if (curr.getLeft() != null) {
          deque.offerLast(curr.getLeft());
        }
        if (curr.getRight() != null) {
          deque.offerLast(curr.getRight());
        }
      }
    }
  }

  /**
   * 判断两个二叉树是否相似，运用后序遍历，思路与判断二叉树是否对称的思路是一致的
   */
  public boolean questionSevenTeen(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    } else if (left == null || right == null) {
      return false;
    }
    boolean leftSimilar = questionSevenTeen(left.getLeft(), right.getLeft());
    boolean rightSimilar = questionSevenTeen(left.getRight(), right.getRight());
    return leftSimilar && rightSimilar;
  }


  /**
   * 查询中序线索二叉树中的指定节点在后序中的前驱的算法，这个写在了线索二叉树的位置里。
   *
   * @return
   */
  public TreeNode questionEighteen() {
    return null;
  }


  /**
   * 二叉树的带权路径，也就是求所有的叶子节点的权值乘以深度的和
   *
   * @param root
   * @return
   */
  public int questionNineteen(TreeNode root) {
    int result = 0;
    int depth = 0;
    if (root == null) {
      return 0;
    }
    Deque<TreeNode> deque = new LinkedList<>();
    deque.offerLast(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      depth++;
      for (int i = 1; i <= size; ++i) {
        TreeNode curr = deque.pollFirst();
        if (curr.getLeft() == null && curr.getRight() == null) {
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
   * 将二叉树转化为中缀表达式，用中序遍历做，注意加括号的位置。根节点不加，以及叶子节点不加。这是第20题
   */
  public void treeToExp(TreeNode root, int depth) {
    if (root == null) {
      return;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      stringBuilder.append(root.getValue());
      return;
    }
    if (depth > 1) {
      stringBuilder.append("(");
    }
    treeToExp(root.getLeft(), depth + 1);
    stringBuilder.append(root.getValue());
    treeToExp(root.getRight(), depth + 1);
    if (depth > 1) {
      stringBuilder.append(")");
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class PareBroNode {

    private Integer val;
    private PareBroNode lChild;
    private PareBroNode rBrother;
  }

  /**
   * 孩子兄弟表示法的森林，求叶子节点的个数
   */
  public int leaves(PareBroNode root) {
    //1.递归结束的条件
    if (root == null) {
      return 0;
    }
    //如果左孩子为空，证明该节点是叶子节点，返回它与它的有兄弟子树的叶子
    if (root.getLChild() == null) {
      return 1 + leaves(root.getRBrother());
    } else {
      return leaves(root.getLChild()) + leaves(root.getRBrother());
    }
  }

  /**
   * 孩子兄弟法表示的树，求树的深度，就是求树的最大高度
   */
  public int depth(PareBroNode root) {
    if (root == null) {
      return 0;
    }
    int hc = depth(root.getLChild());
    int hb = depth(root.getRBrother());
    //子女子树高度加一和兄弟子树高度比较，取大的那个
    if (hc + 1 > hb) {
      return hc + 1;
    } else {
      return hb;
    }
  }

  /**
   * 已知一颗树的层次序列和它各个节点的度，构造它的孩子兄弟链表
   */
  public PareBroNode createCSTree_Degree(int[] e, int[] degree) {
    List<PareBroNode> result = new ArrayList<>();
    for (int tmp : e) {
      result.add(new PareBroNode(tmp, null, null));
    }
    int nodesNumber = e.length;
    int k = 0;
    int d;
    for (int i = 0; i < nodesNumber; ++i) {
      d = degree[i];
      if (d>0) {
        k++;
        result.get(i).setLChild(result.get(k));
        for (int j = 2;j<=d;++j) {
          k++;
          result.get(k-1).setRBrother(result.get(k));
        }
      }
    }
    return result.get(0);
  }


}
