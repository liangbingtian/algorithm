package com.liang.argorithm.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @author liangbingtian
 * @date 2023/03/21 下午3:35
 */
@Component
@Slf4j
public class FeignInvokeInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    String requestId = MDC.get("requestId");
    if (StringUtils.isNotBlank(requestId)) {
      requestTemplate.header("requestId", requestId);
    }
  }
}
