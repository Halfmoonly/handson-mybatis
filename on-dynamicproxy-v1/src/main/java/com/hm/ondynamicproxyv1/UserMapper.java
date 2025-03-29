package com.hm.ondynamicproxyv1;

import com.hm.ondynamicproxyv1.User;

/**
 * @author liuyanoutsee@outlook.com
 **/
public interface UserMapper {
    
    User selectById(@Param("id") int id);
    
    User selectByName(@Param("name") String name);
    
    User selectByNameAndAge(@Param("name") String name,@Param("age") int age);
    
}
