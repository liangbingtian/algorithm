package com.liang.argorithm.aboutproject.transform.consumer.check;

import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.transform.consumer.IAccountXmlConsumer;

/**
 * 测试用的
 *
 * @author liangbingtian
 * @date 2022/02/27 上午11:18
 */
public class MyCheckConsumer implements IAccountXmlConsumer {

  @Override
  public void start() {

  }

  @Override
  public void consume(JSONObject jsonObject) {

  }

  @Override
  public void end() {

  }

  @Override
  public String getXmlPath() {
    return "总账.会计科目";
  }
}
