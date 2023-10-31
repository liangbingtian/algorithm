package com.liang.argorithm.aboutproject.cache.hash;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/15 下午2:19
 * 发送请求的时候，请求需要带上一个令牌，服务器接收到这个请求后需要通过这个令牌去检查redis中的
 * session是否是一个有效的会话。如果没有session的话，用户就需要被迫登录。
 * 登录通过后，返回给客户端一个令牌
 */
public class LoginDemo {

  private final Jedis jedis = new Jedis("127.0.0.1");

  public boolean isSessionValid(String token) {
    if (StringUtils.isBlank(token)) {
      return false;
    }
    final String session = jedis.hget("sessions", "session::" + token);
    if (StringUtils.isBlank(session)) {
      return false;
    }
    //过期了也returnfalse
    return true;
  }

  public void initSession(long userId, String token) {
    jedis.hset("sessions", "session::"+userId, token);
  }

}
