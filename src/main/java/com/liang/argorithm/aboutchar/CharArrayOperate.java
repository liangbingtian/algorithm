package com.liang.argorithm.aboutchar;

import org.springframework.stereotype.Component;

/**
 * 关于字符串的相关操作
 *
 * @author liangbingtian
 * @date 2021/05/27 下午6:51
 */
@Component
public class CharArrayOperate {

  /**
   * 翻转字符串
   */
  public void reverseString(char[] a) {
    for (int i = 0, j = a.length - 1; i < a.length / 2; ++i, --j) {
      char tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
    }
  }

  /**
   * 给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
   * <p>
   * 如果剩余字符少于 k 个，则将剩余字符全部反转。
   * <p>
   * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
   * <p>
   * 示例:
   * <p>
   * 输入: s = "abcdefg", k = 2 输出: "bacdfeg"
   */
  public String reverseStr(String s, int k) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i += 2 * k) {
      //创建一个临时的StringBuffer然后把前k个放进去
      StringBuilder tmp = new StringBuilder();
      if (i + k <= s.length()) {
        tmp.append(s, i, i + k);
        tmp.reverse();
        //判断后边是不是还有k个。
        if (i + 2 * k <= s.length()) {
          tmp.append(s, i + k, i + 2 * k);
        } else {
          tmp.append(s, i + k, s.length());
        }
        result.append(tmp);
        continue;
      }
      tmp.append(s, i, s.length());
      tmp.reverse();
      result.append(tmp);
    }
    return result.toString();
  }

  /**
   * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
   * <p>
   * 示例 1： 输入：s = "We are happy." 输出："We%20are%20happy."
   */
  public String replaceBlankSpace(String s) {
    //java的字符串是放在了方法区而不是堆
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      if (" ".equals(String.valueOf(s.charAt(i)))) {
        result.append("%20");
      } else {
        result.append(s.charAt(i));
      }
    }
    return result.toString();
  }

  /**
   * 给定一个字符串，逐个翻转字符串中的每个单词。
   * <p>
   * 示例 1： 输入: "the sky is blue" 输出: "blue is sky the"
   * <p>
   * 示例 2： 输入: "  hello world!  " 输出: "world! hello" 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
   * <p>
   * 示例 3： 输入: "a good   example" 输出: "example good a" 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
   */
  public StringBuilder removeExtraSpace(String s) {
    //由于java字符串是存储在方法区中的，为常量，不能修改，所以只能重新创建
    int start = 0;
    int end = s.length() - 1;
    StringBuilder stringBuilder = new StringBuilder();
    //跳过前后的空格
    while (s.charAt(start) == ' ') {
      start++;
    }
    while (s.charAt(end) == ' ') {
      end--;
    }
    while (start < end) {
      if (s.charAt(start) != ' ' || stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
        stringBuilder.append(s.charAt(start));
      }
      start++;
    }
    return stringBuilder;
  }

  private void reverseString(StringBuilder sb, int start , int end) {
    while (start<end) {
      char tmpChar = sb.charAt(start);
      sb.setCharAt(start, sb.charAt(end));
      sb.setCharAt(end, tmpChar);
      start++;
      end--;
    }
  }

  private void reverseEachWord(StringBuilder sb) {
    int start = 0;
    int end = 1;
    int n = sb.length();
    while(start<n) {
      while (end<n&&sb.charAt(end)!=' ') {
        end++;
      }
      reverseString(sb, start, end-1);
      start = end+1;
      end = start+1;
    }
  }

  /**
   * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
   *
   * 示例 1： 输入: s = "abcdefg", k = 2 输出: "cdefgab"
   *
   * 示例 2： 输入: s = "lrloseumgh", k = 6 输出: "umghlrlose"   限制： 1 <= k < s.length <= 10000
   */
  public String reverseLeftWords(String s, int n) {
    StringBuilder stringBuilder = new StringBuilder(s);
    reverseString(stringBuilder, 0, n-1);
    reverseString(stringBuilder, n, s.length()-1);
    reverseString(stringBuilder, 0, s.length()-1);
    return stringBuilder.toString();
  }

  /**
   * 最长回文子串,方法一，暴力求解法
   */
  public String longestPalindrome(String s) {
    //1. 一些返回的特殊条件
    if (s.length()<2) {
      return s;
    }
    int maxLength = Integer.MIN_VALUE;
    int begin = 0;
    char[] array = s.toCharArray();
    for (int i = 0;i<array.length-1;++i) {
      for (int j = 0;j<array.length;++j) {
        //最长回文子串。如果长度是最长，并且回文
        if (j-i+1>maxLength&&isPalindrome(array, i, j)) {
          maxLength = j-i+1;
          begin = i;
        }
      }
    }
    return s.substring(begin, begin+maxLength);
  }

  boolean isPalindrome(char[] array, int i, int j) {
    while(i<j) {
      if (array[i]!=array[j]) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  /**
   * 最长回文子串，方法二，中心扩散法
   */
  public String longestPalindrome2(String s) {
    if (s.length()<2) {
      return s;
    }
    int maxLength = 1;
    int begin = 0;
    char[] array = s.toCharArray();
    for (int i=0;i<s.length()-1;++i) {
        int len1 = getPalindromeCenterLen(array, i, i);
        int len2 = getPalindromeCenterLen(array, i, i+1);
        len1 = Math.max(len1, len2);
        if (len1>maxLength) {
          maxLength = len1;
          begin = i-(maxLength-1)/2;
        }
    }
    return s.substring(begin, begin+maxLength);
  }

  private int getPalindromeCenterLen(char[] ch, int left, int right) {
    int i = left;
    int j = right;
    while (i>=0&&j<ch.length) {
      if (ch[i]==ch[j]) {
        i--;
        j++;
      }else {
        break;
      }
    }
    return j-i-1;
  }
}
