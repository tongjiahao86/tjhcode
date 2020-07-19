package com.tjhnode.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.entities.ParametersConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 业务功能参数配置 Mapper 接口
 * </p>
 *
 * @author tjh
 * @since 2019-12-01
 */
@Mapper
@Component
public interface ParametersConfigMapper extends BaseMapper<ParametersConfig> {

    /**
     * 获取参数
     * @param ofid 业务功能id
     * @return
     */
    List<ParametersConfig> getParamConfigList(String ofid);

    /**
     * 添加参数
     * @param model
     * @return
     */
    int insertParamConfig(ParametersConfig model);

    /**
     * 根据主键删除
     * @param keyId 主键id
     * @return
     */
    int deleteParamConfigById(String keyId);

    /**
     * 根据业务功能id删除
     * @param ofid 业务功能id
     * @return
     */
    int deleteParamConfigByOFId(String ofid);
}
