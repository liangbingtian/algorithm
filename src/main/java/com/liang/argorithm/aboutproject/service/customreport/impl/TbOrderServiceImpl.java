package com.liang.argorithm.aboutproject.service.customreport.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.argorithm.aboutproject.entity.TbOrder;
import com.liang.argorithm.aboutproject.mapper.TbOrderMapper;
import com.liang.argorithm.aboutproject.service.ITbOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author liangbingtian
 * @since 2024-03-01
 */
@Service("tbOrderService")
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder> implements ITbOrderService {

    @Override
    public IPage<TbOrder> findListByPage(Integer page, Integer pageCount){
        IPage<TbOrder> wherePage = new Page<>(page, pageCount);
        TbOrder where = new TbOrder();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(TbOrder tbOrder){
        return baseMapper.insert(tbOrder);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(TbOrder tbOrder){
        return baseMapper.updateById(tbOrder);
    }

    @Override
    public TbOrder findById(Long id){
        return  baseMapper.selectById(id);
    }
}
