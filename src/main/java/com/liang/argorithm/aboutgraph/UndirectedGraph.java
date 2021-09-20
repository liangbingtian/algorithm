package com.liang.argorithm.aboutgraph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 无向图
 *
 * @author liangbingtian
 * @date 2021/09/19 下午8:52
 */
@Component
public class UndirectedGraph {

  /**
   * 岛屿的数量，（岛屿就是联通的一个一个小岛）。方法一，深度优先搜索
   */
  public int numIslands(char[][] grid) {
    int nr = grid.length;
    if (nr == 0) {
      return 0;
    }
    int nc = grid[0].length;
    int nums = 0;
    for (int r = 0; r < nr; ++r) {
      for (int c = 0; c < nc; ++c) {
        if (grid[r][c] == 1) {
          nums++;
          dfs(grid, r, c);
        }
      }
    }
    return nums;
  }

  private void dfs(char[][] grid, int r, int c) {
    int nr = grid.length;
    int nc = grid.length;
    grid[r][c] = 0;
    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
      dfs(grid, r - 1, c);
    }
    if (r + 1 < nr && grid[r + 1][c] == '1') {
      dfs(grid, r + 1, c);
    }
    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
      dfs(grid, r, c - 1);
    }
    if (c + 1 < nc && grid[r][c + 1] == '1') {
      dfs(grid, r, c + 1);
    }
  }

  /**
   * 岛屿的数量，方法二，广度优先遍历
   */
  public int numIslands2(char[][] grid) {
    int nr = grid.length;
    if (nr == 0) {
      return 0;
    }
    int nc = grid[0].length;
    int nums = 0;
    for (int r = 0; r < nr; ++r) {
      for (int c = 0; c < nc; ++c) {
        if (grid[r][c] == 1) {
          nums++;
          grid[r][c] = 0;
          Deque<Integer> deque = new LinkedList<>();
          deque.offerLast(r*nc+c);
          while(!deque.isEmpty()) {
            int i = deque.removeFirst();
            int row = i/nc;
            int col = i%nc;
            if (row-1>=0&&grid[row-1][col]=='1') {
              deque.offerLast((row-1)*nc+col);
              grid[row-1][col]='0';
            }
            if (row+1<nr&&grid[row+1][col]=='1') {
              deque.offerLast((row+1)*nc+col);
              grid[row+1][col]='0';
            }
            if (col-1>=0&&grid[row][col-1]=='1') {
              deque.offerLast(row*nc+(col-1));
              grid[row][col-1]='0';
            }
            if (col+1<nc&&grid[row][col+1]=='1') {
              deque.offerLast(row*nc+(col+1));
              grid[row][col+1]='0';
            }
          }
        }
      }
    }
    return nums;
  }
}
