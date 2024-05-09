package com.liang.argorithm.concurrency.laterdoubledelete.aspect;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author liangbingtian
 * @date 2024/02/29 下午2:32
 */
@Aspect
@Component
public class ClearAndReloadCacheAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    /**
     * 切入点，基于注解实现切入点   加上该注解都是AOP切面的切入点
     */
    @Pointcut("@annotation(com.liang.argorithm.concurrency.laterdoubledelete.aspect.ClearAndReloadCache)")
    public void pointCut() {

    }

    /**
     * 环绕通知，环绕通知十分强大，可以决定目标方法是否执行
     */
    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("--------环绕通知---------");
        System.out.println("环绕通知的目标方法名为："+proceedingJoinPoint.getSignature().getName());
        final MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Method targetMethod = signature.getMethod();
        final ClearAndReloadCache annotation = targetMethod.getAnnotation(ClearAndReloadCache.class);

        final String name = annotation.name();
        final Set<String> keys = stringRedisTemplate.keys("*" + name + "*");
        assert keys != null;
        stringRedisTemplate.delete(keys);
        //执行改动数据库的业务
        Object proceed = null;
        try {
           proceed = proceedingJoinPoint.proceed();
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //另启动一个线程延迟删除
        executorService.submit(()->{
            try {
                Thread.sleep(1000);
                stringRedisTemplate.delete("*"+keys+"*");
                System.out.println("---------1s后，在线程中延迟删除完毕---------");
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        return proceed;
    }
}
