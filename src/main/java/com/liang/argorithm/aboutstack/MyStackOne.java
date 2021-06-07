package com.liang.argorithm.aboutstack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列来模拟栈
 *
 * @author liangbingtian
 * @date 2021/06/06 下午10:35
 */
public class MyStackOne {

  //和栈中元素保持一致的队列
  private Queue<Integer> queue1;

  //用来进行备份的队列
  private Queue<Integer> queue2;

  public MyStackOne() {
    queue1 = new LinkedList<>();
    queue2 = new LinkedList<>();
  }

  public void push(int x){
    queue2.offer(x);
    while (!queue1.isEmpty()){
      queue2.offer(queue1.poll());
    }
    Queue<Integer> tmpQueue;
    tmpQueue = queue1;
    queue1 = queue2;
    queue2 = tmpQueue;
  }

  public Integer pop() {
    return queue1.poll();
  }

  public Integer top() {
    return queue1.peek();
  }

  public boolean empty() {
    return queue1.isEmpty();
  }
}
