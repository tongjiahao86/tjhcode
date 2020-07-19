package com.tjhnode.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.security.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author tjh
 * @since 2020-05-31
 */
@Component
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
 
    SysUser getSysUser(String username);

    ResultPaginationModel<SysUser> getUserList(QueryPaginationModel query);
}
