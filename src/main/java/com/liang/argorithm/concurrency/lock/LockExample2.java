package com.liang.argorithm.concurrency.lock;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liangbingtian
 * @date 2022/04/15 上午10:37
 */
@Slf4j
public class LockExample2 {

  private final Map<String, Data> map = new TreeMap<>();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  private final Lock readLock = lock.readLock();
  private final Lock writeLock = lock.writeLock();

  public Data get(String key) {
    readLock.lock();
    try {
      return map.get(key);
    }finally {
      readLock.unlock();
    }
  }

  public Set<String> getAllKeys() {
    readLock.lock();
    try {
      return map.keySet();
    }finally {
      readLock.unlock();
    }
  }

  public Data put(String key, Data value) {
    writeLock.lock();
    try {
      return map.put(key, value);
    }finally {
      writeLock.unlock();
    }
  }

  class Data {

  }


}
