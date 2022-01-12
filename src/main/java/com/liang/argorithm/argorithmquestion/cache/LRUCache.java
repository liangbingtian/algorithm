package com.liang.argorithm.argorithmquestion.cache;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 最近最少使用缓存
 *
 * @author liangbingtian
 * @date 2021/07/16 下午2:48
 */
@Component
@NoArgsConstructor
public class LRUCache {

  private Map<Integer, DLinkedNode> cache = new HashMap<>();
  private int size;
  private int capacity;
  private DLinkedNode head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    head = new DLinkedNode();
    tail = new DLinkedNode();
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DLinkedNode node = cache.get(key);
    if (node==null) {
      return -1;
    }
    moveToHead(node);
    return node.value;
  }

  public void put(int key, int value) {
    DLinkedNode node = cache.get(key);
    if (node==null) {
      //1. 新建节点
      DLinkedNode newNode = new DLinkedNode(key, value, null, null);
      //2. 放入cache
      cache.put(key, newNode);
      //3. 将节点放入双端队列头部
      addToHead(newNode);
      ++size;
      //4. 如果比容量大了，删除最近最少使用的节点，即双端队列的尾部节点
      if (size>capacity) {
        DLinkedNode last = removeTheTail();
        cache.remove(last.getKey());
        --size;
      }
    }else {
      //如果key存在，修改value的值，然后移动到头部
      node.setValue(value);
      moveToHead(node);
    }
  }

  private DLinkedNode removeTheTail() {
    DLinkedNode last = tail.prev;
    removeTheNode(tail.prev);
    return last;
  }

  private void moveToHead(DLinkedNode node) {
    removeTheNode(node);
    addToHead(node);
  }


  private void removeTheNode(DLinkedNode node) {
    //1. 先删除这个节点
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void addToHead(DLinkedNode node) {
    //2. 把该节点移动到头部
    node.next = head.next;
    node.prev = head;
    head.next = node;
    node.next.prev = node;
  }


  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  private static class DLinkedNode{
    private Integer key;
    private Integer value;
    private DLinkedNode prev;
    private DLinkedNode next;
  }
}
