package com.tjhnode.dataservice.model.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 业务功能参数配置
 * </p>
 *
 * @author tjh
 * @since 2019-12-01
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("of_parametersconfig")
public class ParametersConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private int id;

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
    public String getOFId() {
        return OFId;
    }

    public void setOFId(String OFId) {
        this.OFId = OFId;
    }

    /**
     * 主键
     */
    @TableField("ParameterName")
    @JsonProperty("KeyId")
    private String KeyId;

    /**
     * 参数名称
     */
    @TableField("ParameterName")
    @JsonProperty("ParameterName")
    private String ParameterName;

    /**
     * 参数key
     */
    @TableField("ParameterKey")
    @JsonProperty("ParameterKey")
    private String ParameterKey;

    /**
     * 业务功能id
     */
    @TableField("OFId")
    @JsonProperty("OFId")
    private String OFId;



}
