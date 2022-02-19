package com.liang.argorithm.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 推荐的写法
 *
 * @author liangbingtian
 * @date 2022/02/06 下午5:16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {

  String value() default "";
}
