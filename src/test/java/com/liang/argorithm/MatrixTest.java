package com.liang.argorithm;

import com.liang.argorithm.aboutarray.Matrix;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 对于矩阵相关的操作
 *
 * @author liangbingtian
 * @date 2021/05/08 下午10:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrixTest {

  @Autowired
  private Matrix matrix;

  @Test
  public void testWriteOutMatrix() {
    System.out.println(Arrays.deepToString(matrix.generateMatrix(3)));
  }
}
