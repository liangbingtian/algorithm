package com.liang.argorithm.springtest;

import com.liang.argorithm.springtest.annotation.MyComponent;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liangbingtian
 * @date 2023/05/04 上午10:18
 */
public class ApplicationContext {

  //ioc容器
  private Map<String, Object> iocMap = new ConcurrentHashMap<>();

  public ApplicationContext(String packagePath) {
    //扫描指定的包路径
    scanPackage(packagePath);
  }

  private void scanPackage(String packagePath) {
    File[] classFiles = getClassFiles(packagePath);
    processClassFiles(packagePath, classFiles);
  }

  private void processClassFiles(String packagePath, File[] classFiles) {
    //将.替换成/
    for (File classFile : classFiles) {
      String className = classFile.getName().substring(0, classFile.getName().lastIndexOf("."));
      String fullyClassName = packagePath + "." + className;
      String beanName = String.valueOf(className.charAt(0)).toLowerCase() + className.substring(1);
      createBean(beanName, fullyClassName);
    }
  }

  private void createBean(String beanName, String fullyClassName) {
    try {
      Class<?> aClass = Class.forName(fullyClassName);
      //判断是否加上了注解
      if (aClass.isAnnotationPresent(MyComponent.class)) {
        System.out.println("fullyClassName = " + fullyClassName + "加入@MyComponent注解，准备通过反射创建实例，并放入IOCMap中");
        Object instance = aClass.newInstance();
        iocMap.put(beanName, instance);
      }else {
        System.out.println("fullyClassName = " + fullyClassName + "没有加@MyComponent注解，所以不需要创建实例");
      }

    } catch (Exception e) {
      System.out.println("创建实例放入IOC容器时候报错");
    }
  }

  private File[] getClassFiles(String packagePath) {
    //获取目录
    File file = getFile(packagePath);
    //过滤出所有的class文件
    return filterClassFiles(packagePath, file);
  }

  private File[] filterClassFiles(String packagePath, File file) {
    return file.listFiles(f->{
      String fileName = f.getName();
      if (f.isDirectory()) {
        scanPackage(packagePath + "." + fileName);
      }else {
        if (fileName.endsWith(".class")) {
          return true;
        }
      }
      return false;
    });
  }

  private File getFile(String packagePath) {
    //替换路径
    String packageDir = packagePath.replaceAll("\\.", "/");
    URL url = getClass().getClassLoader().getResource(packageDir);
    return new File(url.getFile());
  }
}
