package com.liang.argorithm.aboutproject.cache.expire;

import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/17 下午8:45
 */
public class ExpireDemo {

  private static Jedis jedis = new Jedis("127.0.0.1");

  public static void main(String[] args) {
    jedis.set("test_key", "test_value");
    jedis.expire("test_key", 10);


  }

}
