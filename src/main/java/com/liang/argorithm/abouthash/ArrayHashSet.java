package com.liang.argorithm.abouthash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * 关于数组的散列表
 *
 * @author liangbingtian
 * @date 2021/05/20 下午7:44
 */
@Component
public class ArrayHashSet {

  /**
   * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
   * <p>
   * 示例 1: 输入: s = "anagram", t = "nagaram" 输出: true
   * <p>
   * 示例 2: 输入: s = "rat", t = "car" 输出: false
   * <p>
   * 说明: 你可以假设字符串只包含小写字母。 数组其实就是一个简单的哈希表
   * <p>
   * 1. 暴力法，将两个字符串按照字符的ASC码值排序，然后将排序后的两个字符串进行比较就行了。 2. 下面这种用数组代替哈希表的做法，用哈希表统计字母的频次，数组也是特殊的哈希表，它的哈希函数就是每个字母的asc码值
   *
   * @param s
   * @param t
   * @return
   */
  public boolean isAnagram(String s, String t) {
    int[] record = new int[26];
    for (char a : s.toCharArray()) {
      record[a - 'a'] += 1;
    }
    for (char c : t.toCharArray()) {
      record[c - 'a'] -= 1;
    }
    for (int num : record) {
      if (num != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 字母异位词分组，第一种解法，排序。因为两个字符串异位，所以将他们按照asc码排好序，按照顺序排好的字符串就是key值 时间复杂度O(nklogk) 其中n是字符串的个数
   */
  public List<List<String>> anagramGroup(String[] strs) {
    HashMap<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] array = str.toCharArray();
      Arrays.sort(array);
      String key = new String(array);
      List<String> list = map.getOrDefault(key, new ArrayList<>());
      list.add(str);
      //如果没有的话，则需要把listput回去，所以这里就put回去了
      map.put(key, list);
    }
    return new ArrayList<>(map.values());
  }

  /**
   * 解法二，因为字符串异位，所以两个字符串包含的小写字母以及小写字母的个数是相同的，可以用这些条件拼接到一起，形成key的值 时间复杂度减少了，因为少去了排序的时间复杂度
   */
  public List<List<String>> anagramGroup2(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      int[] nums = new int[26];
      char[] array = str.toCharArray();
      for (char ch : array) {
        nums[ch - 'a']++;
      }
      StringBuilder key = new StringBuilder();
      for (int i = 0; i < nums.length; ++i) {
        if (nums[i] > 0) {
          key.append(i + 'a');
          key.append(nums[i]);
        }
      }
      List<String> list = map.getOrDefault(key.toString(), new ArrayList<>());
      list.add(str);
      map.put(key.toString(), list);
    }
    return new ArrayList<>(map.values());
  }

  /**
   * 编写一个算法来判断一个数 n 是不是快乐数。
   * <p>
   * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为
   * 1，那么这个数就是快乐数。
   * <p>
   * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
   * <p>
   * 示例：
   * <p>
   * 输入：19 输出：true 解释： 1^2 + 9^2 = 82 8^2 + 2^2 = 68 6^2 + 8^2 = 100 1^2 + 0^2 + 0^2 = 1
   */
  public boolean isHappy(int n) {
    Set<Integer> tmpSet = new HashSet<>();
    while (true) {
      int sum = getSum(n);
      if (sum == 1) {
        return true;
      }
      if (tmpSet.contains(sum)) {
        return false;
      } else {
        tmpSet.add(sum);
      }
      n = sum;
    }
  }

  private int getSum(int n) {
    int sum = 0;
    while (n > 0) {
      sum += (n % 10) * (n % 10);
      n /= 10;
    }
    return sum;
  }

  /**
   * 给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。
   * <p>
   * 示例 1：
   * <p>
   * 输入：words = ["bella","label","roller"] 输出：["e","l","l"] 示例 2：
   * <p>
   * 输入：words = ["cool","lock","cook"] 输出：["c","o"]
   * <p>
   * 提示：
   * <p>
   * 1 <= words.length <= 100 1 <= words[i].length <= 100 words[i] 由小写英文字母组成
   */
  public List<String> commonChars(String[] A) {
    List<String> result = new ArrayList<>();
    if (A.length == 0) {
      return result;
    }
    //用来统计所有字符串中每个字母出现的频度，并且先用第一个字符串初始化
    int[] hash = new int[26];
    for (int i = 0; i < A[0].length(); ++i) {
      hash[A[0].charAt(i) - 'a']++;
    }
    //统计其他字符串的字符出现频度，并且更新hash中的最小频度
    for (int i = 1; i < A.length; ++i) {
      int[] otherStr = new int[26];
      for (int j = 0; j < A[i].length(); ++j) {
        otherStr[A[i].charAt(j) - 'a']++;
      }
      for (int k = 0; k < 26; ++k) {
        hash[k] = Math.min(hash[k], otherStr[k]);
      }
    }
    //将统计结果转换为字符串输出
    for (int k = 0; k < 26; ++k) {
      while (hash[k] != 0) {
        char ch = (char) (k + 'a');
        result.add(String.valueOf(ch));
      }
    }
    return result;
  }
}
