-- 数据库初始化脚本
-- mysql 5.7中,默认使用的是严格模式,
-- 这里的日期必须要有时间,所以一定要给出默认值,
-- 要么就修改数据库设置

-- 创建数据库
CREATE DATABASE goodskill;

-- 使用数据库
USE goodskill;

-- 创建秒杀商品表
CREATE TABLE goods(
 `good_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
 `name` varchar (120) NOT NULL COMMENT '商品名称',
 `number` int NOT NULL COMMENT '库存数量',
 `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '秒杀开始时间',
 `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '秒杀结束时间',
 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
 PRIMARY KEY (good_id),
 key index_start_time(start_time),
 key index_end_time(end_time),
 key index_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀商品表';

-- 初始化数据
insert into
  goods(name, number, start_time, end_time)
values
  ('IphoneX', 99, '2019-01-01 00:00:00','2019-01-02 00:00:00'),
  ('OPPO', 99, '2019-01-01 00:00:00','2019-01-02 00:00:00'),
  ('VIVO', 99, '2019-01-01 00:00:00','2019-01-02 00:00:00');

-- 秒杀记录表
CREATE TABLE success_killed(
`good_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '状态标示：-1：无效 0：成功 1：已付款',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
PRIMARY KEY(good_id, user_phone),/* 联合主键*/
key index_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀记录表';



