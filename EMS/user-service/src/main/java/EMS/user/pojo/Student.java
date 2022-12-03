package EMS.user.pojo;

import lombok.Data;

@Data //与数据库保持一致
public class Student {
    private Long studentId;
    private String name;
    private boolean gender;
    private String email;
    private String phone;
}