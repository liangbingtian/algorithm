package com.liang.argorithm.mapstructs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangbingtian
 * @date 2023/11/02 上午9:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentVO {

  private String name;
  private Integer age;
  private String gender;
  private Double height;
  private String birthday;
  private String course;
}
