package com.tjhnode.dataservice.model.vo;

import lombok.Data;

/**
 * 额外参数
 */
@Data
public class ExtraParameter {
    //参数名
    private String parametername;
    //参数值
    private String parametervalue;
    //是否单个值
    private boolean issinglevalue;
    //比较类型
    private int operationtype;
}
