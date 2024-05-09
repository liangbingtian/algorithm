package com.liang.argorithm.aboutproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.argorithm.aboutproject.entity.TbOrder;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author liangbingtian
 * @since 2024-03-01
 */
public interface ITbOrderService extends IService<TbOrder> {

    /**
     * 查询订单表分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<TbOrder>
     */
    IPage<TbOrder> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加订单表
     *
     * @param tbOrder 订单表
     * @return int
     */
    int add(TbOrder tbOrder);

    /**
     * 删除订单表
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改订单表
     *
     * @param tbOrder 订单表
     * @return int
     */
    int updateData(TbOrder tbOrder);

    /**
     * id查询数据
     *
     * @param id id
     * @return TbOrder
     */
    TbOrder findById(Long id);
}
