package com.liang.argorithm.jvm.classloading;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * 打印classloader的示例
 *
 * @author liangbingtian
 * @date 2023/03/06 下午4:17
 */
public class JwtClassLoaderPrintPath {

  public static void main(String[] args) {
    //启动类加载器
    URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
    System.out.println("启动类加载器");
    for (URL url : urls) {
      System.out.println("==>" + url.toExternalForm());
    }
    //扩展类加载器
    printClassLoader("扩展类加载器", JwtClassLoaderPrintPath.class.getClassLoader().getParent());

    //应用类加载器
    printClassLoader("应用类加载器", JwtClassLoaderPrintPath.class.getClassLoader());
  }

  public static void printClassLoader(String name, ClassLoader cl) {
    if (cl!=null) {
      System.out.println(name + "ClassLoader -> " + cl.toString());
      printURLForClassLoader(cl);
    }else {
      System.out.println(name + "ClassLoader -> null");
    }
  }

  private static void printURLForClassLoader(ClassLoader cl) {
    Object ucp = insightField(cl, "ucp");
    Object path = insightField(ucp, "path");
    ArrayList ps = (ArrayList) path;
    for (Object p : ps) {
      System.out.println("==>" + p.toString());
    }
  }

  private static Object insightField(Object object, String fieldName) {
    try {
      Field f = null;
      if (object instanceof URLClassLoader) {
        f = URLClassLoader.class.getDeclaredField(fieldName);
      }else {
        f = object.getClass().getDeclaredField(fieldName);
      }
      f.setAccessible(true);
      return f.get(object);
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


}
