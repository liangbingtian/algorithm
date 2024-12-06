package com.liang.argorithm.ratelimit;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangbingtian
 * @date 2024/10/21 10:46
 */
public class Resilience4jRateLimiterWithRetryExample {

    // 用于记录每次调用的时间戳
    public static List<Long> timestamps = new CopyOnWriteArrayList<>();

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static ExecutorService AD_CREATE_POOL = new ThreadPoolExecutor(8, 8, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            CustomReportThreadFactory.newFactory("create-ad-pool"));



    public static void main(String[] args) {

        // 创建RateLimiter配置
        RateLimiterConfig config = RateLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(10))
                .limitRefreshPeriod(Duration.ofMinutes(1))
                .limitForPeriod(50)
                .build();

        // 创建RateLimiter注册表
        RateLimiterRegistry registry = RateLimiterRegistry.of(config);

        // 获取RateLimiter实例
        RateLimiter rateLimiter = registry.rateLimiter("myRateLimiter");



        // 提交任务到线程池
        List<CompletableFuture<Void>> result = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 200; i++) {
            final CompletableFuture<Void> future = CompletableFuture.runAsync(createTask(rateLimiter, result), AD_CREATE_POOL);
            result.add(future);
        }
        CompletableFuture.allOf(result.toArray(new CompletableFuture[0])).join();
        // 处理重试队列中的任务
            while (result.stream().anyMatch(f -> !f.isDone())) {
                try {
                    Thread.sleep(500); // 避免忙等待
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        analyzeTimestamps(timestamps);
        // 关闭线程池
    }

    private static Runnable createTask(RateLimiter rateLimiter, List<CompletableFuture<Void>> futureList) {
        return () -> {
            if (rateLimiter.acquirePermission()) {
                final int andIncrement = atomicInteger.getAndIncrement();
                long currentTimeStamp = System.currentTimeMillis();
                System.out.println(andIncrement + " " + currentTimeStamp);
                // 执行任务
                timestamps.add(currentTimeStamp);
//                System.out.println("Request processed by " + Thread.currentThread().getName() + " at " + System.currentTimeMillis());
            }else {
                futureList.add(CompletableFuture.runAsync(createTask(rateLimiter, futureList), AD_CREATE_POOL));
            }
        };
    }


    private static void analyzeTimestamps(List<Long> timestamps) {
        // 按时间戳排序
        timestamps.sort(Long::compare);

        // 计算每秒的调用次数
        long startTime = timestamps.get(0);
        int count = 0;
        for (long timestamp : timestamps) {
            if (timestamp - startTime < 950) {
                count++;
            } else {
                System.out.println("Number of calls in 1 second: " + count);
                startTime = timestamp;
                count = 1;
            }
        }
        // 打印最后一秒的调用次数
        System.out.println("Number of calls in 1 second: " + count);
    }

}
