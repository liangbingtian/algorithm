package com.liang.argorithm.aboutproject.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liang.argorithm.aboutproject.entity.JdShopAuthorizeInfo;
import com.liang.argorithm.aboutproject.entity.TbOrder;
import com.liang.argorithm.aboutproject.service.ITbOrderService;
import com.liang.argorithm.aboutproject.service.JdShopAuthorizeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private JdShopAuthorizeInfoService jdShopAuthorizeInfoService;


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

    @GetMapping("/name")
    @Transactional(rollbackFor = Exception.class)
    public void findById1() throws IOException {
        final List<JdShopAuthorizeInfo> list = jdShopAuthorizeInfoService.list();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("source9.json");
        JSONArray jsonData = JSON.parseObject(inputStream, JSONArray.class);
        final List<JdShopAuthorizeInfo> javaList = jsonData.toJavaList(JdShopAuthorizeInfo.class);
        final Map<String, JdShopAuthorizeInfo> collect2 = javaList.stream().collect(Collectors.toMap(JdShopAuthorizeInfo::getUsername, Function.identity(), (a, b)->b));
        for (JdShopAuthorizeInfo shopAuthorizeInfo : list) {
            if (collect2.containsKey(shopAuthorizeInfo.getUsername())) {
                final JdShopAuthorizeInfo jdShopAuthorizeInfo = new JdShopAuthorizeInfo();
                jdShopAuthorizeInfo.setId(shopAuthorizeInfo.getId());
                final JdShopAuthorizeInfo jdShopAuthorizeInfo1 = collect2.get(shopAuthorizeInfo.getUsername());
                jdShopAuthorizeInfo.setRefreshToken(jdShopAuthorizeInfo1.getRefreshToken());
                final DateTime dateTime = DateUtil.offsetSecond(DateUtil.date(jdShopAuthorizeInfo1.getTime()), jdShopAuthorizeInfo1.getExpiresIn());
                jdShopAuthorizeInfo.setAuthorizeExpireTime(dateTime.toJdkDate());
                jdShopAuthorizeInfo.setAccessToken(jdShopAuthorizeInfo1.getAccessToken());
                jdShopAuthorizeInfo.setTfOpen(Byte.valueOf("1"));
                final LambdaUpdateWrapper<JdShopAuthorizeInfo> updateWrapper = Wrappers.lambdaUpdate(JdShopAuthorizeInfo.class)
                        .eq(JdShopAuthorizeInfo::getUsername, shopAuthorizeInfo.getUsername());
                jdShopAuthorizeInfoService.update(jdShopAuthorizeInfo, updateWrapper);
            }
        }
//        final Set<String> collect = list.stream().map(JdShopAuthorizeInfo::getUsername).collect(Collectors.toSet());
//        final Set<JdShopAuthorizeInfo> collect1 = javaList.stream().filter(data -> !collect.contains(data.getUsername())).peek(data->{data.setAppkey("A1D3C721A3E382FF4915BE266B4294F6");data.setAppSecret("8d08db8de0ec468ebe234dcfdc1c3dca");
//            data.setTfOpen(Byte.valueOf("1"));
//            final DateTime dateTime = DateUtil.offsetSecond(DateUtil.date(data.getTime()), data.getExpiresIn());
//                data.setAuthorizeExpireTime(dateTime.toJdkDate());
//        }).collect(Collectors.toSet());
//        jdShopAuthorizeInfoService.saveBatch(collect1);

        System.out.println();
    }

}
