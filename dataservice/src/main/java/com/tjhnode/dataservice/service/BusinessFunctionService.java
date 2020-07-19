package com.tjhnode.dataservice.service;

import com.tjhnode.dataservice.model.vo.BusinessFunctionModel;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @program: dataservice
 * @description: 配置接口服务相关
 * @author: tjh
 * @create: 2019-12-07 19:51
 **/
public interface BusinessFunctionService  {
    BusinessFunctionModel getConfigModelById(String Id);
    Map<String, Object> ExecuteService(BusinessFunctionModel configModel, QueryPaginationModel query);
    boolean ClearCache(String serviceId);
}
