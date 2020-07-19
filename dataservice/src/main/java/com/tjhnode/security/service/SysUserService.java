package com.tjhnode.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.security.entity.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author tjh
 * @since 2020-05-31
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getSysUser(String username);
    ResultPaginationModel<SysUser> getUserList(QueryPaginationModel query);
}
