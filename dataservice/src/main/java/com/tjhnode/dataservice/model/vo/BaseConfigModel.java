package com.tjhnode.dataservice.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 配置对象基类
 */
@Data
public class BaseConfigModel {
    //Id
    private String id;
    //名称
    private String name;
    //sql语句
    private String sqlValue;
    //数据库名
    private String dbName;
    //描述
    private String description;
    //参数集合
    private List<ParameterModel> parameters;
    //排序
    private String orderStr;
    //参数值map
    private Map<String,Object> parameterValues;

}
