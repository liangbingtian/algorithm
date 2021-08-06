package com.liang.argorithm.abouthash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 哈希表
 *
 * @author liangbingtian
 * @date 2021/05/24 下午8:52
 */
@Component
public class AboutHashTable {

  /**
   * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
   * <p>
   * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
   * <p>
   * 示例:
   * <p>
   * 给定 nums = [2, 7, 11, 15], target = 9
   * <p>
   * 因为 nums[0] + nums[1] = 2 + 7 = 9
   * <p>
   * 所以返回 [0, 1]
   */
  public int[] twoSum(int[] nums, int target) {
    int[] result = new int[2];
    if (nums == null || nums.length == 0) {
      return result;
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      int numToFind = target - nums[i];
      if (map.containsKey(numToFind)) {
        result[0] = map.get(numToFind);
        result[1] = i;
        return result;
      }
      map.put(nums[i], i);
    }
    return result;
  }


  /**
   * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
   * <p>
   * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1
   * 。
   * <p>
   * 例如:
   * <p>
   * 输入: A = [ 1, 2] B = [-2,-1] C = [-1, 2] D = [ 0, 2]
   * <p>
   * 输出: 2
   */
  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int a : nums1) {
      for (int b : nums2) {
        int findKey = a + b;
        map.put(findKey, map.getOrDefault(findKey, 0) + 1);
      }
    }
    int count = 0;
    for (int a : nums3) {
      for (int b : nums4) {
        int findKey = -a - b;
        if (map.containsKey(findKey)) {
          count += map.get(findKey);
        }
      }
    }
    return count;
  }

  /**
   * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回
   * true ；否则返回 false。
   * <p>
   * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
   * <p>
   * 注意：
   * <p>
   * 你可以假设两个字符串均只含有小写字母。
   * <p>
   * canConstruct("a", "b") -> false canConstruct("aa", "ab") -> false canConstruct("aa", "aab") ->
   * true
   */
  public boolean canConstruct(String ransomNote, String magazine) {
    //能用数组就不用map，因为map要维护红黑树，哈希表，还有做哈希函数
    int[] a = new int[26];
    for (int i = 0; i < magazine.length(); ++i) {
      a[magazine.charAt(i) - 'a']++;
    }
    for (int j = 0; j < ransomNote.length(); ++j) {
      int key = ransomNote.charAt(j) - 'a';
      a[key]--;
      if (a[key] < 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
   * <p>
   * 注意： 答案中不可以包含重复的三元组。
   * <p>
   * 示例：
   * <p>
   * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
   * <p>
   * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
   */
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length<3) {
      return result;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length-2; ++i) {
      if (nums[i] > 0) {
        return result;
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum > 0) {
          while(left<right&&nums[right]==nums[--right]);
        } else if (sum < 0) {
          while(left<right&&nums[left]==nums[++left]);
        } else {
          result.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
          while (left < right && nums[right] == nums[--right])
          while (left < right && nums[left] == nums[++left]);
        }
      }
    }
    return result;
  }
}
