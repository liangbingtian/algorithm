package com.liang.argorithm.aboutproject.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/04/26 下午12:20
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

//  @Autowired
//  private RedisClient redisClient;
//
//  @RequestMapping("/set")
//  public String set(@RequestParam("k") String k, @RequestParam("v") String v) throws Exception {
//    redisClient.set(k, v);
//    return "SUCCESS";
//  }
//
//  @RequestMapping("/get")
//  public String get(@RequestParam("k") String key) throws Exception{
//    return redisClient.get(key);
//  }
}
