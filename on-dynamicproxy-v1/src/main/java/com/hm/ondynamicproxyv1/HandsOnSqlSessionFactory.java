package com.hm.ondynamicproxyv1;


import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyanoutsee@outlook.com
 */
public class HandsOnSqlSessionFactory {

    /**
     * jdk动态代理
     * @param mapperClass
     * @return
     * @param <T>
     */
    @SuppressWarnings("all")
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperClass},
                new MapperInvocationHandler());
    }


}
