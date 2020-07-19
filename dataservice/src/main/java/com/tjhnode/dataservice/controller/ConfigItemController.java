package com.tjhnode.dataservice.controller;

import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.entities.ConfigItemModel;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.dataservice.service.ConfigItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @program: dataservice
 * @description: 业务功能配置项
 * @author: tjh
 * @create: 2019-11-24 18:35
 **/
@RestController     //此注解指明该控制器直接返回数据，而不进行页面跳转
@RequestMapping("api/configitem")
@Api("接口基本配置API")
public class ConfigItemController {

    private static final Logger logger= LoggerFactory.getLogger(ConfigItemController.class);

    @Autowired
    private ConfigItemService configItemService;

    /**
     * 获取接口配置列表
     * @param query 分页、查询条件
     * @param classificationId 目录id
     * @return
     */
    @ApiOperation("接口配置列表")
    @PostMapping("/getconfiglist/{classificationId}")
    public JSONResult getConfigList(@RequestBody QueryPaginationModel query, @PathVariable("classificationId") String classificationId){
        ErrorCode code=null;
        ResultPaginationModel<ConfigItemModel> result=new ResultPaginationModel<>();
        try{
            result=configItemService.getConfigList(query,classificationId);
            code = ErrorCodeManager.GetCode("0");
        }
        catch (Exception ex){
            code = ErrorCodeManager.GetCode("21001");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ConfigItemController","getConfigList",ex.getMessage()));
        }
        return new JSONResult(code,result);
    }

    /**
     * 接口配置详细信息
     * @param ofid 接口编号
     * @return
     */
    @ApiOperation("接口详细信息")
    @GetMapping("/getconfigitemdetail/{ofid}")
    public JSONResult getConfigItemDetail(@PathVariable("ofid") String ofid){
        ErrorCode code;
        ConfigItemModel entity=new ConfigItemModel();
        try {
            entity=configItemService.getConfigItemDetail(ofid);
            code = ErrorCodeManager.GetCode("0");
        }
        catch (Exception ex){
            code = ErrorCodeManager.GetCode("21002");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ConfigItemController","getConfigItemDetail",ex.getMessage()));
        }
        return new JSONResult(code,entity);
    }

    /**
     * 新增
     * @param model
     * @return
     */
    @ApiOperation("新增接口")
    @PostMapping("/insertconfigitem")
    public JSONResult insertConfigItem(@RequestBody ConfigItemModel model) {
        ErrorCode code=null;
        String result="";
        try {
            result=configItemService.insertConfigItem(model);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            result="";
            code = ErrorCodeManager.GetCode("21003");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ConfigItemController","insertConfigItem",ex.getMessage()));
        }
        return new JSONResult(code,result);
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @ApiOperation("修改接口")
    @PostMapping("/updateconfigitem")
    public JSONResult updateConfigItem(@RequestBody ConfigItemModel model){
        ErrorCode code=null;
        boolean flag=false;
        try {
            flag=configItemService.updateConfigItem(model);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            flag=false;
            code = ErrorCodeManager.GetCode("21003");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.ConfigItemController","updateConfigItem",ex.getMessage()));
        }
        return new JSONResult(code,flag);
    }

    /**
     * 删除
     * @param ofid
     * @return
     */
    @ApiOperation("删除接口")
    @PostMapping("/deleteconfigitem/{ofid}")
    public JSONResult deleteConfigItem(@PathVariable("ofid") String ofid)
    {
        ErrorCode code=null;
        boolean flag=false;
        try {
            flag=configItemService.deleteConfigItem(ofid);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            flag=false;
            code = ErrorCodeManager.GetCode("21003");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller","deleteConfigItem",ex.getMessage()));
        }
        return new JSONResult(code,flag);
    }

}
