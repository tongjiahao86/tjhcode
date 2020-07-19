package com.tjhnode.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.entities.ConfigItemModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @program: dataservice
 * @description:业务功能配置项
 * @author: tjh
 * @create: 2019-11-24 13:39
 **/
@Mapper
@Component
public interface ConfigItemMapper extends BaseMapper<ConfigItemModel> {
    ConfigItemModel getConfigItemDetail(String ofid);
    int insertConfigItem(ConfigItemModel model);
    int updateConfigItem(ConfigItemModel model);
    int deleteConfigItem(String ofid);
}
