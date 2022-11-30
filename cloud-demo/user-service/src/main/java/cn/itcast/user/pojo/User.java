package cn.itcast.user.pojo;

import lombok.Data;

@Data //与数据库保持一致
public class User {
    private Long id;
    private String username;
    private String address;
}