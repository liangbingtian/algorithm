package com.liang.argorithm.sort;

import com.alibaba.fastjson.JSON;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 * 练习一下十大排序算法
 *
 * @author liangbingtian
 * @date 2021/04/23 下午8:34
 */
public class SortPractice {

  /**
   * 计数排序
   *
   * @param array
   * @return
   */
  private int[] countingSort(int[] array) {
    //1. 找出数组的最大值和最小值
    int max = array[0];
    int min = array[0];
    for (int number : array) {
      if (number > max) {
        max = number;
      }
      if (number < min) {
        min = number;
      }
    }
    //2. 创建presum数组，并且建立它
    int[] presum = new int[max - min + 1];
    for (int number : array) {
      presum[number - min]++;
    }
    for (int i = 1; i < presum.length; ++i) {
      presum[i] += presum[i - 1];
    }
    //3. 创建临时数组并且利用presum对原来的数组进行排序
    int[] tmpArray = new int[array.length];
    for (int i = array.length - 1; i >= 0; --i) {
      int tmpArrayIndex = presum[array[i] - min];
      tmpArray[tmpArrayIndex - 1] = array[i];
      presum[array[i] - min]--;
    }
    return Arrays.copyOfRange(tmpArray, 0, tmpArray.length);
  }

  /**
   * 桶排序
   *
   * @param nums
   */
  private void bucketSort(int[] nums) {
    int max = nums[0];
    int min = nums[0];
    int index = 0;
    for (int num : nums) {
      if (num > max) {
        max = num;
      }
      if (num < min) {
        min = num;
      }
    }
    int bucketsRange = (max - min) / nums.length + 1;
    int[][] buckets = new int[bucketsRange][0];
    for (int num : nums) {
      int bucketIndex = (num - min) / nums.length;
      int[] bucket = buckets[bucketIndex];
      bucket = addItemsInBucket(bucket, num);
      buckets[bucketIndex] = bucket;
    }
    for (int[] bucket : buckets) {
      if (bucket.length == 0) {
        continue;
      }
      insertSort(bucket);
      for (Integer num : bucket) {
        nums[index++] = num;
      }
    }
  }

  private int[] addItemsInBucket(int[] bucket, int num) {
    int index = 0;
    int[] newBucket = new int[bucket.length + 1];
    for (int tmpNum : bucket) {
      newBucket[index++] = tmpNum;
    }
    newBucket[index] = num;
    return newBucket;
  }

  /**
   * 插入排序
   *
   * @param nums
   */
  private void insertSort(int[] nums) {
    for (int i = 1; i < nums.length; ++i) {
      int j = i;
      int tmp = nums[i];
      while (j > 0 && tmp < nums[j - 1]) {
        nums[j] = nums[j - 1];
        j--;
      }
      if (i != j) {
        nums[j] = tmp;
      }
    }
  }

  /**
   * 冒泡排序
   *
   * @param array
   */
  private void bubbleSort(int[] array) {
    for (int i = 0; i < array.length; ++i) {
      boolean flag = true;
      for (int j = 0; j < array.length - i - 1; ++j) {
        if (array[j] > array[j + 1]) {
          int tmp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = tmp;
          flag = false;
        }
      }
      if (flag) {
        break;
      }
    }
  }

  /**
   * 选择排序
   *
   * @param array
   */
  private void selectSort(int[] array) {
    for (int i = 0; i < array.length; ++i) {
      int min = i;
      for (int j = i + 1; j < array.length; ++j) {
        if (array[j] < array[min]) {
          min = j;
        }
      }
      if (min != i) {
        int tmp = array[min];
        array[min] = array[i];
        array[i] = tmp;
      }
    }
  }

  /**
   * 插入排序一
   */
  private void insertSort1(int[] array) {
    for (int i = 1; i < array.length; ++i) {
      int tmp = array[i];
      int j = i;
      while (j > 0 && array[j - 1] > tmp) {
        array[j] = array[j - 1];
        j--;
      }
      if (j != i) {
        array[j] = tmp;
      }
    }
  }

