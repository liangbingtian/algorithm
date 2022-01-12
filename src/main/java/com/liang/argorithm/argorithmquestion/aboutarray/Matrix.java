package com.liang.argorithm.argorithmquestion.aboutarray;

import org.springframework.stereotype.Component;

/**
 * 矩阵相关的算法
 *
 * @author liangbingtian
 * @date 2021/05/08 下午9:55
 */
@Component
public class Matrix {

  /**
   * 螺旋矩阵二，给出一个n，画出从1到n^2的矩阵
   * @param n
   * @return
   */
  public int[][] generateMatrix(int n) {
    int[][] result = new int[n][n];
    //循环的次数
    int loopNum = n/2;
    //定义每次循环的起始变量
    int startX = 0;
    int startY = 0;
    //距离起始变量的偏移量
    int offset = 1;
    //给矩阵中每个空格进行复制
    int count = 1;
    //矩阵的中间位置，如果矩阵是奇数的话需要给中间位置填值
    int mid = n/2;
    int i = 0;
    int j = 0;
    while(loopNum > 0) {
      i = startX;
      j = startY;
      for (;j<startY+n-offset;++j) {
        result[i][j] = count++;
      }
      for (;i<startX+n-offset;++i) {
        result[i][j] = count++;
      }
      for (;j>startY;j--) {
        result[i][j] = count++;
      }
      for (;i>startX;i--) {
        result[i][j] = count++;
      }
      startX++;
      startY++;
      offset+=2;
      loopNum--;
    }
    if (n%2==1) {
      result[mid][mid] = count;
    }
    return result;
  }
}
