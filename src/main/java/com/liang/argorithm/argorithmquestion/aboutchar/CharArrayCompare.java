package com.liang.argorithm.argorithmquestion.aboutchar;

import org.springframework.http.converter.json.GsonBuilderUtils;
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

  /**
   * 最长公共前缀
   */
  public String getLongestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) {
      return "";
    }
    String prefix = strs[0];
    for (int i = 0; i < strs.length; ++i) {
      prefix = longestCommonPrefix(strs[i], prefix);
      if (prefix.length() == 0) {
        break;
      }
    }
    return prefix;
  }

  public String longestCommonPrefix(String str1, String str2) {
    //根据短的一个字符串找到最少公共前缀
    int length = Math.min(str1.length(), str2.length());
    int index = 0;
    while (index < length && str1.charAt(index) == str2.charAt(index)) {
      index++;
    }
    return str1.substring(0, index);
  }

  /**
   * 正则表达式匹配
   */
   public boolean isMatch(String s, String p) {
     //初始化boolean二维数组，其起始值f[0][0] = true;表示动态规划的最后的两个空串匹配上的值为true
     int m = s.length();
     int n = p.length();
     boolean[][] f = new boolean[m+1][n+1];
     f[0][0] = true;
     for (int i=1;i<=m;++i) {
       for (int j=1;j<=n;++j) {
         //如果p的最后一个位置是*
         if (p.charAt(j-1)=='*') {
           f[i][j] = f[i][j-2];
           if (matches(s, p, i, j-1)) {
             f[i][j] = f[i][j]||f[i-1][j];
           }
         }else {
           if (matches(s, p, i, j)) {
             f[i][j] = f[i-1][j-1];
           }
         }
       }
     }
     return f[m][n];
   }

  /**
   * 比较字符串s和字符串p的第i个和第j个字符是否相同，如果相同 返回true，如果不同返回false
   */
  public boolean matches(String s, String p, int i, int j) {
    if (i==0) {
      return false;
    }
    if (p.charAt(j-1)=='.') {
      return true;
    }
    return s.charAt(i-1)==p.charAt(j-1);
  }

  public static void main(String[] args) {
    String str1 = "aa";
    String str2 = "a*";
    boolean isTrue = new CharArrayCompare().isMatch(str1, str2);
    System.out.println(isTrue);
  }

}
