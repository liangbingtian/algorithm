package com.liang.argorithm.es;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工增删改查的应用程序
 * 老版本的
 *
 * @author liangbingtian
 * @date 2022/09/02 下午5:19
 */
public class EmployeeCRUDSimpleApp {

  public static void main(String[] args) throws IOException {
    //先构建client客户端
    Settings settings = Settings.builder()
        .build();
    TransportClient client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
//    createEmployee(client);
//    getEmployee(client);
    updateEmployee(client);
    client.close();
  }

  private static void createEmployee(TransportClient client) throws IOException {
    IndexResponse response = client.prepareIndex("company", "employee", "1")
        .setSource(XContentFactory.jsonBuilder().startObject()
            .field("name", "jack")
            .field("age", 27)
            .field("position", "technique")
            .field("country", "china")
            .field("join_date", "2017-01-01")
            .field("salary", 10000).endObject()).get();
    System.out.println(response.getResult());
  }

  /**
   * 获取员工信息
   */
  private static void getEmployee(TransportClient client) {
    GetRequestBuilder getResponse = client.prepareGet("company", "employee", "1");
    System.out.println(getResponse.get().getSourceAsString());
  }

  /**
   * 修改员工信息
   */
  private static void updateEmployee(TransportClient client) throws IOException {
    UpdateResponse builder = client.prepareUpdate("company", "employee", "1")
        .setDoc(XContentFactory.jsonBuilder().startObject().field("position", "technique manager")
            .endObject()).get();
    System.out.println(builder.getResult());
  }

  private static void deleteEmployee(TransportClient client) {
    DeleteResponse response = client.prepareDelete("company", "employee", "1").get();
    System.out.println(response.getResult());
  }

}
