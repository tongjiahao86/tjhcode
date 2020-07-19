package com.tjhnode.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JSONResult implements Serializable {

    public JSONResult(){}

    @JsonProperty("DataContext")
    private Object DataContext;

    @JsonProperty("ErrorCode")
    private com.tjhnode.common.vo.ErrorCode ErrorCode;

    //private ResultModel data;

    @JSONField(name="DataContext")
    @JsonIgnore
    public Object getDataContext() {
        return DataContext;
    }

    public void setDataContext(Object dataContext) {
        DataContext = dataContext;
    }

    @JSONField(name = "ErrorCode")
    @JsonIgnore
    public com.tjhnode.common.vo.ErrorCode getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(com.tjhnode.common.vo.ErrorCode errorCode) {
        ErrorCode = errorCode;
    }

    public JSONResult(com.tjhnode.common.vo.ErrorCode errorCode, Object dataContext) {
        this.DataContext=dataContext;
        this.ErrorCode = errorCode;
    }


}
