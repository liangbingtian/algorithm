package com.liang.argorithm.argorithmquestion.aboutchar;

import org.springframework.stereotype.Component;

/**
 * 文本串与模式串的匹配与比较等问题
 *
 * @author liangbingtian
 * @date 2021/06/02 下午9:00
 */
@Component
public class CharArrayCompare {

  /**
   * 实现 strStr() 函数。即KMP算法
   * <p>
   * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
   * <p>
   * 示例 1: 输入: haystack = "hello", needle = "ll" 输出: 2
   * <p>
   * 示例 2: 输入: haystack = "aaaaa", needle = "bba" 输出: -1
   */
  int strStr(String haystack, String needle) {
    int[] next = new int[needle.length()];
    getNext(next, needle);
    int j = -1;
    for (int i = 0; i < haystack.length(); ++i) {
      while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
        j = next[j];
      }
      if (haystack.charAt(i) == needle.charAt(j + 1)) {
        j++;
      }
      if (j == needle.length() - 1) {
        return i - needle.length() + 1;
      }
    }
    return -1;
  }

  void getNext(int[] nums, String needle) {
    int j = -1;
    nums[0] = j;
    for (int i = 1; i < needle.length(); ++i) {
      while (j >= 0 && nums[j + 1] != nums[i]) {
        j = nums[j];
      }
      if (nums[j + 1] == nums[i]) {
        j++;
      }
      nums[i] = j;
    }
  }

}
