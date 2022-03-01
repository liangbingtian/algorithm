package com.liang.argorithm.aboutproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Document(collection = "accountingStandardItem")
@Getter
@Setter
public class AccountingStandardItem {

  @Id
  private String id;

  private String code;

  private String name;

  private String type;

  private String layer;

  private String direction;

  /**
   * 上级科目编码
   */
  private String pcode;

  /**
   * 是否叶子节点
   */
  private String bleaf;

  @Override
  public String toString() {
    return "AccountingStandardItem{" +
        "id='" + id + '\'' +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", layer='" + layer + '\'' +
        ", direction='" + direction + '\'' +
        '}';
  }
}
