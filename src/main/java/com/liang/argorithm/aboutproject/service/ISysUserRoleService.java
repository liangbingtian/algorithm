package com.liang.argorithm.aboutproject.service;

import com.liang.argorithm.aboutproject.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    List<SysUserRole> listByUserId(Integer userId);

}
