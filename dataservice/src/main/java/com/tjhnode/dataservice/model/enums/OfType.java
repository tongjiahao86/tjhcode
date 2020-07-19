package com.tjhnode.dataservice.model.enums;

public enum OfType {
    SQL(1),
    Procedure(2);
    private Integer code;
    OfType(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}
