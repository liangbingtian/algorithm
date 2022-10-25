package com.liang.argorithm.estest;

import com.liang.argorithm.es.EmployeeCRUDSimpleApp2;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 有关于es的测试
 *
 * @author liangbingtian
 * @date 2022/10/25 下午2:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class EsTest1 {

  @Autowired
  private EmployeeCRUDSimpleApp2 app2;

  @Test
  public void testExist() throws IOException {
    System.out.println(app2.isExistsIndex("employee"));
  }

}
