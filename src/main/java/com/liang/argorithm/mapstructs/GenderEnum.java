package com.liang.argorithm.mapstructs;

/**
 * @author liangbingtian
 * @date 2023/11/02 上午9:35
 */
public enum  GenderEnum {

  /**
   * 男性
   */
  Male("1","男"),

  /**
   * 女性
   */
  Female("0","女");

  private final String code;
  private final String name;

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }

  GenderEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

}
