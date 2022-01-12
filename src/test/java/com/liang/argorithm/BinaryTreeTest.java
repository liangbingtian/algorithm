package com.liang.argorithm;

import com.liang.argorithm.argorithmquestion.aboutbinarytree.BinaryTreeBuild;
import com.liang.argorithm.argorithmquestion.aboutbinarytree.BinaryTreeTraversal.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 二叉树测试用例
 *
 * @author liangbingtian
 * @date 2021/07/12 下午10:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BinaryTreeTest {

  @Autowired
  private BinaryTreeBuild binaryTreeBuild;

  @Test
  public void buildBinaryTreeTest() {
    int[] middle = new int[]{9, 3, 15, 20, 7};
    int[] back = new int[]{9, 15, 7, 20, 3};
    TreeNode treeNode = binaryTreeBuild
        .buildTreeNodeFromMidAndBack(middle, back, 0, middle.length - 1, 0, back.length - 1);
    System.out.println("");
  }

  @Test
  public void buildBinaryTreeTest2() {
    int[] middle = new int[]{9, 3, 15, 20, 7};
    int[] front = new int[]{3, 9, 20, 15, 7};
    TreeNode treeNode = binaryTreeBuild
        .buildTreeNodeFromMidAndFront(middle, 0, middle.length - 1, front, 0, front.length - 1);
    System.out.println("");
  }

}
