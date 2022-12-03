package EMS.user.service;

import EMS.user.mapper.UserMapper;
import EMS.user.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Student queryById(Long id) {
        return userMapper.selectById(id);
    }

}