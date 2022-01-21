package com.liang.argorithm.iopractice.niochatroom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝方法样例
 *
 * @author liangbingtian
 * @date 2022/01/17 下午9:38
 */
interface FileCopyRunner {

  void copyFile(File source, File target);
}

public class FileCopyDemo {

  private static void close(Closeable closeable) {
    if (closeable!=null) {
      try {
        closeable.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    FileCopyRunner noBufferStreamCopy = (source, target) -> {
      InputStream fin = null;
      OutputStream fout = null;
      try {
        fin = new FileInputStream(source);
        fout = new FileOutputStream(target);
        int result;
        while ((result = fin.read()) != -1) {
          fout.write(result);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        close(fin);
        close(fout);
      }
    };
    FileCopyRunner bufferedStreamCopy = (source, target) -> {
      InputStream fin = null;
      OutputStream fout = null;
      try {
        fin = new BufferedInputStream(new FileInputStream(source));
        fout = new BufferedOutputStream(new FileOutputStream(target));
        byte[] buffer = new byte[1024];
        int result;
        while((result = fin.read(buffer))!=-1) {
          fout.write(buffer, 0, result);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }finally {
        close(fin);
        close(fout);
      }
    };
    FileCopyRunner nioBufferCopy = (source, target) -> {
      FileChannel fin = null;
      FileChannel fout = null;

      try {
        fin = new FileInputStream(source).getChannel();
        fout = new FileOutputStream(target).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while((fin.read(buffer))!=-1) {
          buffer.flip();
          while (buffer.hasRemaining()) {
            fout.write(buffer);
          }
          buffer.clear();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }finally {
        close(fin);
        close(fout);
      }
    };
    FileCopyRunner nioTransferCopy = (source, target) -> {
      FileChannel fin = null;
      FileChannel fout = null;
      try {
        fin = new FileInputStream(source).getChannel();
        fout = new FileOutputStream(target).getChannel();
        long transferred = 0L;
        while (transferred!=fin.size()) {
          transferred+=fin.transferTo(0, fin.size(), fout);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }finally {
        close(fin);
        close(fout);
      }
    };
  }


}
