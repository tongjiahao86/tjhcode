package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.dataservice.model.entities.SysPermission;
import com.tjhnode.dataservice.mapper.SysPermissionMapper;
import com.tjhnode.dataservice.model.vo.MenuCatalog;
import com.tjhnode.dataservice.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author tjh
 * @since 2020-07-18
 */
@Service
@DS("appconfig")
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    /**
     * 根据用户获取菜单
     *
     * @param userid
     * @return
     */
    @Override
    public List<MenuCatalog>  selectMenuByUserId(String userid) {
        List<MenuCatalog> nodeList=new ArrayList<>();
        try{
            nodeList= permissionMapper.selectMenuByUserId(userid);
            List<MenuCatalog> parentList=nodeList.stream()
                    .filter(x-> x.getParentId().equals("0"))
                    .collect(Collectors.toList());

            for (MenuCatalog item:parentList) {
                item.setChildren(getChilds(item,nodeList));
            }
        }
        catch (Exception ex){
            throw ex;
        }
        return  nodeList;
    }

    /**
     *
     * @param node
     * @param catalogList
     * @return
     */
    private List<MenuCatalog> getChilds(MenuCatalog node,List<MenuCatalog> catalogList){
        List<MenuCatalog>  childrens = catalogList.stream()
                .filter(x -> x.getParentId().equals(node.getId()))
                .collect(Collectors.toList());
        for (MenuCatalog catalog:childrens) {
            catalog.setChildren(getChilds(catalog,catalogList));
        }
        return childrens;
    }
}
