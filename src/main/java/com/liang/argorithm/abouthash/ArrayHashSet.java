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
   *
   * 1. 暴力法，将两个字符串按照字符的ASC码值排序，然后将排序后的两个字符串进行比较就行了。
   * 2. 下面这种用数组代替哈希表的做法，用哈希表统计字母的频次，数组也是特殊的哈希表，它的哈希函数就是每个字母的asc码值
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
   * 字母异位词分组，第一种解法，排序。因为两个字符串异位，所以将他们按照asc码排好序，按照顺序排好的字符串就是key值
   * 时间复杂度O(nklogk) 其中n是字符串的个数
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
   * 解法二，因为字符串异位，所以两个字符串包含的小写字母以及小写字母的个数是相同的，可以用这些条件拼接到一起，形成key的值
   * 时间复杂度减少了，因为少去了排序的时间复杂度
   */
   public List<List<String>> anagramGroup2(String[] strs) {
     Map<String, List<String>> map = new HashMap<>();
     for (String str : strs) {
       int[] nums = new int[26];
       char[] array = str.toCharArray();
       for (char ch : array) {
         nums[ch-'a']++;
       }
       StringBuilder key = new StringBuilder();
       for (int i=0;i<nums.length;++i) {
         if (nums[i]>0) {
           key.append(i+'a');
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
}
