package com.hm.ondynamicproxyv1;

/**
 * @Author: liuyanoutsee@outlook.com
 * @Date: 2025/3/29 21:50
 * @Project: handson-mybatis
 * @Version: 1.0.0
 * @Description:
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义的InvocationHandler
 */
public class MapperInvocationHandler implements InvocationHandler {
    private static final String JDBCURL = "jdbc:mysql://192.168.18.100:3308/lyflexi";
    private static final String DBUSER = "root";
    private static final String PASSWORD = "root";
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("select")) {
            return invokeSelect(proxy, method, args);
        }
        return null;
    }

    private Object invokeSelect(Object proxy, Method method, Object[] args) {
        String sql = createSelectSql(method);
        System.out.println(sql);
        try (Connection conn = DriverManager.getConnection(JDBCURL, DBUSER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof Integer) {
                    statement.setInt(i + 1, (int) arg);
                } else if (arg instanceof String) {
                    statement.setString(i + 1, arg.toString());
                }
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                //需要提供jdbc的返回结果，需要根据jdbc的返回结果来创建返回类型User，因此需要如下两个参数
                return parseResult(rs, method.getReturnType());
            }
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 需要提供jdbc的返回结果，需要根据jdbc的返回结果来创建返回类型User，因此需要如下两个参数
     * @param rs
     * @param returnType
     * @return
     * @throws Exception
     */
    private Object parseResult(ResultSet rs, Class<?> returnType) throws Exception {
        Constructor<?> constructor = returnType.getConstructor();
        Object result = constructor.newInstance();
        Field[] declaredFields = returnType.getDeclaredFields();
        //遍历所有的反射字段名
        for (Field declaredField : declaredFields) {
            //字段值，有JDBC返回
            Object columnValue = null;
            //字段名，由Java反射提供
            String filedName = declaredField.getName();
            if (declaredField.getType() == String.class) {
                //获取反射字段名对应的jdbc返回结果的字段值
                columnValue = rs.getString(filedName);
            } else if (declaredField.getType() == Integer.class) {
                columnValue = rs.getInt(filedName);
            }
            declaredField.setAccessible(true);
            declaredField.set(result, columnValue);
        }
        return result;
    }

    /**
     * 创建sql
     * @param method
     * @return
     */
    private String createSelectSql(Method method) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select ");
        List<String> selectCols = getSelectCols(method.getReturnType());
        sqlBuilder.append(String.join(",", selectCols));
        sqlBuilder.append(" from ");
        String tableName = getSelectTableName(method.getReturnType());
        sqlBuilder.append(tableName);
        sqlBuilder.append(" where ");
        //  select * from user where id = ?
        String where = getSelectWhere(method);
        sqlBuilder.append(where);
        return sqlBuilder.toString();
    }

    /**
     * stream流计算全部的查询字段并进行拼接
     *
     * 返回PreparedStatement的where子句
     * @param method
     * @return
     */
    private String getSelectWhere(Method method) {
        return Arrays.stream(method.getParameters())
                .map((parameter) -> {
                    Param param = parameter.getAnnotation(Param.class);
                    String column = param.value();
                    return column + " = ?";
                }).collect(Collectors.joining(" and "));
    }

    /**
     * 根据自定义注解反射获取表名
     * @param returnType
     * @return
     */
    private String getSelectTableName(Class<?> returnType) {
        Table table = returnType.getAnnotation(Table.class);
        if (table == null) {
            throw new RuntimeException("返回值无法确定查询表");
        }
        return table.tableName();
    }

    /**
     * 利用反射返回所有的列名
     * @param returnType
     * @return
     */
    private List<String> getSelectCols(Class<?> returnType) {
        Field[] declaredFields = returnType.getDeclaredFields();
        return Arrays.stream(declaredFields).map(Field::getName).toList();
    }

}