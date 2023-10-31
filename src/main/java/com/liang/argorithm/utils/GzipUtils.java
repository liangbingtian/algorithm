package com.liang.argorithm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;


/**
 * 文件压缩、哈希工具类
 * @author liuqiangm
 */
@Slf4j
public class GzipUtils {

  /**
   * 根据输入流，返回gzip解压缩后的字节流
   * @param inputStream
   * @return
   */
  public static byte[] getGzipCompressBytes(InputStream inputStream) {
    try {
      byte[] byteArray = IOUtils.toByteArray(inputStream);
      return getGzipCompressBytes(byteArray);
    } catch (IOException e) {
      log.error("", e);
    }
    return new byte[0];
  }

  /**
   * 根据输入的字节流，返回gzip压缩后的字节流
   * @param byteArray
   * @return
   */
  public static byte[] getGzipCompressBytes(byte[] byteArray) {
    if(byteArray == null) {
      return new byte[0];
    }
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try(OutputStream outputStream = new GZIPOutputStream(byteArrayOutputStream)) {
      outputStream.write(byteArray);
      outputStream.close();
      return byteArrayOutputStream.toByteArray();
    }
    catch (Exception e) {
      log.error("", e);
    }
    return new byte[0];
  }

  /**
   * 根据输入的字节流，返回gzip解压后的字节流
   * @param byteArray
   * @return
   */
  public static byte[] getGzipUnCompressBytes(byte[] byteArray) {
    try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray)) {
      try(GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
        return IOUtils.toByteArray(gzipInputStream);
      }
    } catch (IOException e) {
      log.error("", e);
    }
    return new byte[0];
  }

  /**
   * 根据输入的字节流，返回gzip解压后的输入流
   * @param byteArray
   * @return
   */
  public static InputStream getGzipUnCompressInputStream(byte[] byteArray) {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
    GZIPInputStream gzipInputStream = null;
    try {
      gzipInputStream = new GZIPInputStream(byteArrayInputStream);
    } catch (IOException e) {
      log.error("", e);
    }
    return gzipInputStream;
  }

}
