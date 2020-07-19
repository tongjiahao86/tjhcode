package com.tjhnode.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.entities.SqlConfigModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SqlConfigMapper extends BaseMapper<SqlConfigModel> {
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
    int insertConfigItem(SqlConfigModel model);

    /**
     * 修改sql配置
     * @param model
     * @return
     */
    int updateConfigItem(SqlConfigModel model);

    /**
     * 删除
     * @param keyId
     * @return
     */
    int deleteConfigItem(String keyId);
}
