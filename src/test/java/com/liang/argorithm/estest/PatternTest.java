package com.liang.argorithm.estest;

import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 拆分表达式的工具类
 *
 * @author liangbingtian
 * @date 2022/10/28 下午3:43
 */
@Slf4j
public class PatternTest {

  /**
   * url的截取测试
   */
  @Test
  public void test1() {
    URI uri = URI
        .create("http://192.168.3.160:9300/statics/2022/08/31/a_简报_20220831193723A004.pdf");
    String path = uri.getPath();
    String r1 = "statics";
    String replace = path.replace("/" + r1 + "/", "");
    System.out.println(replace);
  }

  /**
   * url编码测试
   *
   * @throws UnsupportedEncodingException
   */
  @Test
  public void test2() throws UnsupportedEncodingException {
    System.out.println(URLEncoder.encode("2022/09/13/20220913简报_20220913165651A018.pdf", "UTF-8"));
  }

  /**
   * lambda表达式的一些转换测试
   */
  @Test
  public void test3() {
    List<Long> list = Lists.newArrayList(1L, 2L, 3L);
    String result = list.stream().map(data -> Long.toString(data))
        .collect(Collectors.joining(","));
    System.out.println(result);
  }
}
