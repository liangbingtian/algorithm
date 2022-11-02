package com.liang.argorithm.dto.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 基本信息的dto
 *
 * @author liangbingtian
 * @date 2022/10/27 下午2:49
 */
@Data
@ApiModel(value = "es查询相关的实体")
public class InfoDataDTO {

  @ApiModelProperty(value = "是否排重，【默认排重，true.排重;false.不排重】")
  private Boolean isCollapse = true;

  @ApiModelProperty(value = "发布开始时间", required = true)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date startPubTime;

  @ApiModelProperty(value = "发布结束时间", required = true)
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Date endPubTime;

}
