package com.liang.argorithm.aboutarray;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 滑动窗口相关题目
 *
 * @author liangbingtian
 * @date 2021/05/07 下午6:58
 */
@Component
public class SlidingWindow {

  /**
   * 209 长度最小的连续子数组 找出该数组中满足其和>=target的长度最小的连续子数组，并返回其长度。如果不存在符合条件的子数组就返回0 第一种，暴力解法。时间复杂度为O(N)
   */
  public int midSubArrayLen(int target, int[] nums) {
    int length = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; ++i) {
      int result = 0;
      for (int j = i; j < nums.length; ++j) {
        if ((result += nums[j]) >= target) {
          int tmpLength = j - i + 1;
          length = Math.min(length, tmpLength);
          break;
        }
      }
    }
    length = (length == Integer.MAX_VALUE) ? 0 : length;
    return length;
  }

  /**
   * 所谓滑动窗口，就是不断调节子序列的起始位置和终止位置，从而得出我们想要的结果。 解题关键，滑窗起始位置的指针，滑窗终止位置的指针，以及起始位置和终止位置移动的条件
   */
  public int midSubArrayLen2(int target, int[] nums) {
    int result = Integer.MAX_VALUE;
    int sumValue = 0;
    int i = 0;
    for (int j = 0; j < nums.length; ++j) {
      sumValue += nums[j];
      while (sumValue >= target) {
        int tmpLength = j - i + 1;
        result = Math.min(result, tmpLength);
        sumValue -= nums[i++];
      }
    }
    result = (result == Integer.MAX_VALUE) ? 0 : result;
    return result;
  }

  /**
   * 904 水果成篮问题，其实本质上就是找到最长连续子串，子串中包含的水果类型最多为2
   */
  public int totalFruit(int[] tree) {
    int result = Integer.MIN_VALUE;
    int i = 0;
    Counter counter = new Counter();
    for (int j = 0; j < tree.length; ++j) {
      counter.add(tree[j], 1);
      while (counter.size() >= 3) {
        counter.add(tree[i], -1);
        if (counter.get(tree[i]) == 0) {
          counter.remove(tree[i]);
        }
        i++;
      }
      int tmpLength = j - i + 1;
      result = Math.max(result, tmpLength);
    }
    result = (result == Integer.MIN_VALUE) ? 0 : result;
    return result;
  }

  static class Counter extends HashMap<Object, Integer> {

    public Integer get(Object key) {
      return getOrDefault(key, 0);
    }

    public void add(Object key, int value) {
      put(key, get(key) + value);
    }

    public void addIfPresent(Object key, int value) {
      if (super.get(key) == null) {
        return;
      }
      put(key, get(key) + value);
    }
  }

  public int totalFruit2(int[] tree) {
    Map<Integer, Integer> basket = new HashMap<>();
    int i = 0;
    int resultLength = Integer.MIN_VALUE;
    for (int j = 0; j < tree.length; ++j) {
      basket.put(tree[j], basket.getOrDefault(tree[j], 0) + 1);
      while (basket.size() >= 3) {
        basket.put(tree[i], basket.getOrDefault(tree[i], 0) - 1);
        if (basket.getOrDefault(tree[i], 0) == 0) {
          basket.remove(tree[i]);
        }
        i++;
      }
      int tmpLength = j - i;
      if (tmpLength > resultLength) {
        resultLength = tmpLength;
      }
    }
    resultLength = (resultLength == Integer.MIN_VALUE) ? -1 : resultLength;
    return resultLength;
  }

  /**
   * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
   */
  public String minWindow(String s, String t) {
    //记录t中的各个字符以及各个字符出现的个数
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();
    for (int i = 0; i < t.length(); ++i) {
      char c = t.charAt(i);
      need.put(c, need.getOrDefault(c, 0) + 1);
    }
    int resultLength = Integer.MAX_VALUE;
    String resultStr = "";
    int i = 0;
    //匹配到的字符串
    int valid = 0;
    for (int j = 0; j < s.length(); ++j) {
      char c = s.charAt(j);
      if (need.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        if (window.get(c).equals(need.get(c))) {
          valid++;
        }
      }
      while (valid == need.size()) {
        int tmpLength = j - i + 1;
        if (tmpLength < resultLength) {
          resultLength = tmpLength;
          resultStr = s.substring(i, j + 1);
        }
        char d = s.charAt(i++);
        if (window.containsKey(d)) {
          if (window.get(d).equals(need.get(d))) {
            valid--;
          }
          window.put(d, window.getOrDefault(d, 0) - 1);
        }
      }
    }
    return resultStr;
  }

  /**
   * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
   * <p>
   * 返回滑动窗口中的最大值。
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums.length == 1) {
      return nums;
    }
    MyQueue myQueue = new MyQueue();
    int[] result = new int[nums.length - k + 1];
    for (int i = 0; i < k; ++i) {
      myQueue.push(i);
    }
    int num = 0;
    result[num++] = myQueue.front();
    for (int i = k; i < nums.length; ++i) {
      myQueue.pop(nums[i-k]);
      myQueue.push(nums[i]);
      result[num++] = myQueue.front();
    }
    return result;
  }

  private static class MyQueue {

    private final Deque<Integer> slidingWindows;

    public MyQueue() {
      slidingWindows = new LinkedList<>();
    }

    void pop(int value) {
      if (!slidingWindows.isEmpty() && slidingWindows.peek() == value) {
        slidingWindows.pop();
      }
    }

    void push(int value) {
      while (!slidingWindows.isEmpty() && slidingWindows.peekLast() < value) {
        slidingWindows.pollLast();
      }
      slidingWindows.addLast(value);
    }

    Integer front() {
      return slidingWindows.peek();
    }
  }
}
