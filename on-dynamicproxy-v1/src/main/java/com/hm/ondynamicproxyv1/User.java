package com.hm.ondynamicproxyv1;

import lombok.Data;

/**
 *
 */
@Data
@Table(tableName = "user")
public class User {
    private Integer id;
    private String name;
    private Integer age;
}
