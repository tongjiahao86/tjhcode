package com.tjhnode.dataservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.entities.SqlConfigModel;

import java.io.IOException;
import java.util.List;

public interface SqlConfigService extends IService<SqlConfigModel> {
    /**
     * sql配置详情
     * @param ofid
     * @return
     */
    SqlConfigModel getSqlConfig(String ofid);

    /**
     * 新增sql配置
     * @param model
     * @return
     */
    String insertConfigItem(SqlConfigModel model);

    /**
     * 修改sql配置
     * @param model
     * @return
     */
    boolean updateConfigItem(SqlConfigModel model);

    /**
     * 删除
     * @param keyId
     * @return
     */
    boolean deleteConfigItem(String keyId);

    /**
     * 获取数据库名
     * @return
     * @throws IOException
     */
    List<String> getDbName();
}
