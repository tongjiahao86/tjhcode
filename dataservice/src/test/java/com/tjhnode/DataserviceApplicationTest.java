package com.tjhnode;


import com.tjhnode.common.utils.AttributeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;


public class DataserviceApplicationTest {

    @Test
    public void test() {
//        String pass = "123456";
//        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
//        String hashPass = bcryptPasswordEncoder.encode(pass);
//        System.out.println(hashPass);
//
//        boolean f = bcryptPasswordEncoder.matches("admin",hashPass);
//        System.out.println(f);

       String str= AttributeUtils.getMD5("123456");
    }
}
