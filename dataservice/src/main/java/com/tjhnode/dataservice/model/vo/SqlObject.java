package com.tjhnode.dataservice.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @program: dataservice
 * @description:
 * @author: tjh
 * @create: 2019-11-28 21:04
 **/
public class SqlObject implements Serializable {

    @JsonProperty("SQL")
    public String SQL;

    @JsonProperty("ObjectName")
    public String ObjectName;

    @JsonProperty("ObjectType")
    public String ObjectType;

    @JsonIgnore
    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }

    @JsonIgnore
    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    @JsonIgnore
    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String objectType) {
        ObjectType = objectType;
    }
}
