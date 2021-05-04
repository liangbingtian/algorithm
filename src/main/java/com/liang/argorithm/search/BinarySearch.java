package com.liang.argorithm.search;

import com.sun.security.auth.UnixNumericUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 二分查找及相关类型题
 *
 * @author liangbingtian
 * @date 2021/04/26 下午6:23
 */
@Component
@Slf4j
public class BinarySearch {

  /**
   * 二分查找，时间复杂度为logn 如果不存在元素时候，返回其应该插入的位置 条件，元素有序，并且元素不能重复
   */
  public int BinarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int pivot = left + (right - left) / 2;
      if (nums[pivot] == target) {
        return pivot;
      } else if (nums[pivot] < target) {
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }
    }
    return left;
  }

  /**
   * 在排序数组中查找元素的第一个位置和最后一个位置
   */
  public int[] searchFirstAndLastPosition(int[] nums, int target) {
    if (nums.length == 0) {
      return new int[]{-1, -1};
    }
    int firstPosition = findFirstPosition(nums, target);
    if (firstPosition == -1) {
      return new int[]{-1, -1};
    }
    int lastPosition = findLastPosition(nums, target);
    return new int[]{firstPosition, lastPosition};
  }

  /**
   * 注意要向上取整，比如{5,7,7,8,8}。如果left设置为8之后，不向上取整的话，middle还是原来的那个位置，这样就会造成死循环
   *
   * @param nums
   * @param target
   * @return
   */
  private int findLastPosition(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int pivot = left + ((right - left) + 1) / 2;
      if (nums[pivot] == target) {
        //如果等于那么最后一个target的位置要么是它要么在它的右边
        left = pivot;
      } else if (nums[pivot] < target) {
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }
    }
    return left;
  }

  /**
   * 找到排序数组中出现的第一个位置。保证跳出循环的时候是left==right的，这样好定位元素
   *
   * @param nums
   * @param target
   * @return
   */
  private int findFirstPosition(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int pivot = left + (right - left) / 2;
      if (nums[pivot] == target) {
        //如果等于那么第一target的位置要么是它要么在它的左边
        right = pivot;
      } else if (nums[pivot] < target) {
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }
    }
    if (target != nums[left]) {
      return -1;
    }
    return left;
  }

  /**
   * 二分查找求x的平方根的整数部分 设整数部分为k，则根号x>=k，所以x>=k^2
   */
  public int mySqrt(int a) {
    int left = 0;
    int right = a;
    int finalResult = -1;
    while (left < right) {
      int pivot = left + (right - left) / 2;
      log.info("left:{}, right:{}, pivot:{}", left, right, pivot);
      int number = a / pivot;
      if (pivot <= number) {
        finalResult = pivot;
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }
    }
    return finalResult;
  }

  public boolean isPerfectSquare(int num) {
    if(num == 1 || num == 0) {
      return true;
    }
    long left = 0;
    long right = num/2;
    long count = 0;
    while(left<=right) {
      count++;
      long pivot = left + (right-left)/2;
      long multiNumber = pivot * pivot;
      log.info("第{}次查找，left为:{},right为:{},pivot为:{},multiNumber为:{},num为:{}", count, left, right, pivot, multiNumber, num);
      if(pivot == num) {
        return true;
      }else if(multiNumber < num) {
        left = pivot + 1;
      }else {
        right = pivot - 1;
      }
    }
    return false;
  }


}
