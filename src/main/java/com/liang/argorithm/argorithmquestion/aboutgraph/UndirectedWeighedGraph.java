package com.liang.argorithm.argorithmquestion.aboutgraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 有权重的联通图，这个其实叫作联通网
 *
 * @author liangbingtian
 * @date 2021/10/12 下午12:45
 */
public class UndirectedWeighedGraph {

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  private static class NodesAndWeigh {

    private int from;
    private int to;
    private int weight;

  }

  /**
   * 最小生成树算法Kruskal，即找到最小生成边
   */
  int MinSpanTree_Kruskal(List<List<Integer>> matrix, int N) {
    List<NodesAndWeigh> weighList = new ArrayList<>();
    for (int i = 0; i < matrix.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        NodesAndWeigh nodesAndWeigh = new NodesAndWeigh(i, j, matrix.get(i).get(j));
        weighList.add(nodesAndWeigh);
      }
    }
    weighList.sort((Comparator.comparingInt(o -> o.weight)));
    //初始化并查集
    UFSet ufSet = new UFSet(N);
    for (NodesAndWeigh curr : weighList) {
      //先加入，再判断，滑动窗口那块也是。这块先保持统一吧
      int from = curr.getFrom();
      int to = curr.getTo();
      int weight = curr.getWeight();
      ufSet.mergeNode(from, to, weight);
      if (ufSet.pathCount == N - 1) {
        return ufSet.result;
      }
    }
    return ufSet.result;
  }


  private static class UFSet {

    private final int[] parents;//根节点
    private int pathCount;//记录路径个数
    private int result;

    public UFSet(int m) {
      parents = new int[m];
      for (int i = 0; i < parents.length; ++i) {
        parents[i] = i;
      }
      pathCount = 0;
      result = 0;
    }

    int find(int m) {
      return m == parents[m] ? m : find(m);
    }

    void mergeNode(int m, int n, int weight) {
      int rootM = find(m);
      int rootN = find(n);
      if (rootM != rootN) {
        parents[rootM] = rootN;
        result += weight;
        pathCount++;
      }
    }
  }

  /**
   * 最小生成树Prim算法
   */
  int MinSpanTree_Prim(List<List<Integer>> matrix, int N) {
    //1.初始化图，用字典
    List<List<int[]>> graph = new ArrayList<>(N);
    for (int i=0;i<N;++i) {
      graph.add(new ArrayList<int[]>());
    }
    for (List<Integer> edge : matrix) {
      //因为是无向图，所以得各声明一次
      int from = edge.get(0);
      int to = edge.get(1);
      int cost = edge.get(2);
      graph.get(from).add(to, new int[]{to, cost});
      graph.get(to).add(from, new int[]{from, cost});
    }
    PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0]!=o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
    //选择加点法的第一个节点,就选择节点0
    queue.add(new int[]{0, 0});
    //1.声明结果长度
    //2.声明已经确定了的节点
    int result = 0;
    Set<Integer> confirmedNode = new HashSet<>();
    //3. 结束的条件是优先队列中无边了
    while(!queue.isEmpty()) {
      int[] curr = queue.poll();
      int weigh = curr[0];
      int fromNode = curr[1];
      if (confirmedNode.contains(fromNode)) {
        continue;
      }
      confirmedNode.add(fromNode);
      result+=weigh;
      //继续将所有出边的权值加入优先队列中
      for (int[] a: graph.get(fromNode)) {
        int toIndex = a[0];
        int toIndexWeight = a[1];
        queue.add(new int[]{toIndexWeight, toIndex});
      }
    }
    return result;
  }
}