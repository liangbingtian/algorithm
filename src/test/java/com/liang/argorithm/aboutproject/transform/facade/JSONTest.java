package com.liang.argorithm.aboutproject.transform.facade;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.liang.argorithm.aboutproject.entity.JdShopAuthorizeInfo;
import com.liang.argorithm.aboutproject.mapper.JdShopAuthorizeInfoMapper;
import com.liang.argorithm.aboutproject.service.JdShopAuthorizeInfoService;
import com.liang.argorithm.aboutproject.transform.node.PatternNode;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sun.security.provider.MD5;

/**
 * @author liangbingtian
 * @date 2022/03/03 上午11:49
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class JSONTest {

  @Autowired
  private JdShopAuthorizeInfoService jdShopAuthorizeInfoService;

  @Test
  public void getJSONObjectWithIndex() {
    String jsonStr = "{\"a\":{\"b\":[\"c\",\"d\"],\"e\":[[[\"g\",\"h\"]],[[\"i\",\"j\"]]]}}";
    JSONObject jsonObject = JSONObject.parseObject(jsonStr);
    JSONTransformFacade.getJSONObjectWithIndex(jsonObject);
    String targetJSONStr = "{\"a\":{\"b\":{\"_0\":\"c\",\"_1\":\"d\"},\"e\":{\"_0\":{\"_0\":{\"_0\":\"g\",\"_1\":\"h\"}},\"_1\":{\"_0\":{\"_0\":\"i\",\"_1\":\"j\"}}}}}";
    Assert.assertEquals(targetJSONStr, jsonObject.toString());
  }

  /**
   * 一对一映射
   */
  @Test
  public void getNewRevisedJsonObject() {
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source1.json");
    InputStream ruleStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source1_onebyone.json");
    JSONObject result = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    Assert.assertEquals(
        "{\"a1\":{\"b1\":123,\"c1\":[1,2],\"d1\":[{\"e1\":{\"f1\":1}},{\"e1\":{\"f1\":2}}]}}",
        result.toString());
  }

  /**
   * 主->主子/主子->主子孙
   */
  @Test
  public void testFatherSon() {
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source2.json");
    InputStream ruleStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source2_fatherson.json");
    JSONObject result = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    System.out.println(result);
    Assert.assertEquals("{\"a1\":{\"d1\":[{\"e1\":1,\"b1\":123},{\"e1\":2,\"b1\":123}]}}",
        result.toString());
  }

  /**
   * 主子->主/主子孙->主子（可以映射为数组）
   */
  @Test
  public void testSonFather() {
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source3.json");
    InputStream ruleStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source3_sonfatherarr.json");
    JSONObject result = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    System.out.println(result);
//    Assert.assertEquals("{\"a1\":{\"b1\":123,\"e1\":[1,2]}}", result.toString());
  }

  /**
   * 主子->主/主子孙->主子映射（对象数组）
   */
  @Test
  public void testSonFatherObjArray() {
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source3.json");
    InputStream ruleStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source3_sonfatherobjarr.json");
    JSONObject result = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    Assert.assertEquals("{\"a1\":{\"b1\":123,\"e1\":[{\"e2\":1},{\"e2\":2}]}}", result.toString());
  }

  /**
   * 必填项映射
   */
  @Test
  public void testEssential() {
    InputStream inputStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source3.json");
    InputStream ruleStream = this.getClass().getClassLoader()
        .getResourceAsStream("pattern/source3_essential.json");
    JSONObject result = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    Assert.assertEquals("{\"a1\":{\"b1\":123,\"c1\":\"2019-06-01\"}}", result.toString());
  }

  /**
   * 转换为目标的对象数组，数组里的元素按顺序可以不同
   */
  @Test
  public void testObjectArrayOrder() {
    String source = "{\"a\":{\"b\":12,\"c\":123,\"d\":1234}}";
    JSONObject object = JSONObject.parseObject(
        "{\"r->r\":{\"r.a\":{\"r.a.b.[\":{\"1->r.a.b.[.\":{\"r.a.b->r.a.b.[.a\":{}},\"2->r.a.b.[.\":{\"r.a.b->r.a.b.[.b\":{}},\"3->r.a.b.[.\":{\"r.a.b->r.a.b.[.c\":{}}}}}}",
        Feature.OrderedField);
    JSONObject result = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(JSONObject.parseObject(source, Feature.OrderedField),
            object);
    System.out.println(result.toJSONString());
    System.out.println(object.toJSONString());

  }


  /**
   * 测试将Pattern的JSONObject变成的PatternNode
   */
  @Test
  public void getPatternNode() {
    String jsonStr = "{\"r.a->r.a1\":{\"r.a.d.[->r.a1.d1.[\":{\"r.a.d.[.->r.a1.d1.[.\":{\"r.a.d.[.e->r.a1.d1.[.e1\":{},\"r.a.b->r.a1.d1.[.b1\":{}}}}}";
    JSONObject jsonObject = JSONObject.parseObject(jsonStr, Feature.OrderedField);
//    PatternNode patternNode = PatternNode.getPatternNodeFromJSONObject(jsonObject);
    System.out.println("");
  }

  /**
   * 测试将用例1转化为CompressedNode
   */
  @Test
  public void getCompressedNode() {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(
        "pattern/source4.json");
    InputStream ruleStream = this.getClass().getClassLoader().getResourceAsStream(
        "pattern/source4_transform.json");
    JSONObject jsonObject = JSONTransformFacade
        .getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
  }

  @Test
  public void test3() {
    try {
      String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=c8e46ce1-0a8a-444d-b9c0-0a95bd7c2545";
      byte[] bytes = FileUtils.readFileToByteArray(
          new File("/Users/liangbingtian/Desktop/文件测试/2161652515030_.pic_hd.jpg"));
      String value = Base64.encodeBase64String(bytes);
      log.info("Base64:{}", value);
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(bytes);
      byte[] digest = md5.digest();
      StringBuilder stringBuilder = new StringBuilder();
      for (byte b : digest) {
        stringBuilder.append(String.format("%02x", b & 0xff));
      }
      log.info("MD5:{}", stringBuilder.toString());
      Map<String, Object> request = new HashMap<>();
      request.put("msgtype", "image");
      Map<String, Object> son = new HashMap<>();
      son.put("base64", value);
      son.put("md5", stringBuilder.toString());
      request.put("image", son);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response = restTemplate
          .postForEntity(url, JSON.toJSONString(request), String.class);
      System.out.println();
    } catch (IOException | NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testException() {
    try {
      Integer i = null;
      i.intValue();
    } catch (Exception e) {
      log.error("topic:{},pattern:{}, offset:{}，本条消息预警时出现异常，异常信息为:{}, data为:{}", "1", "2",
          "3", e.getMessage(), "asdfjkhsakhfdjkhsjfhk");
    }
  }

  @Test
  public void testJson() throws IOException {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("pattern/wenhai_data.json");
    InputStream inputStream1 = this.getClass().getClassLoader().getResourceAsStream("pattern/warning.json");
    String str2 = "[\"f9dfe486ae5d4610659c12ea48ed685a\",\"9957d90d273d692c0e7aaae310d76c7c\",\"f97ed93569129fa7b2d2292c0415ddb3\",\"1bf04110a6a3e9e2da807b8f1769d29c\",\"1182442d71bfa3607fb145cfec935e7a\",\"672289af9fc87e52cec74165464e9162\",\"50d2feaa7ce3997248780899b65bd3e1\",\"c2616fd3101121bfdb9030bdcefc1239\",\"ec9babc99c0ed77af0ce41e00add2b20\",\"be8d203e6ac90219f546d7c02e99befc\",\"93e31a47013189f31686576f232c1f31\",\"bebcc2204d2b61525fb67d9055bb3766\",\"a71ea94054f1a201275a751cbc48b572\",\"87cb381e3a8fe2d5518dbeea039f26e4\",\"381157e1e255e9e2c9590bfc16f33bbb\",\"62e2b1cd2a42ac785dece9dd0575a0e0\",\"29c021b8b7574470c9ac1d6b28e68bf8\",\"10717cb9faba559a84ef64ec475295bb\",\"93cfdac10a2266e9d551bd5d49d404cd\",\"b7947dce532b59516dc2808028379c6f\"]";
    List<String> strings2 = JSON.parseArray(str2, String.class);
    Object o = JSONObject.parseObject(inputStream, JSONArray.class);
    Object o1 = JSONObject.parseObject(inputStream1, JSONArray.class);
    JSONArray arrays = new JSONArray();
    JSONArray arrays1 = new JSONArray();
    for (Object array : (JSONArray) o) {
      arrays.addAll((JSONArray) array);
    }
    for (Object array : (JSONArray) o1) {
      arrays1.addAll((JSONArray) array);
    }
    List<String> strings = arrays.toJavaList(String.class);
    List<String> strings1 = arrays1.toJavaList(String.class);
    List<String> result = strings2.stream().filter(data -> !strings1.contains(data))
        .collect(Collectors.toList());
    System.out.println(JSON.toJSONString(result));
  }

  @Test
  @Transactional
  public void testJson2() throws IOException {
    final List<JdShopAuthorizeInfo> list = jdShopAuthorizeInfoService.list();
//    final Set<String> collect = list.stream().map(JdShopAuthorizeInfo::getUsername).collect(Collectors.toSet());
//    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source9.json");
//    JSONArray jsonData = JSON.parseObject(inputStream, JSONArray.class);
//    final List<JdShopAuthorizeInfo> javaList = jsonData.toJavaList(JdShopAuthorizeInfo.class);
//    final List<JdShopAuthorizeInfo> collect1 = javaList.stream().filter(data -> !collect.contains(data.getUsername())).collect(Collectors.toList());
    System.out.println();
  }
}