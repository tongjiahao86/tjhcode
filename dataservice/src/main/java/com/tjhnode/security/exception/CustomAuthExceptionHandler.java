package com.tjhnode.security.exception;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.controller.ParametersConfigController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: dataservice
 * @description: 自定义未授权 token无效、权限不足返回信息处理类
 * @author: tjh
 * @create: 2020-06-21 12:32
 **/
@Component
public class CustomAuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler, AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(ParametersConfigController.class);

    @Autowired
    private static ErrorCode errorCode;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Throwable cause=e.getCause();
        //response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control","no-cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if(cause instanceof InvalidTokenException){
            logger.error("InvalidTokenException : {}",cause.getMessage());
            //Token无效
            errorCode= ErrorCodeManager.GetCode("1001");
            response.getWriter().write(JSON.toJSONString(new JSONResult(errorCode,"")));
        }else{
            logger.error("AuthenticationException : NoAuthentication");
            //资源未授权
            errorCode= ErrorCodeManager.GetCode("1004");
            response.getWriter().write(JSON.toJSONString(new JSONResult(errorCode,"")));
        }
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control","no-cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        //访问资源的用户权限不足
        logger.error("AccessDeniedException : {}",e.getMessage());
        //资源未授权
        errorCode= ErrorCodeManager.GetCode("1003");
        response.getWriter().write(JSON.toJSONString(new JSONResult(errorCode,"")));
    }

    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Throwable cause=exception.getCause();
        response.setContentType("application/json;charset=UTF-8");
        //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control","no-cache");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.addHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        logger.error("AccessDeniedException : {}",exception.getMessage());
        //资源未授权
        errorCode= ErrorCodeManager.GetCode("1005");
        response.getWriter().write(JSON.toJSONString(new JSONResult(errorCode,"")));
    }



}
