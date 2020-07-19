package com.tjhnode.security.service.Impl;

import com.tjhnode.security.entity.SysUser;
import com.tjhnode.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * 登录专用类
 * 自定义类，实现了UserDetailsService接口，用户登录时调用的第一类
 * @author 程就人生
 *
 */
@Component
public class MyCustomUserService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
        SysUser myUserDetail =sysUserService.getSysUser(username);
        if(myUserDetail!=null) {
            myUserDetail.setUsername(username);
            myUserDetail.setPassword(myUserDetail.getPassword());
        }
        return myUserDetail;
    }
}