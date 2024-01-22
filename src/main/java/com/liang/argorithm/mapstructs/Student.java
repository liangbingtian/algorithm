package com.liang.argorithm.mapstructs;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangbingtian
 * @date 2023/11/02 上午9:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  private String name;
  private int age;
  private GenderEnum gender;
  private Double height;
  private Date birthday;

}
