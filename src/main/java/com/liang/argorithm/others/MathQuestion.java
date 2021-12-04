package com.liang.argorithm.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 有关数学相关的问题
 *
 * @author liangbingtian
 * @date 2021/11/21 上午11:00
 */
public class MathQuestion {

  @Getter
  @Setter
  @AllArgsConstructor
  private static class ListNode {

    private int value;
    private ListNode next;
  }

  /**
   * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
   * <p>
   * 请你将两个数相加，并以相同形式返回一个表示和的链表。
   * <p>
   * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
   * <p>
   * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers
   * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
   *
   * @param l1
   * @param l2
   * @return
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null;
    ListNode tail = null;
    int carry = 0;
    while(l1!=null||l2!=null) {
      int n1 = l1!=null?l1.getValue():0;
      int n2 = l2!=null?l2.getValue():0;
      int sum = n1+n2+carry;
      if(head==null) {
        head = tail = new ListNode(sum%10, null);
      }else {
        tail.setNext(new ListNode(sum%10, null));
        tail = tail.getNext();
      }
      carry = sum/10;
      if (l1!=null) {
        l1 = l1.next;
      }
      if (l2!=null) {
        l2 = l2.next;
      }
    }
    if (carry>0) {
      tail.setNext(new ListNode(carry, null));
    }
    return head;
  }
}
