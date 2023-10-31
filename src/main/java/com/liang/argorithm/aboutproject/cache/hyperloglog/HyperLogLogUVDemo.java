package com.liang.argorithm.aboutproject.cache.hyperloglog;

import java.text.SimpleDateFormat;
import java.util.Date;
import redis.clients.jedis.Jedis;

/**
 * @author liangbingtian
 * @date 2023/06/17 下午2:53
 */
public class HyperLogLogUVDemo {

  private Jedis jedis = new Jedis("127.0.0.1");

  public void initUVData() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new Date());
    for (int i = 0;i<1358;i++) {
      //返回1的话证明这条数据之前有，返回0的话证明这条数据之前没有
      jedis.pfadd("hyperloglog_uv_"+today, String.valueOf(i+1));
    }
  }

  public long getUV() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new Date());
    return jedis.pfcount("hyperloglog_uv_"+today);
  }

  public static void main(String[] args) {

  }

}
