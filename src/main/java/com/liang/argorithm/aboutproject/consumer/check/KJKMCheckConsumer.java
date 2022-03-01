package com.liang.argorithm.aboutproject.consumer.check;

import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.entity.AccountingItem;
import com.liang.argorithm.aboutproject.consumer.AccountXmlConsumer;
import com.liang.argorithm.aboutproject.consumer.IAccountXmlConsumer;
import java.util.List;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 会计科目相关的内容检查
 *
 * @author liangbingtian
 * @date 2022/02/28 下午10:19
 */
@Setter
@Slf4j
public class KJKMCheckConsumer extends AccountXmlConsumer<AccountingItem> implements
    IAccountXmlConsumer {

  @Override
  protected void initAction() {
    log.info("检查会计科目");
    //更新任务状态为已开始
  }

  @Override
  protected AccountingItem getTargetObject(JSONObject jsonObject) {
    return null;
  }

  @Override
  protected void saveBatch(List<AccountingItem> objectList) {

  }

  @Override
  public String getXmlPath() {
    return "总账.会计科目";
  }

  @Override
  public void end() {
    super.end();
    //更新检查的任务状态
  }
}
