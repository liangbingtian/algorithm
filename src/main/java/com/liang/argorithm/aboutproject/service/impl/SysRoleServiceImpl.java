package com.liang.argorithm.aboutproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.argorithm.aboutproject.entity.SysRole;
import com.liang.argorithm.aboutproject.service.ISysRoleService;
import com.liang.argorithm.aboutproject.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public SysRole selectById(Integer id){
        return roleMapper.selectById(id);
    }

    @Override
    public SysRole selectByName(String name) {
        return roleMapper.selectByName(name);
    }

}




