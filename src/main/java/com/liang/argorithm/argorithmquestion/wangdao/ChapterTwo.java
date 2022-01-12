package com.liang.argorithm.argorithmquestion.wangdao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 第二章
 *
 * @author liangbingtian
 * @date 2021/08/06 下午7:53
 */
@Component
public class ChapterTwo {

  private StringBuilder stringBuilder = new StringBuilder();

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  private static class ListNode {

    int val;
    ListNode next;

    public void printAllNode() {
      StringBuilder sb = new StringBuilder();
      sb.append(val);
      ListNode _next = next;
      while (_next != null) {
        sb.append(",").append(_next.val);
        _next = _next.next;
      }
      System.out.println(sb);
    }
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  private static class DListNode{
    int val;
    int freq;
    DListNode prev;
    DListNode next;
  }

  public void questionTwo(ListNode root, int value) {
    ListNode dummyNode = new ListNode();
    dummyNode.next = root;
    ListNode curr = dummyNode;
    while (curr.next != null) {
      if (curr.next.val == value) {
        curr.next = curr.next.next;
      } else {
        curr = curr.next;
      }
    }
  }

  public void questionThree(ListNode root) {
    if (root == null) {
      return;
    }
    questionThree(root.next);
    stringBuilder.append(root.val);
  }

  /**
   * 删除最小的节点
   *
   * @param root
   */
  public void questionFour(ListNode root) {
    int minValue = Integer.MAX_VALUE;
    ListNode minPosition = null;
    ListNode dummyNode = new ListNode();
    dummyNode.next = root;
    ListNode curr = dummyNode;
    while (curr.next != null) {
      if (curr.next.val < minValue) {
        minValue = curr.next.val;
        minPosition = curr;
      } else {
        curr = curr.next;
      }
    }
    dummyNode.next = dummyNode.next.next;
  }

  /**
   * 链表倒置
   */
  public ListNode questionFive(ListNode root) {
    ListNode prev = null;
    ListNode curr = root;
    while (curr != null) {
      ListNode tmp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = tmp;
    }
    return prev;
  }

  /**
   * 构造有序链表
   */
  public ListNode questionSix(ListNode root) {
    ListNode dummyNode = new ListNode();
    ListNode curr = root;
    ListNode tmp = root.next;
    curr.next = dummyNode.next;
    dummyNode.next = curr;
    curr = tmp;
    while (curr.next != null) {
      ListNode curr2 = dummyNode;
      while (curr2.next != null && curr2.next.val < curr.val) {
        curr2 = curr2.next;
      }
      tmp = curr.next;
      curr.next = curr2.next;
      curr2.next = curr;
      curr = tmp;
    }
    return dummyNode.next;
  }



  /**
   * 链表相交
   */
  public ListNode questionEight(ListNode l1, ListNode l2) {
    //1. 看两个链表哪个长哪个短，并且将长的往后移动使两个位置对齐
    int len1 = 0;
    int len2 = 0;
    ListNode curr1 = l1;
    ListNode curr2 = l2;
    while (l1 != null) {
      len1++;
      l1 = l1.next;
    }
    while (l2 != null) {
      len2++;
      l2 = l2.next;
    }
    if (len1 > len2) {
      int tmp1 = len1;
      len1 = len2;
      len2 = tmp1;
      ListNode tmp2 = curr1;
      curr1 = curr2;
      curr2 = tmp2;
    }
    int gap = len2 - len1;
    while (gap-- != 0) {
      curr2 = curr2.next;
    }
    while (curr1 != null) {
      if (curr1 == curr2) {
        return curr1;
      }
      curr1 = curr1.next;
      curr2 = curr2.next;
    }
    return null;
  }

  /**
   * 将链表A根据奇偶序号拆成两个链表
   */
  public ListNode questionTen(ListNode A) {
    ListNode dummyNode = new ListNode();
    dummyNode.next = A;
    ListNode curr = dummyNode;
    int i = 0;
    ListNode B = new ListNode();
    ListNode currB = B;
    while (curr.next != null) {
      i++;
      if (i % 2 == 0) {
        ListNode tmp = curr.next;
        curr.next = curr.next.next;
        tmp.next = currB.next;
        currB.next = tmp;
        currB = currB.next;
        continue;
      }
      curr = curr.next;
    }
    return B.next;
  }

  /**
   * 删除排序链表中的重复元素，使用快慢指针
   *
   * @return
   */
  public ListNode questionTwelve(ListNode root) {
    if (root == null) {
      return null;
    }
    ListNode slow = root;
    ListNode fast = root.next;
    while (fast != null) {
      if (slow.val != fast.val) {
        slow = slow.next;
      } else {
        slow.next = fast.next;
      }
      fast = fast.next;
    }
    return root;
  }

  /**
   * 就是用到前面学过的先链表翻转，然后归并排序、时间复杂度为O(n)。直接在纸上手撕了。
   *
   * @return
   */
  public ListNode questionThirteen() {
    return null;
  }

  /**
   * A,和B都是排序链表；从A和B的公共元素中产生单链表C。是公共元素
   *
   * @return
   */
  public ListNode questionFourteen(ListNode A, ListNode B) {
    ListNode C = new ListNode();
    ListNode curr = C;
    while (A != null && B != null) {
      if (A.val < B.val) {
        A = A.next;
      } else if (B.val < A.val) {
        B = B.next;
      } else {
        ListNode insert = new ListNode(A.val, null);
        curr.next = insert;
        curr = curr.next;
        A = A.next;
        B = B.next;
      }
    }
    return C;
  }

  /**
   * 求A和B的交集，存放在A链表，用归并的方式
   *
   * @param
   */
  public void nodesIntersection(ListNode A, ListNode B) {
    ListNode dummyNode = new ListNode();
    dummyNode.next = A;
    ListNode curr = dummyNode;
    //java中不需要，但是这里也写上，表明有删除节点的操作
    ListNode tmp = null;
    while (A != null && B != null) {
      if (A.val < B.val) {
        tmp = A;
        A = A.next;
        tmp = null;
      } else if (A.val > B.val) {
        tmp = B;
        B = B.next;
        tmp = null;
      } else {
        curr.next = A;
        curr = curr.next;
        A = A.next;
        tmp = B;
        B = B.next;
        tmp = null;
      }
      //如果A或B有剩余，将A或B的节点空间也释放
      while (A != null) {
        tmp = A;
        A = A.next;
        tmp = null;
      }
      while (B != null) {
        tmp = B;
        B = B.next;
        tmp = null;
      }
    }
  }

  /**
   * 判断B序列中是否有A序列。相当于数组的kmp算法。但这个是链表。先暴力解
   *
   * @param A
   * @param B
   */
  public boolean questionSixteen(ListNode A, ListNode B) {
    ListNode currB = B;
    ListNode currA = A;
    ListNode prev = B;
    while (currA != null && currB != null) {
      if (currB.val == currA.val) {
        currA = currA.next;
        currB = currB.next;
      } else {
        prev = prev.next;
        currB = prev;
        currA = A;
      }
    }
    return currA == null;
  }


  /**
   * 判断循环双链表是否对称，不算头节点的话，分为奇数个节点和偶数个节点，奇数个节点的话最后的终止条件是都遍历到了一个节点，偶数节点的终止条件是，最后一次遍历完后，prev和next的指向交换了。
   *
   *
   */
   public boolean questionSeventeen(DListNode root) {
     DListNode p = root.prev;
     DListNode q = root.next;
     while (p!=q&&p.next!=q) {
       if (p.val==q.val) {
         p=p.prev;
         q=q.next;
       }else {
         return false;
       }
     }
     return true;
   }

  /**
   * 链接两个循环单链表，要将h2链接到h1的尾部。这个就是找出h1,和h2的尾节点，然后将h1的尾巴链接到h2的头部，将h2的尾部链接到h1的头部。
   * @param h1
   * @param h2
   */
   public void questionEighteen(ListNode h1, ListNode h2) {
     ListNode currH1 = h1;
     ListNode currH2 = h2;
     while (currH1.next!=h1) {
       currH1 = currH1.next;
     }
     while (currH2.next!=h2) {
       currH2 = currH2.next;
     }
     currH1.next = h2;
     currH2.next = h1;
   }

  /**
   * 带头节点的循环单链表，每次删除一个最小的正数
   * @param
   */
  public void questionNineteen(ListNode root) {
    StringBuilder stringBuilder = new StringBuilder();
    ListNode curr = root;
    int minValue = Integer.MAX_VALUE;
    ListNode minValuePrev = root;
    ListNode tmp = null;
    while(root.next!=root) {
      if (curr.next==root) {
        stringBuilder.append(minValue);
        tmp = minValuePrev.next;
        minValuePrev.next = minValuePrev.next.next;
        tmp = null;
        minValue = Integer.MAX_VALUE;
        minValuePrev = root;
      }else {
        minValue = Math.min(minValue, curr.next.val);
        minValuePrev = minValuePrev.next.val<curr.next.val?minValuePrev:curr;
      }
      curr = curr.next;
    }
    root = null;
    System.out.println(stringBuilder);
  }

  /**
   * 和LRU类似的双向链表头插入
   * @param root
   */
  public DListNode questionTwenty(DListNode root, int x) {
    //1. 先寻找x
    DListNode curr = root;
    DListNode tmp = null;
    while (curr.next!=null&&curr.next.val!=x) {
      curr = curr.next;
    }
    if (curr.next==null) {
      return null;
    }else {
      //1. 把这个节点取下来。把频度加1
      tmp = curr.next;
      tmp.setFreq(tmp.getFreq()+1);
      if (tmp.next!=null) {
        tmp.next.prev = curr;
      }
      curr.next = curr.next.next;
      //2. 将节点插入头部。按照频度的递减顺序。
      curr  = root;
      while (curr.next!=null&&curr.next.freq>curr.freq) {
        curr = curr.next;
      }
      tmp.next = curr.next;
      tmp.prev = curr;
      tmp.next.prev = tmp;
      curr.next = tmp;
    }
    return tmp;
  }

  /**
   * 带表头节点的单链表。查找倒数第k个位置上的节点，如果查找成功，输出，如果不成功返回-1
   * 有头节点了。设置两个指针。倒数第k节点，就是需要从头节点出发，走length-k步，到达倒数第k个节点的上一个节点。输出下一个几点就行。
   * 让第一个指针先从头节点开始走k步。到达第k个节点。再从第k个节点开始，走到末尾节点，就走了length-k步。这时候整好符合要走length-k步的要求，让第二个指针开始同步走。
   * @param head
   * @param k
   * @return
   */
  public int questionTwentyOne(ListNode head, int k) {
    ListNode curr1 = head;
    ListNode curr2 = head;
    while(curr1.next!=null&&k--!=0) {
      curr1 = curr1.next;
    }
    //如果退出之后k>0证明没路可走了。
    if (k>0) {
      return -1;
    }
    while (curr1.next!=null) {
      curr1 = curr1.next;
      curr2 = curr2.next;
    }
    return curr2.next.val;
  }

  /**
   * 上边刷过的链表相交问题
   * 将两个链表以表尾对齐。对齐时候，长的末尾指针走的步数是n-m步，这个稍微思考下就行。
   */
  public void questionTwentyTwo(){

  }

  /**
   * 删除绝对值相等的点,但是链表没排序。做法1，先排序，再删除，复杂度为O(nlogn)。做法2，空间换时间，用一个set。
   */
  public void questionTwentyThree(ListNode head) {

  }

  /**
   * 环形链表，并找环形链表的相交点。
   * @param root
   */
  public void questionTwentyFour(ListNode root) {

  }

  /**
   * 回文链表，用归并的方式，将后半部分链表翻转，然后再运用归并的方式合并。
   * @param root
   */
  public void questionTwentyFive(ListNode root) {
    //1.将链表折半拆成两个，并将后半个倒置
    ListNode prev = root;
    ListNode fast = root;
    ListNode slow = root;
    while(fast!=null&&fast.next!=null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    prev.next = null;
    ListNode p1 = root;
    ListNode p2 =questionFive(slow);
    ListNode curr = root;
    p1 = p1.next;
    while (p1!=null&&p2!=null) {
      curr.next = p2;
      p2 = p2.next;
      curr = curr.next;
      curr.next = p1;
      p1 = p1.next;
      curr = curr.next;
    }
    if (p1!=null) {
      curr.next = p1;
    }
    if (p2!=null) {
      curr.next = p2;
    }
  }




  public static void main(String[] args) {
    ChapterTwo.ListNode node5 = new ListNode(5, null);
    ChapterTwo.ListNode node4 = new ListNode(4, node5);
    ChapterTwo.ListNode node3 = new ListNode(3, node4);
    ChapterTwo.ListNode node2 = new ListNode(2, node3);
    ChapterTwo.ListNode node1 = new ListNode(1, node2);
    node5.next = node1;
    new ChapterTwo().questionNineteen(node1);
  }


}
