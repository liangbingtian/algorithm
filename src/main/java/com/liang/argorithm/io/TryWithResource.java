package com.liang.argorithm.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * 用try-with-resource代替try-catch-finally
 *
 * @author liangbingtian
 * @date 2023/05/12 上午10:14
 */
public class TryWithResource {

  public void scannerTryCatchFinally() {
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File("abc/d.txt"));
      while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      scanner.close();
    }
  }

  public void scannerTryWithResource() {
    try (Scanner scanner = new Scanner(new File("abc/d.txt"))) {
      while (scanner.hasNext()) {
        System.out.println(scanner.nextLine());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void inputOutputWithResource() {
    try(BufferedInputStream buf1 = new BufferedInputStream(new FileInputStream("abc/d.txt"));
        BufferedOutputStream buf2 = new BufferedOutputStream(new FileOutputStream("abc/e.txt"))) {
      int b;
      while ((b = buf1.read())!=-1) {
        buf2.write(b);
      }
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

}
