package com.liang.argorithm.concurrency.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/02/20 下午4:40
 */
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

  @RequestMapping("/test")
  public Long test() {
    return RequestHolder.getId();
  }
}
