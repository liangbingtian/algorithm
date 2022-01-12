package com.liang.argorithm.argorithmquestion.aboutstack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 用栈解决对称匹配的问题
 *
 * @author liangbingtian
 * @date 2021/06/07 下午5:03
 */
@Component
public class MatchingWithStack {

  /**
   * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
   * <p>
   * 有效字符串需满足：
   * <p>
   * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。 示例 1: 输入: "()" 输出: true
   * <p>
   * 示例 2: 输入: "()[]{}" 输出: true
   * <p>
   * 示例 3: 输入: "(]" 输出: false
   * <p>
   * 示例 4: 输入: "([)]" 输出: false
   * <p>
   * 示例 5: 输入: "{[]}" 输出: true
   */
  public boolean isValid(String s) {
    //1.第一种情况，字符串遍历完，栈不为空，证明相应的左括号没有右括号来匹配。
    //2.第二种情况，发现匹配的括号不相等，返回false。
    //3.第三种情况，字符串还没遍历完，栈就空了，证明相应的右括号没有左括号来匹配。
    Deque<Character> deque = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      if (ch == '(') {
        deque.push(')');
      } else if (ch == '[') {
        deque.push(']');
      } else if (ch == '{') {
        deque.push('}');
      } else if (deque.isEmpty() || !deque.peek().equals(ch)) {
        return false;
      } else {
        deque.pop();
      }
    }
    return deque.isEmpty();
  }

  /**
   * 消除重复的字符串 直接用字符串来完成栈的操作
   */
  public String removeDuplicates(String s) {
    StringBuilder result = new StringBuilder();
    int top = -1;
    for (char c : s.toCharArray()) {
      if (top == -1 || c != result.charAt(top)) {
        result.append(c);
        top++;
      } else {
        result.deleteCharAt(top--);
      }
    }
    return result.toString();
  }

  /**
   * 根据 逆波兰表示法，求表达式的值。
   * <p>
   * 有效的运算符包括 + ,  - ,  * ,  / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
   * <p>
   * 说明：
   * <p>
   * 整数除法只保留整数部分。 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
   * <p>
   * 示例 1： 输入: ["2", "1", "+", "3", " * "] 输出: 9 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
   * <p>
   * 示例 2： 输入: ["4", "13", "5", "/", "+"] 输出: 6 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
   * <p>
   * 示例 3： 输入: ["10", "6", "9", "3", "+", "-11", " * ", "/", " * ", "17", "+", "5", "+"] 输出: 22 解释:
   * 该算式转化为常见的中缀算术表达式为： ((10 * (6 / ((9 + 3) * -11))) + 17) + 5 = ((10 * (6 / (12 * -11))) + 17) + 5
   * = ((10 * (6 / -132)) + 17) + 5 = ((10 * 0) + 17) + 5 = (0 + 17) + 5 = 17 + 5 = 22
   * <p>
   * 逆波兰表达式：是一种后缀表达式，所谓后缀就是指算符写在后面。
   * <p>
   * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
   * <p>
   * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
   * <p>
   * 逆波兰表达式主要有以下两个优点：
   * <p>
   * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
   * <p>
   * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
   */
  public int evalRPN(String[] tokens) {
    Deque<Integer> stack = new LinkedList<>();
    for (String string : tokens) {
      if (!isOpe(string)) {
        stack.push(Integer.valueOf(string));
      } else if (string.equals("+")) {
        stack.push(stack.pop() + stack.pop());
      } else if (string.equals("-")) {
        stack.push(-stack.pop() + stack.pop());
      } else if (string.equals("*")) {
        stack.push(stack.pop() * stack.pop());
      } else if (string.equals("/")) {
        int num1 = stack.pop();
        int num2 = stack.pop();
        stack.push(num2 / num1);
      }
    }
    return stack.pop();
  }

  private boolean isOpe(String s) {
    return s.length() == 1 && s.charAt(0) < '0' || s.charAt(0) > '9';
  }

  /**
   * 模拟栈的操作判断入栈序列和出栈序列是否合法
   */
  public static boolean validateStackSequences(int[] pushed, int[] popped) {
    Deque<Integer> deque = new ArrayDeque<>();
    int pushIndex = 0;
    int popIndex = 0;
    deque.offerFirst(pushed[pushIndex++]);
    while(!deque.isEmpty()) {
      if (deque.peekFirst().equals(popped[popIndex])) {
        deque.pollFirst();
        popIndex++;
        if (deque.isEmpty()&&pushIndex<pushed.length) {
          deque.offerFirst(pushed[pushIndex++]);
        }
      }else if (pushIndex<pushed.length) {
        deque.offerFirst(pushed[pushIndex++]);
      }else {
        return false;
      }
    }
    return true;
  }


  /**
   * 共享栈
   */
  @Getter
  @Setter
  private class SharedStack {

    private int[] num;
    private int s1;
    private int s2;

    public SharedStack(int k) {
      num = new int[k];
      s1 = -1;
      s2 = num.length;
    }

    public boolean isFull() {
      return (s2 - s1) == 1;
    }

    /**
     * @param i     表是入的栈号，1表示入s1栈，2表示入s2栈
     * @param value
     * @return
     */
    public boolean push(int i, int value) {
      if (isFull()) {
        return false;
      }
      if (i == 1) {
        num[s1++] = value;
      } else if (i == 2) {
        num[s2--] = value;
      } else {
        return false;
      }
      return true;
    }

    public int pop(int i, int value) {
      if (i == 1) {
        if (s1 == -1) {
          return -1;
        }
        return num[s1--];
      } else if (i == 2) {
        if (s2 == num.length) {
          return -1;
        }
        return num[s2++];
      }
      return -1;
    }

  }

}
