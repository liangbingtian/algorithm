package com.liang.argorithm.aboutproject.service;

import com.liang.argorithm.aboutproject.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 *
 */
public interface ISysPermissionService extends IService<SysPermission> {

    List<SysPermission> listByRoleId(Integer roleId);

}
