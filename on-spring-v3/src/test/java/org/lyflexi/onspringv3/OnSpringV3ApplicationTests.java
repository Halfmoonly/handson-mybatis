package org.lyflexi.onspringv3;

import org.junit.jupiter.api.Test;
import org.lyflexi.onspringv3.v3.config.SpringMybatisConfig;
import org.lyflexi.onspringv3.v3.dao.AccountMapper;
import org.lyflexi.onspringv3.v3.dao.ProductMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class OnSpringV3ApplicationTests {

    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(SpringMybatisConfig.class);

        ProductMapper productMapper = (ProductMapper) context.getBean("productMapper");
        productMapper.qryProductInfoById(1);
        AccountMapper accountMapper = (AccountMapper) context.getBean("accountMapper");
        accountMapper.qryAccount(1);
    }

}
