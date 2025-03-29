package com.hm.onjdbcv1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author liuyanoutsee@outlook.com
 */
public class Main {
    private static final String JDBCURL = "jdbc:mysql://192.168.18.100:3308/lyflexi";
    private static final String DBUSER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        System.out.println(jdbcSelectById(1));
    }

    private static User jdbcSelectById(int id) {;
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBCURL, DBUSER, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
