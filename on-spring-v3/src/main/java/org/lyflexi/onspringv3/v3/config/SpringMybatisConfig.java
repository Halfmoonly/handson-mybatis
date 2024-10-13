package org.lyflexi.onspringv3.v3.config;

import org.lyflexi.onspringv3.v3.anno.EnableMapperScanner;
import org.lyflexi.onspringv3.v3.compent.TulingMapperRegister;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * [来个全套]
 *
 * @slogan: 高于生活，源于生活
 * @Description: TODO
 * @author: lyflexi
 * @date 2020/5/4 15:06
 */
@Configuration
@ComponentScan(value = {"org.lyflexi.onspringv3.v3"})
@EnableMapperScanner(basePackage = "org.lyflexi.onspringv3.v3.dao")
public class SpringMybatisConfig {

/*	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("Zw726515");
		dataSource.setUrl("jdbc:mysql://localhost:3306/tuling-spring-trans?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}*/

}
