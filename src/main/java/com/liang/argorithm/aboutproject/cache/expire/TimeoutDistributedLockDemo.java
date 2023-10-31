package com.liang.argorithm.aboutproject.cache.expire;

import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/17 下午8:57
 */
public class TimeoutDistributedLockDemo {

  private static Jedis jedis = new Jedis("127.0.0.1");


  public void lock(String key, String value) {
    jedis.setnx(key, value);
    jedis.expire(key, 60);
  }

  public void unlock(String key) {
    jedis.del(key);
  }

}
