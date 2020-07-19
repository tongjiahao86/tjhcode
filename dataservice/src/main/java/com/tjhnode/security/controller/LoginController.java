package com.tjhnode.security.controller;

import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @program: dataservice
 * @description: 登录
 * @author: tjh
 * @create: 2020-07-11 19:05
 **/
@RestController
@RequestMapping("/oauth")
public class LoginController {


    @Autowired
    private TokenEndpoint tokenEndpoint;

    private ErrorCode errorCode= ErrorCodeManager.GetCode("0");

    /**
     * =====================================
     * 描   述 : 自定义返回信息添加基本信息
     * 参   数 :  [principal, parameters]
     * 返 回 值 : top.qinxq.single.entity.vo.R
     * =====================================
     */
    @PostMapping("/token")
    public JSONResult postAccessTokenWithUserInfo(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        Map<String, Object> data = new LinkedHashMap();
        try {
            if (principal == null) {
                errorCode = ErrorCodeManager.GetCode("1005");
            } else {
                OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
                data.put("accessToken", accessToken.getValue());
                if (accessToken.getRefreshToken() != null) {
                    data.put("refreshToken", accessToken.getRefreshToken().getValue());
                }

            }
        } catch (Exception ex) {
            data.clear();
            errorCode = ErrorCodeManager.GetCode("1005");
        }
        return new JSONResult(errorCode, data);
    }






   /* @Autowired
    private OAuth2ClientProperties oauth2ClientProperties;

    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;

    @PostMapping("/login")
    public OAuth2AccessToken login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        // 创建 ResourceOwnerPasswordResourceDetails 对象
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setClientId(oauth2ClientProperties.getClientId());
        resourceDetails.setClientSecret(oauth2ClientProperties.getClientSecret());
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        // 创建 OAuth2RestTemplate 对象
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        // 获取访问令牌
        return restTemplate.getAccessToken();
    }*/

}


