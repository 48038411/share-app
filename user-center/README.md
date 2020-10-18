# 用户服务中心
## 本服务为share-app项目的用户模块
### 提供接口有
- 用户登录接口，微信登录以及用户登录
- 查询单个用户信息接口
- 用户登录校验，用户权限校验
- 用户中心接收rocketmq下发的增加积分队列并对用户添加积分
- 用户兑换资源后扣除积分
- 用户签到接口
- 查询用户日志接口
## 基础环境
jdk v1.8  
mysql v8.0  
rocketmq v4.7.1  
maven
## 技术栈
 - springboot
 - mysql8
 - rocketmq
 - tkmybatis
 - 微信sdk
 - jwt
 - aop
## 项目目录结构
```shell
.
├─user-center
│  │  pom.xml 依赖文件
│  │  
│  └─src
│      └─main
│          ├─java
│          │  └─com
│          │      └─soft1851.share
│          │          └─user
│          │              │  UserCenterApplication.java 项目启动类
│          │              │  
│          │              ├─auth 自定义注解以及鉴权相关
│          │              │      AuthAspect.java aop切面拦截接口
│          │              │      CheckAuthorization.java 鉴权注解
│          │              │      CheckLogin.java 登录注解
│          │              ├─common 配置
│          │              │      ResponseResult.java 封装统一返回类型
│          │              │      
│          │              ├─configuration 配置
│          │              │      WxConfiguration.java 微信第三方登录信息配置
│          │              │      
│          │              ├─controller
│          │              │      UserController.java 用户服务中心接口
│          │              │          
│          │              ├─dao 底层mapper实现
│          │              ├─domain
│          │              │  └─dto 参数封装dto
│          │              │  └─entity 实体类型dto
│          │              │      
│          │              ├─rocketmq
│          │              │      AddBonusListener.java 监听rocketmq下发的消息
│          │              │      
│          │              ├─service
│          │              │  └─impl
│          │              │      
│          │              └─utils
│          │                      DateUtil.java
│          │                      JwtOperator.java
│          │                      
│          └─resources
│              │  application-dev.yml 开发环境
│              │  
│              ├─db 该项目数据库文件

```
