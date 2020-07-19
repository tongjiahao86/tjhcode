package com.tjhnode.dataservice.model.vo;

import com.tjhnode.dataservice.model.enums.ParameterType;
import lombok.Data;

/**
 * 参数对象
 */
@Data
public class ParameterModel {
    //参数键
    private String parameterkey;
    //参数名
    private String parametername;
    //是否必需参数
    private int isrequisite;
    //参数值
    private String parametervalue;
    //参数类型
    private ParameterType parametertype;

}
