package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.dataservice.mapper.ClassificationMapper;
import com.tjhnode.dataservice.model.entities.ClassificationModel;
import com.tjhnode.dataservice.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@DS("appconfig")
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, ClassificationModel> implements ClassificationService {

    @Autowired
    private ClassificationMapper classificationMapper;    //注入mapper

    /**
     * 目录树
     * @return
     */
    @Override
    public List<ClassificationModel> findAll() {

        try {
            //获取所有目录资源
            List<ClassificationModel> list = classificationMapper.findAll();
            //获取所有跟目录
            List<ClassificationModel> parentList = list.stream().filter(x -> x.getParentId().equals("0"))
                    .collect(Collectors.toList());

            //递归加载树
            for (ClassificationModel child : parentList) {
                child.setChildren(getChildrens(list, child));
            }
            return parentList;
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * 递归加载子节点
     * @param list 所有数据
     * @param node 当前节点
     * @return
     */
    private List<ClassificationModel> getChildrens(List<ClassificationModel> list,
                                                   ClassificationModel node){

        List<ClassificationModel> childrens=
                list.stream().filter(x->x.getParentId().equals(node.getClassificationId()))
                             .collect(Collectors.toList());
        for (ClassificationModel item:childrens) {
            item.setChildren(getChildrens(list,item));
        }
        return childrens;
    }

    @Override
    public String InsertClassification(ClassificationModel model) {
        String keyid="";
        if (StringUtils.isNullOrEmpty(model.getClassificationId())) {
            model.setClassificationId(UUID.randomUUID().toString());
        }
        if(model.getCreateDate()==null){
                model.setCreateDate(LocalDateTime.now());
        }
        int res= classificationMapper.InsertClassification(model);
        if(res>0){
            keyid=model.getClassificationId();
        }
        return keyid;
    }

    @Override
    public boolean UpdateClassification(ClassificationModel model) {
        boolean flag=false;
        if(!StringUtils.isNullOrEmpty(model.getClassificationId())){
            if(model.getModifyDate()==null){
                model.setModifyDate(LocalDateTime.now());
            }
            flag= classificationMapper.UpdateClassification(model) > 0;
        }
        return flag;
    }

    @Override
    public boolean DeleteClassification(String classificationId) {
        boolean flag=false;
        try {
            //根据id递归查询出所有子节点，先删除子节点，再删除父节点
            List<String> list=GetChildsList(classificationId);
             for(String id : list){
                 DeleteClassification(id);
             }
            flag= classificationMapper.DeleteClassification(classificationId) > 0;
        } catch (Exception e) {
            flag=false;
        }
        return flag;
    }

    @Override
    public ClassificationModel ClassificationDetail(String classificationId) {
        return classificationMapper.ClassificationDetail(classificationId);
    }

    @Override
    public List<String> GetChildsList(String parentId) {

        return classificationMapper.GetChildsList(parentId);
    }


}
