package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjhnode.common.utils.YamlUtils;
import com.tjhnode.dataservice.mapper.SqlConfigMapper;
import com.tjhnode.dataservice.model.entities.SqlConfigModel;
import com.tjhnode.dataservice.model.vo.SqlObject;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @program: dataservice
 * @description: sql配置业务层
 * @author: tjh
 * @create: 2019-11-25 22:34
 **/
@Service
@DS("appconfig")
public class SqlConfigServiceImpl extends ServiceImpl<SqlConfigMapper, SqlConfigModel> implements com.tjhnode.dataservice.service.SqlConfigService {

    @Autowired
    private SqlConfigMapper sqlConfigMapper;

    /**
     * sql配置详情
     *
     * @param ofid
     * @return
     */
    @Override
    public SqlConfigModel getSqlConfig(String ofid) {
        SqlConfigModel configModel = new SqlConfigModel();
        configModel= sqlConfigMapper.getSqlConfig(ofid);
        if(configModel!=null) {
            //sql
            String[] sqlArr = configModel.getSqlValue().split(";");
            //前段对象名
            String[] objectName = new String[]{};
            if (configModel.getObjectName() != null) {
                configModel.getObjectName().split(";");
            }
            //返回值类型
            String[] objectType = new String[]{};
            if (configModel.getObjectType() != null) {
                configModel.getObjectType().split(";");
            }
            List<SqlObject> list = new ArrayList<>();
            for (int i = 0; i < sqlArr.length; i++) {
                val sqlObject = new SqlObject();
                sqlObject.setSQL(sqlArr[i]);
                sqlObject.setObjectName(objectName.length > 0 ? objectName[i] : "");
                sqlObject.setObjectType(objectType.length > 0 ? objectType[i] : "");
                list.add(sqlObject);
            }
            configModel.setSqlObjects(list);
        }
        return configModel;
    }

    /**
     * 新增sql配置
     *
     * @param model
     * @return
     */
    @Override
    public String insertConfigItem(SqlConfigModel model) {
        //拼接sql
        model.setSqlValue(StringUtils.join(model.getSqlObjects(), ";"));
        //拼接对象名
        model.setObjectName(StringUtils.join(model.getObjectName(), ";"));
        //拼接返回值类型
        model.setReturnType(StringUtils.join(model.getReturnType(), ";"));
        if (StringUtils.isEmpty(model.getKeyId())) {
            model.setKeyId(UUID.randomUUID().toString());
        }
        return sqlConfigMapper.insertConfigItem(model) > 0 ? model.getKeyId() : "";
    }

    /**
     * 修改sql配置
     *
     * @param model
     * @return
     */
    @Override
    public boolean updateConfigItem(SqlConfigModel model) {
        boolean flag = false;
        if (!StringUtils.isEmpty(model.getKeyId())) {
            //拼接sql
            model.setSqlValue(StringUtils.join(model.getSqlObjects(), ";"));
            //拼接对象名
            model.setObjectName(StringUtils.join(model.getObjectName(), ";"));
            //拼接返回值类型
            model.setReturnType(StringUtils.join(model.getReturnType(), ";"));
            flag = sqlConfigMapper.updateConfigItem(model) > 0;
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param keyId 主键
     * @return
     */
    @Override
    public boolean deleteConfigItem(String keyId) {
        return sqlConfigMapper.deleteConfigItem(keyId) > 0;
    }

    /**
     * 获取数据库名
     * @return
     * @throws IOException
     */
    @Override
    public List<String> getDbName() {

        return YamlUtils.getdbName();
    }
}
