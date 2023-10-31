package com.liang.argorithm.aboutproject.cache.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/15 上午11:32
 */
public class BlogDemo {

  private Jedis jedis = new Jedis("127.0.0.1");

  public boolean publishBlog(long id, String title, String content, String author, String time) {
    if (jedis.hexists("article::"+id, title)) {
      return false;
    }
    Map<String, String> blog = new HashMap<>();
    blog.put("id", String.valueOf(id));
    blog.put("title", title);
    blog.put("content", content);
    blog.put("author", author);
    blog.put("time", time);
    blog.put("content_length", String.valueOf(content.length()));

    jedis.hmset("article::" + id, blog);
    return true;
  }

  public Map<String, String> getBlog(long id) {
     Map<String, String> map = jedis.hgetAll("article::" + id);
     incrementBlogViewCount(id);
     return map;
  }

  private void incrementBlogViewCount(long id) {
    jedis.hincrBy("article::"+id, "view_count", 1);
  }

  public void updateBlog(long id, Map<String, String> updateBlog) {
    jedis.hmset("article::"+id, updateBlog);
  }

  public void incrementLikeCount(long id) {
    jedis.hincrBy("article::"+id, "like_count", 1);
  }

  public void showPage(long pageNum, long pageSize) {
    long startNum = (pageNum-1)*pageSize;
    long endNum = pageNum*pageSize -1;
    final List<String> list = jedis.lrange("list", startNum, endNum);
  }

}
