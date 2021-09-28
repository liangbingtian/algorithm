package com.liang.argorithm.aboutproject.mapper;

import com.liang.argorithm.aboutproject.entity.AlgorithmQuestion;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface AlgorithmQuestionMapper extends Mapper<AlgorithmQuestion> {
}