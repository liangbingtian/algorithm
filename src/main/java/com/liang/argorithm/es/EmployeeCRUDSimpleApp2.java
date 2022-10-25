package com.liang.argorithm.es;

import java.io.IOException;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 新版本的highlevel
 *
 * @author liangbingtian
 * @date 2022/10/14 上午11:41
 */
@Component
public class EmployeeCRUDSimpleApp2 {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  /**
   * 判断索引是否存在该索引
   */
  public boolean isExistsIndex(String indexName) throws IOException {
    GetIndexRequest request = new GetIndexRequest(indexName);
    return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
  }
}
