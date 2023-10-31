package com.liang.argorithm.aboutproject.cache.hash;


import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/14 下午5:50
 * 社交网站会把微博里的长链接转化为短链接，短链接占用空间少，并且可以以它作为key，进行网址点击数量追踪
 */
public class ShortUrlDemo {

  private Jedis jedis = new Jedis("127.0.0.1");

  private static final String SHORT_URL = "short_url_access_count";

  public String getShortUrl(String url) {
    //设置url的访问初始值
    jedis.hset(SHORT_URL, url, "0");
    return url;
  }

  /**
   * 给短链接进行访问次数的增长
   * @param shortUrl
   */
  public void incrementShortUrlAccessCount(String shortUrl) {
    jedis.hincrBy(SHORT_URL, shortUrl, 1);
  }

  public String getShortUrlAccessCount(String shortUrl) {
    return jedis.hget(SHORT_URL, shortUrl);
  }

}
