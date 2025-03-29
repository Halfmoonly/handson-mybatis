package org.lyflexi.onspring;

import org.junit.jupiter.api.Test;
import org.lyflexi.onspring.v1.config.SpringMybatisConfig;
import org.lyflexi.onspring.v1.dao.AccountMapper;
import org.lyflexi.onspring.v1.entity.AccountInfo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class V1Test {

	@Test
	void contextLoads() {
		AnnotationConfigApplicationContext context
				= new AnnotationConfigApplicationContext(SpringMybatisConfig.class);


		//Spring默认的包扫描@ComponentScan规则会过滤掉接口、抽象类，因此无法创建accountMapper，因此下述代码执行报错
		//org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'accountMapper' available
		AccountMapper accountMapper = (AccountMapper) context.getBean("accountMapper");
		accountMapper.qryById("11111");

//		AccountServiceImpl accountService = (AccountServiceImpl) context.getBean("accountServiceImpl");
//		AccountInfo accountInfo = accountService.qryById("1234562");
//		System.out.println(accountInfo);

	}

}
