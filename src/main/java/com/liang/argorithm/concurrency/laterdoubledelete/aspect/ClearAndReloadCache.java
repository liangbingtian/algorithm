package com.liang.argorithm.concurrency.laterdoubledelete.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liangbingtian
 * @date 2024/02/29 上午11:41
 * 延时双删
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface ClearAndReloadCache {
    String name() default "";
}
