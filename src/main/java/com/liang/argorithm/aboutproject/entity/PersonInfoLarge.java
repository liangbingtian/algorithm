package com.liang.argorithm.aboutproject.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "person_info_large")
public class PersonInfoLarge {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String account;

    private String name;

    private String area;

    private String title;

    private String motto;
}