package com.liang.argorithm.argorithmquestion.wangdao;

import java.util.Deque;
import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 第四章节
 *
 * @author liangbingtian
 * @date 2021/08/24 下午3:42
 */
public class ChapterFour {

  /**
   * 就是实现一个循环队列，用数组实现
   */
  @Getter
  @Setter
  private static class CircularQueue {

    private int[] queue;
    private int head;
    private int count;

    public CircularQueue(int capacity) {
      queue = new int[capacity];
    }

    public boolean isFull() {
      return count == queue.length;
    }

    public boolean isEmpty() {
      return count == 0;
    }

    public boolean input(int value) {
      if (isFull()) {
        return false;
      }
      int inputIndex = (head + count) % queue.length;
      queue[inputIndex] = value;
      count++;
      return true;
    }

    public boolean output(int value) {
      if (isEmpty()) {
        return false;
      }
      head = (head + 1) & queue.length;
      count--;
      return true;
    }
  }


  /**
   * 第二题是弱智问题
   */
  public void questionTwo() {

  }

  /**
   * 第三题，用两个栈来模拟一个队列
   */
  private static class MyQueue {

    private final Deque<Integer> inputStack;
    private final Deque<Integer> outputStack;

    public MyQueue() {
      inputStack = new LinkedList<>();
      outputStack = new LinkedList<>();
    }

    public boolean isEmpty() {
      return inputStack.isEmpty() && outputStack.isEmpty();
    }

    public void input(int value) {
      inputStack.offerFirst(value);
    }

    public int output() {
      if (isEmpty()) {
        return -1;
      }
      while (!inputStack.isEmpty()) {
        outputStack.offerFirst(inputStack.removeFirst());
      }
      return outputStack.removeFirst();
    }

    public int peek() {
      if (isEmpty()) {
        return -1;
      }
      int value = this.output();
      outputStack.offerFirst(value);
      return value;
    }
  }

  /**
   * 第四题，用链表实现循环队列，这次用另一种方式，用一个虚拟头结点表示头部。当head==tail 时表示空了，当tail.next == head的时候表示队满
   */
  private static class CircularQueue2 {

    private ListNode head;
    private ListNode tail;
    private int count;
    private int capacity;

    public CircularQueue2() {
      head = tail = new ListNode();
      count = capacity = 0;
      //这道题尾部需要一直接在首部
      tail.next = head;
    }

    public void enQueue(int value) {
      if (isEmpty()) {
        head.value = value;
      } else {
        if (count < capacity) {
          tail.next.value = value;
        } else {
          ListNode insertNode = new ListNode(null, value);
          insertNode.next = tail.next;
          tail.next = insertNode;
          capacity++;
        }
        tail = tail.next;
      }
      count++;
    }

    public int deQueue() {
      if (isEmpty()) {
        return -1;
      }
      int result = head.value;
      head = head.next;
      return result;
    }


    public boolean isEmpty() {
      return count == 0;
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  private static class ListNode {

    private ListNode next;
    private int value;
  }
}
