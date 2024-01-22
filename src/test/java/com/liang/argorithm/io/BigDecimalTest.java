package com.liang.argorithm.io;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.junit.Test;

/**
 * 和BigDecimal相关的
 *
 * @author liangbingtian
 * @date 2023/12/08 下午2:49
 */
public class BigDecimalTest {

    @Test
    public void testBigDecimalFormat() {
        final DecimalFormat format = new DecimalFormat("0.00%");
        BigDecimal bigDecimal = new BigDecimal("0.12345");
        System.out.println(format.format(bigDecimal.doubleValue()));
    }
}
