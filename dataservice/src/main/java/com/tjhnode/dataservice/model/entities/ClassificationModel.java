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
import java.util.List;

/**
 * 业务功能分类信息表
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("of_classification")
public class ClassificationModel implements Serializable {

    public ClassificationModel() {}

    @JsonIgnore
    public String getClassificationId() {return ClassificationId;}
    @JsonIgnore
    public String getParentId() {return ParentId;}
    @JsonIgnore
    public String getClassificationName() {return ClassificationName; }
    @JsonIgnore
    public Integer getSortCode() {return SortCode;}
    @JsonIgnore
    public Integer getDeleteMark() {return DeleteMark; }

    @JsonIgnore
    public LocalDateTime getCreateDate() {return CreateDate;}
    @JsonIgnore
    public LocalDateTime getModifyDate() { return ModifyDate; }

    public void setClassificationId(String classificationId) {
        ClassificationId = classificationId;
    }

    public void setParentId(String parentId) {ParentId = parentId;}

    public void setClassificationName(String classificationName) {ClassificationName = classificationName;}

    public void setSortCode(int sortCode) {SortCode = sortCode;}

    public void setDeleteMark(int deleteMark) { DeleteMark = deleteMark;}

    public void setCreateDate(LocalDateTime createDate) {CreateDate = createDate;}

    public void setModifyDate(LocalDateTime modifyDate) {ModifyDate = modifyDate;}

    @JsonIgnore
    public List<ClassificationModel> getChildren() {
        return children;
    }

    public void setChildren(List<ClassificationModel> children) {
        this.children = children;
    }

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private int id;

    @JsonProperty("children")
    public List<ClassificationModel> children;

    // 分类id
    @TableField("ClassificationId")
    @JsonProperty("ClassificationId")
    private String ClassificationId;

    //父id
    @TableField("ParentId")
    @JsonProperty("ParentId")
    private String ParentId;

    //分类名称
    @TableField("ClassificationName")
    @JsonProperty("ClassificationName")
    private String ClassificationName;

    //排序码
    @JsonProperty("SortCode")
    private Integer SortCode;

    //删除标记
    @TableField("DeleteMark")
    @JsonProperty("DeleteMark")
    private Integer DeleteMark;

    //创建时间
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    private LocalDateTime CreateDate;

    //修改时间
    @TableField("ModifyDate")
    @JsonProperty("ModifyDate")
    private LocalDateTime ModifyDate;
}
