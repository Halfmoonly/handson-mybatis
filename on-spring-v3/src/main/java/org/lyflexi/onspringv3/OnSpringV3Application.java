package org.lyflexi.onspringv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//关闭三合一注解，屏蔽默认包扫描
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
public class OnSpringV3Application {

    public static void main(String[] args) {
        SpringApplication.run(OnSpringV3Application.class, args);
    }

}
