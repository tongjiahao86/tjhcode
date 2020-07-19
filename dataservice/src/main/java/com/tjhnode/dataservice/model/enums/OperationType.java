package com.tjhnode.dataservice.model.enums;

public enum OperationType {
    like(1),
    equal(2),
    between(3);


    private Integer code;

    OperationType(Integer code) {
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}
