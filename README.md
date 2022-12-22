## 项目重构

- 2022年12月22日

添加feign支持，修改注册中心为eureka

有关feign的远程调用统一写在了feign-api模块，其它模块在pom.xml中引用该模块

建议每个人的分支内写一个README作为API文档
