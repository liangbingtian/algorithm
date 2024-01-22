package com.liang.argorithm.config;

import com.liang.argorithm.interceptor.RestTemplateInterceptor;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate的配置
 *
 * @author liangbingtian
 * @date 2024/01/05 上午10:41
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(restTemplateInterceptor()));
        return restTemplate;
    }

    @Bean
    public RestTemplateInterceptor restTemplateInterceptor() {
        return new RestTemplateInterceptor();
    }


}
