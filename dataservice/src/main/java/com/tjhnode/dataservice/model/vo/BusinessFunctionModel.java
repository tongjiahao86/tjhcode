package com.tjhnode.dataservice.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 业务功能模块配置对象
 */
@Data
public class BusinessFunctionModel extends BaseConfigModel {
    //业务功能模块类型
    private String ofType;
    //返回类型
    private String returnType;
    //对象名
    private List<String> objName;
    //错误信息
    private String errorMes;
    //json对象结构
    private List<JsonObjSchame> objSchame;
}
