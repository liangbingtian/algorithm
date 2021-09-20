package com.liang.argorithm.aboutgraph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 有向无环图 写出有向无环图，并写出其拓扑排序的方法
 *
 * @author liangbingtian
 * @date 2021/09/20 下午4:25
 */
@Component
public class DirectedAcyclicGraph {

  //节点个数
  private int nodeNum;
  //邻接矩阵
  private List<List<Integer>> adj;
  //----bfs的各个属性
  //记录每个元素的入度个数
  private int[] inDegree;
  //队列用来进行拓扑排序
  private Deque<Integer> deque;

  //------dfs相关的属性
  private int[] nodeColors;
  private boolean valid;
  //拓扑排序的结果
  private List<Integer> result;


  public DirectedAcyclicGraph() {
  }

  public DirectedAcyclicGraph(int nodeNum) {
    this.nodeNum = nodeNum;
    adj = new ArrayList<>(nodeNum);
    for (int i = 0; i < nodeNum; ++i) {
      adj.add(new LinkedList<>());
    }
    inDegree = new int[nodeNum];
    deque = new LinkedList<>();
    result = new ArrayList<>();
    nodeColors = new int[nodeNum];
    valid = true;
  }

  public void addEdge(int from, int to) {
    adj.get(from).add(to);
    inDegree[to]++;
  }

  //进行拓扑排序,广度优先遍历
  public boolean topLogicalSort_bfs() {
    result.clear();
    //1.先将度数为0的节点放入队列
    for (int i = 0; i < inDegree.length; ++i) {
      if (inDegree[i] == 0) {
        deque.offerLast(i);
      }
    }
    //重复过程直到队列为空。将度数为0的节点输出，并将它的出度边都去掉
    while (!deque.isEmpty()) {
      int curr = deque.removeFirst();
      result.add(curr);
      Iterator<Integer> iterator = adj.get(curr).iterator();
      while (iterator.hasNext()) {
        int num = iterator.next();
        if (--inDegree[num] == 0) {
          deque.offerLast(num);
        }
      }
    }
    return result.size() >= nodeNum;
  }


  public boolean topLogicalSort_dfs(){
    //1.找到一个未遍历过的节点
    for (int i=0;i<adj.size();++i) {
      if (nodeColors[i]==0) {
        bfs(i);
        if (!valid){
          return false;
        }
      }
    }
    //2. 将result倒置即为拓扑排序的真实序列
    return true;
  }

  //深度优先便来进行拓扑排序的递归函数
  private void bfs(int u) {
    //1.先将其设置为1
    nodeColors[u] = 1;
    //2.再进行条件递归，深搜相当于后续遍历
    Iterator<Integer> iterator = adj.get(u).iterator();
    while(iterator.hasNext()) {
      int num = iterator.next();
      if (nodeColors[num]==0) {
        bfs(num);
        if (!valid) {
          return;
        }
      }else if (nodeColors[num]==1) {
        valid=true;
        return;
      }
    }
    nodeColors[u]=2;
    result.add(u);
  }

  public static void main(String[] args) {
    DirectedAcyclicGraph directedAcyclicGraph = new DirectedAcyclicGraph(4);
    directedAcyclicGraph.addEdge(1, 2);
    directedAcyclicGraph.addEdge(1, 3);
  }
}
