package com.liang.argorithm.argorithmquestion.aboutstack;

import java.util.ArrayDeque;
import java.util.Deque;
import org.springframework.stereotype.Component;

/**
 * 使用一个队列就可以
 *
 * @author liangbingtian
 * @date 2021/06/07 上午12:02
 */
@Component
public class MyStackTwo {

  private Deque<Integer> queue;

  public MyStackTwo() {
    queue = new ArrayDeque<>();
  }

  public void push(int x) {
    queue.push(x);
  }

  public Integer pop() {
    int size = queue.size();
    while (size - 1 > 0) {
      queue.push(queue.pop());
      size--;
    }
    return queue.pop();
  }

  public Integer peek() {
    return queue.peekLast();
  }

  public boolean empty() {
    return queue.isEmpty();
  }
}
