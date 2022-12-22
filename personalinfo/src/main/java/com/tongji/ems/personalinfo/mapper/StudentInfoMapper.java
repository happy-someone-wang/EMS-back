package com.tongji.ems.personalinfo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tongji.ems.personalinfo.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentInfoMapper extends BaseMapper<Student> {
}
