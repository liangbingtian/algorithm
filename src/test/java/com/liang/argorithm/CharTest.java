package com.liang.argorithm;

import com.liang.argorithm.argorithmquestion.aboutchar.CharArrayOperate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 字符串的测试用例
 *
 * @author liangbingtian
 * @date 2021/05/29 下午8:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CharTest {

  @Autowired
  private CharArrayOperate charArrayOperate;

  @Test
  public void reverseWordTest() {
    System.out.println(charArrayOperate.reverseStr("abcdefg", 2));
  }

}
