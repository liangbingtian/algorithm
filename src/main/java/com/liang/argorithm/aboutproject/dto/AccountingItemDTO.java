package com.liang.argorithm.aboutproject.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class AccountingItemDTO {

  @JSONField(name = "科目编号")
  private String code;

  @JSONField(name = "科目名称")
  private String name;

  @JSONField(name = "科目类型")
  private String type;

  @JSONField(name = "科目级次")
  private String layer;

  @JSONField(name = "余额方向")
  private String direction;
}
