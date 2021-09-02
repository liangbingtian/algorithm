package com.liang.argorithm.aboutstack;

import java.util.ArrayDeque;
import java.util.Deque;
import org.springframework.stereotype.Component;

/**
 * 用栈来模拟队列的操作
 *
 * @author liangbingtian
 * @date 2021/06/05 下午6:56
 */
@Component
public class MyQueue {
  private final Deque<Integer> stIn;
  private final Deque<Integer> stOut;

  public MyQueue() {
    stIn = new ArrayDeque<>();
    stOut = new ArrayDeque<>();
  }

  public void push(int x) {
    stIn.offerFirst(x);
  }

  public int pop() {
    if (stOut.isEmpty()) {
      while(!stIn.isEmpty()) {
        stOut.offerFirst(stIn.pollFirst());
      }
    }
    return stOut.pop();
  }

  public int peek() {
    int result = pop();
    stOut.push(result);
    return result;
  }

  public boolean empty() {
    return stIn.isEmpty()&&stOut.isEmpty();
  }
}
