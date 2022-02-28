package com.liang.argorithm.concurrency.immutable;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * final的相关的例子
 *
 * @author liangbingtian
 * @date 2022/02/19 下午5:43
 */
@Slf4j
public class ImmutableExample1 {

  private final static Integer a = 1;
  private final static String b = "2";
  //不允许将map的引用指向一个新map。但是可以修改map中的值(指的是修改原有的值，添加删除新值等)
  private static final Map<Integer, Integer> map = Maps.newHashMap();

  static {
    map.put(1, 2);
    map.put(3, 4);
    map.put(5, 6);
  }

  public static void main(String[] args) {
    map.put(1,3);
    log.info("{}", map.toString());
  }

}
