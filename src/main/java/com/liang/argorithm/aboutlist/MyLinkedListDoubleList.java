package com.liang.argorithm.aboutlist;

import org.springframework.stereotype.Component;

/**
 * 双向链表
 *
 * @author liangbingtian
 * @date 2021/05/12 下午4:29
 */
@Component
public class MyLinkedListDoubleList {

  int size;
  ListNode head;
  ListNode tail;


  public MyLinkedListDoubleList() {
    size = 0;
    head = new ListNode(0);
    tail = new ListNode(0);
    head.next = tail;
    tail.prev = head;
  }

  private static class ListNode {

    ListNode prev;
    ListNode next;
    int val;

    public ListNode(int val) {
      this.val = val;
    }
  }

  public int get(int index) {
    if (index < 0 || index >= size) {
      return -1;
    }
    ListNode curr;
    if (index + 1 < size - index) {
      curr = head;
      for (int i = 0; i < index + 1; ++i) {
        curr = curr.next;
      }
    } else {
      curr = tail;
      for (int i = 0; i < size - index; ++i) {
        curr = curr.prev;
      }
    }
    return curr.val;
  }

  public void addAtHead(int val) {
    size++;
    ListNode pred = head;
    ListNode succ = pred.next;
    ListNode toAdd = new ListNode(val);
    toAdd.prev = pred;
    toAdd.next = succ;
    pred.next = toAdd;
    succ.prev = toAdd;
  }

  public void addAtTail(int val) {
    size++;
    ListNode pred = tail.prev;
    ListNode succ = tail;
    ListNode toAdd = new ListNode(val);
    toAdd.prev = pred;
    toAdd.next = succ;
    pred.next = toAdd;
    succ.prev = toAdd;
  }

  public void addAtIndex(int index, int val) {
    if (index > size) {
      return;
    }
    if (index < 0) {
      index = 0;
    }
    ListNode toAdd = new ListNode(val);
    ListNode pred;
    ListNode succ;
    if (index < size - index) {
      pred = head;
      for (int i = 0; i < index; ++i) {
        pred = pred.next;
      }
      succ = pred.next;
    } else {
      succ = tail;
      for (int i = 0; i < size - index; ++i) {
        succ = succ.prev;
      }
      pred = succ.prev;
    }
    size++;
    toAdd.prev = pred;
    toAdd.next = succ;
    pred.next = toAdd;
    succ.prev = toAdd;
  }

  public void deleteAtIndex(int index) {
    if (index >= size) {
      return;
    }
    if (index < 0) {
      index = 0;
    }
    ListNode pred;
    ListNode succ;
    if (index < size - index) {
      pred = head;
      for (int i = 0; i < index; ++i) {
        pred = pred.next;
      }
      succ = pred.next.next;
    } else {
      succ = tail;
      for (int i = 0; i < size - index - 1; ++i) {
        succ = succ.prev;
      }
      pred = succ.prev.prev;
    }
    size--;
    pred.next = succ;
    succ.prev = pred;
  }
}
