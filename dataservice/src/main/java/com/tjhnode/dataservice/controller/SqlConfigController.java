package com.tjhnode.dataservice.controller;

import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.entities.SqlConfigModel;
import com.tjhnode.dataservice.service.SqlConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dataservice
 * @description: sql配置
 * @author: tjh
 * @create: 2019-11-30 22:35
 **/
@RestController
@RequestMapping("api/sqlconfig")
@Api("sql配置")
public class SqlConfigController {

    @Autowired
    private SqlConfigService sqlConfigService;

    @ApiOperation("sql配置详情")
    @GetMapping("/getsqlconfig/{ofid}")
    public JSONResult getSqlConfig(@PathVariable("ofid") String ofid){
        SqlConfigModel configModel = new SqlConfigModel();
        ErrorCode code;
        try {
            configModel=sqlConfigService.getSqlConfig(ofid);
            code= ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            code= ErrorCodeManager.GetCode("23001");
        }
        return new JSONResult(code,configModel);
    }

    @ApiOperation("新增sql配置")
    @PostMapping("/insertconfigitem")
    public JSONResult insertConfigItem(@RequestBody SqlConfigModel model){
        String keyId="";
        ErrorCode code;
        try {
            keyId=sqlConfigService.insertConfigItem(model);
            code= ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            code= ErrorCodeManager.GetCode("23002");
        }
        return new JSONResult(code,keyId);
    }

    @ApiOperation("修改sql配置")
    @PostMapping("/updateconfigitem")
    public JSONResult updateConfigItem(@RequestBody SqlConfigModel model){
        boolean flag=false;
        ErrorCode code;
        try {
            flag=sqlConfigService.updateConfigItem(model);
            code= ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            flag=false;
            code= ErrorCodeManager.GetCode("23003");
        }
        return new JSONResult(code,flag);
    }

    @ApiOperation("删除sql配置")
    @PostMapping("/deleteconfigitem/{keyid}")
    public JSONResult deleteConfigItem(@PathVariable("keyid")String keyid){
        boolean flag=false;
        ErrorCode code;
        try {
            flag=sqlConfigService.deleteConfigItem(keyid);
            code= ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            flag=false;
            code= ErrorCodeManager.GetCode("23004");
        }
        return new JSONResult(code,flag);
    }

    @ApiOperation("获取数据库名称")
    @GetMapping("/getdbname")
    public JSONResult getDbName(){
        List<String> result=new ArrayList<>();
        ErrorCode code;
        try {
            result=sqlConfigService.getDbName();
            code= ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            code= ErrorCodeManager.GetCode("23005");
        }
        return new JSONResult(code,result);
    }
}
