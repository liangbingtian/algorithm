package com.liang.argorithm.aboutproject.mapper;

import com.liang.argorithm.aboutproject.entity.MonitorWordDict;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorWordDictMapper {

    int batchInsert(List<MonitorWordDict> dicts);
}