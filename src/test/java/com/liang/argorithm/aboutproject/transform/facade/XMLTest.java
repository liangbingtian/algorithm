package com.liang.argorithm.aboutproject.transform.facade;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * xml的行列转换
 *
 * @author liangbingtian
 * @date 2022/03/26 下午8:58
 */
public class XMLTest {

  @Test
  public void coltorow() throws Exception {
    String str = XMLTransformFacade
        .getColToRowXmlStr(this.getClass().getClassLoader().getResourceAsStream(
            "pattern/coltorow1.xml"),
            "a.b");
    System.out.println(str);
  }

  /**
   * xml转json
   */
  @Test
  public void xmlToJsonWithOutSet() {
    JSONObject jsonObject = XMLTransformFacade.getJSONObjectFromXMLWithoutAttr(
        this.getClass().getClassLoader().getResourceAsStream("pattern/coltorow2.xml"));
    System.out.println(jsonObject.toJSONString());
  }

}
