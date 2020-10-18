# 内容服务中心
## 本服务为share-app项目的内容模块
### 提供接口有
- 用户公告的新增以及查询
- 用户提交资源的审核以及查询
- 查询指定资源接口
- 查询资源接口
- 投稿接口
- 兑换接口
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
 - swagger
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
│          │          └─content
│          │              │  ContentCenterApplication.java 项目启动类
│          │              │  
│          │              ├─auth 自定义注解以及鉴权相关
│          │              │      AuthAspect.java aop切面拦截接口
│          │              │      CheckAuthorization.java 鉴权注解
│          │              │      CheckLogin.java 登录注解
│          │              │      GlobalExceptionErrorHandler.java 全局异常统一处理
│          │              ├─common 配置
│          │              │      ResponseResult.java 封装统一返回类型
│          │              │      
│          │              ├─configuration 配置
│          │              │      GlobalFeignConfiguration.java 全局feign请求日志配置类
│          │              │      NacosWeightedRule.java Nocos请求规则配置类
│          │              │      SwaggerConfiguration.java Swagger配置类
│          │              │      UserCenterFeignConfiguration.java 用户中心feign请求日志配置
│          │              │      UserCenterRibbonConfig.java 用户中心Ribbon规则
│          │              │      
│          │              ├─controller
│          │              │          
│          │              ├─dao 底层mapper实现
│          │              ├─domain
│          │              │  └─dto 参数封装dto
│          │              │  └─entity 实体类型dto
│          │              │  └─enums 枚举常量封装
│          │              │      
│          │              ├─feignclient
│          │              │  └─interceptor feign请求拦截
│          │              │    UserCenterFeignClient.java 封装user-center的接口
│          │              │      
│          │              ├─service
│          │              │  └─impl
│          │              │      
│          │              └─utils
│          │                      JwtOperator.java
│          │                      
│          └─resources
│              │  application-dev.yml 开发环境
│              │  
│              ├─db 该项目数据库文件

```
