package com.liang.argorithm.aboutproject.consumer.save;

import com.alibaba.fastjson.JSONObject;
import com.liang.argorithm.aboutproject.dto.AccountingItemDTO;
import com.liang.argorithm.aboutproject.entity.AccountingItem;
import com.liang.argorithm.aboutproject.entity.AccountingStandardItem;
import com.liang.argorithm.aboutproject.repository.AccountingRepository;
import com.liang.argorithm.aboutproject.consumer.AccountXmlConsumer;
import com.liang.argorithm.aboutproject.consumer.IAccountXmlConsumer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 会计科目相关存储，批量存储的相关具体类
 *
 * @author liangbingtian
 * @date 2022/02/28 下午10:50
 */
@Slf4j
@Setter
public class KJKMSaveConsumer extends AccountXmlConsumer<AccountingItem> implements
    IAccountXmlConsumer {

  private AccountingRepository repository;

  private Map<String, AccountingStandardItem> accountingStandardItemMap = null;

  @Override
  protected void initAction() {
    log.info("1.处理会计科目");
    List<AccountingItem> itemList = repository.findAll();
    int maxSize = Math.max(16, (int)(itemList.size()/0.75)+1);
    accountingStandardItemMap = new HashMap<>(maxSize);
    itemList.forEach(item->{
      accountingStandardItemMap.put(item.getCode(), item.getAccountingStandardItem());
    });
    //更新任务状态。
  }

  @Override
  protected AccountingItem getTargetObject(JSONObject jsonObject) {
    AccountingItemDTO dto = jsonObject.toJavaObject(AccountingItemDTO.class);
    AccountingItem item = new AccountingItem();
    BeanUtils.copyProperties(dto, item);
    item.setRelation("N");
    item.setAccountingStandardItem(accountingStandardItemMap.get(item.getCode()));
    return item;
  }

  @Override
  protected void saveBatch(List<AccountingItem> objectList) {
    repository.saveAll(objectList);
  }

  @Override
  public String getXmlPath() {
    return "总账.会计科目";
  }
}
