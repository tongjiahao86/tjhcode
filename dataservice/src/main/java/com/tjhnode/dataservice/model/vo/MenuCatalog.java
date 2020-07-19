package com.tjhnode.dataservice.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: dataservice
 * @description: 菜单树
 * @author: tjh
 * @create: 2020-07-13 19:49
 **/
@Data
public class MenuCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(serialize =  false)
    private String id;
    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    private Integer menuType;
    /**
     * 菜单名称
     */
    @TableField(value="name")
    private String name;
    /**
     * 父id
     */
    @JSONField(serialize =  false)
    private String parentId;

    /**
     * 组件
     */
    private String component;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单排序
     */
    private Double sortNo;

    private Integer isLeaf;

    public MenuCatalog() {

    }
    private List<MenuCatalog> children;
}
