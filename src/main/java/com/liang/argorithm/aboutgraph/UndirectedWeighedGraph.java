package com.liang.argorithm.aboutgraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
  void MinSpanTree_Kruskal(List<List<Integer>> matrix) {
    List<NodesAndWeigh> weighList = new ArrayList<>();
    for (int i = 0; i < matrix.size(); ++i) {
      for (int j = 0; j < i; ++j) {
        NodesAndWeigh nodesAndWeigh = new NodesAndWeigh(i, j, matrix.get(i).get(j));
        weighList.add(nodesAndWeigh);
      }
    }
    weighList.sort((Comparator.comparingInt(o -> o.weight)));
    //初始化树
    List<List<Integer>> forest = new ArrayList<>();
    Collections.fill(forest, new ArrayList<>());
    for (int i = 0; i < forest.size(); ++i) {
      forest.get(i).add(i);
    }
    //从小到大遍历权值的列表，如果一个权值链接的两个节点不属于同一个树则把他们加到一个树中
    for (NodesAndWeigh curr : weighList) {
      int from = curr.getFrom();
      int to = curr.getTo();
      int fromIndex = Integer.MAX_VALUE;
      int toIndex = Integer.MIN_VALUE;
      for (List<Integer> tree : forest) {
        if (tree.contains(from)) {
          fromIndex = from;
        }
        if (tree.contains(to)) {
          toIndex = to;
        }
      }
      if (fromIndex != toIndex) {
        forest.get(fromIndex).addAll(forest.get(toIndex));
        forest.get(toIndex).clear();
        System.out.println(fromIndex + "-->" + toIndex);
      }
    }
  }


}
