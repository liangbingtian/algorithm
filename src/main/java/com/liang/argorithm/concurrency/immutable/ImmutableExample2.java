package com.liang.argorithm.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Map;

/**
 * 第二个例子
 *
 * @author liangbingtian
 * @date 2022/02/19 下午9:15
 */
public class ImmutableExample2 {

  private static Map<Integer, Integer> map = Maps.newHashMap();

  //Immutable类不能修改修饰的值。
  private static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

  private static final ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

  private static final ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2)
      .put(3, 4).build();

  static {
    map.put(1, 2);
    map.put(3, 4);
    map.put(5, 6);
    //声明为unmodifiableMap后，不能对map的值进行修改。
    map = Collections.unmodifiableMap(map);
  }

  public static void main(String[] args) {
    map.put(7, 3);
  }

}
