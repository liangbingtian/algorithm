package com.liang.argorithm.concurrency.annoations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口类
 *
 * @author liangbingtian
 * @date 2022/02/06 下午5:22
 */
@RestController
@Slf4j
public class TestController {

  @RequestMapping("/test")
  public String test() {
    return "test";
  }
}
