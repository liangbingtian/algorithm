package com.liang.argorithm.mysqlpractice.service.impl;

import com.liang.argorithm.aboutproject.entity.PersonInfoLarge;
import com.liang.argorithm.aboutproject.mapper.PersonInfoLargeMapper;
import com.liang.argorithm.mysqlpractice.service.IPersonInfoLarge;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liangbingtian
 * @date 2022/01/26 下午2:04
 */
@Service
public class PersonInfoLargeImpl implements IPersonInfoLarge {

  @Autowired
  private PersonInfoLargeMapper personInfoLargeMapper;


  @Override
  public void insertManyData() {
    int limit = 1000000;
    while(limit--!=0) {
      personInfoLargeMapper.insert(randomPersonInfo());
    }
  }

  private PersonInfoLarge randomPersonInfo() {
    PersonInfoLarge personInfo = new PersonInfoLarge();
    personInfo.setAccount(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
    personInfo.setName(UUID.randomUUID().toString().replace("-", "").substring(0, 20));
    personInfo.setArea(UUID.randomUUID().toString().replace("-", "").substring(0, 20));
    personInfo.setTitle(UUID.randomUUID().toString().replace("-", "").substring(0, 20));
    personInfo.setMotto(UUID.randomUUID().toString().replace("-", ""));
    return personInfo;
  }
}
