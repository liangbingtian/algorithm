package com.liang.argorithm.argorithmquestion.aboutlist;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 交换链表的元素相关
 *
 * @author liangbingtian
 * @date 2021/05/17 下午10:55
 */
@Component
public class ListElementChange {

  /**
   * 链表倒置
   * @param listNode
   * @return
   */
  public ListNode reverseList(ListNode listNode) {
    ListNode pre = null;
    ListNode curr = listNode;
    while (curr != null) {
      ListNode tmp = curr.next;
      curr.next = pre;
      pre = curr;
      curr = tmp;
    }
    return pre;
  }

  /**
   * 链表两两交换
   * @param head
   * @return
   */
  public ListNode swapPairs(ListNode head) {
    ListNode dummyHead = new ListNode();
    dummyHead.next = head;
    ListNode curr = dummyHead;
    while (curr.next != null && curr.next.next != null) {
      ListNode tmp1 = curr.next;
      ListNode tmp2 = curr.next.next.next;

      curr.next = curr.next.next;
      curr.next.next = tmp1;
      curr.next.next.next = tmp2;
      //两两交换要移动两位
      curr = curr.next.next;
    }
    return dummyHead.next;
  }

  /**
   * 排序链表
   * @param root
   * @return
   */
  public ListNode listSort(ListNode root) {
    if (root == null || root.next == null) {
      return root;
    }
    ListNode prev = null;
    ListNode fast = root;
    ListNode slow = root;
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    prev.next = null;
    ListNode l1 = listSort(root);
    ListNode l2 = listSort(slow);
    return merge(l1, l2);
  }

  private ListNode merge(ListNode l1, ListNode l2) {
    ListNode p = new ListNode();
    ListNode result = p;
    while (l1 != null && l2 != null) {
      if (l1.var < l2.var) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }
    if (l1 != null) {
      p.next = l1;
    }
    if (l2 != null) {
      p.next = l2;
    }
    return result.next;
  }


  @Data
  private static class ListNode {

    Integer var;
    ListNode next;
  }
}
