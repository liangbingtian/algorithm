package com.liang.argorithm;

import com.liang.argorithm.argorithmquestion.aboutarray.SlidingWindow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 相关代码的测试用例
 *
 * @author liangbingtian
 * @date 2021/05/07 下午7:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SlidingWindowTest {

  @Autowired
  private SlidingWindow slidingWindow;

  /**
   *  7
   *  [2,3,1,2,4,3]。这个是第一个测试用例，本应该返回2，结果返回了-1
   *  这个问题修改完成。
   */
  @Test
  public void midSubArrayLenTest() {
    int[] nums = new int[]{2,3,1,2,4,3};
    System.out.println(slidingWindow.midSubArrayLen2(7, nums));
  }

  /**
   * 第一个测试用例过了
   * String s = "ADOBECODEBANC";
   *     String t = "ABC";
   * 第二个测试用例没过
   * "cabwefgewcwaefgcf"
   * "cae"
   */
  @Test
  public void minWindowsArrayTest() {
    String s = "cabwefgewcwaefgcf";
    String t = "cae";
    System.out.println(slidingWindow.minWindow(s, t));
  }

  @Test
  public void totalFruitTest() {
    int[] tree = new int[]{1,2,1};
    int result = slidingWindow.totalFruit2(tree);
    System.out.println(result);
  }
}
