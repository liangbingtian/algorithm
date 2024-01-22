package com.liang.argorithm.filter;

import com.liang.argorithm.concurrency.threadLocal.mdcuse.MdcUtils;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2024/01/05 上午10:17
 */
@Slf4j
public class LogFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MdcUtils.add(UUID.randomUUID().toString());
        log.info("记录请求日志");
        chain.doFilter(request, response);
        log.info("记录相应日志");
    }

    @Override
    public void destroy() {

    }
}
