package com.liang.argorithm.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * es的bean相关配置
 *
 * @author liangbingtian
 * @date 2022/10/25 下午3:06
 */
@Configuration
@Slf4j
public class ElasticSearchConfiguration {

  @Autowired
  private ElasticSearchProperties elasticsearchProperties;

  @Bean(name = "restHighLevelClient", destroyMethod = "close")
  @Scope("singleton")
  public RestHighLevelClient createInstance() {
    String host = elasticsearchProperties.getHost();
    Integer maxConnectTotal = elasticsearchProperties.getMaxConnectTotal();
    Integer maxConnectPerRoute = elasticsearchProperties.getMaxConnectPerRoute();
    Integer connectionRequestTimeoutMillis = elasticsearchProperties.getConnectionRequestTimeoutMillis();
    Integer socketTimeoutMillis = elasticsearchProperties.getSocketTimeoutMillis();
    Integer connectTimeoutMillis = elasticsearchProperties.getConnectTimeoutMillis();
    Long strategy = elasticsearchProperties.getKeepAliveStrategy();
    RestHighLevelClient restHighLevelClient;
    try {
      if (StringUtils.isEmpty(host)) {
        host = BaseConstant.DEFAULT_ES_HOST;
      }
      String[] hosts = host.split(",");
      HttpHost[] httpHosts = new HttpHost[hosts.length];
      for (int i = 0; i < httpHosts.length; i++) {
        String h = hosts[i];
        httpHosts[i] = new HttpHost(h.split(":")[0], Integer.parseInt(h.split(":")[1]), "http");
      }
      RestClientBuilder builder = RestClient.builder(httpHosts);
      builder.setRequestConfigCallback(requestConfigBuilder -> {
        requestConfigBuilder.setConnectTimeout(connectTimeoutMillis);
        requestConfigBuilder.setSocketTimeout(socketTimeoutMillis);
        requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeoutMillis);
        return requestConfigBuilder;
      });
      builder.setHttpClientConfigCallback(httpClientBuilder -> {
        httpClientBuilder.disableAuthCaching();
        httpClientBuilder.setMaxConnTotal(maxConnectTotal);
        httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
        if (strategy > 0){
          httpClientBuilder.setKeepAliveStrategy((httpResponse, httpContext) -> strategy);
        }
        return httpClientBuilder;
      });
      restHighLevelClient = new RestHighLevelClient(builder);
    } catch (Exception e) {
      log.error("create RestHighLevelClient Instance error", e);
      return null;
    }
    return restHighLevelClient;
  }


}
