package com.liang.argorithm.others;

import org.springframework.stereotype.Component;

/**
 * 爬楼梯问题
 *
 * @author liangbingtian
 * @date 2021/04/25 下午9:25
 */
@Component
public class ClimbStairs {

  /**
   * 第一种是使用递归方式的动态规划
   */
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }
    return climbStairs(n - 1) + climbStairs(n - 2);
  }

  public int climbStairs2(int n) {
    int[] memoryNums = new int[n+1];
    return climbStairsByMemoryArray(n, memoryNums);
  }

  /**
   * 第二种是使用一个记录数组去记录递归树中重复计算的节点
   */
  private int climbStairsByMemoryArray(int n, int[] memoryNums) {
    if (memoryNums[n] > 0) {
      return memoryNums[n];
    }
    if (n <= 2) {
      memoryNums[n] = n;
    } else {
      memoryNums[n] = climbStairsByMemoryArray(n - 1, memoryNums) + climbStairsByMemoryArray(n - 2, memoryNums);
    }
    return memoryNums[n];
  }

  /**
   * 比上一种还要优化的方式，化递归为迭代
   */
  public int climbStairs3(int n) {
    if (n<=2) {
      return n;
    }
    int[] nums = new int[n+1];
    nums[1] = 1;
    nums[2] = 2;
    for (int i=3;i<=n;++i) {
      nums[i] = nums[i-1] + nums[i-2];
    }
    return nums[n];
  }

  /**
   * 滚动数组的方式，将空间复杂度化为1
   */
  public int climbStairs4(int n) {
    if (n<=2) {
      return n;
    }
    int first = 1;
    int second = 2;
    for (int i=3;i<=n;++i) {
      int third = first + second;
      first = second;
      second = third;
    }
    return second;
  }

}
