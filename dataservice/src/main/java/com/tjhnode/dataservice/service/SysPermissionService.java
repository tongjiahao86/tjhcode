package com.tjhnode.dataservice.service;

import com.tjhnode.dataservice.model.entities.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.vo.MenuCatalog;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author tjh
 * @since 2020-07-18
 */
public interface SysPermissionService extends IService<SysPermission> {
    /**
     * 根据用户获取菜单
     * @param userid
     * @return
     */
    List<MenuCatalog> selectMenuByUserId(String userid);
}
