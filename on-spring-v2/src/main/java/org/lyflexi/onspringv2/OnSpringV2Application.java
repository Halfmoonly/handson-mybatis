package org.lyflexi.onspringv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//关闭三合一注解，屏蔽默认包扫描
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
public class OnSpringV2Application {

    public static void main(String[] args) {
        SpringApplication.run(OnSpringV2Application.class, args);
    }

}
