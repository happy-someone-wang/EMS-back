package EMS.order.mapper;

import EMS.order.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

//    @Select("select * from tb_order where id = #{id}")//暂时使用sql语句，后续更新
//    Order findById(Long id);
}
