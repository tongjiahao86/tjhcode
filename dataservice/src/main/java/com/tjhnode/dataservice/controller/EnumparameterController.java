package com.tjhnode.dataservice.controller;


import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.entities.Enumparameters;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.dataservice.service.EnumParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 参数配置
 * </p>
 *
 * @author tjh
 * @since 2019-11-30
 */
@RestController
@RequestMapping("api/enumparameter")
@Api("参数配置API")
public class EnumparameterController {

    private static final Logger logger = LoggerFactory.getLogger(EnumparameterController.class);

    @Autowired
    private EnumParameterService enumParameterService;

    @ApiOperation("参数配置列表")
    @PostMapping("/getenumparameterlist")
    public JSONResult getEnumParameterList(@RequestBody QueryPaginationModel model) {
        ErrorCode code;
        ResultPaginationModel<Enumparameters> result = new ResultPaginationModel<>();
        try {
            result = enumParameterService.getEnumParameterList(model);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("22001");
            logger.info(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.EnumparameterController", "getEnumParameterList", ex.getMessage()));
        }
        return new JSONResult(code, result);
    }

    @ApiOperation("参数详情")
    @GetMapping("/getparameterdetail/{keyid}")
    public JSONResult getParameterDetail(@PathVariable("keyid") String keyid) {
        Enumparameters enumparameters = new Enumparameters();
        ErrorCode code;
        try {
            enumparameters = enumParameterService.getParameterDetail(keyid);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("22002");
            logger.info(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.EnumparameterController", "getParameterDetail", ex.getMessage()));
        }
        return new JSONResult(code, enumparameters);
    }

    @ApiOperation("新增参数配置")
    @PostMapping("/insertenumparameter")
    public JSONResult insertEnumParameter(@RequestBody Enumparameters model) {
        String keyId = "";
        ErrorCode code;
        try {
            keyId = enumParameterService.insertEnumParameter(model);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("22003");
            logger.info(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.EnumparameterController", "insertEnumParameter", ex.getMessage()));
        }
        return new JSONResult(code, keyId);
    }

    @ApiOperation("修改参数配置")
    @PostMapping("/updateenumparameter")
    public JSONResult updateEnumParameter(@RequestBody Enumparameters model) {
        boolean flag = false;
        ErrorCode code;
        try {
            flag = enumParameterService.updateEnumParameter(model);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("22004");
            logger.info(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.EnumparameterController", "updateEnumParameter", ex.getMessage()));
        }
        return new JSONResult(code, flag);
    }

    @ApiOperation("删除参数配置")
    @PostMapping("/deleteenumparameter/{keyid}")
    public JSONResult deleteEnumParameter(@PathVariable("keyid") String keyid) {
        boolean flag = false;
        ErrorCode code;
        try {
            flag = enumParameterService.deleteEnumParameter(keyid);
            code = ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            code = ErrorCodeManager.GetCode("22005");
            logger.info(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller.EnumparameterController", "deleteEnumParameter", ex.getMessage()));
        }
        return new JSONResult(code, flag);
    }


}

