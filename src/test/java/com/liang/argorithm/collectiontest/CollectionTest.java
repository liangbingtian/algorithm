package com.liang.argorithm.collectiontest;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.kennycason.kumo.nlp.filter.WordSizeFilter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author liangbingtian
 * @date 2024/01/04 上午9:55
 */
public class CollectionTest {

    @Test
    public void testCollectionMethod() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);

        //获取并集
        final Collection<Integer> unionList = CollectionUtils.union(list, list2);
        System.out.println(unionList);

        //获取交集
        final Collection<Integer> intersectionList = CollectionUtils.intersection(list, list2);
        System.out.println(intersectionList);

        //获取交集的补集
        final Collection<Integer> disjunction = CollectionUtils.disjunction(list, list2);
        System.out.println(disjunction);

        //获取差集
        final Collection<Integer> subtractList = CollectionUtils.subtract(list, list2);
        System.out.println(subtractList);
    }

    //guava包的list
    @Test
    public void testLists() {
        //两个集合做笛卡尔积
        final ArrayList<Integer> list1 = Lists.newArrayList(1, 2, 3);
        final ArrayList<Integer> list2 = Lists.newArrayList(4, 5);
        final List<List<Integer>> productList = Lists.cartesianProduct(list1, list2);
        System.out.println(productList);

        //将大集合分割成若干个小集合
        final ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        final List<List<Integer>> partition = Lists.partition(list, 2);
        System.out.println(partition);

        //流处理，使用transform
        final ArrayList<String> list3 = Lists.newArrayList("a", "b", "c");
        final List<String> transformList = Lists.transform(list3, String::toUpperCase);
        System.out.println(transformList);

        //颠倒顺序
        final ArrayList<Integer> arrayList = Lists.newArrayList(3, 1, 2);
        final List<Integer> reverseList = Lists.reverse(arrayList);
        System.out.println(reverseList);
    }

    @Test
    public void BooleanTests() {
        Boolean aBoolean = Boolean.TRUE;
        System.out.println(BooleanUtils.isTrue(aBoolean));
        System.out.println(BooleanUtils.isFalse(aBoolean));
        //判断不为true或false，有可能为相反的或者null
        System.out.println(BooleanUtils.isNotTrue(aBoolean));
        System.out.println(BooleanUtils.isNotFalse(aBoolean));
        //转换成数字
        System.out.println(BooleanUtils.toInteger(aBoolean));
        //拆包装
        System.out.println(BooleanUtils.toBooleanDefaultIfNull(aBoolean, false));
    }

    @Test
    public void StringTest() {
        //测试字符串的一些用法
        String str = null;
        final String[] split = StringUtils.split(str, ",");
        //如果是null的话直接分出来的数组就是null,不会出现空指针异常
        System.out.println(split);

        //判断是否为纯数字
        String str1 = "123";
        String str2 = "123q";
        //小数点不算纯数字
        String str3 = "0.33";
        System.out.println(StringUtils.isNumeric(str1));
        System.out.println(StringUtils.isNumeric(str2));
        System.out.println(StringUtils.isNumeric(str3));
    }

    @Test
    public void joinTest() {
        final ArrayList<String> list = Lists.newArrayList("a", "b", "c");
        final ArrayList<Integer> list2 = Lists.newArrayList(1, 2, 3);
        System.out.println(StringUtils.join(list, ","));
        System.out.println(StringUtils.join(list2, " "));
    }

    @Test
    public void AssertTest() {
        String str = null;
        //用hutool的
        Assert.checkBetween(4, 1, 3, "数字{}不在数字{}和{}之间", 2, 1, 3);
    }

    @Test
    public void DigestTest() {
        System.out.println(DigestUtils.md5Hex("苏三说技术"));
        System.out.println(DigestUtils.sha256Hex("苏三说技术"));
    }

    @Test
    public void changeCode() {
        // 十进制数字
        int decimalNumber = 26045;

        // 步骤1：将十进制数转换为 Unicode 字符
        char unicodeChar = (char) decimalNumber;

        // 步骤2：将 Unicode 字符编码为 UTF-8 字节序列
        byte[] utf8Bytes = String.valueOf(unicodeChar).getBytes(StandardCharsets.UTF_8);

        // 步骤3：将字节序列转换回字符串
        String utf8String = new String(utf8Bytes, StandardCharsets.UTF_8);

        // 输出 UTF-8 编码的字符串
        System.out.println("UTF-8 编码的字符串: " + utf8String);
    }

    @Test
    public void TwoToTeen() {
        String binary = "0011"; // 十六进制字符串
        int id = Integer.parseInt(binary, 2); // 将十六进制字符串转换为长整型十进制数值
        System.out.println("二进制数 " + binary + " 转换为十进制数为: " + id);
    }

    @Test
    public void TeenToSixteen() {
        //219是销量 10000  11011
        //235是库存 100000 101011
        int decimal = 219; // 十进制数值
        String hex = Integer.toBinaryString(decimal); // 将十进制数值转换为十六进制字符串
        System.out.println("十进制数 " + decimal + " 转换为二进制数为: " + hex);
    }

    @Test
    public void testDecimal() {
        System.out.println(DateUtil.parse("2024-05-09 12:23:23", DatePattern.NORM_DATE_FORMAT));
    }

    @Test
    public void testArray() {
        final List<String> strings = JSON.parseArray("[\"切词1\", \"切词2\"]", String.class);
        System.out.println(JSON.toJSONString(strings));
    }

    @Test
    public void wordSize() {
        final WordSizeFilter wordSizeFilter = new WordSizeFilter(2, 6);
        final boolean test = wordSizeFilter.test("男");
        System.out.println();
    }


}
