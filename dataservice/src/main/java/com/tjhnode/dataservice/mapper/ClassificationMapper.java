package com.tjhnode.dataservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjhnode.dataservice.model.entities.ClassificationModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classification表mapper接口
 * DAO接口添加的@Mapper并不是Spring的注解，而是ibatis的注解，并没有声明这个DAO接口作为Spring的Bean，
 * 因此Spring不能进行管理，导致注入报错,在DAO接口加上@Component 或者 @Repository注解声明为Spring的Bean
 */
@Mapper
@Component
public interface ClassificationMapper extends BaseMapper<ClassificationModel> {
    /**
     * 获取所有数据
     * @return
     */
    List<ClassificationModel> findAll();

    /**
     * 添加目录
     * @param model
     */
    int InsertClassification(ClassificationModel model);

    /**
     * 修改目录
     * @param model
     * @return
     */
    int UpdateClassification(ClassificationModel model);

    /**
     * 删除目录
     * @param classificationId 目录id
     * @return
     */
    int DeleteClassification(String classificationId);

    /**
     * 目录详情
     * @param classificationId
     * @return
     */
    ClassificationModel ClassificationDetail(String classificationId);

    /**
     * 根据父id获取所有子节点
     * @param parentId
     * @return
     */
    List<String> GetChildsList(String parentId);
}
