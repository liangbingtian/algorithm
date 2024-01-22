package com.liang.argorithm.mapstructs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangbingtian
 * @date 2023/11/02 上午9:59
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Course {

  private String courseName;
  private int sortNo;
  private long id;

}