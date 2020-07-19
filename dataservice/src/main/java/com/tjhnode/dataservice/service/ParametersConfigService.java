package com.tjhnode.dataservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.entities.ParametersConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务功能参数配置 服务类
 * </p>
 *
 * @author tjh
 * @since 2019-12-01
 */
public interface ParametersConfigService extends IService<ParametersConfig> {
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
    String insertParamConfig(ParametersConfig model);

    /**
     * 根据主键删除
     * @param keyId 主键id
     * @return
     */
    boolean deleteParamConfigById(String keyId);

    /**
     * 根据业务功能id删除
     * @param ofid 业务功能id
     * @return
     */
    boolean deleteParamConfigByOFId(String ofid);
}
