package com.liang.argorithm.wangdao;

import java.util.Deque;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 第三章
 *
 * @author liangbingtian
 * @date 2021/08/21 下午5:24
 */
@Component
public class ChapterThree {

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class ListNode {

    int value;
    ListNode next;
  }

  /**
   * 就相当于是括号匹配
   *
   * @param s
   */
  public boolean questionThree(String s) {
    Deque<Character> deque = new LinkedList<>();
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      if (ch == 'I') {
        deque.offerFirst('O');
      } else if (deque.isEmpty() || deque.peekFirst() != ch) {
        return false;
      } else {
        deque.pollFirst();
      }
    }
    return deque.isEmpty();
  }

  /**
   * 给一个单链表，看其是否中心对称
   *
   * @param root
   * @return
   */
  public boolean questionFour(ListNode root) {
    Deque<ListNode> deque = new LinkedList<>();
    ListNode prev = root;
    ListNode slow = root;
    ListNode fast = root;
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    prev.next = null;
    ListNode curr = root;
    while (curr != null) {
      deque.offerFirst(curr);
      curr = curr.next;
    }
    curr = slow;
    while (curr != null) {
      if (deque.isEmpty() || deque.peekFirst().value != curr.value) {
        return false;
      }
      deque.pollFirst();
      curr = curr.next;
    }
    return true;
  }

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