package com.liang.argorithm.others;

import java.util.List;

/**
 * 递归相关的习题
 *
 * @author liangbingtian
 * @date 2021/10/04 下午3:34
 */
public class Recursive {

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

  public static void main(String[] args) {
    new Recursive().generateParenthesis(3);
  }
}
