package com.liang.argorithm.mysqlpractice.service;


import cn.hutool.core.date.DateUtil;
import com.liang.argorithm.aboutproject.entity.PersonInfoLarge;
import com.liang.argorithm.aboutproject.entity.TbOrder;
import com.liang.argorithm.aboutproject.mapper.PersonInfoLargeMapper;
import com.liang.argorithm.aboutproject.service.ITbOrderService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liangbingtian
 * @date 2022/01/26 下午2:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.liang.argorithm.ArgorithmApplication.class)
public class IPersonInfoLargeTest {

  @Autowired
  private PersonInfoLargeMapper personInfoLargeMapper;

  @Resource
  private ITbOrderService tbOrderService;

  @Test
  public void insert() {
    int limit = 10;
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

  @Test
  public void select() {
    final TbOrder byId = tbOrderService.findById(1L);
    System.out.println(byId);
  }

  @Test
  public void change() {
    System.out.println(new StringBuilder(null));
  }

}