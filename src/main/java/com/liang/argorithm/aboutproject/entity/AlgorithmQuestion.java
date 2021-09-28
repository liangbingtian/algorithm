package com.liang.argorithm.aboutproject.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "algorithm_question")
public class AlgorithmQuestion {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 题目的名称
     */
    @Column(name = "question_name")
    private String questionName;

    /**
     * 题目编号
     */
    @Column(name = "question_no")
    private String questionNo;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date ts;
}