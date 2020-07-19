package com.tjhnode.dataservice.model.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: dataservice
 * @description: 业务功能配置
 * @author: tjh
 * @create: 2019-11-24 14:29
 **/
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("of_configitem")
public class ConfigItemModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private int id;
    /**
     * 业务功能id
     */
    @TableField("OFID")
    @JsonProperty("OFID")
    public String OFID;

    /**
     * 业务功能名称
     */
    @TableField("OFName")
    @JsonProperty("OFName")
    public String OFName;

    /**
     * 业务功能描述
     */
    @TableField("OFDescription")
    @JsonProperty("OFDescription")
    public String OFDescription;

    /**
     * 错误描述
     */
    @TableField("OFError")
    @JsonProperty("OFError")
    public String OFError;

    /**
     * 业务功能类型
     */
    @TableField("OFType")
    @JsonProperty("OFType")
    public String OFType;

    /**
     * 请求类型
     */
    @TableField("RequestType")
    @JsonProperty("RequestType")
    public String RequestType;

    /**
     * 排序码
     */
    @TableField("SortCode")
    @JsonProperty("SortCode")
    public Integer SortCode;

    /**
     * 有效标志
     */
    @TableField("EnabledMark")
    @JsonProperty("EnabledMark")
    public Integer EnabledMark;

    /**
     * 创建日期
     */
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    public LocalDateTime CreateDate;

    /**
     * 分类id
     */
    @TableField("ClassificationId")
    @JsonProperty("ClassificationId")
    public String ClassificationId;

    @JsonIgnore
    public String getOFID() {
        return OFID;
    }

    @JsonIgnore
    public String getOFName() {
        return OFName;
    }

    @JsonIgnore
    public String getOFDescription() {
        return OFDescription;
    }

    @JsonIgnore
    public String getOFError() {
        return OFError;
    }

    @JsonIgnore
    public String getOFType() {
        return OFType;
    }

    @JsonIgnore
    public String getRequestType() {
        return RequestType;
    }

    @JsonIgnore
    public Integer getSortCode() {
        return SortCode;
    }

    @JsonIgnore
    public Integer getEnabledMark() {
        return EnabledMark;
    }

    @JsonIgnore
    public LocalDateTime getCreateDate() {
        return CreateDate;
    }

    @JsonIgnore
    public String getClassificationId() {
        return ClassificationId;
    }

    public void setOFID(String OFID) {
        this.OFID = OFID;
    }


    public void setOFName(String OFName) {
        this.OFName = OFName;
    }


    public void setOFDescription(String OFDescription) {
        this.OFDescription = OFDescription;
    }

    public void setOFError(String OFError) {
        this.OFError = OFError;
    }

    public void setOFType(String OFType) {
        this.OFType = OFType;
    }

    public void setRequestType(String requestType) {
        RequestType = requestType;
    }

    public void setSortCode(Integer sortCode) {
        SortCode = sortCode;
    }

    public void setEnabledMark(Integer enabledMark) {
        EnabledMark = enabledMark;
    }

    public void setCreateDate(LocalDateTime createDate) {
        CreateDate = createDate;
    }

    public void setClassificationId(String classificationId) {
        ClassificationId = classificationId;
    }
}
