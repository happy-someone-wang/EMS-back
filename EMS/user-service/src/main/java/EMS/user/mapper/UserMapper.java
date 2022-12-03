package EMS.user.mapper;

import EMS.user.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<Student> {
//    @Select("select * from student where student_id = #{id}")
//    User findById(@Param("id") Long id);
}