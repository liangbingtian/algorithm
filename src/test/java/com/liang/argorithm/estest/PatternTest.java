package com.liang.argorithm.estest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        .create(
            "https://qingtianyuqing.obs.cn-southwest-2.myhuaweicloud.com/report/prod/125/20230213/舆情简报_824a466fa0ff4c8a778f5ffa.pdf");
    String path = uri.getPath();
    System.out.println(path.replaceFirst("/", ""));
  }

  @Test
  public void testCode() throws UnsupportedEncodingException {
    String filename = "20230215舆情简报_362c45ccb4983d5a89399490.pdf";
    filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)
        .substring(filename.lastIndexOf("/") + 1);
    System.out.println(filename);
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

  /**
   * 使用split来分割多个字符串
   */
  @Test
  public void test4() {
    String str = "[\"北京&(冬奥会|冬残奥会)!(公园)\"]";
    List<String> strings = JSON.parseArray(str, String.class);
    for (String tmp : strings) {
      String[] split = tmp.split("[!|&()]");
      for (String subStr : split) {
        if (StringUtils.isEmpty(subStr)) {
          continue;
        }
        System.out.println(subStr);
      }
    }
  }

  @Test
  public void test5() {
    Long one = 1L;
    int two = 2;
    System.out.println(one - two);
  }

  @Test
  public void toDecimal() {
    BigDecimal one = new BigDecimal("5.377668229329E+15");
    System.out.println(one.toPlainString());
  }

  @Test
  public void toDate() throws ParseException {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date d = null;

    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse("2021-09-01T16:19:10Z");
    System.out.println(df.format(d));
    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXX").parse("2021-09-01T16:19:10Z");
    System.out.println(df.format(d));
    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2021-09-01T16:19:10Z");
    System.out.println(df.format(d));
    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse("2021-09-01T16:19:10-00");
    System.out.println(df.format(d));
    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXX").parse("2021-09-01T16:19:10+0800");
    System.out.println(df.format(d));
    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2021-09-01T16:19:10+08:00");
    System.out.println(df.format(d));
    d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse("2021-09-01T16:19:10+08:30");
    System.out.println(df.format(d));
  }

  @Test
  public void toDate2() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX");
    LocalDateTime parse = LocalDateTime.parse("2022-12-06T09:00Z", dateTimeFormatter);
    Date date = Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
    System.out.println();
  }
}
