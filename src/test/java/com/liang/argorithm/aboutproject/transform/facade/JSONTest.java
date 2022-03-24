package com.liang.argorithm.aboutproject.transform.facade;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.liang.argorithm.aboutproject.transform.node.PatternNode;
import java.io.InputStream;
import jdk.internal.util.xml.impl.Input;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liangbingtian
 * @date 2022/03/03 上午11:49
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class JSONTest {

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
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source3.json");
    InputStream ruleStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source3_sonfatherarr.json");
    JSONObject result = JSONTransformFacade.getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    System.out.println(result);
    Assert.assertEquals("{\"a1\":{\"b1\":123,\"e1\":[1,2]}}", result.toString());
  }

  /**
   * 主子->主/主子孙->主子映射（对象数组）
   */
  @Test
  public void testSonFatherObjArray() {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source3.json");
    InputStream ruleStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source3_sonfatherobjarr.json");
    JSONObject result = JSONTransformFacade.getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    Assert.assertEquals("{\"a1\":{\"b1\":123,\"e1\":[{\"e2\":1},{\"e2\":2}]}}", result.toString());
  }

  /**
   * 必填项映射
   */
  @Test
  public void testEssential() {
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source3.json");
    InputStream ruleStream = this.getClass().getClassLoader().getResourceAsStream("pattern/source3_essential.json");
    JSONObject result = JSONTransformFacade.getJSONObjectFromSourceAndPattern(inputStream, ruleStream);
    Assert.assertEquals("{\"a1\":{\"b1\":123,\"c1\":\"2019-06-01\"}}", result.toString());
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
}