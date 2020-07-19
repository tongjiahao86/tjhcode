package com.tjhnode.dataservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.vo.*;
import com.tjhnode.dataservice.service.BusinessFunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: dataservice
 * @description: 配置接口统一调用
 * @author: tjh
 * @create: 2019-12-01 21:18
 **/
@RestController
@Api("配置接口统一调用")
@RequestMapping("api/dataservice")
public class DataServiceController {

    @Autowired
    private BusinessFunctionService businessFunctionService;



    @ApiOperation("执行数据服务")
    @RequestMapping(value = "/rundataservice/{serviceId}",method = {RequestMethod.GET,RequestMethod.POST})
    public JSONResult RunDataService(@RequestBody JSONObject objData, @PathVariable("serviceId") String serviceId)
    {
        ErrorCode code = ErrorCodeManager.GetCode("0");
        BusinessFunctionModel model= null;
        //错误信息
        String errMessage="";
        try {
            model = new BusinessFunctionModel();
            model=businessFunctionService.getConfigModelById(serviceId);
            if(StringUtils.isNullOrEmpty(model.getName())){
                //未选择数据源
                code= ErrorCodeManager.GetCode("10004");
                code.Message=code.Message.replace("FuncId",serviceId);
            }else {
                errMessage=model.getErrorMes();
                if(!CollectionUtils.isEmpty(model.getParameters())){
                    for (ParameterModel parameter:model.getParameters()){
                        if(objData!=null){
                            Object parameterValue=objData.get(parameter.getParameterkey());
                            if(parameter.getIsrequisite()==1 && parameterValue==null){
                                //必填参数未传值，提示缺少参数
                                code= ErrorCodeManager.GetCode("10002");
                                code.Message=code.Message.replace("ParameterName",parameter.getParametername());
                            }
                        }
                        else{
                            code = ErrorCodeManager.GetCode("10005");
                        }
                    }
                }
                if(code.Code.equals("0")){
                    //记录请求日志

                    //执行数据服务
                    Object obj = businessFunctionService.ExecuteService(model, null);
                    code = ErrorCodeManager.GetCode("0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            code= ErrorCodeManager.GetCode("100033");
            code.Message=code.Message.replace("{FuncName}",errMessage);
        }
        return new JSONResult(code,model);
    }

    @ApiOperation("执行数据服务")
    @RequestMapping(value = "/runservicelist/{serviceId}", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONResult RunServiceList(@RequestBody JSONObject objData, @PathVariable("serviceId") String serviceId) {
        ErrorCode code = ErrorCodeManager.GetCode("0");
        BusinessFunctionModel model = null;
        ResultPaginationModel<Object> list =new ResultPaginationModel<>();
        //错误信息
        String errMessage="";
        if (objData != null) {
            QueryPaginationModel query = new QueryPaginationModel();
            query.setPageindex(objData.getInteger("pageindex"));
            query.setPagesize(objData.getInteger("pagesize"));
            if(objData.get("extparameters")!=null){
                List<Map<String,Object>> extlist=new ArrayList<>();
                List<ExtraParameter> eplist=new ArrayList<>();
                extlist=(List<Map<String,Object>>)objData.get("extparameters");
                for (Map<String,Object> map:extlist){
                    ExtraParameter ep = new ExtraParameter();
                    ep.setParametername(map.get("parametername")==null?null:String.valueOf(map.get("parametername")));
                    ep.setParametervalue(map.get("parametervalue")==null?null:String.valueOf(map.get("parametervalue")));
                    ep.setIssinglevalue(map.get("issinglevalue")==null?false:(boolean)map.get("issinglevalue"));
                    ep.setOperationtype(map.get("operationtype")==null?0:(int)map.get("operationtype"));
                    eplist.add(ep);
                }
                query.setExtraParameters(eplist);
            }

            try {
                model = new BusinessFunctionModel();
                model = businessFunctionService.getConfigModelById(serviceId);
                if(StringUtils.isNullOrEmpty(model.getName())){
                    //未选择数据源
                    code= ErrorCodeManager.GetCode("10004");
                    code.Message=code.Message.replace("FuncId",serviceId);
                }else {
                    errMessage=model.getErrorMes();
                    if(!CollectionUtils.isEmpty(model.getParameters())){
                        for (ParameterModel parameter:model.getParameters()){
                            if(objData!=null){
                                Object parameterValue=objData.get(parameter.getParameterkey());
                                if(parameter.getIsrequisite()==1 && parameterValue==null){
                                    //必填参数未传值，提示缺少参数
                                    code= ErrorCodeManager.GetCode("10002");
                                    code.Message=code.Message.replace("ParameterName",parameter.getParametername());
                                }
                            }
                            else{
                                code = ErrorCodeManager.GetCode("10005");
                            }
                        }
                    }
                    if(code.Code.equals("0")) {
                        //记录请求日志

                        Map<String, Object> result = businessFunctionService.ExecuteService(model, query);
                        list.setResultList((List<Map<String, Object>>) result.get("ResultList"));
                        list.setTotal((long) result.get("Total"));
                        code = ErrorCodeManager.GetCode("0");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                code= ErrorCodeManager.GetCode("10033");
                code.Message=code.Message.replace("{FuncName}",errMessage);
            }
        }
        return new JSONResult(code, list);
    }

    @ApiOperation("清除缓存")
    @PostMapping("api/rundataservice/clearcache/{serviceId}")
    public  JSONResult ClearCache(String serviceId){
        boolean flag=false;
        ErrorCode code;
        try {
            flag= businessFunctionService.ClearCache(serviceId);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            flag=false;
            code= ErrorCodeManager.GetCode("10033");
        }
        return new JSONResult(code,flag);
    }
}
