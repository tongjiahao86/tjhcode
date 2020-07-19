package com.tjhnode;

import com.tjhnode.common.utils.AttributeUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableCaching
@SpringBootApplication
public class DataserviceApplication {

    public static void main(String[] args) {
        ///String s=AttributeUtils.sha256_HMAC("qwer","123456");
        SpringApplication.run(DataserviceApplication.class, args);
    }

}
