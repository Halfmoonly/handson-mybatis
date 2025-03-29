package com.hm.ondynamicproxyv1;

import com.hm.ondynamicproxyv1.User;

/**
 * @author liuyanoutsee@outlook.com
 **/
public interface UserMapper {

    //程序编译后参数id变成了形如arg1 arg2的形式，因此我们需要而外定义@Param注解来标记参数名
    User selectById(@Param("id") int id);
    
    User selectByName(@Param("name") String name);
    
    User selectByNameAndAge(@Param("name") String name,@Param("age") int age);
    
}
