package com.tjhnode.dataservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.entities.ConfigItemModel;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;

public interface ConfigItemService extends IService<ConfigItemModel> {
    /**
     * 接口配置列表
     * @param query
     * @param classificationaId
     * @return
     */
    ResultPaginationModel<ConfigItemModel> getConfigList(QueryPaginationModel query, String classificationaId);

    /**
     * 接口配置详情
     * @param ofid
     * @return
     */
    ConfigItemModel getConfigItemDetail(String ofid);

    /**
     * 新增
     * @param model
     * @return
     */
    String insertConfigItem(ConfigItemModel model);

    /**
     * 修改
     * @param model
     * @return
     */
    boolean updateConfigItem(ConfigItemModel model);

    /**
     * 删除
     * @param ofid
     * @return
     */
    boolean deleteConfigItem(String ofid);
}
