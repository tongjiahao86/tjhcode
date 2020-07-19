package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.dataservice.mapper.ConfigItemMapper;
import com.tjhnode.dataservice.model.entities.ConfigItemModel;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.dataservice.service.ConfigItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @program: dataservice
 * @description: 业务功能配置项
 * @author: tjh
 * @create: 2019-11-24 13:30
 **/
@Service
@DS("appconfig")
public class ConfigItemServiceImpl extends ServiceImpl<ConfigItemMapper, ConfigItemModel> implements ConfigItemService {

    @Autowired
    private ConfigItemMapper configItemMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 接口配置列表
     *
     * @param query
     * @param classificationaId
     * @return
     */
    @Override
    public ResultPaginationModel<ConfigItemModel> getConfigList(QueryPaginationModel query, String classificationaId) {
        ResultPaginationModel<ConfigItemModel> result=new ResultPaginationModel<>();
        try {
            String str="";
            //条件查询，拼接sql
            if(query.getQuerylist()!=null && query.getQuerylist().size()>0){
                Set<String> keySet=query.getQuerylist().keySet();
                for (String key : keySet){
                    str+=" and "+key+" like '%"+query.getQuerylist().get(key)+"%'";
                }
            }
            if(query.getPageindex()==0)
            {
                query.setPageindex(1);
            }
            int startRow=(query.getPageindex()-1)*query.getPagesize();
            int endRow=query.getPageindex()*query.getPagesize()-1;
            str+=String.format(" limit %d,%d",startRow,endRow);
            List<Map<String,Object>> maps=jdbcTemplate.queryForList(String.format("select OFID,OFName,OFDescription,OFError,OFType,RequestType,SortCode,EnabledMark from of_configitem where ClassificationId='%s' %s",classificationaId,str));
            result.setResultList(maps);
            result.setTotal(getTotal(query,classificationaId));
        } catch (DataAccessException e) {
            throw e;
        }
        return result;
    }

    /**
     * 获取总条数
     *
     * @param classificationaId
     * @return
     */
    public Long getTotal(QueryPaginationModel query, String classificationaId) {
        try {
            String str="";
            if(query.getQuerylist()!=null && query.getQuerylist().size()>0){
                Set<String> keySet=query.getQuerylist().keySet();
                for (String key : keySet){
                    str+=" and "+key+" like '%"+query.getQuerylist().get(key)+"%'";
                }
            }
            List<Map<String,Object>> maps=jdbcTemplate.queryForList(String.format("select count(1) total from of_configitem where ClassificationId='%s' %s",classificationaId,str));
            return (Long)maps.get(0).get("total");
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 接口配置详情
     *
     * @param ofid
     * @return
     */
    @Override
    public ConfigItemModel getConfigItemDetail(String ofid) {
        return configItemMapper.getConfigItemDetail(ofid);
    }

    /**
     * 新增
     *
     * @param model
     * @return
     */
    @Override
    public String insertConfigItem(ConfigItemModel model) {
        String keyid="";
        try {
            if(StringUtils.isNullOrEmpty(model.getOFID())){
                model.setOFID(UUID.randomUUID().toString());
            }
            if(model.getCreateDate()==null){
                model.setCreateDate(LocalDateTime.now());
            }
            int res=configItemMapper.insertConfigItem(model);
            if(res>0){
                keyid=model.getOFID();
            }
        } catch (Exception e) {
            throw e;
        }
        return keyid;
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    public boolean updateConfigItem(ConfigItemModel model) {
        boolean flag=false;
        try {
            if(!StringUtils.isNullOrEmpty(model.getOFID())){
                flag= configItemMapper.updateConfigItem(model) > 0;
            }
        } catch (Exception e) {
            throw e;
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param ofid
     * @return
     */
    @Override
    public boolean deleteConfigItem(String ofid) {
        boolean flag=false;
        try {
            if(!StringUtils.isNullOrEmpty(ofid)){
                //删除对应的参数，sql配置

                flag= configItemMapper.deleteConfigItem(ofid) > 0;
            }
        } catch (Exception e) {
            throw e;
        }
        return flag;
    }


}
