package com.tjhnode.dataservice.controller;


import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.vo.MenuCatalog;
import com.tjhnode.dataservice.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author tjh
 * @since 2020-07-13
 */
@RestController
@RequestMapping("api/syspermission")
@Api("权限")
public class SysPermissionController {

    private static final Logger logger = LoggerFactory.getLogger(ParametersConfigController.class);

    @Autowired
    private ErrorCode errorCode=ErrorCodeManager.GetCode("0");

    @Autowired
    private SysPermissionService permissionService;

    @ApiOperation("获取菜单")
    @PostMapping("/getmenucatalog/{userid}")
    public JSONResult getMenuCatalog(@PathVariable("userid") String userid ){
        List<MenuCatalog> catalogList=new ArrayList<>();
        try {
            //111
            catalogList=permissionService.selectMenuByUserId(userid);
        } catch (Exception e) {
            errorCode=ErrorCodeManager.GetCode("25001");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.SysPermissionController", "getMenuCatalog",  e.getMessage()));
        }
        return new JSONResult(errorCode,catalogList);
    }
}

