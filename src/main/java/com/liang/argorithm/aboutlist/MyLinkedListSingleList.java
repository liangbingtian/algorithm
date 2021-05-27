package com.liang.argorithm.aboutlist;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 数据结构707题设计链表
 *
 * @author liangbingtian
 * @date 2021/05/11 下午6:15
 */
@Component
public class MyLinkedListSingleList {

  ListNode head;//哨兵节点
  int size;

  public MyLinkedListSingleList() {
    size = 0;
    head = new ListNode(0);
  }

  public int get(int index) {
    if (index < 0 || index >= size) {
      return -1;
    }
    ListNode pred = head;
    for (int i = 0; i < index + 1; ++i) {
      pred = pred.next;
    }
    return pred.val;
  }

  public void addAtHead(int val) {
    size++;
    ListNode toAdd = new ListNode(val);
    toAdd.next = head.next;
    head.next = toAdd;
  }

  public void addAtTail(int val) {
    size++;
    ListNode pred = head;
    while (pred.next != null) {
      pred = pred.next;
    }
    pred.next = new ListNode(val);
  }

  public void addAtIndex(int index, int val) {
    if (index > size) {
      return;
    }
    if (index < 0) {
      index = 0;
    }
    size++;
    ListNode pred = head;
    for (int i = 0; i < index; ++i) {
      pred = pred.next;
    }
    ListNode toAdd = new ListNode(val);
    toAdd.next = pred.next;
    pred.next = toAdd;
  }

  public void deleteAtIndex(int index) {
    if (index < 0 || index >= size) {
      return;
    }
    size--;
    ListNode pred = head;
    for (int i = 0; i < index; ++i) {
      pred = pred.next;
    }
    pred.next = pred.next.next;
  }

  private static class ListNode {

    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }
  }
}
