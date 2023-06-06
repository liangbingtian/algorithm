package com.liang.argorithm.characteristic.lambda;

/**
 * 三个参数的泛型
 *
 * @author liangbingtian
 * @date 2023/06/03 上午9:11
 */
@FunctionalInterface
public interface ThreeFunction<T, U, K, R> {

  R apply(T t, U u, K k);

}
