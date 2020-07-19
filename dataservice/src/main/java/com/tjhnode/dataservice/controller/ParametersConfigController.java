package com.tjhnode.dataservice.controller;


import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.entities.ParametersConfig;
import com.tjhnode.dataservice.service.ParametersConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 业务功能参数配置 前端控制器
 * </p>
 *
 * @author tjh
 * @since 2019-12-01
 */
@RestController
@RequestMapping("api/parametersconfig")
@Api("业务功能参数配置")
public class ParametersConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ParametersConfigController.class);

    @Autowired
    private ParametersConfigService parametersConfigService;

    @ApiOperation("获取接口参数")
    @GetMapping("/getparamconfiglist/{ofid}")
    public JSONResult getParamConfigList(@PathVariable("ofid") String ofid){
        ErrorCode code;
        List<ParametersConfig> list=new ArrayList<>();
        try {
            list=parametersConfigService.getParamConfigList(ofid);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("24001");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ParametersConfigController", "getParamConfigList", ex.getMessage()));
        }
        return new JSONResult(code,list);
    }

    @ApiOperation("添加参数")
    @PostMapping("/insertparamconfig")
    public JSONResult insertParamConfig(@RequestBody ParametersConfig model){
        ErrorCode code;
        String keyId="";
        try {
            keyId=parametersConfigService.insertParamConfig(model);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("24002");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ParametersConfigController", "insertParamConfig", ex.getMessage()));
        }
        return new JSONResult(code,keyId);
    }

    @ApiOperation("删除参数")
    @PostMapping("/deleteparamconfigbyid/{keyid}")
    public JSONResult deleteParamConfigById(@PathVariable("keyid")String keyid){
        ErrorCode code;
        boolean flag=false;
        try {
            flag=parametersConfigService.deleteParamConfigById(keyid);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            flag=false;
            code = ErrorCodeManager.GetCode("24002");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ParametersConfigController", "deleteParamConfigById", ex.getMessage()));
        }
        return new JSONResult(code,flag);
    }


}

