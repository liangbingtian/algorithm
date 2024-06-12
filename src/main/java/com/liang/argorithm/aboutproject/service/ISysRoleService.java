package com.liang.argorithm.aboutproject.service;

import com.liang.argorithm.aboutproject.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface ISysRoleService extends IService<SysRole> {

     SysRole selectById(Integer id);

     SysRole selectByName(String name);

}
