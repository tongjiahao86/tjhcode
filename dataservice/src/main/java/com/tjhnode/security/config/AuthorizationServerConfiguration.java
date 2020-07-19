package com.tjhnode.security.config;

import com.tjhnode.security.exception.CustomWebResponseExceptionTranslator;
import com.tjhnode.security.service.Impl.MyCustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: dataservice
 * @description: 认证服务器
 * @author: tjh
 * @create: 2020-06-27 12:08
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    /**
     * 注入权限验证控制器 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 注入userDetailsService，开启refresh_token需要用到
     */
    @Autowired
    private MyCustomUserService myCustomUserService;

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * jwt额外配置
     */
    @Autowired
    private JWTokenEnhancer jwtTokenEnhancer;

    @Autowired
    private CustomWebResponseExceptionTranslator webResponseExceptionTranslator;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         * 普通 jwt 模式
         */
//         endpoints.tokenStore(jwtTokenStore)
//                .accessTokenConverter(jwtAccessTokenConverter)
//                .userDetailsService(kiteUserDetailsService)
//                /**
//                 * 支持 password 模式
//                 */
//                .authenticationManager(authenticationManager);

        /**
         * jwt 增强模式
         */
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
        endpoints.tokenStore(jwtTokenStore)
                .userDetailsService(myCustomUserService)
                /**
                 * 支持 password 模式
                 */
                .authenticationManager(authenticationManager)
                //jwt
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                //该字段设置设置refresh token是否重复使用,true:reuse;false:no reuse.
                .reuseRefreshTokens(false);
        endpoints.exceptionTranslator(webResponseExceptionTranslator);

        /**
         * redis token 方式
         */
//        endpoints.authenticationManager(authenticationManager)
//                .tokenStore(redisTokenStore)
//                .userDetailsService(kiteUserDetailsService);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);

//        clients.inMemory()
//                .withClient("order-client")
//                .secret(passwordEncoder.encode("order-secret-8888"))
//                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                .accessTokenValiditySeconds(3600)
//                .scopes("all")
//                .and()
//                .withClient("user-client")
//                .secret(passwordEncoder.encode("user-secret-8888"))
//                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//                .accessTokenValiditySeconds(3600)
//                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }

}
