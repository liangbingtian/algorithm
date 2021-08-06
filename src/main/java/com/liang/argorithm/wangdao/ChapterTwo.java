package com.liang.argorithm.wangdao;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 第二章
 *
 * @author liangbingtian
 * @date 2021/08/06 下午7:53
 */
@Component
public class ChapterTwo {

  private StringBuilder stringBuilder = new StringBuilder();

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  private class ListNode {
    int val;
    ListNode next;
  }

  public void questionTwo(ListNode root, int value) {
    ListNode dummyNode = new ListNode();
    dummyNode.next = root;
    ListNode curr = dummyNode;
    while (curr.next!=null) {
      if (curr.next.val == value) {
        curr.next = curr.next.next;
      }else {
        curr = curr.next;
      }
    }
  }

  public void questionThree(ListNode root) {
    if (root==null) {
      return;
    }
    questionThree(root.next);
    stringBuilder.append(root.val);
  }

  /**
   * 删除最小的节点
   * @param root
   */
  public void questionFour(ListNode root) {
    int minValue = Integer.MAX_VALUE;
    ListNode minPosition = null;
    ListNode dummyNode = new ListNode();
    dummyNode.next = root;
    ListNode curr = dummyNode;
    while(curr.next!=null) {
      if (curr.next.val<minValue) {
        minValue = curr.next.val;
        minPosition = curr;
      }else {
        curr = curr.next;
      }
    }
    dummyNode.next = dummyNode.next.next;
  }

  /**
   * 链表倒置
   */
  public ListNode questionFive(ListNode root) {
    ListNode prev = null;
    ListNode curr = root;
    while(curr!=null) {
      ListNode tmp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = tmp;
    }
    return prev;
  }

  /**
   * 构造有序链表
   */
  public ListNode questionSix(ListNode root) {
    ListNode dummyNode = new ListNode();
    dummyNode.setNext(root);
    ListNode curr = dummyNode;
    ListNode result = new ListNode();
    result.setNext(new ListNode(dummyNode.next.val, null));
    while(curr.next!=null) {
      ListNode curr2 = result;
      while(curr2.next!=null&&curr2.next.val<curr.next.val) {
        curr2 = curr2.next;
      }
      ListNode insertNode = new ListNode(curr.next.val, null);
      insertNode.setNext(curr2.getNext());
      curr2.setNext(insertNode);
      curr = curr.next;
    }
    return result.next;
  }
}
