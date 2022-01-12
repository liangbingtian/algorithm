package com.liang.argorithm.argorithmquestion.aboutstack;

import java.util.Deque;
import java.util.LinkedList;
import org.springframework.stereotype.Component;

/**
 * 用栈来计算最大区域的问题,还有单调栈相关
 *
 * @author liangbingtian
 * @date 2021/08/18 下午12:24
 */
@Component
public class MaxArea {

  /**
   * 84 柱状图中的最大矩形 暴力法1，用i和j遍历左右边界，同事找到里面最小的一个height，然后算出面积同时别忘了更新最大面积
   */
  public int largestRectangleArea(int[] heights) {
    int maxArea = Integer.MIN_VALUE;
    for (int i = 0; i < heights.length - 1; ++i) {
      int minHeight = heights[i];
      for (int j = i + 1; j < heights.length; ++j) {
        minHeight = Math.min(minHeight, heights[j]);
        maxArea = Math.max(maxArea, minHeight * (j - i + 1));
      }
    }
    return maxArea;
  }

  /**
   * 暴力法2，枚举柱子的高度，同时找其左右边界，左边界是左边第一次出现的小于它的下标，右边界是第一次出现的，小于它的下标
   */
  public int largestRectangleArea2(int[] heights) {
    int maxArea = Integer.MIN_VALUE;
    for (int i = 0; i < heights.length; ++i) {
      int j = i - 1;
      int k = i + 1;
      while (j >= 0 && heights[j] >= heights[i]) {
        j--;
      }
      while (k < heights.length && heights[k] >= heights[i]) {
        k++;
      }
      maxArea = Math.max(heights[i] * (k - j - 1), maxArea);
    }
    return maxArea;
  }

  /**
   * 使用栈的方法。通过栈来寻找左右边界。用栈垒起来表示这些元素的右边界还暂时不知道
   *
   * @param args
   */
  public int largestRectangleArea3(int[] heights) {
    Deque<Integer> deque = new LinkedList<>();
    int maxArea = Integer.MIN_VALUE;
    deque.offerFirst(-1);
    deque.offerFirst(heights[0]);
    for (int i = 1; i < heights.length; ++i) {
      while (deque.peekFirst() != -1 && heights[i] < heights[deque.peekFirst()]) {
        //找到了右边界，currentIndex为当前需要处理的下标
        int currentIndex = deque.pollFirst();
        //左边界的下标就是currentIndex的下一个元素。右边界的下标就是i
        maxArea = Math.max(maxArea, heights[currentIndex] * (i - deque.peekFirst() - 1));
      }
      //如果出现元素相等的情况，只需记录后一次
      while (deque.peekFirst() != -1 && heights[i] == heights[deque.peekFirst()]) {
        deque.pollFirst();
      }
      //将当前的下标放入，保证高度是从小到大排列的
      deque.offerFirst(i);
    }
    //当栈不为空时继续处理，此时,栈顶元素自身作为最右边的元素处理(不是右边界)
    //只不过这个时候右边没有比它高度还小的柱形了，这个时候计算宽度应该假设最右边还有一个下标为 len
    while (deque.peekFirst() != -1) {
      int currentIndex = deque.pollFirst();
      int rightBound = heights.length;
      maxArea = Math.max(maxArea, heights[currentIndex] * (rightBound - deque.peekFirst() - 1));
    }
    return maxArea;
  }

  /**
   * 接雨水。暴力解法，一根柱子能接的雨量是左边包括它的最大柱子，与右边包括它的最大柱子。中的最小值，减去当前柱子的高度，就是当前柱子能接的水梁
   *
   * @param
   */
  public int trap(int[] height) {
    int result = 0;
    for (int i = 1; i < height.length - 1; ++i) {
      int maxLeft = Integer.MIN_VALUE;
      int maxRight = Integer.MIN_VALUE;
      for (int j = i; j >= 0; j--) {
        maxLeft = Math.max(maxLeft, height[j]);
      }
      for (int j = i; j < height.length; ++j) {
        maxRight = Math.max(maxRight, height[j]);
      }
      result += Math.min(maxLeft, maxRight) - height[i];
    }
    return result;
  }

  /**
   * 接雨水，暴力解法2，对于暴力解法一来说，每次遍历到i的时候，都要向左和向右重新遍历寻找左边的最大值和右边的最大值。这个遍历是不必要的，我们只需要单独做一下
   * 遍历，用两个数组记录i往右边的最大元素，和i往左边的最大元素。
   */
  public int trap2(int[] height) {
    int result = 0;
    int[] leftMaxNum = new int[height.length];
    int[] rightMaxNum = new int[height.length];
    leftMaxNum[0] = height[0];
    rightMaxNum[height.length - 1] = height[height.length - 1];
    for (int i = 1; i < height.length; ++i) {
      leftMaxNum[i] = Math.max(leftMaxNum[i - 1], height[i]);
    }
    for (int i = height.length - 2; i >= 0; i--) {
      rightMaxNum[i] = Math.max(rightMaxNum[i + 1], height[i]);
    }
    for (int i = 1; i < height.length - 1; ++i) {
      result += Math.min(leftMaxNum[i], rightMaxNum[i]) - height[i];
    }
    return result;
  }

  /**
   * 用栈的形势。为什么上边从1遍历到n-2,因为盛水的话至少需要左端点和右端点有包围它的柱子。
   * @param height
   * @return
   */
  public int trap3(int[] height) {
    Deque<Integer> deque = new LinkedList<>();
    int result = 0;
    for (int i=0;i<height.length;++i) {
      while (!deque.isEmpty()&&height[deque.peekFirst()]<height[i]) {
        int currIndex = deque.pollFirst();
        if (deque.isEmpty()) {
          break;
        }
        int leftBound = deque.peekFirst();
        result += (Math.min(height[leftBound], height[i])-height[currIndex])*(i-leftBound-1);
      }
      deque.offerFirst(i);
    }
    return result;
  }

  public static void main(String[] args) {
    int[] a = {2, 1, 5, 6, 2, 3};
    new MaxArea().largestRectangleArea2(a);
  }
}
