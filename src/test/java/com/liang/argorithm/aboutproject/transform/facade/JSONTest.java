package com.liang.argorithm.aboutproject.transform.facade;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
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

  @Test
  public void getNewRevisedJsonObject() {
    String jsonStr = "{\"r.a->r.a1\":{\"./.d.[->./.d1.[\":{\"./.->./.\":{\"./e->./e1\":{},\"r.a.b->./b1\":{}}}}}";
    JSONObject jsonObject = JSONObject.parseObject(jsonStr, Feature.OrderedField);
    JSONObject revisedObject = new JSONObject(true);
    JSONTransformFacade.revisePatternJSONObject(jsonObject, "", "", revisedObject);
    String targetJSONStr = "{\"r.a->r.a1\":{\"r.a.d.[->r.a1.d1.[\":{\"r.a.d.[.->r.a1.d1.[.\":{\"r.a.d.[.e->r.a1.d1.[.e1\":{},\"r.a.b->r.a1.d1.[.b1\":{}}}}}";
    Assert.assertEquals(targetJSONStr, revisedObject.toString());
  }
}