package com.liang.argorithm.aboutlist;

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


  @Data
  private static class ListNode {

    Integer var;
    ListNode next;
  }
}
