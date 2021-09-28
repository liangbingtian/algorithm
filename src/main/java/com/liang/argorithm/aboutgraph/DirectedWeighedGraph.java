package com.liang.argorithm.aboutgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import org.springframework.stereotype.Component;

/**
 * 有向带权图
 *
 * @author liangbingtian
 * @date 2021/09/28 下午1:13
 */
@Component
public class DirectedWeighedGraph {

  public static final int inf = Integer.MAX_VALUE/2;
  /**
   * 网络延迟距离，求单元点到其他节点的最短路径，再找出这些路径中的最大值
   */
  public int networkDelayTime(int[][] times, int n, int k) {
    //1.初始化邻接矩阵
    int[][] g = new int[n][n];
    for (int[] curr : times) {
      Arrays.fill(curr, inf);
    }
    for (int[] curr : times) {
      int x = curr[0]-1;
      int y = curr[1]-1;
      g[x][y]=curr[2];
    }
    //2.初始化距离
    int[] distance = new int[n];
    Arrays.fill(distance, inf);
    distance[k-1] = 0;
    //3.初始化节点是否已经被更新过的数组
    boolean[] used = new boolean[n];
    Arrays.fill(used, false);
    for (int i=0;i<n;++i) {
      int x = -1;
      for (int y=0;y<n;++y) {
        if (!used[y]&&(x==-1||distance[y]<distance[x])) {
          x = y;
        }
      }
      used[x] = true;
      for (int y=0;y<n;++y) {
        distance[y] = Math.min(distance[y], distance[x]+g[x][y]);
      }
    }
    int max = Arrays.stream(distance).max().getAsInt();
    return max==inf?-1:max;
  }

  /**
   * 网络延迟距离，由于上边查找最小值并更新相邻节点的最短路径的时间复杂度太高了，所以把那一步换成优先队列去做
   */
  public int networkDelayTime2(int[][] times, int n, int k) {
    List<List<int[]>> g = new ArrayList<>();
    for (int i=0;i<n;++i) {
      g.add(new ArrayList<>(n));
    }
    for (int[] curr:times) {
      int x = curr[0]-1;
      int y = curr[1]-1;
      int weight = curr[2]-1;
      g.get(x).set(y, new int[]{y, weight});
    }
    int[] distance = new int[n];
    Arrays.fill(distance, inf);
    distance[k-1]=0;
    PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0]!=o2[0]?o1[0]-o2[0]:o1[1]-o2[1]);
    queue.offer(new int[]{0, k-1});
    while(!queue.isEmpty()) {
      int[] currMin = queue.poll();
      int dis = currMin[0];
      int x = currMin[1];
      if (distance[x]<dis) {
        continue;
      }
      for (int[] curr:g.get(x)) {
        if (curr[1]+dis<distance[curr[0]]) {
          distance[curr[0]] = curr[1]+dis;
          queue.offer(new int[]{distance[curr[0]], curr[0]});
        }
      }
    }
    int max = Arrays.stream(distance).max().getAsInt();
    return max==inf?-1:max;
  }


}
