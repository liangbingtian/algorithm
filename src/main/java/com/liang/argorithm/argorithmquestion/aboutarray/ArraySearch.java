package com.liang.argorithm.argorithmquestion.aboutarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * @author liangbingtian
 * @date 2021/07/27 下午7:03
 */
@Component
public class ArraySearch {

  /**
   * 三数之和 判断数组中是否存在a+b+c=0 1. 暴力法
   */
  List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length <= 2) {
      return Collections.emptyList();
    }
    Arrays.sort(nums);
    Set<List<Integer>> result = new HashSet<>();
    for (int i = 0; i < nums.length - 2; ++i) {
      for (int j = i + 1; j < nums.length - 1; ++j) {
        for (int k = j + 1; k < nums.length; ++k) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> values = Arrays.asList(nums[i], nums[j], nums[k]);
            result.add(values);
          }
        }
      }
    }
    return new ArrayList<>(result);
  }

  /**
   * 主要元素问题，如果找到元素，并且元素个数超过了n/2，就证明找到了。 做法1，哈希表。时间复杂度和空间复杂度都为O(n)
   */
  public int majorityElement(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int num = nums[i];
      map.put(num, map.getOrDefault(i, 0) + 1);
      if (map.get(num) > nums.length / 2) {
        return num;
      }
    }
    return -1;
  }

  /**
   * 主要元素，摩尔投票算法
   *
   * @param nums
   * @return
   */
  public int majorityElement2(int[] nums) {
    int candidate = -1;
    int count = 0;
    //1. 第一轮遍历找出候选人。
    for (int value : nums) {
      if (count == 0) {
        candidate = value;
      }
      if (value == candidate) {
        count++;
      } else {
        count--;
      }
    }
    //2. 再判断候选人有没有资格
    count = 0;
    for (int num : nums) {
      if (num == candidate) {
        count++;
      }
    }
    return count > nums.length / 2 ? candidate : -1;
  }

  /**
   * 缺失的第一个最小正整数。 将数组转化为哈希表实现O(N),O(1)
   */
  public int firstMissingPositive(int[] nums) {
    //将所有的负数转化为正。
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] <= 0) {
        nums[i] = nums.length + 1;
      }
    }
    //搜索所有在范围[1,N]内的数，如果存在，则在数组中相应的位置打上负号标记
    for (int value : nums) {
      int searchNum = Math.abs(value);
      if (searchNum >= 1 && searchNum <= nums.length) {
        nums[searchNum - 1] = -Math.abs(nums[searchNum - 1]);
      }
    }
    //查找第一个正数，如果存在则返回下标，如果不存在，则返回nums.length+1
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] > 0) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  /**
   * 有序数组的平方
   *
   * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
   *
   * 示例 1： 输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100] 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
   *
   * 示例 2： 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
   *
   * 由于原数组有序，则负数平方有可能是正数，但是不论是负数还是正数都是在最左边和最右边，此时可以使用双指针法
   */
  public int[] sortedSquares(int[] a) {
    int n = a.length-1;
    int[] result = new int[a.length];
    for (int i=0,j=a.length-1;i<=j;) {
      if (a[i]*a[i]<=a[j]*a[j]) {
        result[n--] = a[j]*a[j];
        j--;
      }else {
        result[n--] = a[i]*a[i];
        i++;
      }
    }
    return result;
  }

  /**
   * 数组逆转，才用递归方式
   */
   public void reverseNum(int[] nums, int depth) {
     if (depth>=nums.length) {
       return;
     }
     int tmp = nums[depth];
     reverseNum(nums, depth+1);
     nums[nums.length-1-depth] = tmp;
   }

}
