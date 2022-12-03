package EMS.order.client;

import EMS.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userservice") //feign远程调用，名称来源于nacos中毒的微服务名称
public interface Userclient {

    @GetMapping("api/user/{id}")
    User findById(@PathVariable("id") Long id);
}
