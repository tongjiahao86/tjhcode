package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.dataservice.mapper.EnumParameterMapper;
import com.tjhnode.dataservice.model.entities.Enumparameters;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.dataservice.service.EnumParameterService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tjh
 * @since 2019-11-30
 */
@Service
@DS("appconfig")
public class EnumParameterServiceImpl extends ServiceImpl<EnumParameterMapper, Enumparameters> implements EnumParameterService {

    @Autowired
    private EnumParameterMapper enumParameterMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 参数注册列表
     *
     * @param query 分页、条件查询
     * @return
     */
    @Override
    public ResultPaginationModel<Enumparameters> getEnumParameterList(QueryPaginationModel query) {
        ResultPaginationModel<Enumparameters> result = new ResultPaginationModel<>();
        try{
            String str="";
            if(query.getQuerylist()!=null &&query.getQuerylist().size()>0){
                val keySet = query.getQuerylist().keySet();
                for (String key:keySet) {
                    str+=" and "+key+" like '%"+query.getQuerylist().get(key)+"%'";
                }
            }
            str+=String.format(" limit %d,%d",query.getPageindex(),query.getPagesize());
            val maps = jdbcTemplate.queryForList("select KeyId,ParameterName,ParameterKey,Description,SortCode,CreateDate from enumparameters where 1=1 " + str);
            result.setResultList(maps);
            result.setTotal(getTotal(query));
        }
        catch (Exception ex)
        {
            throw ex;
        }
        return result;
    }

    /**
     *
     * @param query
     * @return
     */
    private long getTotal(QueryPaginationModel query){
        try {
            String str = "";
            if (query.getQuerylist() != null && query.getQuerylist().size() > 0) {
                val keySet = query.getQuerylist().keySet();
                for (String key : keySet) {
                    str += " and " + key + " like '%" + query.getQuerylist().get(key) + "%'";
                }
            }
            val maps = jdbcTemplate.queryForList("select count(1) total from enumparameters where 1=1 " + str);
            return (long) maps.get(0).get("total");
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    /**
     * 新增参数
     *
     * @param model
     * @return
     */
    @Override
    public String insertEnumParameter(Enumparameters model) {
        if(StringUtils.isNullOrEmpty(model.getKeyId()))
        {
            model.setKeyId(UUID.randomUUID().toString());
        }
        return enumParameterMapper.insertEnumParameter(model)>0?model.getKeyId():"";
    }

    /**
     * 修改参数
     *
     * @param model
     * @return
     */
    @Override
    public boolean updateEnumParameter(Enumparameters model) {
        boolean flag=false;
        if(!StringUtils.isNullOrEmpty(model.getKeyId()))
        {
            flag= enumParameterMapper.updateEnumParameter(model) > 0;
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param KeyId 主键
     * @return
     */
    @Override
    public boolean deleteEnumParameter(String KeyId) {
        boolean flag=false;
        if(!StringUtils.isNullOrEmpty(KeyId))
        {
            flag= enumParameterMapper.deleteEnumParameter(KeyId) > 0;
        }
        return flag;
    }

    /**
     * 参数注册详情
     *
     * @param KeyId 主键
     * @return
     */
    @Override
    public Enumparameters getParameterDetail(String KeyId) {
        return enumParameterMapper.getParameterDetail(KeyId);
    }
}
