package com.liang.argorithm.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置
 *
 * @author liangbingtian
 * @date 2022/10/25 下午3:03
 */
@Data
@Component
public class ElasticSearchProperties {

  @Value("${elasticsearch.host:127.0.0.1:9200}")
  private String host;
  /**
   * 连接池里的最大连接数
   */
  @Value("${elasticsearch.max_connect_total:30}")
  private Integer maxConnectTotal;

  /**
   * 某一个/每服务每次能并行接收的请求数量
   */
  @Value("${elasticsearch.max_connect_per_route:10}")
  private Integer maxConnectPerRoute;

  /**
   * http clilent中从connetcion pool中获得一个connection的超时时间
   */
  @Value("${elasticsearch.connection_request_timeout_millis:2000}")
  private Integer connectionRequestTimeoutMillis;

  /**
   * 响应超时时间，超过此时间不再读取响应
   */
  @Value("${elasticsearch.socket_timeout_millis:30000}")
  private Integer socketTimeoutMillis;

  /**
   * 链接建立的超时时间
   */
  @Value("${elasticsearch.connect_timeout_millis:2000}")
  private Integer connectTimeoutMillis;

  /**
   * keep_alive_strategy
   */
  @Value("${elasticsearch.keep_alive_strategy:-1}")
  private Long keepAliveStrategy;
}
