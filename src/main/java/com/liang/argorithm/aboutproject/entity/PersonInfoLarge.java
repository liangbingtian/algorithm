package com.liang.argorithm.aboutproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName(value = "person_info_large")
public class PersonInfoLarge {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String name;

    private String area;

    private String title;

    private String motto;
}