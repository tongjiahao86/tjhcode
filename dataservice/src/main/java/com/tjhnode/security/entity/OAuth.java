package com.tjhnode.security.entity;

import lombok.Data;

/**
 * @program: dataservice
 * @description:
 * @author: tjh
 * @create: 2020-07-06 20:54
 **/
@Data
public class OAuth {

    /**
     * 用户名
     */
    public String username;

    /**
     * 密码
     */
    public String password;

    public String grant_type;

    public String client_id;

    public String client_secret;

    public String scope;
}
