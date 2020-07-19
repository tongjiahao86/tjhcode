package com.tjhnode.dataservice.controller;

import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.entities.ClassificationModel;
import com.tjhnode.dataservice.service.ClassificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dataservice
 * @description: 目录
 * @author: tjh
 * @create: 2019-12-27 21:38
 **/
@RestController     //此注解指明该控制器直接返回数据，而不进行页面跳转
@RequestMapping("api/classfication")
@Api("目录信息API")
public class ClassficationController {

    private static final Logger logger = LoggerFactory.getLogger(ClassficationController.class);
    @Autowired
    private ClassificationService classificationServiceImpl;

        /**
         * 获取目录树
         *
         * @return
         */
        @ApiOperation("目录树")
        @RequestMapping(value = "/findAll", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
            public JSONResult findAll() {
                ErrorCode errorCode = null;
                List<ClassificationModel> list = new ArrayList<>();
                try {
                    list = classificationServiceImpl.findAll();
                    errorCode = ErrorCodeManager.GetCode("0");
                } catch (Exception e) {
                    list.clear();
                    errorCode = ErrorCodeManager.GetCode("20001");
                    //logger.info(String.format("时间：%s, 方法名：%s, 错误信息：%s \r\n", LocalDateTime.now(),"findAll",e.getMessage()));
                }
                return new JSONResult(errorCode, list);
            }
        /**
         * 新增功能
         *
         * @param model
         * @return
         */
        @ApiOperation("新增目录")
        @PostMapping(value = "/insertclassification")
        public JSONResult InsertClassification(@RequestBody ClassificationModel model) {
            ErrorCode errorCode = null;
            String classificationId = "";
            try {
                classificationId = classificationServiceImpl.InsertClassification(model);
                errorCode = ErrorCodeManager.GetCode("0");
            } catch (Exception e) {
                classificationId = "";
                errorCode = ErrorCodeManager.GetCode("20002");
            }
            return new JSONResult(errorCode, classificationId);
        }

    /**
     * 修改功能
     * @param model
     * @return
     */
    @ApiOperation("修改目录")
    @PostMapping(value = "/updateclassification")
    public JSONResult UpdateClassification(@RequestBody ClassificationModel model) {
        ErrorCode errorCode = null;
        boolean flag = false;
        try {
            flag = classificationServiceImpl.UpdateClassification(model);
            errorCode = ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            errorCode = ErrorCodeManager.GetCode("20003");
            flag = false;
        }
        return new JSONResult(errorCode,flag);
    }

    /**
     * 获取目录详情
     * @param classificationId
     * @return
     */
    @ApiOperation("目录详情")
    @GetMapping("/classificationdetail/{classificationId}")
    public JSONResult ClassificationDetail(@PathVariable("classificationId") String classificationId){
        ErrorCode errorCode = null;
        ClassificationModel model=null;
        try {
            model=classificationServiceImpl.ClassificationDetail(classificationId);
            errorCode = ErrorCodeManager.GetCode("0");
        } catch (Exception e) {
            errorCode = ErrorCodeManager.GetCode("20004");
        }
        return new JSONResult(errorCode,model);
    }

    /**
     * 删除目录
     * @param classificationId 目录id
     * @return
     */
    @ApiOperation("删除目录")
    @GetMapping("/deleteclassification/{classificationId}")
    public JSONResult DeleteClassification(@PathVariable("classificationId") String classificationId){
        ErrorCode errorCode = null;
        boolean flag=false;
        try {
            flag=classificationServiceImpl.DeleteClassification(classificationId);
            errorCode = ErrorCodeManager.GetCode("0");
        } catch (Exception e){
            errorCode = ErrorCodeManager.GetCode("20005");
        }
        return new JSONResult(errorCode,flag);
    }
}
