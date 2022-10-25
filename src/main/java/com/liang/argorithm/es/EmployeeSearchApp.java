package com.liang.argorithm.es;

import static java.net.InetAddress.getByName;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 * 一些稍微复杂点儿的查询， 员工搜索
 *
 * @author liangbingtian
 * @date 2022/10/14 上午11:08
 */
public class EmployeeSearchApp {

  public static void main(String[] args) throws UnknownHostException {
    Settings settings = Settings.builder().build();
    TransportClient client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
  }
}
