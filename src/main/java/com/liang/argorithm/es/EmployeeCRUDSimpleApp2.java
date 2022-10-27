package com.liang.argorithm.es;

import java.io.IOException;
import org.elasticsearch.client.Request;
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

  public static final String MONITOR_CREATE_INDEX = "%3Cscheme_user_{}_%7Bnow%2Fd%7ByyyyMMdd%7CUTC%2B8%7D%7D-000001%3E";

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  /**
   * 判断索引是否存在该索引
   */
  public boolean isExistsIndex(String indexName) throws IOException {
    GetIndexRequest request = new GetIndexRequest(indexName);
    return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
  }

  private static Request putIndex(String name) {
//    Request request = new Request("PUT", "/"+StrUtil)
    return null;
  }
}
