package com.liang.argorithm.others;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 递归相关的习题
 *
 * @author liangbingtian
 * @date 2021/10/04 下午3:34
 */
public class Dfs {

  /**
   * n代表生成括号的对数，请你给出所有生成的括号的排列组合
   *
   * @param n
   * @return
   */
  List<String> generateParenthesis(int n) {
    generateParenthesis(0, 0, n, "");
    return null;
  }

  private void generateParenthesis(int left, int right, int n, String result) {
    //如果左括号和右括号都用完了，打印结果
    if (left == n && right == n) {
      System.out.println(result);
      return;
    }
    if (left < n) {
      generateParenthesis(left + 1, right, n, result + "(");
    }
    if (left > right) {
      generateParenthesis(left, right + 1, n, result + ")");
    }
  }

  /**
   * 给定一个正整数 n ，你可以做如下操作：
   * <p>
   * 如果 n 是偶数，则用 n / 2替换 n 。 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。 n 变为 1 所需的最小替换次数是多少？
   */
  public int integerReplacement(int n) {
    return integerReplacementDfs(n);
  }

  private final Map<Long, Integer> map = new HashMap<>();

  private int integerReplacementDfs(long n) {
    if (n == 1) {
      return 0;
    }
    if (map.containsKey(n)) {
      return map.get(n);
    }
    int ans = n % 2 == 0 ? integerReplacementDfs(n / 2)
        : Math.min(integerReplacementDfs(n - 1), integerReplacementDfs(n + 1));
    map.put(n, ++ans);
    return ans;
  }

  private int integerReplacementBfs(long n) {
    Deque<Long> deque = new ArrayDeque<>();
    deque.addLast(n);
    Map<Long, Integer> map = new HashMap<>();
    map.put(n, 0);
    while(!deque.isEmpty()) {
      long tmp = deque.pollFirst();
      int step = map.get(tmp);
      long[] nums = tmp%2==0?new long[]{tmp/2}:new long[]{tmp-1,tmp+1};
      for (long curr:nums) {
        if (curr==1) {
          return step+1;
        }
        if (!map.containsKey(curr)) {
          map.put(curr, step+1);
          deque.addLast(curr);
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    new Dfs().generateParenthesis(3);
  }


}
