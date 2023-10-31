package com.liang.argorithm.aboutproject.cache.list;

import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/15 下午2:40
 * 秒杀活动案例
 */
public class SecKillDemo {

  private final Jedis jedis = new Jedis("127.0.0.1");

  /**
   * 秒杀请求入队
   */
  public void enqueueSecKillRequest(String request) {
    jedis.lpush("request_queue", request);
  }

  public String dequeueSecKillRequest() {
    return jedis.rpop("request_queue");
  }
}
