package com.liang.argorithm.es;

import cn.hutool.core.util.StrUtil;
import com.liang.argorithm.dto.es.InfoDataDTO;
import com.liang.argorithm.dto.es.PageList;
import com.liang.argorithm.dto.es.ResultVO;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
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

  /**
   * 创建索引名请求体
   */
  public static String MONITOR_CREATE_INDEX_BODY = "{\"aliases\":{\"scheme_user_{}\":{\"is_write_index\":true}},\"settings\":{\"number_of_shards\":1,\"number_of_replicas\":0}}";

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  /**
   * 判断索引是否存在该索引
   */
  public boolean isExistsIndex(String indexName) throws IOException {
    GetIndexRequest request = new GetIndexRequest(indexName);
    return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
  }

  /**
   * 用给定的请求体去创建索引
   */
   public String createByJsonStr(String name) throws IOException {
     Response response = restHighLevelClient.getLowLevelClient().performRequest(putIndex(name));
     HttpEntity entity = response.getEntity();
     return EntityUtils.toString(entity);
   }

  /**
   * 查询信息，包括去重查询
   */
  public PageList<ResultVO> getInformationList(InfoDataDTO dataDTO) {
    CollapseBuilder collapseBuilder = null;
    CardinalityAggregationBuilder aggregationBuilder = null;
    if (dataDTO.getIsCollapse()) {
      collapseBuilder = new CollapseBuilder("price");
      aggregationBuilder = AggregationBuilders.cardinality("count").field("price");
    }
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    return null;

  }

  private static Request putIndex(String name) {
    Request request = new Request("PUT", "/"+ StrUtil.format(MONITOR_CREATE_INDEX, name));
    request.setJsonEntity(StrUtil.format(MONITOR_CREATE_INDEX_BODY, name));
    return request;
  }
}
