package com.liang.argorithm.aboutstack;


/**
 * 实现简单的循环队列
 *
 * @author liangbingtian
 * @date 2021/08/19 下午5:48
 */
public class MyCircularQueue {

  private int[] queue;
  private int headIndex;
  private int count;
  private int capacity;

  public MyCircularQueue(int capacity) {
    this.capacity = capacity;
    this.queue = new int[capacity];
    this.headIndex = 0;
    this.count = 0;
  }

  public boolean enQueue(int value) {
    //如果数组是满的，返回失败
    if (capacity == count) {
      return false;
    }
    //如果不为空，尾指针的下一个位置插入。同时将count加1
    int tailIndex = (headIndex + count) % capacity;
    queue[tailIndex] = value;
    count++;
    return true;
  }

  public boolean deQueue() {
    //如果是空的，那么出队失败
    if (count == 0) {
      return false;
    }
    //否则调整head的指针，并且把count--
    headIndex = (headIndex + 1) % capacity;
    count--;
    return true;
  }

  public int front() {
    if (count==0) {
      return -1;
    }
    return queue[this.headIndex];
  }

  public int rear() {
    if (count==0) {
      return -1;
    }
    int tailIndex = (headIndex + count -1)%capacity;
    return queue[tailIndex];
  }

  public boolean isEmpty() {
    return this.count==0;
  }

  public boolean isFull() {
    return this.count==this.capacity;
  }

  
}
