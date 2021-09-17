package com.liang.argorithm.aboutheap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 和堆相关的一些问题
 *
 * @author liangbingtian
 * @date 2021/09/14 下午10:27
 */
public class HeapQuestion {

  /**
   * 首先是最基本的问题，堆排序
   */
  public void heapSort(int[] a) {
    //1.首先数组是一个完全二叉树，先将数组进行堆化，从最后一个非叶子节点开始
    for (int i = a.length / 2; i >= 0; --i) {
      buildHeapDive(a, i, a.length);
    }
    for (int i = a.length-1;i>=0;--i) {
      swap(a, 0, i);
      buildHeapDive(a, 0, i);
    }
  }

  //建堆，使用下沉操作
  private void buildHeapDive(int[] a, int index, int length) {
    while (true) {
      int largest = index;
      int left = 2 * index + 1;
      int right = 2 * index + 2;
      if (left<length&&a[left]>a[largest]) {
        largest = left;
      }
      if (right<length&&a[right]>a[largest]) {
        largest = right;
      }
      if (largest==index) {
        break;
      }
      int tmp = a[largest];
      a[largest] = a[index];
      a[index] = tmp;
    }
  }


  //插入之后用上浮的方式建堆
  private void buildHeapFloat(int[] a, int i) {
    while (i>0&&a[i]>a[i/2]) {
      swap(a, i, i/2);
      i = i/2;
    }
  }

  private void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

  /**
   * 最小的前k个数。可以直接调包使用优先队列,java的优先队列是小跟堆
   */
  List<Integer> getLeastNumber(int[] nums, int k) {
    List<Integer> result = new ArrayList<>();
    if (k==0) {
      return result;
    }
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int a:nums) {
      queue.offer(a);
    }
    while (0!=k--) {
      result.add(queue.poll());
    }
    return result;
  }

  /**
   * 滑动窗口的最大值，这个是老生常谈的问题了，但是这里用优先队列去做，其实代码是一个意思
   */
  public int[] topKFrequent(int[] nums, int k){
    int[] result = new int[nums.length-k+1];
    PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i=0;i<k;++i) {
      queue.offer(nums[i]);
    }
    int num = 0;
    result[num++] = queue.peek();
    for (int i = k;i<nums.length;++i) {
      queue.remove(i-k);
      queue.add(nums[i]);
      result[num++] = queue.peek();
    }
    return result;
  }






}
