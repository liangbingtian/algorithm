package com.liang.argorithm.argorithmquestion.wangdao;

import java.util.Deque;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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


}
