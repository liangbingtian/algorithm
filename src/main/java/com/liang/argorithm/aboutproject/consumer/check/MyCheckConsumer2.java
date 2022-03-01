package com.liang.argorithm.aboutproject.consumer.check;

import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.consumer.IAccountXmlConsumer;

/**
 * @author liangbingtian
 * @date 2022/02/28 下午4:24
 */
public class MyCheckConsumer2 implements IAccountXmlConsumer {

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
    return "总账.现金流量项目";
  }
}
