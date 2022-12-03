package EMS.order.service;

import EMS.order.client.Userclient;
import EMS.order.mapper.OrderMapper;
import EMS.order.pojo.Order;
import EMS.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired  //引入元年成调用代理
    private Userclient userClient;

    public Order queryOrderById(Long orderId)
    {
//         1.查询订单
        Order order = orderMapper.selectById(orderId);
//         2.用Feign远程调用
        User user = userClient.findById(order.getUserId());
//         3.封装user到Order
        order.setUser(user);
//         4.返回
        return order;
    }
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        // 4.返回
//        return order;
//    }
}
