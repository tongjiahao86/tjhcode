package com.tjhnode.dataservice.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.common.utils.Cache;
import com.tjhnode.common.utils.YamlUtils;
import com.tjhnode.dataservice.mapper.BusinessFunctionMapper;
import com.tjhnode.dataservice.model.enums.ParameterType;
import com.tjhnode.dataservice.model.vo.*;
import com.tjhnode.dataservice.service.BusinessFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: dataservice
 * @description: 执行业务功能模块
 * @author: tjh
 * @create: 2019-12-03 19:25
 **/
@Service
@DS("appconfig")
public class BusinessFunctionImpl extends ServiceImpl<BusinessFunctionMapper, BusinessFunctionModel> implements BusinessFunctionService {

    @Autowired
    DynamicRoutingDataSource dynamicRoutingDataSource;

    @Autowired
    BusinessFunctionMapper businessFunctionMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 获取服务配置
     *
     * @param Id 服务id
     * @return
     */
    public BusinessFunctionModel getConfigModelById(String Id) {
        BusinessFunctionModel model = null;
        String xPath = "LzProduct/DataService/BusinessFuction" + Id + "/";
        Object data = Cache.get(xPath);
        if (data != null) {
            model = (BusinessFunctionModel) data;
        } else {
            String sql = "select DBNAME,SQLVALUE,OBJECTNAME,OBJECTTYPE,RETURNTYPE,B.OFDESCRIPTION,B.OFNAME,B.OFTYPE,OFERROR from of_sqlconfig a inner join of_configitem b on a.OFID=b.OFID where a.OFID=?";
            try {
                //使用jdbc访问数据库
                Map<String, Object> map = jdbcTemplate.queryForMap(sql, Id);
                if (map != null) {
                    model = new BusinessFunctionModel();
                    model.setDbName(null != map.get("DBNAME") ? String.valueOf(map.get("DBNAME")) : "");
                    String sqlValue = (null != map.get("SQLVALUE") ? String.valueOf(map.get("SQLVALUE")) : "").toLowerCase();
                    model.setSqlValue(sqlValue);
                    model.setReturnType(null != map.get("RETURNTYPE") ? String.valueOf(map.get("RETURNTYPE")) : "");
                    model.setErrorMes(null != map.get("OFERROR") ? String.valueOf(map.get("OFERROR")) : "");
                    model.setId(Id);
                    model.setDescription(null != map.get("OFDESCRIPTION") ? String.valueOf(map.get("OFDESCRIPTION")) : "");
                    model.setName(null != map.get("OFNAME") ? String.valueOf(map.get("OFNAME")) : "");
                    model.setOfType(null != map.get("OFTYPE") ? String.valueOf(map.get("OFTYPE")) : "");

                    //统一返回格式处理
                    List<JsonObjSchame> schameList = new ArrayList<>();
                    //前端对象处理,用；切割
                    if (map.get("OBJECTNAME") != null && map.get("OBJECTTYPE") != null) {
                        //前端对象名
                        String[] objectName = String.valueOf(map.get("OBJECTNAME")).split(";");
                        //对象返回类型
                        String[] objectType = String.valueOf(map.get("OBJECTTYPE")).split(";");
                        for (int i = 0; i < objectName.length; i++) {
                            JsonObjSchame objSchame = new JsonObjSchame();
                            objSchame.setObjectname(objectName[i]);
                            objSchame.setObjecttype(objectType[i]);
                            schameList.add(objSchame);
                        }
                        model.setObjSchame(schameList);
                    }
                    //处理参数
                    sql = "select PARAMETERKEY,PARAMETERNAME,ISREQUISITE from of_parametersconfig where OFID=?";
                    List<Map<String, Object>> parameterList = jdbcTemplate.queryForList(sql, Id);
                    if (parameterList != null && parameterList.size() > 0) {
                        List<ParameterModel> parameterModelList = new ArrayList<>();
                        for (Map<String, Object> param : parameterList) {
                            ParameterModel parameter = new ParameterModel();
                            parameter.setParameterkey(null != param.get("PARAMETERKEY") ? String.valueOf(param.get("PARAMETERKEY")).toLowerCase() : "");
                            parameter.setParametername(null != param.get("PARAMETERNAME") ? String.valueOf(param.get("PARAMETERNAME")) : "");
                            parameter.setIsrequisite(null != param.get("ISREQUISITE") ? (Integer) param.get("ISREQUISITE") : 0);
                            if ("SQL".equals(model.getOfType())) {
                                //参数化替换
                                if (model.getSqlValue().indexOf("'{" + parameter.getParameterkey() + "}'") > -1) {
                                    parameter.setParametertype(ParameterType.VarChar);
                                    model.getSqlValue().replaceAll("'\\{" + parameter.getParameterkey() + "}'", ":" + parameter.getParameterkey());
                                } else {
                                    parameter.setParametertype(ParameterType.Int32);
                                    model.getSqlValue().replaceAll("{" + parameter.getParameterkey() + "}", ":" + parameter.getParameterkey());
                                }
                            }
                            parameterModelList.add(parameter);
                        }
                        model.setParameters(parameterModelList);
                    }
                }
            } catch (DataAccessException e) {
                e.printStackTrace();

            }
            Cache.put(xPath, model);
        }
        return model;
    }


