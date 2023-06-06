package com.liang.argorithm.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 测试IO相关
 *
 * @author liangbingtian
 * @date 2023/03/08 下午5:43
 */
@Slf4j
public class IOTest {

  @Test
  public void testIOBuffer() {
    long start = System.currentTimeMillis();
    String s = "/Users/liangbingtian/Desktop/algorithm/src/test/resources/pdftest/";
    try (InputStream in = new FileInputStream(s + "低光照图像增强算法综述_马龙.pdf");
        OutputStream ou = new FileOutputStream(s + "out.pdf")) {
      int content;
      while ((content = in.read()) != -1) {
        ou.write(content);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    System.out.println("普通复制的耗时：" + (end - start));

    long start1 = System.currentTimeMillis();
    try (InputStream in = new BufferedInputStream(new FileInputStream(s + "低光照图像增强算法综述_马龙.pdf"));
        OutputStream ou = new BufferedOutputStream(new FileOutputStream(s + "out.pdf"))) {
      int content;
      while ((content = in.read()) != -1) {
        ou.write(content);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    long end1 = System.currentTimeMillis();
    System.out.println("缓冲区的耗时：" + (end1 - start1));
  }


  @Test
  public void testIOBufferAndArray() {
    long start = System.currentTimeMillis();
    String s = "/Users/liangbingtian/Desktop/algorithm/src/test/resources/pdftest/";
    try (InputStream in = new FileInputStream(s + "低光照图像增强算法综述_马龙.pdf");
        OutputStream ou = new FileOutputStream(s + "out.pdf")) {
      int len;
      byte[] bytes = new byte[4 * 1024];
      while ((len = in.read(bytes)) != -1) {
        ou.write(bytes, 0, len);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    System.out.println("进行数据的读取耗费的时间为:" + (end - start));

    long start1 = System.currentTimeMillis();
    try (InputStream in = new BufferedInputStream(new FileInputStream(s + "低光照图像增强算法综述_马龙.pdf"));
        OutputStream ou = new BufferedOutputStream(new FileOutputStream(s + "out.pdf"))) {
      int length;
      byte[] bytes = new byte[4*1024];
      while ((length = in.read(bytes)) != -1) {
        ou.write(bytes, 0, length);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    long end1 = System.currentTimeMillis();
    System.out.println("进行数据读取耗费的时间为:" + (end1 - start1));
  }

  /**
   * 随机访问流
   */
  @Test
  public void randomAccess() throws IOException {
    RandomAccessFile accessFile = new RandomAccessFile("/Users/liangbingtian/Desktop/algorithm/src/test/resources/pdftest/文本.txt", "rw");
    log.info("读取之前的偏移量为:{}, 当前读到的字符为:{}, 读取后的偏移量为:{}", accessFile.getFilePointer(), (char) accessFile.read(), accessFile.getFilePointer());
    accessFile.seek(6);
    log.info("读取之前的偏移量为:{}, 当前读到的字符为:{}, 读取后的偏移量为:{}", accessFile.getFilePointer(), (char) accessFile.read(), accessFile.getFilePointer());
    accessFile.write(new byte[]{'U','V', 'W'});
    accessFile.seek(0);
    log.info("读取之前的偏移量为:{}, 当前读到的字符为:{}, 读取后的偏移量为:{}", accessFile.getFilePointer(), (char) accessFile.read(), accessFile.getFilePointer());
  }

  /**
   * nio监听流
   */
  @Test
  public void listenStream() throws IOException, InterruptedException {
    WatchService watchService = FileSystems.getDefault().newWatchService();
    Path path = Paths.get("/Users/liangbingtian/Desktop/algorithm/src/test/resources/pdftest");
    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    WatchKey key;
    while ((key = watchService.take())!=null) {
      for (WatchEvent<?> event : key.pollEvents()) {
        System.out.println("被调用了");
      }
      key.reset();
    }

  }

  @Test
  public void test1() {
    String str1 = "abc";
    String str2 = new String("abc");
    System.out.println(str1==str2);
  }


}