  /**
   * 插入排序二
   */
  private void insertSort2(int[] array) {
    for (int i = 1; i < array.length; ++i) {
      int j = i - 1;
      int tmp = array[i];
      while (j >= 0 && array[j] > tmp) {
        array[j+1] = array[j];
        j--;
      }
      if (++j != i) {
        array[j] = tmp;
      }
    }
  }

  /**
   * 希尔排序
   */
  private void ShellSort(int[] array) {
    int gap = 1;
    while(gap<array.length) {
      gap = gap*3+1;
    }
    while (gap>0) {
      for (int i=gap;i<array.length;++i) {
        int j = i-gap;
        int tmp = array[i];
        while (j>=0&&tmp<array[j]) {
          array[j+gap] = array[j];
          j-=gap;
        }
        if ((j+=gap)!=i) {
          array[j] = tmp;
        }
      }
      gap = (int) Math.floor((double)gap/3);
    }
  }

  /**
   * 归并排序
   */
  private void MergeSort(int[] array, int left, int right) {
    if (left<right) {
      int middle = left+((right-left)>>1);
      MergeSort(array, left, middle);
      MergeSort(array, middle+1, right);
      merge(array, left, middle, right);
    }
  }

  private void merge(int[] array, int left, int middle, int right) {
    int[] tmpArray = new int[right-left+1];
    int index = 0;
    int leftIndex = left;
    int rightIndex = middle+1;
    while (leftIndex<=middle&&rightIndex<=right) {
      if (array[leftIndex]<array[rightIndex]) {
        tmpArray[index++] = array[leftIndex++];
      }else {
        tmpArray[index++] = array[rightIndex++];
      }
    }
    if (leftIndex<=middle) {
      System.arraycopy(array, leftIndex, tmpArray, index, middle-leftIndex+1);
    }
    if (rightIndex<=right){
      System.arraycopy(array, rightIndex, tmpArray, index, right-rightIndex+1);
    }
    System.arraycopy(tmpArray, 0, array, left, tmpArray.length);
  }

  /**
   * 快速排序
   */
  private void quickSort1(int[] nums, int left ,int right) {
    if (left<right) {
      int partitionIndex = partition3(nums, left, right);
      quickSort1(nums, left, partitionIndex-1);
      quickSort1(nums, partitionIndex+1, right);
    }
  }

  /**
   * 划分的第一种写法。每次都需要把数组遍历完，比较耗费时间，但是可以保证稳定性
   */
  private int partition1(int[] nums, int left, int right) {
    int pivot = nums[left];
    int index = left + 1;
    for (int i = left+1;i<=right;++i) {
      if (nums[i]<pivot) {
        swap(nums, i, index++);
      }
    }
    swap(nums, left, index-1);
    return index-1;
  }

  private void swap(int[] nums, int i, int j) {
    if(i!=j) {
      int tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
    }
  }

  /**
   * 划分的第二种写法，两个指针开工，双端遍历，填坑法,比单端遍历的时间应该来说快一些
   */
  private int partition2(int[] nums, int left, int right) {
    int pivot = nums[left];
    while(left<right) {
      while(nums[right]>=pivot&&left<right) {
        right--;
      }
      if (left<right) {
        nums[left] = nums[right];
      }
      while (nums[left]<=pivot&&left<right) {
        left++;
      }
      if (left<right){
        nums[right] = nums[left];
      }
    }
    nums[left] = pivot;
    return left;
  }

  private int partition3(int[] nums, int left, int right) {
    int pivot = nums[left];
    int start = left;
    while (left<right) {
      while (nums[right]>=pivot&&left<right) {
        right--;
      }
      while (nums[left]<=pivot&&left<right) {
        left++;
      }
      if (left>=right) {
        break;
      }
      swap(nums, left, right);
    }
    swap(nums, left, start);
    return left;
  }

}
