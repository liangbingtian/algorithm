package com.liang.argorithm.aboutproject.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * monitor_word_dict
 *
 * @mbg.generated 2022-11-02 15:38:34
 */
@Table(name = "monitor_word_dict")
public class MonitorWordDict {
    /**
     *
     * monitor_word_dict.id
     *
     */
    @Id
    @JSONField(serialzeFeatures = {SerializerFeature.WriteNonStringValueAsString})
    private Integer id;

    /**
     *
     * monitor_word_dict.first
     *
     */
    private String first;

    /**
     *
     * monitor_word_dict.second
     *
     */
    private String second;

    /**
     *
     * monitor_word_dict.third
     *
     */
    private String third;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }
}