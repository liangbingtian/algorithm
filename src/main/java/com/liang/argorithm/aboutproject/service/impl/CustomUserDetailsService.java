package com.liang.argorithm.aboutproject.service.impl;

import com.liang.argorithm.aboutproject.entity.SysRole;
import com.liang.argorithm.aboutproject.entity.SysUser;
import com.liang.argorithm.aboutproject.entity.SysUserRole;
import com.liang.argorithm.aboutproject.service.ISysRoleService;
import com.liang.argorithm.aboutproject.service.ISysUserRoleService;
import com.liang.argorithm.aboutproject.service.ISysUserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author liangbingtian
 * @date 2024/06/11 下午2:07
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        SysUser user = sysUserService.selectByName(username);

        // 判断用户是否存在
        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
        for (SysUserRole userRole : userRoles) {
            SysRole role = roleService.selectById(userRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // 返回UserDetails实现类
        return new User(user.getName(), user.getPassword(), authorities);
    }
}

