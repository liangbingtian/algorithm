package com.liang.argorithm.argorithmquestion.aboutarray;

import org.springframework.stereotype.Component;

/**
 * 移除元素
 *
 * @author liangbingtian
 * @date 2021/05/03 下午10:48
 */
@Component
public class RemoveArrayElements {

  /**
   * 移除数组中的某个元素 由于数组是存储于一块地址连续的存储区域，所以只能覆盖不能删除 解法一：暴力求解法 时间复杂度是O(n^2)，空间复杂度是O(1)
   */
  public int removeElementsInArray(int[] nums, int value) {
    int size = nums.length;
    for (int i = 0; i < size; ++i) {
      if (value == nums[i]) {
        for (int j = i + 1; j < size; ++j) {
          nums[j - 1] = nums[j];
        }
        size--;
        //不能少了i--这一步。因为新移动过来的元素还并没有参与遍历。比如2,3,3这种情况，删除第二个3，如果不加i--，那么第三个3就被忽略了。
        i--;
      }
    }
    return size;
  }

  /**
   * 快慢指针法，将时间复杂度减少为O(n)
   */
  public int removeElementsInArray2(int[] nums, int value) {
    int lowIndex = 0;
    for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
      if (nums[fastIndex] != value) {
        nums[lowIndex++] = nums[fastIndex];
      }
    }
    return lowIndex;
  }

  /**
   * 移动零 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 和删除元素的这个题目类似，只不过删除的元素变成了零
   */
  public void moveZeros(int[] nums) {
    int slowIndex = 0;
    for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
        if (nums[fastIndex]!=0) {
          nums[slowIndex] = nums[fastIndex];
          if (slowIndex!=fastIndex) {
            nums[fastIndex]=0;
          }
          slowIndex++;
        }
    }
  }

  /**
   * 删除有序数组中的重复元素，同样使用快慢指针法，由于是有序数组， 若i<j,且nums[i] == nums[j],则如果存在i<k<j，则必有nums[i] == nums[k] ==
   * nums[j]
   */
  public int removeDuplicateElementsInSortedArray(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int lowIndex = 1;
    for (int fastIndex = 1; fastIndex < nums.length; ++fastIndex) {
      if (nums[fastIndex] != nums[fastIndex - 1]) {
        nums[lowIndex++] = nums[fastIndex];
      }
    }
    return lowIndex;
  }

  /**
   * 比较两个含有退格的字符串是否相等 方法一是重构字符串使用栈的方式，这样的时间和空间复杂度都是O(M+N)，其中M，N为字符串str1和str2的长度
   */
  public boolean compareBackSpaceString(String str1, String str2) {
    return buildStringForBackSpaceCompare(str1).equals(buildStringForBackSpaceCompare(str2));
  }

  /**
   * 重构两个字符串使得其不含有#
   *
   * @return
   */
  private String buildStringForBackSpaceCompare(String str) {
    StringBuilder stringBuffer = new StringBuilder();
    int length = str.length();
    for (int i = 0; i < length; ++i) {
      char ch = str.charAt(i);
      if (ch != '#') {
        stringBuffer.append(ch);
      } else {
        if (stringBuffer.length() != 0) {
          stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
      }
    }
    return stringBuffer.toString();
  }

  /**
   * 第二种方式，将空间复杂度变为了O(1)，设置两个指针，从后边往前分别遍历比较两个字符串
   */
  public boolean compareBackSpaceString2(String str1, String str2) {
    int i = str1.length() - 1;
    int j = str2.length() - 1;
    while (i >= 0 || j >= 0) {
      int skip1 = 0;
      int skip2 = 0;
      while (i >= 0) {
        if (str1.charAt(i) == '#') {
          skip1++;
          i--;
        } else if (skip1 > 0) {
          i--;
          skip1--;
        } else {
          break;
        }
      }
      while (j >= 0) {
        if (str2.charAt(j) == '#') {
          skip2++;
          j--;
        } else if (skip2 > 0) {
          j--;
          skip2--;
        } else {
          break;
        }
      }
      if (i >= 0 && j >= 0) {
        if (str1.charAt(i) != str2.charAt(j)) {
          return false;
        }
      } else {
        if (i >= 0 || j >= 0) {
          return false;
        }
      }
      i--;
      j--;
    }
    return true;
  }

  public int[] sortedSquares(int[] nums) {
    int rightIndex = nums.length;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] * nums[i - 1] <= 0) {
        rightIndex = i;
        break;
      }
    }
    int leftIndex = rightIndex - 1;
    int[] resultArray = new int[nums.length];
    int resultArrayIndex = 0;
    while (leftIndex >= 0 && rightIndex <= nums.length - 1) {
      int leftResult = nums[leftIndex] * nums[leftIndex];
      int rightResult = nums[rightIndex] * nums[rightIndex];
      if (leftResult <= rightResult) {
        resultArray[resultArrayIndex++] = leftResult;
        leftIndex--;
      } else {
        resultArray[resultArrayIndex++] = rightResult;
        rightIndex++;
      }
    }
    while (leftIndex >= 0) {
      if (nums[leftIndex] >= 0) {
        for (int i = 0; i <= leftIndex; ++i) {
          resultArray[resultArrayIndex++] = nums[i] * nums[i];
        }
        break;
      }
      resultArray[resultArrayIndex++] = nums[leftIndex] * nums[leftIndex];
      leftIndex--;
    }
    while (rightIndex <= nums.length - 1) {
      resultArray[resultArrayIndex++] = nums[rightIndex] * nums[rightIndex];
      rightIndex++;
    }
    return resultArray;
  }

  /**
   * 因为是增序的，所以前后两端的双指针会更简单
   */
  public int[] sortedSquares1(int[] nums) {
    int[] resultArray = new int[nums.length];
    for (int i = 0, j = nums.length - 1, resultIndex = nums.length - 1; i <= j; ) {
      if (nums[i] * nums[i] <= nums[j] * nums[j]) {
        resultArray[resultIndex--] = nums[j] * nums[j];
        j--;
      } else {
        resultArray[resultIndex--] = nums[i] * nums[i];
        i++;
      }
    }
    return resultArray;
  }

}
