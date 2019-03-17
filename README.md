# SSM框架的基本练习

##简介
整合SSM框架的DEMO项目，参考课程：http://www.imooc.com/u/2145618/courses?sort=publish
。本项目只实现了后端代码部分，通过Postman进行接口功能测试；
并针对高并发场景，扩展使用Redis作为原子计数器（watch事务+decr操作），RabbitMQ作为消息队列记录用户抢购行为，MySQL做异步存储的方案；
采用JMeter进行压力和性能测试；

##开发环境及框架
+ **IDE**： IntelliJ IDEA
+ **管理工具**：Maven
+ **JDK**： JDK1.8以上
+ **Web容器**：Tomcat
+ **数据库**：Mysql
+ **框架**：Spring + SpringMVC + Mybatis + Redis + RabbitMQ
+ **日志**：slf4j + logback
+ **接口测试**：Postman
+ **压测工具**：JMeter

##构建pom文件，添加基本的JAR包依赖

##建立数据库
sql脚本在goodskill/src/main/sql/schema.sql

##修改servlet版本为3.1

##entity实现表和类的映射

##DAO层实现接口

##MyBatis实现SQL
配置参考官方文档：http://www.mybatis.org/mybatis-3/zh/getting-started.html

##Spring整合
配置参考官方文档：https://docs.spring.io/spring/docs/4.1.7.RELEASE/spring-framework-reference/pdf/spring-framework-reference.pdf

