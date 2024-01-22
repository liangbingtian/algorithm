package com.liang.argorithm.interceptor;

import com.liang.argorithm.concurrency.threadLocal.mdcuse.MdcUtils;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author liangbingtian
 * @date 2024/01/04 下午6:20
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body, @NotNull ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set("traceId", MdcUtils.get());
        return execution.execute(request, body);
    }
}
