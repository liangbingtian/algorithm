package com.liang.argorithm.argorithmquestion.aboutlist;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 链表间的交互操作
 *
 * @author liangbingtian
 * @date 2021/05/18 下午11:13
 */
@Component
public class ListInterAction {

  /**
   * 判断两个链表间是否有相交，是的话返回相交节点的地址。（注意理解链表相交意味着什么）
   *
   * @param headA
   * @param headB
   * @return
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //找出两个链表哪个长，并且让currA指向长链表的开头
    ListNode currA = headA;
    ListNode currB = headB;
    int lengthA = 0, lengthB = 0;
    while (currA != null) {
      lengthA++;
      currA = currA.next;
    }
    while (currB != null) {
      lengthB++;
      currB = currB.next;
    }
    //位置要重置
    currA = headA;
    currB = headB;
    if (lengthA < lengthB) {
      int tmpLength = lengthA;
      lengthA = lengthB;
      lengthB = tmpLength;

      ListNode tmp = currA;
      currA = currB;
      currB = tmp;
    }

    //移动currA指针似的两个链表尾部对齐
    int moveStep = lengthA - lengthB;
    while (moveStep-- > 0) {
      currA = currA.next;
    }
    while (currA != null) {
      if (currA == currB) {
        return currA;
      }
      currA = currA.next;
      currB = currB.next;
    }
    return null;
  }

  /**
   * 环形链表：所谓环形指的是从链表尾部引出一根指针，指向链表内的某个元素
   * 判断有没有环就是使用快慢指针，看这两个指针最终能否相遇,如果不能相遇则没环
   */
  ListNode detectCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while(fast!=null&&fast.next!=null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        slow = head;
        while(slow != fast) {
          fast = fast.next;
          slow = slow.next;
        }
        return slow;
      }
    }
    return null;
  }



  @Data
  private static class ListNode {

    ListNode next;
    Integer value;
  }
}
