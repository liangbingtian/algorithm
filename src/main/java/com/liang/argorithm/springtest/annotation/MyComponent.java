package com.liang.argorithm.springtest.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author liangbingtian
 * @date 2023/05/04 上午9:51
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyComponent {

  String value() default "";

}
