package com.liang.argorithm.aboutlist;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 移除链表元素
 *
 * @author liangbingtian
 * @date 2021/05/10 下午6:03
 */
@Component
public class RemoveListElement {

  /**
   * 第一种方法，直接在原来的list上进行操作
   *
   * @param head
   * @param val
   * @return
   */
  public ListNode removeElements(ListNode head, int val) {
    while (head != null && (head.val == val)) {
      head = head.next;
    }
    ListNode current = head;
    while (current != null && current.next != null) {
      if (current.next.val == val) {
        current.next = current.next.next;
      } else {
        current = current.next;
      }
    }
    return head;
  }

  /**
   * 第二种方法，使用虚拟头节点
   *
   * @param head
   * @param val
   * @return
   */
  public ListNode removeElements2(ListNode head, int val) {
    ListNode virtualHead = new ListNode();
    virtualHead.next = head;
    ListNode current = virtualHead;
    while (current.next != null) {
      if (current.next.val == val) {
        current.next = current.next.next;
      } else {
        current = current.next;
      }
    }
    return virtualHead.next;
  }


  /**
   * 删除倒数第n个节点，采用快慢指针的方式，快指针先走n次，然后再快慢同时走
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode();
    dummy.next = head;
    ListNode fast = dummy;
    ListNode slow = dummy;
    while (n-->0) {
      fast = fast.next;
    }
    ListNode prev = new ListNode();
    while (fast!=null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next;
    }
    prev.next = slow.next;
    return dummy.next;
  }


  @Data
  static
  class ListNode {

    int val;
    ListNode next;
  }
}
