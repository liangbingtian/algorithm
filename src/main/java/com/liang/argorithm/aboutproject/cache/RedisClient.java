package com.liang.argorithm.aboutproject.cache;

import org.springframework.stereotype.Component;


/**
 * redis客户端
 *
 * @author liangbingtian
 * @date 2022/04/26 上午11:14
 */
@Component
public class RedisClient {

//  @Resource(name = "redisPool")
//  private JedisPool jedisPool;
//
//  public void set(String key, String value) throws Exception {
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      jedis.set(key, value);
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
//
//  public String get(String key) throws Exception {
//    Jedis jedis = null;
//    try {
//      jedis = jedisPool.getResource();
//      return jedis.get(key);
//    } finally {
//      if (jedis != null) {
//        jedis.close();
//      }
//    }
//  }
}
