package com.hm.ondynamicproxyv1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnDynamicproxyV1ApplicationTests {

    @Test
    void contextLoads() {
        HandsOnSqlSessionFactory handsOnSqlSessionFactory = new HandsOnSqlSessionFactory();
        UserMapper mapper = handsOnSqlSessionFactory.getMapper(UserMapper.class);
        User user = mapper.selectById(2);
        System.out.println(user);
        System.out.println(mapper.selectByName("jerry"));
        System.out.println(mapper.selectByNameAndAge("jerry",10));
    }

}
