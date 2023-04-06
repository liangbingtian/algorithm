package com.liang.argorithm.springtest.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liangbingtian
 * @date 2023/04/02 下午7:27
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration interceptorRegistration = registry
        .addInterceptor(new HandlerInterceptor() {
          @Override
          public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
              Object handler) throws Exception {
            return false;
          }

          @Override
          public void postHandle(HttpServletRequest request, HttpServletResponse response,
              Object handler, ModelAndView modelAndView) throws Exception {

          }

          @Override
          public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
              Object handler, Exception ex) throws Exception {

          }
        });

  }


}
