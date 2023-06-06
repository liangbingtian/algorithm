package com.liang.argorithm.characteristic.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author liangbingtian
 * @date 2023/06/05 下午5:25
 */
public class CreateStream {


  private static Stream<String> createStreamFromCollection() {
    List<String> strings = Arrays.asList("123", "456");
    return strings.stream();
  }

  private static Stream<String> createStreamFromValues() {
    return Stream.of("123", "456");
  }

  private static Stream<String> createStreamFromArrays() {
    return Arrays.stream(new String[]{"123", "456"});
  }

  private static Stream<String> getStreamFromFile() {
    Path path = Paths.get("a/b/c.txt");
    try(Stream<String> lines = Files.lines(path)) {
      return lines;
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

}
