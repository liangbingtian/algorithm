package com.liang.argorithm.dto.es;

import java.util.List;
import lombok.Data;

/**
 * 分页查询
 *
 * @author liangbingtian
 * @date 2022/10/27 下午2:30
 */
@Data
public class PageList<T> {

  private List<T> list;

  private int totalPage = 0;

  private long totalElement = 0;

  private Object[] sortValues;

  private int currentPage;

  private int pageSize;

  /**
   * ES默认分页最多查询10000条，多了直接返回
   */
  private int maxPageCount = 10000;
}
