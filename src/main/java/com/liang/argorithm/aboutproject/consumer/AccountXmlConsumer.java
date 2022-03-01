package com.liang.argorithm.aboutproject.consumer;

import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public abstract class AccountXmlConsumer<T> implements IAccountXmlConsumer {

  protected final int batchSize = 2000;
  private List<T> objectList = new ArrayList<>(batchSize);
  private boolean init = true;
  protected BigDecimal progressStep = BigDecimal.valueOf(10);

  public void setProgressStep(BigDecimal progressStep) {
    this.progressStep = progressStep;
  }

  @Override
  public void start() {
    if (init) {
      init = false;
      initAction();
    }
  }

  @Override
  public void consume(JSONObject jsonObject) {
    T object = getTargetObject(jsonObject);
    if (object == null) {
      return;
    }
    objectList.add(object);
    if (objectList.size() == batchSize) {
      saveBatch(objectList);
      objectList.clear();
    }
  }

  /**
   * 遍历结束，列表当中仍然存在不足batchSize的元素，则补充save一次。
   */
  @Override
  public void end() {
    if (CollectionUtils.isNotEmpty(objectList)) {
      saveBatch(objectList);
      objectList = null;
    }
  }

  protected abstract void initAction();

  protected abstract T getTargetObject(JSONObject jsonObject);

  protected abstract void saveBatch(List<T> objectList);

  /**
   * 针对输入的List进行转换
   *
   * @param objectList
   * @return
   */
  protected List<List<T>> splitBatchList(List<T> objectList) {
    List<List<T>> resultList = new LinkedList<>();
    List<T> list = new ArrayList<>(batchSize);
    Iterator<T> iterator = objectList.iterator();
    // 一边加入新的List，一遍删除旧的List，节省内存，方便gc。
    int index = 0;
    while (iterator.hasNext()) {
      T object = iterator.next();
      iterator.remove();
      list.add(object);
      index++;
      // 达到了批处理大小，加入结果集，然后重置
      if (index == batchSize) {
        resultList.add(list);
        index = 0;
        list = new ArrayList<>(batchSize);
      }
    }
    if (!list.isEmpty()) {
      resultList.add(list);
    }
    return resultList;
  }
}
