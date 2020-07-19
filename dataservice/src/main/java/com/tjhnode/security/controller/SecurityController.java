package com.tjhnode.security.controller;


import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import com.tjhnode.dataservice.model.vo.QueryPaginationModel;
import com.tjhnode.dataservice.model.vo.ResultPaginationModel;
import com.tjhnode.security.entity.SysUser;
import com.tjhnode.security.service.SysUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author tjh
 * @since 2020-05-31
 */
@RestController
@RequestMapping("api/security")
@Api("security信息API")
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private ErrorCode errorCode;

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("用户信息")
    @GetMapping("/getuser/{username}")
    public JSONResult getUser(@PathVariable("username")String username)
    {
        SysUser user=new SysUser();
        try {
            user=sysUserService.getSysUser(username);
            errorCode= ErrorCodeManager.GetCode("0");
        } catch (Exception ex) {
            errorCode=ErrorCodeManager.GetCode("1015");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller","SecurityController",ex.getMessage()));
        }
        return new JSONResult(errorCode,user);
    }

    @ApiOperation("获取用户列表")
    @PostMapping("/getUserList")
    public JSONResult getUserList(@RequestBody QueryPaginationModel query){
        ResultPaginationModel<SysUser> result=new ResultPaginationModel<>();
        try {
            if(query ==null){
                errorCode=ErrorCodeManager.GetCode("10002");
                errorCode.Message=errorCode.Message.replace("{ParameterName}","{PageIndex、PageSize}");
            }else {
                result = sysUserService.getUserList(query);
                errorCode= ErrorCodeManager.GetCode("0");
            }
        } catch (Exception ex) {
            errorCode=ErrorCodeManager.GetCode("1014");
            logger.error(String.format("时间：%s, Package: %s, 方法名：%s, 错误信息：%s \r\n",
                    LocalDateTime.now(), "com.tjhnode.dataservice.controller","SecurityController",ex.getMessage()));
        }
        return new JSONResult(errorCode,result);
    }


    /**
     * 解析token
     * @param authentication
     * @return
     */
    @GetMapping(value = "/get")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get(Authentication authentication){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //authentication.getCredentials();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        String jwtToken = details.getTokenValue();
        Claims claims = Jwts.parser()
                .setSigningKey("dev".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims;
    }


}

