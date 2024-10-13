package org.lyflexi.onspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author: lyflexi
 * @project: hands-on-mybatis
 * @Date: 2024/10/13 17:03
 */
//关闭三合一注解，屏蔽默认包扫描
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
public class HandOnMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandOnMybatisApplication.class, args);
    }
}
