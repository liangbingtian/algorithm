package com.liang.argorithm.aboutproject.repository;

import com.liang.argorithm.aboutproject.entity.AccountingItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * mongo的存储类
 *
 * @author liangbingtian
 * @date 2022/03/01 上午10:05
 */
public interface AccountingRepository extends MongoRepository<AccountingItem, String> {

}
