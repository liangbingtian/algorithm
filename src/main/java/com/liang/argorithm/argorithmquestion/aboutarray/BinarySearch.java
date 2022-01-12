package com.liang.argorithm.argorithmquestion.aboutarray;

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
   * [] 区间对应着 left<=right  这时候不能left<right。因为退出时候是left=right,比如[2,2]，这个时候2就没有遍历就退出了。
   * [) 区间对应着 left<right  这个时候必然不能使用left<=right,因为(1),left=right的时候死循环。(2)右边界的时候数组溢出
   */
  public int BinarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length;
    while (left <= right) {
      int pivot = left + (right - left) / 2;
      if (nums[pivot] == target) {
        return pivot;
      } else if (nums[pivot] < target) {
        left = pivot + 1;
      } else {
        right = pivot;
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
   * 查找两个正向有序数组的中位数
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //1. 首先找到num1和num2中最小的，把最小的放到num1上。
    if (nums1.length > nums2.length) {
      int[] tmp = nums1;
      nums1 = nums2;
      nums2 = tmp;
    }
    //2. 令出整体中位数的位置。不管num1和num2的长度和为奇数或偶数，都是这个
    int m = nums1.length;
    int n = nums2.length;
    int totalIndex = (m + n + 1) / 2;
    //3. 开始二分查找nums1，找到最大的i使得nums[i-1]<=num[j]
    //因为我们找的是数组m里的i,但是用作比较的是i-1，其实上是左闭右开区间。
    int left = 0;
    int right = m;
    while (left < right) {
      //由于查找的是最大的i，又由于数组是递增的，所以查找都是靠右查，需要向上取整
      int i = left + (right - left + 1) / 2;
      int j = totalIndex - i;
      if (nums1[i-1]>nums2[j]) {
        right = i-1;
      }else {
        left = i;
      }
    }
    //4. 退出循环left==right时候，即找到了分割线的位置。
    int i = left;
    int j = totalIndex - i;
    //5. 处理边界的特殊情况
    int num1Left = (i==0)?Integer.MIN_VALUE:nums1[i-1];
    int num2Left = (j==0)?Integer.MIN_VALUE:nums2[j-1];
    //左闭右开。
    int num1Right = (i==m)?Integer.MAX_VALUE:nums1[i];
    int num2Right = (j==n)?Integer.MAX_VALUE:nums2[j];
    //6. 处理奇数和偶数中位数不同的情况
    if ((m+n)%2==0) {
      return (Math.max(num1Left, num2Left)+Math.min(num1Right, num2Right))/2.0;
    }else {
      return Math.max(num1Left, num2Left);
    }
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
    if (num == 1 || num == 0) {
      return true;
    }
    long left = 0;
    long right = num / 2;
    long count = 0;
    while (left <= right) {
      count++;
      long pivot = left + (right - left) / 2;
      long multiNumber = pivot * pivot;
      log.info("第{}次查找，left为:{},right为:{},pivot为:{},multiNumber为:{},num为:{}", count, left, right,
          pivot, multiNumber, num);
      if (pivot == num) {
        return true;
      } else if (multiNumber < num) {
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }
    }
    return false;
  }


}
