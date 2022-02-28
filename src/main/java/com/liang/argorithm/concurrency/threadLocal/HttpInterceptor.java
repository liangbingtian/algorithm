package com.liang.argorithm.concurrency.threadLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * http拦截器
 *
 * @author liangbingtian
 * @date 2022/02/20 下午4:29
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    log.info("preHandle");
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    RequestHolder.remove();
    log.info("afterCompletion");
    return;
  }
}
