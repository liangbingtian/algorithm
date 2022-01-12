package com.liang.argorithm.argorithmquestion.aboutheap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * 有关于有限队列
 *
 * @author liangbingtian
 * @date 2021/06/12 下午6:23
 */
@Component
public class MinHeap {

  /**
   * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
   * <p>
   * 示例 1: 输入: nums = [1,1,1,2,2,3], k = 2 输出: [1,2]
   * <p>
   * 示例 2: 输入: nums = [1], k = 1 输出: [1]
   * <p>
   * 提示： 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
   * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 你可以按任意顺序返回答案。
   */
  public int[] topKFrequent(int[] nums, int k) {
    int[] result = new int[k];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }
    Set<Map.Entry<Integer, Integer>> set = map.entrySet();
    Queue<Entry<Integer, Integer>> queue = new PriorityQueue<>(
        Comparator.comparingInt(Entry::getValue));
    for (Entry<Integer, Integer> entry : set) {
      queue.offer(entry);
      if (queue.size() > k) {
        queue.poll();
      }
    }
    for (int i = k - 1; i >= 0; --i) {
      result[i] = Objects.requireNonNull(queue.poll()).getKey();
    }
    return result;
  }

  /**
   * 直接声明大顶堆的方式
   */
  public int[] topKFrequent2(int[] nums, int k) {
    int[] result = new int[k];
    Map<Integer, Integer> map = new HashMap<>();
    for (int tmp : nums){
      map.put(tmp, map.getOrDefault(tmp, 0)+1);
    }
    PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(((o1, o2) -> (o2.getValue()-o1.getValue())));
    queue.addAll(map.entrySet());
    for (int i=0;i<k;++i) {
      result[i] = Objects.requireNonNull(queue.poll()).getKey();
    }
    return result;
  }
}
