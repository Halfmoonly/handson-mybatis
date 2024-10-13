package org.lyflexi.onspringv2.v2.config;


import org.lyflexi.onspringv2.v2.anno.EnableMapperScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/4 15:06
 */
@Configuration
@ComponentScan(value = {"org.lyflexi.onspringv2.v2"})
@EnableMapperScanner(basePackage = "org.lyflexi.onspringv2.v2.dao")
public class SpringMybatisConfig {



}
