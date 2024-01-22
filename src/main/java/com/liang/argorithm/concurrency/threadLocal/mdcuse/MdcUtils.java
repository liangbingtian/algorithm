package com.liang.argorithm.concurrency.threadLocal.mdcuse;

import org.slf4j.MDC;

/**
 * MDC相关的工具类
 *
 * @author liangbingtian
 * @date 2024/01/04 下午6:38
 */
public class MdcUtils {

    private static final String TRACE_ID = "TRACE_ID";
    
    public static String get() {
        return MDC.get(TRACE_ID);
    }

    public static void add(String value) {
        MDC.put(TRACE_ID, value);
    }

}
