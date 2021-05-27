package com.liang.argorithm;

import com.liang.argorithm.aboutlist.MyLinkedListDoubleList;
import com.liang.argorithm.aboutlist.MyLinkedListSingleList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试链表相关
 *
 * @author liangbingtian
 * @date 2021/05/11 下午6:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListTest {

  @Autowired
  private MyLinkedListSingleList myLinkedList;

  @Autowired
  private MyLinkedListDoubleList doubleList;

  /**
   * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
   * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
   */
  @Test
  public void testMyLinkedList() {
    doubleList.addAtHead(1);
//    doubleList.addAtHead(7);
//    doubleList.addAtHead(2);
//    doubleList.addAtIndex(3,0);
//    doubleList.addAtHead(6);
    doubleList.addAtTail(3);
//    doubleList.addAtHead(4);
    doubleList.addAtIndex(1, 2);
    doubleList.get(1);
    doubleList.deleteAtIndex(1);
    doubleList.get(1);
//    doubleList.addAtHead(6);
  }
}
