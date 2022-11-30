package cn.itcast.order.mapper;

import cn.itcast.order.pojo.Order;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;

public interface OrderMapper {

    @Select("select * from tb_order where id = #{id}")//暂时使用sql语句，后续更新
    Order findById(Long id);
}
