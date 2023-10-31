package com.liang.argorithm.aboutproject.cache.list;

import java.util.List;
import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/15 下午3:40
 */
public class SendEmailDemo {

  private final Jedis jedis = new Jedis("127.0.0.1");

  String key = "send_email_queue";

  public void enqueueSendEmailTask(String task) {
    jedis.lpush(key, task);
  }

  /**
   * 阻塞式的获取发送任务，也就是如果获取不到的话阻塞等待一段时间
   */
  public List<String> takeSendTask() {
    return jedis.brpop(5000, key);
  }

}
