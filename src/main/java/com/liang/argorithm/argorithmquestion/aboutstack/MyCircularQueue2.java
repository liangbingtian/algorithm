package com.liang.argorithm.argorithmquestion.aboutstack;

import lombok.Data;

/**
 * 用单链表实现的循环队列
 *
 * @author liangbingtian
 * @date 2021/08/19 下午10:44
 */
public class MyCircularQueue2 {

  @Data
  private static class Node {

    int value;
    Node next;

    public Node(int value) {
      this.value = value;
      next = null;
    }

    public Node() {
    }
  }

  private Node head;
  private Node tail;
  private int count;
  private final int capacity;

  public MyCircularQueue2(int capacity) {
    this.capacity = capacity;
    head = tail = new Node();

  }

  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    }
    if (isEmpty()) {
      head.value = value;
    } else {
      if (tail.next == null) {
        tail.next = new Node(value);

      } else {
        tail.next.value = value;
      }
      tail = tail.next;
      if (count == capacity - 1) {
        tail.next = head;
      }
    }
    count++;
    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }
    if (head != tail) {
      head = head.next;
    }
    count--;
    return true;
  }

  public int front() {
    if (isEmpty()) {
      return -1;
    }
    return head.value;
  }

  public int rear() {
    if (isEmpty()) {
      return -1;
    }
    return tail.value;
  }

  public boolean isFull() {
    return this.count == this.capacity;
  }

  public boolean isEmpty() {
    return this.count == 0;
  }
}
