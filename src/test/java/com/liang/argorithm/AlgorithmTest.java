package com.liang.argorithm;

import com.alibaba.fastjson.JSON;
import com.liang.argorithm.others.ClimbStairs;
import com.liang.argorithm.others.RemoveArrayElements;
import com.liang.argorithm.search.BinarySearch;
import com.liang.argorithm.sort.SortPractice;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试一些结果
 *
 * @author liangbingtian
 * @date 2021/04/23 下午8:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgorithmTest {

  @Autowired
  private SortPractice sortPractice;

  @Autowired
  private BinarySearch binarySearch;

  @Autowired
  private ClimbStairs climbStairs;

  @Autowired
  private RemoveArrayElements removeElements;

  @Test
  public void testSortResult() {
    int[] array = new int[]{-13709, 8319, -13757, 3058, -13768, 2316, 13190, 459, 19593, -9457,
        -18022, 5908, 9305, -16493, 1780, 12605, 12775, 18021, 5194, 11046, 7074, 5408, 13928,
        -4107, 19755, 3899, 17786};
    List<Integer> reuslt = readSourceFileAndUseIt(
        "/Users/liangbingtian/Desktop/leecode测试文件/数组排序.json");
    int[] sortArray = reuslt.stream().mapToInt(Integer::intValue).toArray();
    long startTime = System.currentTimeMillis();
    sortPractice.quickSort1(sortArray);
    long endTime = System.currentTimeMillis();
    System.out.println("耗费时间为:" + (endTime - startTime));
    System.out.println(Arrays.toString(sortArray));
  }

  @Test
  public void testClimbStairs() {
    long startTime = System.currentTimeMillis();
    System.out.println("可以使用的方法数为:" + climbStairs.climbStairs4(45));
    long endTime = System.currentTimeMillis();
    System.out.println("耗费时间为:" + (endTime - startTime));
  }

  /**
   * 测试用例，最大整数2147483647，原因是一开始加一溢出
   * 测试用例，如果将long改成int的话，这个值无法测试通过:808201，原因是乘法溢出
   * 改成除法后，又把long改回了int。5测试也没通过,完全平方根不能用简单的/这种除法了，因为会产生向下取整的问题。
   */
  @Test
  public void testMySqrt() {
    boolean result = binarySearch.isPerfectSquare(5);
    System.out.println("结果为:" + result);
  }

  @Test
  public void testSearchRange() {
    int[] a = new int[]{5, 7, 7, 8, 8, 10};
    long startTime = System.currentTimeMillis();
    System.out.println("返回的结果为:"+ Arrays.toString(binarySearch.searchFirstAndLastPosition(a, 8)));
    long endTime = System.currentTimeMillis();
    System.out.println("消耗的时间为:" + (endTime-startTime));
  }

  /**
   * 第一个测试用例是"ab##"和"c#d#"的问题已经改成。
   * 第二个测试用例是"bbbextm"和"bbb#extm"最外层的while循环没有处理好
   */
  @Test
  public void testRemove() {
    String str1 = "bbbextm";
    String str2 = "bbb#extm";
    boolean result = removeElements.compareBackSpaceString2(str1, str2);
    System.out.println(result);
  }

  private List<Integer> readSourceFileAndUseIt(String fileName) {
    try {
      InputStream fileReadInputStream = new ByteArrayInputStream(
          Files.readAllBytes(Paths.get(fileName)));
      String arrayStr = IOUtils.toString(fileReadInputStream, StandardCharsets.UTF_8);
      return JSON.parseArray(arrayStr, Integer.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

}
