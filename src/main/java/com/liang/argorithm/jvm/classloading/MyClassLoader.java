package com.liang.argorithm.jvm.classloading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;

/**
 * @author liangbingtian
 * @date 2023/03/06 下午5:06
 */
public class MyClassLoader extends ClassLoader{


  public static void main(String[] args) throws IOException {
//    InputStream resourceAsStream = MyClassLoader.class.getClassLoader()
//        .getResourceAsStream("test/Hello.class");
//    byte[] bytes = IOUtils.toByteArray(resourceAsStream);
//    System.out.println(Base64.getEncoder().encodeToString(bytes));
    try {
      new MyClassLoader().findClass("com.liang.argorithm.jvm.classloading.Hello").newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    try {
      String helloBase64 = loadClass();
      byte[] bytes = Base64.getDecoder().decode(helloBase64);
      return defineClass(name, bytes, 0, bytes.length);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String loadClass() throws IOException {
    InputStream resourceAsStream = MyClassLoader.class.getClassLoader().getResourceAsStream(
        "test/测试.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
    Set<String> stringSet = new HashSet<>();
    String str = null;
    while ((str = reader.readLine()) != null) {
      if (!str.equals("")) {
        stringSet.add(str);
      }
    }
    for (String s : stringSet) {
      return s;
    }
    return "";
  }

}
