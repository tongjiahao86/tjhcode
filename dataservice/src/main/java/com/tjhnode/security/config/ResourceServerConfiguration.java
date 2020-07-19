package com.tjhnode.security.config;

import com.tjhnode.security.exception.CustomAuthExceptionHandler;
import com.tjhnode.security.service.Impl.MyCustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @program: dataservice
 * @description: 资源服务器
 * @author: tjh
 * @create: 2020-06-27 19:46
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static String RESOURCE_ID="project_api2";

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    /**
     * 注入userDetailsService，开启refresh_token需要用到
     */
    @Autowired
    private MyCustomUserService myCustomUserService;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private CustomAuthExceptionHandler customAuthExceptionHandler;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(jwtTokenStore)
                //token过期，权限不足，自定义返回信息
                .authenticationEntryPoint(customAuthExceptionHandler)
                .accessDeniedHandler(customAuthExceptionHandler);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login").permitAll()
                .failureHandler(customAuthExceptionHandler)
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/security/**","/login/**","/oauth/**","/v2/api-docs/**", "/swagger-resources/**", "/druid/**").permitAll()
            .anyRequest().authenticated();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(myCustomUserService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }



}