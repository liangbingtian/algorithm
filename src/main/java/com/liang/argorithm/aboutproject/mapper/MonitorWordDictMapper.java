package com.liang.argorithm.aboutproject.mapper;

import com.liang.argorithm.aboutproject.entity.MonitorWordDict;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

@Repository
public interface MonitorWordDictMapper extends BaseMapper<MonitorWordDict> {

    int batchInsert(List<MonitorWordDict> dicts);
}