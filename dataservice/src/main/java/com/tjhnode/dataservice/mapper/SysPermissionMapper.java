package com.tjhnode.dataservice.mapper;

import com.tjhnode.dataservice.model.entities.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.vo.MenuCatalog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author tjh
 * @since 2020-07-18
 */
@Mapper
@Component
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 根据用户获取菜单
     * @param userid
     * @return
     */
    List<MenuCatalog> selectMenuByUserId(String userid);
}
