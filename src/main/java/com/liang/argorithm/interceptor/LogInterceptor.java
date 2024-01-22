package com.liang.argorithm.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 服务端接收MDC
 *
 * @author liangbingtian
 * @date 2023/03/21 下午3:45
 */
@Slf4j
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String requestId = request.getHeader("requestId");
    if (StringUtils.isBlank(requestId)) {
      requestId = UUID.randomUUID().toString().replace("-", "");
    }
    MDC.put("requestId", requestId);
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    MDC.remove("requestId");
  }
}