    /**
     * 执行服务
     * @param configModel 配置对象已经融合了参数值
     * @return
     */
    public Map<String, Object> ExecuteService(BusinessFunctionModel configModel, QueryPaginationModel query){
        Map<String, Object> returnResult = new HashMap<>();
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            JdbcTemplate jdbcTemplate=new JdbcTemplate();
            //判断数据源是否存在
            boolean flag= YamlUtils.isDbConfig(configModel.getDbName());
            if(!flag){
                //如果不存在，默认读取master
                jdbcTemplate.setDataSource(dynamicRoutingDataSource.getDataSource("master"));
            }else {
                jdbcTemplate.setDataSource(dynamicRoutingDataSource.getDataSource(configModel.getDbName()));
            }
            DatabaseMetaData databaseMetaData=jdbcTemplate.getDataSource().getConnection().getMetaData();
            NamedParameterJdbcTemplate parameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
            if(query!=null){
                //执行list服务
                String sql=configModel.getSqlValue();
                //分页
                int pageIndex=query.getPageindex();
                if(pageIndex==0){
                    pageIndex=1;
                }
                int startRow=0;
                if(pageIndex==1){
                    startRow=(pageIndex-1)*query.getPagesize();
                }else{
                    startRow=(pageIndex-1)*query.getPagesize()+1;
                }
                int endRow=pageIndex*query.getPagesize();
                StringBuilder str=null;
                if(query.getExtraParameters()!=null && !query.getExtraParameters().isEmpty()){
                    //处理额外参数
                    //构造查询条件
                    str=new StringBuilder(sql);
                    //操作类型
                    String operationType = "";
                    for (ExtraParameter extraParameter : query.getExtraParameters()){
                        switch (extraParameter.getOperationtype()){
                            case 1:
                                operationType="like";
                                break;
                            case 2:
                                operationType="=";
                                break;
                            case 3:
                                //区间
                                if(extraParameter.getParametervalue()!=null){
                                    String[] parameters=extraParameter.getParametervalue().split(",");
                                    if(extraParameter.getParametervalue().indexOf(",")==0){
                                        //逗号在首位，<=
                                        str.append(String.format(" and %s<='%s'",extraParameter.getParametername(),parameters[1]));
                                    }
                                    else if(extraParameter.getParametervalue().indexOf(",")==extraParameter.getParametervalue().length()-1){
                                        //在末尾
                                        str.append(MessageFormat.format(" and ({0}>='{1}' and {0}<='{2}')",extraParameter.getParametername(),parameters[0],sdf.format(new Date())));
                                    }else{
                                        //首尾都有值
                                        str.append(MessageFormat.format(" and ({0}>='{1}' and {0}<='{2}')",extraParameter.getOperationtype(),parameters[0],parameters[1]));
                                    }
                                }
                                break;
                            default:
                                operationType="=";
                                break;
                        }
                        //是否是单个值
                        if(extraParameter.isIssinglevalue()){
                            if((!StringUtils.isNullOrEmpty(extraParameter.getParametervalue()))){
                                if(operationType.equals("like")){
                                    str.append(extraParameter.getParametername()).append(operationType).append("%"+extraParameter.getParametervalue()+"%");
                                }else{
                                    str.append(extraParameter.getParametername()).append(operationType).append(extraParameter.getParametervalue());
                                }
                            }
                        }else{
                            String[] parametervalues=extraParameter.getParametervalue().split(",");
                            str=new StringBuilder();
                            for (String parametervalue:parametervalues) {
                                if(operationType.equals("like")) {
                                    str.append(MessageFormat.format(" or {0}{1}{2}", extraParameter.getParametername(), operationType,"%"+parametervalue+"%" ));
                                }else{
                                    str.append(MessageFormat.format(" or {0}{1}{2}", extraParameter.getParametername(), operationType,parametervalue));
                                }
                            }
                        }
                    }
                }
                if("List".equals(configModel.getReturnType())){
                    //总条数
                    String countSql="select count(1) from ("+str.toString()+")";
                    long total=parameterJdbcTemplate.queryForObject(countSql,configModel.getParameterValues(),long.class);
                    StringBuilder sb=new StringBuilder();
                    //获取数据库类型
                    String databaseType=databaseMetaData.getDatabaseProductName().toUpperCase();
                    if("SQLSERVER".equals(databaseType) || "ORACLE".equals(databaseType)||"DB2".equals(databaseType)){
                        sb.append(MessageFormat.format("select * from (select row_number() over( order by {0}) as rows,* from (select derivedtabl.*,'' as row_number_falg)) where rows between {1} and {2}"
                                ,configModel.getOrderStr()),startRow,endRow);
                    }else if("MYSQL".equals(databaseType)){
                        sb.append(MessageFormat.format("select * {0} order by {1} limit {2},{3}",str.toString(),configModel.getOrderStr(),startRow,endRow));
                    }else{
                        returnResult.put("ResultList", null);
                        returnResult.put("Total", 0);
                        return returnResult;
                    }
                    List<Map<String, Object>> list=parameterJdbcTemplate.queryForList(sb.toString(),configModel.getParameterValues());
                    returnResult.put("ResultList",list);
                    returnResult.put("Total", total);
                    return returnResult;
                }
            }
            else {
                List<JsonObjSchame> objSchames = configModel.getObjSchame();
                String[] sqllst = configModel.getSqlValue().split(";");
                switch (configModel.getReturnType()) {
                    case "DataSet":
                        //返回多个数据集
                        for (int i = 0; i <= sqllst.length; i++) {
                            //去掉sql最前面的空格
                            String sql = sqllst[i].replaceAll("^\\s*","").toLowerCase();
                            if ("Object".equals(objSchames.get(i).getObjecttype())) {
                                if (sql.indexOf("select") ==0) {
                                    //查询
                                    Map<String, Object> objectMap = parameterJdbcTemplate.queryForMap(sql, configModel.getParameterValues());
                                    returnResult.put(objSchames.get(i).getObjectname(), objectMap);
                                } else {
                                    //增删改
                                    int num = parameterJdbcTemplate.update(sql, configModel.getParameterValues());
                                    returnResult.put(objSchames.get(i).getObjectname(), num);
                                }
                            } else if ("List<Object>".equals(objSchames.get(i).getObjecttype())) {
                                if (sql.indexOf("select") > -1) {
                                    List<Map<String, Object>> mapList = parameterJdbcTemplate.queryForList(sql, configModel.getParameterValues());
                                    returnResult.put(objSchames.get(i).getObjectname(), mapList);
                                } else {
                                    int num = parameterJdbcTemplate.update(sql, configModel.getParameterValues());
                                    returnResult.put(objSchames.get(i).getObjectname(), new int[num]);
                                }
                            }
                        }
                        break;
                    case "DataTable":
                        //返回一个数据集
                        if ("Object".equals(objSchames.get(0).getObjecttype())) {
                            Map<String, Object> objectMap = parameterJdbcTemplate.queryForMap(sqllst[0], configModel.getParameterValues());
                            returnResult.put(objSchames.get(0).getObjectname(), objectMap);
                        } else if ("List<Object>".equals(objSchames.get(0).getObjecttype())) {
                            List<Map<String, Object>> mapList = parameterJdbcTemplate.queryForList(sqllst[0], configModel.getParameterValues());
                            returnResult.put(objSchames.get(0).getObjectname(), mapList);
                        }
                        break;
                    case "NonQuery":
                        //受影响行数
                        int num = parameterJdbcTemplate.update(sqllst[0], configModel.getParameterValues());
                        returnResult.put(objSchames.get(0).getObjectname(), num);
                        break;
                    case "Scalar":
                        //返回一个值
                        Map<String, Object> objectMap = parameterJdbcTemplate.queryForMap(sqllst[0], configModel.getParameterValues());
                        returnResult.put(objSchames.get(0).getObjectname(), objectMap);
                        break;
                }
            }
        }
        catch (SQLException | IOException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return returnResult;
    }

    /**
     * 清除缓存
     * @param serviceId 服务id
     * @return
     */
    public boolean ClearCache(String serviceId){
        boolean flag=true;
        try {
            String xPath = "LzProduct/DataService/BusinessFuction" + serviceId + "/";
            Object data = Cache.get(xPath);
            if (data != null) {
                Cache.remove(xPath);
            }
        } catch (Exception ex){
            flag=false;
        }
        return flag;
    }


}
