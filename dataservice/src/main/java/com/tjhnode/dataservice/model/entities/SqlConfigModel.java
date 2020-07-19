package com.tjhnode.dataservice.model.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tjhnode.dataservice.model.vo.SqlObject;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: dataservice
 * @description: sql配置
 * @author: tjh
 * @create: 2019-11-25 21:54
 **/
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("of_sqlconfig")
public class SqlConfigModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("id")
    private int id;
    /**
     * 主键
     */
    @TableField("KeyId")
    @JsonProperty("KeyId")
    public String KeyId;

    /**
     * 数据库名称
     */
    @TableField("DBName")
    @JsonProperty("DBName")
    public String DBName;

    /**
     * 返回类型
     */
    @TableField("ReturnType")
    @JsonProperty("ReturnType")
    public String ReturnType;

    /**
     * sql语句
     */
    @TableField("SqlValue")
    @JsonProperty("SqlValue")
    public String SqlValue;

    /**
     * 分隔符
     */
    @TableField("SplitChar")
    @JsonProperty("SplitChar")
    public String SplitChar;

    /**
     * 返回字段
     */
    @TableField("ReturnColumn")
    @JsonProperty("ReturnColumn")
    public String ReturnColumn;

    /**
     * 业务功能id
     */
    @TableField("OFID")
    @JsonProperty("OFID")
    public String OFID;

    /**
     * 对象名称（用','分割）
     */
    @TableField("ObjectName")
    @JsonProperty("ObjectName")
    public String ObjectName;

    /**
     * 排序码
     */
    @TableField("ParameterName")
    @JsonProperty("SortCode")
    public Integer SortCode;

    /**
     * 创建日期
     */
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    public LocalDateTime CreateDate;

    /**
     * 修改日期
     */
    @TableField("ModifyDate")
    @JsonProperty("ModifyDate")
    public LocalDateTime ModifyDate;

    @JsonProperty("SqlObjects")
    public List<SqlObject> SqlObjects;

    public List<SqlObject> getSqlObjects() {
        return SqlObjects;
    }

    public void setSqlObjects(List<SqlObject> sqlObjects) {
        this.SqlObjects = sqlObjects;
    }

    @JsonIgnore
    public LocalDateTime getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        ModifyDate = modifyDate;
    }

    /**
     *
     */
    @JsonProperty("ObjectType")
    public String ObjectType;

    /**
     * 排序字段
     */
    @JsonProperty("OrderColumn")
    public String OrderColumn;

    @JsonIgnore
    public String getKeyId() {
        return KeyId;
    }

    public void setKeyId(String keyId) {
        KeyId = keyId;
    }

    @JsonIgnore
    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    @JsonIgnore
    public String getReturnType() {
        return ReturnType;
    }

    public void setReturnType(String returnType) {
        ReturnType = returnType;
    }

    @JsonIgnore
    public String getSqlValue() {
        return SqlValue;
    }

    public void setSqlValue(String sqlValue) {
        SqlValue = sqlValue;
    }

    @JsonIgnore
    public String getSplitChar() {
        return SplitChar;
    }

    public void setSplitChar(String splitChar) {
        SplitChar = splitChar;
    }

    @JsonIgnore
    public String getReturnColumn() {
        return ReturnColumn;
    }

    public void setReturnColumn(String returnColumn) {
        ReturnColumn = returnColumn;
    }

    @JsonIgnore
    public String getOFID() {
        return OFID;
    }

    public void setOFID(String OFID) {
        this.OFID = OFID;
    }

    @JsonIgnore
    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    @JsonIgnore
    public Integer getSortCode() {
        return SortCode;
    }

    public void setSortCode(Integer sortCode) {
        SortCode = sortCode;
    }

    @JsonIgnore
    public LocalDateTime getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        CreateDate = createDate;
    }

    @JsonIgnore
    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String objectType) {
        ObjectType = objectType;
    }

    @JsonIgnore
    public String getOrderColumn() {
        return OrderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        OrderColumn = orderColumn;
    }
}
