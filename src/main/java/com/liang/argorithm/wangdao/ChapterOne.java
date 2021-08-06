package com.liang.argorithm.wangdao;

import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 王道线性表课后题
 *
 * @author liangbingtian
 * @date 2021/08/02 上午9:06
 */
@Component
public class ChapterOne {

  public int questionOne(int[] a) {
    int MIN_VALUE = Integer.MAX_VALUE;
    int minValueIndex = 0;
    for (int i = 0; i < a.length; ++i) {
      if (MIN_VALUE > a[i]) {
        minValueIndex = i;
        MIN_VALUE = a[i];
      }
    }
    int result = a[minValueIndex];
    a[minValueIndex] = a[a.length - 1];
    return result;
  }

  public void questionTwo(int[] a) {
    for (int i = 0, j = a.length - 1; i < j; ++i, --j) {
      int tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
    }
  }

  public void questionTwo(int[] a, int start, int end) {
    for (; start < end; ++start, --end) {
      int tmp = a[start];
      a[start] = a[end];
      a[end] = tmp;
    }

  }

  public int questionThree(int[] a, int x) {
    int slowIndex = 0;
    for (int fastIndex = 0; fastIndex < a.length; ++fastIndex) {
      if (x != a[fastIndex]) {
        a[slowIndex++] = a[fastIndex];
      }
    }
    return slowIndex;
  }

  public int questionFour(int[] a, int s, int t) {
    if (s >= t) {
      return -1;
    }
    //找出大于s的第一个元素
    int first = 0;
    int last;
    for (; first <= s; ++first) {
      ;
    }
    for (last = first; last < t; ++last) {
      ;
    }
    for (; last < a.length; ++last) {
      a[first++] = a[last];
    }
    return first;
  }


  public int questionFive(int[] a, int s, int t) {
    if (s >= t) {
      return -1;
    }
    //找出大于等于s的第一个元素
    int first = 0;
    int last;
    for (; first < s; ++first) {
      ;
    }
    for (last = first; last <= t; ++last) {
      ;
    }
    for (; last < a.length; ++last) {
      a[first++] = a[last];
    }
    return first;
  }

  /**
   * 从有序表中删除重复的元素
   *
   * @param a
   * @return
   */
  public int questionSix(int[] a) {
    int lowIndex = 1;
    for (int fastIndex = 1; fastIndex < a.length; ++fastIndex) {
      if (a[fastIndex] != a[fastIndex - 1]) {
        a[lowIndex++] = a[fastIndex];
      }
    }
    return lowIndex;
  }

  /**
   * 将两个有序的顺序表合并为一个新的顺序表
   *
   * @param a
   * @return
   */
  public int[] questionSeven(int[] a, int[] b) {
    int[] c = new int[a.length + b.length];
    int aStart = 0;
    int bStart = 0;
    int i = 0;
    if (a[aStart] < b[bStart]) {
      c[i++] = a[aStart++];
    } else {
      c[i++] = b[bStart++];
    }
    while (aStart < a.length) {
      c[i++] = a[aStart++];
    }
    while (bStart < b.length) {
      c[i++] = b[bStart++];
    }
    return c;
  }

  /**
   * a中存放着[m+n]个元素。将数组m和数组n交换顺序
   *
   * @param a
   */
  public void questionEight(int[] a, int m, int n) {
    questionTwo(a, 0, a.length - 1);
    questionTwo(a, 0, n - 1);
    questionTwo(a, n, a.length - 1);
  }

  /**
   * 递增数组a，找到x，如果找到了与后继交换，找不到的话插入到对应位置。
   *
   * @param a
   * @param x
   */
  public int[] questionNine(int[] a, int x) {
    int left = 0;
    int right = a.length;
    while (left < right) {
      int middle = left + (right - left) / 2;
      if (x < a[middle]) {
        right = middle;
      } else if (x > a[middle]) {
        left = middle + 1;
      } else {
        if (middle + 1 < a.length) {
          int tmp = a[middle];
          a[middle] = a[middle + 1];
          a[middle + 1] = tmp;
        }
        return a;
      }
    }
    int[] b = new int[a.length + 1];
    System.arraycopy(a, 0, b, 0, left);
    b[left] = x;
    System.arraycopy(a, left, b, left + 1, a.length - left);
    return b;
  }

  /**
   * 将数组循环左移。比如1，2，3，4，5  循环左移两位变成3,4,5,1,2
   *
   * @param a
   * @param l
   * @return
   */
  public void questionTen(int[] a, int l) {
    questionTwo(a, 0, a.length - 1);
    questionTwo(a, 0, a.length - l - 1);
    questionTwo(a, a.length - l, a.length - 1);
  }

  /**
   * 两个升序数组的中位数。时间复杂度为O(log(min(m,n)))。空间复杂度为O(1)
   *
   * @param a
   * @param b
   */
  public double questionEleven(int[] a, int[] b) {
    if (a.length > b.length) {
      int[] tmp = a;
      a = b;
      b = tmp;
    }
    int m = a.length;
    int n = b.length;
    int totalMidIndex = (m + n + 1) / 2;
    int left = 0;
    int right = m;
    while (left < right) {
      int i = left + (right - left + 1) / 2;
      int j = totalMidIndex - i;
      if (a[i - 1] > b[j]) {
        right = i - 1;
      } else {
        left = i;
      }
    }
    int i = left;
    int j = totalMidIndex - i;
    int aLeft = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
    int bLeft = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
    int aRight = (i == m) ? Integer.MAX_VALUE : a[i];
    int bRight = (j == n) ? Integer.MAX_VALUE : a[j];
    if ((m + n) % 2 == 0) {
      return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2.0;
    } else {
      return Math.max(aLeft, bLeft);
    }
  }


  /**
   * 求出数组中的主要元素
   *
   * @param a
   * @return
   */
  public int questionTwelve(int[] a) {
    int candidate = -1;
    int count = 0;
    for (int value : a) {
      if (count == 0) {
        candidate = value;
      }
      if (candidate == value) {
        count++;
      } else {
        count--;
      }
    }
    count = 0;
    for (int value : a) {
      if (candidate == value) {
        count++;
      }
    }
    return count>a.length/2?candidate:-1;
  }


  public static void main(String[] args) {
    int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
    new ChapterOne().questionTen(a, 7);
    System.out.println(Arrays.toString(a));
  }
}