package com.liang.argorithm.aboutproject.cache.set;

import java.util.List;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * 网站UV统计案例，也就是说进行去重
 * @author liangbingtian
 * @date 2023/06/15 下午3:56
 */
public class UVDemo {

  private Jedis jedis = new Jedis("127.0.0.1");

  private static final String key = "USER_ACCESS_SET";

  //用户去重
  public void addUserAccess(long userId) {
    jedis.sadd(key, String.valueOf(userId));
  }

  //获取set中的数量
  public long getCount() {
    final Set<String> smembers = jedis.smembers(key);
    final Boolean abc = jedis.sismember(key, "abc");
    return jedis.scard(key);
  }

  //添加很多
  public void addMore(List<String> tags) {
    String[] a = new String[tags.size()];
    jedis.sadd(key, tags.toArray(a));
  }
}
