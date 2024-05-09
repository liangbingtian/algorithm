package com.liang.argorithm.aboutproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author liangbingtian
 * @since 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbOrder对象", description="订单表")
public class TbOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "订单编码")
    private String orderCode;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "订单名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "删除标记，0未删除  1已删除")
    private Integer deleteFlag;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String createUserCode;

    @ApiModelProperty(value = "更新人")
    private String updateUserCode;

    @ApiModelProperty(value = "版本号")
    private Integer version;

    @ApiModelProperty(value = "备注")
    private String remark;


}
