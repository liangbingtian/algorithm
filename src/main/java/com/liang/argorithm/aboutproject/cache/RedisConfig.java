package com.liang.argorithm.aboutproject.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis的配置文件
 *
 * @author liangbingtian
 * @date 2022/04/23 下午5:15
 */
@Configuration
public class RedisConfig {

//  @Bean(name = "redisPool")
//  public JedisPool jedisPool(@Value("${jedis.host}") String host,
//      @Value("${jedis.port}") int port) {
//    return new JedisPool(host, port);
//  }
}
