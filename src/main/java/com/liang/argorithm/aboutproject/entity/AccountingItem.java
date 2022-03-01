package com.liang.argorithm.aboutproject.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Getter
@Setter
@ToString
@Document(collection = "accountingItem")
public class AccountingItem {

  @Id
  private String id;

  private String nsrsbh;

  private String orgid;

  private String corpid;

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

  /**
   * 对应关系
   */
  private String relation;

  /**
   * correspondingToCellItem 对应关系的设置及修改时间，用于对取数按设置时间进行排序
   */
  private Date cellItemUpdateTime;

  private String createTime;

  private AccountingStandardItem accountingStandardItem;


}
