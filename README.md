## 项目重构

- 2022年12月22日

添加feign支持，修改注册中心为eureka

有关feign的远程调用统一写在了feign-api模块，其它模块在pom.xml中引用该模块

建议每个人的分支内写一个README作为API文档

## personalInfo模块后端API

### 获取学生或教师的个人信息

get `/person?id=<>&role=<>`

id: 学生或教师的ID，学生为7位学号，教师为其工号 <br/>
role: 用户角色，该参数值只能为`teacher`或`student`,

当查询人物存在时，返回如下信息

http://localhost:8081/person?id=2050001&role=student

```json
{
  "studentId": 2050001,
  "gender": "女",
  "school": "软件学院",
  "name": "周慧敏",
  "startYear": "2020",
  "selfDesc": "我自盛开，胡蝶飞来",
  "avatar": "https://s2.loli.net/2022/11/05/8j6akmQ9xViINKG.jpg",
  "residence": "湖南省广元市",
  "email": "2050001@tongji.edu.cn",
  "status": "查询成功",
  "tags": "实验仪器杀手,吃良达人"
}
```

http://localhost:8081/person?id=63571&role=teacher

```json
{
  "teacherId": 63571,
  "gender": "男",
  "name": "李振江",
  "selfDesc": "师者，所以传道授业解惑也",
  "avatar": "https://s2.loli.net/2022/11/05/8j6akmQ9xViINKG.jpg",
  "email": "63571@tongji.edu.cn",
  "status": "查询成功",
  "tags": "讲师,软件学院"
}
```

其中selfDesc是用户的一句话简介，tags是用户的标签，多个标签用逗号 `,`分割，status指示查询的结果，以中文表示

当查询的用户不存在时，返回的信息如下
```json
{
  "status": "查无此人"
}
```

其余情况均返回400错误