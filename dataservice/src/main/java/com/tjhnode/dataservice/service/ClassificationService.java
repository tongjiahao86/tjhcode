package com.tjhnode.dataservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjhnode.dataservice.model.entities.ClassificationModel;

import java.util.List;

public interface ClassificationService extends IService<ClassificationModel> {
    List<ClassificationModel> findAll();

    String InsertClassification(ClassificationModel model);

    boolean UpdateClassification(ClassificationModel model);

    boolean DeleteClassification(String classificationId);
    ClassificationModel ClassificationDetail(String classificationId);

    List<String> GetChildsList(String parentId);

}
