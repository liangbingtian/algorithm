package com.liang.argorithm;

import com.alibaba.fastjson.JSON;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * 测试一些结果
 *
 * @author liangbingtian
 * @date 2021/04/23 下午8:49
 */
public class AlgorithmTest {

  @Test
  public void testSortResult() {

  }

  private List<Integer> readSourceFileAndUseIt(String fileName) {
    try {
      InputStream fileReadInputStream = new ByteArrayInputStream(Files.readAllBytes(Paths.get(fileName)));
      String arrayStr = IOUtils.toString(fileReadInputStream, StandardCharsets.UTF_8);
      return JSON.parseArray(arrayStr, Integer.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

}
