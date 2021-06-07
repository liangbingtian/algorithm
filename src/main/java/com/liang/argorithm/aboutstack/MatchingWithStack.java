package com.liang.argorithm.aboutstack;

import java.util.Deque;
import java.util.LinkedList;
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
   * 消除重复的字符串
   * 直接用字符串来完成栈的操作
   */
  public String removeDuplicates(String s) {
    StringBuilder result = new StringBuilder();
    int top = -1;
    for (char c : s.toCharArray()) {
      if (top==-1||c!=result.charAt(top)) {
        result.append(c);
        top++;
      }else {
        result.deleteCharAt(top--);
      }
    }
    return result.toString();
  }


}
