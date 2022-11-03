package com.liang.argorithm.aboutproject.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 监测词库
 *
 * monitor_word_dict
 *
 * @mbg.generated 2022-11-03 10:48:36
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@TableName("monitor_word_dict")
public class MonitorWordDict {
    /**
     * 自增主键
     *
     * monitor_word_dict.id
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 一级标签名称
     *
     * monitor_word_dict.one_level_name
     *
     */
    private String oneLevelName;

    /**
     * 二级标签名称
     *
     * monitor_word_dict.second_level_name
     *
     */
    private String secondLevelName;

    /**
     * 展示排序
     *
     * monitor_word_dict.sort
     *
     */
    private Integer sort;

    /**
     * 是否删除（0-未删、1-删除）
     *
     * monitor_word_dict.is_deleted
     *
     */
    private Integer isDeleted;

    /**
     * 备注
     *
     * monitor_word_dict.remark
     *
     */
    private String remark;

    /**
     * 词库关键词
     *
     * monitor_word_dict.key_words
     *
     */
    private String keyWords;
}