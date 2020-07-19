package com.tjhnode.dataservice.model.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 参数注册
 * </p>
 *
 * @author tjh
 * @since 2019-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("enumparameters")
public class Enumparameters implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private int id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @JsonIgnore
    public String getKeyId() {
        return KeyId;
    }

    public void setKeyId(String keyId) {
        KeyId = keyId;
    }

    @JsonIgnore
    public String getParameterName() {
        return ParameterName;
    }

    public void setParameterName(String parameterName) {
        ParameterName = parameterName;
    }

    @JsonIgnore
    public String getParameterKey() {
        return ParameterKey;
    }

    public void setParameterKey(String parameterKey) {
        ParameterKey = parameterKey;
    }

    @JsonIgnore
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
    public LocalDateTime getModifyDate() {
        return ModifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        ModifyDate = modifyDate;
    }

    @TableField("KeyId")
    @JsonProperty("KeyId")
    private String KeyId;

    @TableField("ParameterName")
    @JsonProperty("ParameterName")
    private String ParameterName;

    @TableField("ParameterKey")
    @JsonProperty("ParameterKey")
    private String ParameterKey;

    @TableField("Description")
    @JsonProperty("Description")
    private String Description;

    /**
     * 排序码
     */
    @TableField("SortCode")
    @JsonProperty("SortCode")
    private Integer SortCode;

    /**
     * 创建日期
     */
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    private LocalDateTime CreateDate;

    /**
     * 修改日期
     */
    @TableField("ModifyDate")
    @JsonProperty("ModifyDate")
    private LocalDateTime ModifyDate;



}
