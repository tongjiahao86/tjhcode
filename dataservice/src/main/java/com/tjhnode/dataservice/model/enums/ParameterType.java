package com.tjhnode.dataservice.model.enums;

public enum ParameterType {
    Int32(1),
    VarChar(2),
    DataTime(3);

    private Integer code;

    ParameterType(Integer code) {
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}