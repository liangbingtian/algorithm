package com.liang.argorithm.aboutproject.transform.consumer;

import com.alibaba.fastjson.JSONObject;

/**
 * 包可见权限
 *
 * @author liuqiangm
 */
public interface IAccountXmlConsumer {

  /**
   * 消费开始
   */
  default void start() {

  }

  /**
   * 获取到指定xml路径下的xml之后，真正执行处理逻辑的consume方法
   *
   * @param jsonObject
   */
  void consume(JSONObject jsonObject);

  /**
   * 消费结束
   */
  default void end() {

  }

  /**
   * xml节点的路径。以：root.a.b.c为格式
   */
  String getXmlPath();
}
