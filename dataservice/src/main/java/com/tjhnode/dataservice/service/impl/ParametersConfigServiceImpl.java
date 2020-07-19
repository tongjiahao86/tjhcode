package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.dataservice.mapper.ParametersConfigMapper;
import com.tjhnode.dataservice.model.entities.ParametersConfig;
import com.tjhnode.dataservice.service.ParametersConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 业务功能参数配置 服务实现类
 * </p>
 *
 * @author tjh
 * @since 2019-12-01
 */
@Service
@DS("appconfig")
public class ParametersConfigServiceImpl extends ServiceImpl<ParametersConfigMapper, ParametersConfig> implements ParametersConfigService {

    @Autowired
    private ParametersConfigMapper parametersConfigMapper;
    /**
     * 获取参数
     *
     * @param ofid 业务功能id
     * @return
     */
    @Override
    public List<ParametersConfig> getParamConfigList(String ofid) {
        return parametersConfigMapper.getParamConfigList(ofid);

    }

    /**
     * 添加参数
     *
     * @param model
     * @return
     */
    @Override
    public String insertParamConfig(ParametersConfig model) {
        if(StringUtils.isNullOrEmpty(model.getKeyId()))
        {
            model.setKeyId(UUID.randomUUID().toString());
        }
        return parametersConfigMapper.insertParamConfig(model)>0?model.getKeyId():"";
    }

    /**
     * 根据主键删除
     *
     * @param keyId 主键id
     * @return
     */
    @Override
    public boolean deleteParamConfigById(String keyId) {
        return parametersConfigMapper.deleteParamConfigById(keyId) > 0;
    }

    /**
     * 根据业务功能id删除
     *
     * @param ofid 业务功能id
     * @return
     */
    @Override
    public boolean deleteParamConfigByOFId(String ofid) {
        return parametersConfigMapper.deleteParamConfigByOFId(ofid) > 0;
    }
}
