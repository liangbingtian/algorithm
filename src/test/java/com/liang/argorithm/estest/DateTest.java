package com.liang.argorithm.estest;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 日期相关的测试
 *
 * @author liangbingtian
 * @date 2022/10/27 下午3:40
 */
@Slf4j
public class DateTest {

    //Asia/Shanghai
    @Test
    public void test1() {
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();
        long mills = clock.millis();
        Date legacyDate = Date.from(instant);
        log.info("clock:{}, instant:{}, mills:{}, legacyDate:{}", clock,
                instant.atZone(clock.getZone()), mills, legacyDate);
    }

    /**
     * 各个Zone的时区
     */
    @Test
    public void test2() {
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Africa/Nairobi");
        System.out.println(zone1.getRules());
    }

    /**
     * LocalTime定义了一个没有时区信息的时间，是该时区下的当前时间
     */
    @Test
    public void test3() {
        ZoneId zone1 = ZoneId.of("Etc/GMT+8");
        ZoneId zone2 = ZoneId.of("America/Cuiaba");
        //单纯输出该时区下的时间
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        log.info("now1:{}, now2:{}, isBefore:{}, hoursBetween:{},minutesBetween:{}", now1, now2, now1.isBefore(now2), hoursBetween, minutesBetween);
    }

    /**
     * localTime和localDate工厂模式创建时间
     */
    @Test
    public void test4() {
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);
    }

    @Test
    public void test5() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        //得到今天是星期几
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        log.info("today:{}, tomorrow:{}, yesterday:{},independenceDay:{}, dayOfWeek:{}", today, tomorrow, yesterday, independenceDay, dayOfWeek);
    }

    @Test
    public void test6() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, Month.NOVEMBER, 7, 0, 0, 0);
        //可以获取好多信息
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        Month month = localDateTime.getMonth();
        //chronic_field https://blog.csdn.net/weixin_49114503/article/details/121658418
        LocalDateTime plus = localDateTime.minus(15, ChronoUnit.DAYS);
        Instant instant = plus.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(Date.from(instant));
    }

    @Test
    public void test7() {
        List<BigDecimal> numList = Arrays.asList(new BigDecimal("2.347"));
        final BigDecimal totalCost = numList.stream()
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(totalCost);
    }


    @Test
    public void test8() {
        final DateTime dateTime = DateUtil.offsetMillisecond(DateUtil.date(Long.parseLong("1718259965499")), Integer.parseInt("1915360000"));
        System.out.println(dateTime);
    }

    @Test
    public void test9() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10);
        int batch_size = 5;
        for (int i = 0; i < list.size(); i += batch_size) {
          int end = Math.min(i+batch_size, list.size());
          final List<Integer> dataList = list.subList(i, end);
          System.out.println(dataList);
        }
    }


}
