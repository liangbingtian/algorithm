package com.liang.argorithm.aboutproject.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liang.argorithm.aboutproject.entity.TbOrder;
import com.liang.argorithm.aboutproject.service.ITbOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author liangbingtian
 * @since 2024-03-01
 */
@Api(tags = {"订单表"})
@RestController
@RequestMapping("/tb-order")
public class TbOrderController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private ITbOrderService tbOrderService;


    @ApiOperation(value = "新增订单表")
    @PostMapping()
    public int add(@RequestBody TbOrder tbOrder){
        return tbOrderService.add(tbOrder);
    }

    @ApiOperation(value = "删除订单表")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return tbOrderService.delete(id);
    }

    @ApiOperation(value = "更新订单表")
    @PutMapping()
    public int update(@RequestBody TbOrder tbOrder){
        return tbOrderService.updateData(tbOrder);
    }

    @ApiOperation(value = "查询订单表分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<TbOrder> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return tbOrderService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询订单表")
    @GetMapping("{id}")
    public TbOrder findById(@PathVariable Long id){
        return tbOrderService.findById(id);
    }

}
