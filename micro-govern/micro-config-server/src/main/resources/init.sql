

CREATE DATABASE `micro_govern`;

USE `micro_govern`;

CREATE TABLE `micro_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `key1` varchar(100) NOT NULL COMMENT '配置标识',
  `value1` varchar(1000) DEFAULT NULL COMMENT '配置值',
  `application` varchar(50) NOT NULL COMMENT '应用: common special',
  `profile` varchar(50) NOT NULL COMMENT '环境: template',
  `label` varchar(50) DEFAULT NULL COMMENT '分支: master',
  `configType` varchar(255) DEFAULT NULL COMMENT '业务分类: common domain notice ...',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key1` (`key1`) USING BTREE
) ;

insert into `micro_config` (`id`, `key1`, `value1`, `application`, `profile`, `label`, `configType`, `description`) values('1','common.mysql.url','192.168.159.111','common','template','master','common','mysql通用url');
insert into `micro_config` (`id`, `key1`, `value1`, `application`, `profile`, `label`, `configType`, `description`) values('2','common.mysql.username','root','common','template','master','common','mysql通用username');
insert into `micro_config` (`id`, `key1`, `value1`, `application`, `profile`, `label`, `configType`, `description`) values('3','common.mysql.password','123456','common','template','master','common','mysql通用password');