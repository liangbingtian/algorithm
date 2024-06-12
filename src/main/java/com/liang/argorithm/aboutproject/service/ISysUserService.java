package com.liang.argorithm.aboutproject.service;

import com.liang.argorithm.aboutproject.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.argorithm.aboutproject.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public interface ISysUserService extends IService<SysUser> {


     SysUser selectById(Integer id);

     SysUser selectByName(String name);

}
