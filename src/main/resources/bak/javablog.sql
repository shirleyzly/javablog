/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : javablog

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-10-26 16:16:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(500) NOT NULL DEFAULT '' COMMENT '标题',
  `pic` varchar(200) NOT NULL DEFAULT '' COMMENT '封面图',
  `description` varchar(300) NOT NULL DEFAULT '' COMMENT '描述',
  `content` text NOT NULL COMMENT '内容',
  `type` tinyint(4) unsigned NOT NULL DEFAULT '2' COMMENT '类型1:php 2:java',
  `grade` tinyint(4) NOT NULL DEFAULT '0' COMMENT '等级（0编程基础 1编程进阶）',
  `click` bigint(20) NOT NULL DEFAULT '0' COMMENT '点击数量',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '为什么要用Redis', 'http://localhost:8088/images/java.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', '最近阅读了《Redis开发与运维》，非常不错。\n\n这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。\n\n按照五点把书中的内容进行一下整理：\n\n为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；\n\nRedis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；\n\nRedis的高级功能：包括持久化、复制、哨兵、集群介绍；\n\n理解Redis：理解内存、阻塞；这部分是非常重要的，前面介绍的都可以成为术，这里应该属于道的部分；\n\n开发技巧：主要是一些开发实战的总结，包括缓存设计与常见坑点。\n\n先来开启第一部分的内容，对Redis来一次重新打量。\n\n本系列内容基于：redis-3.2.12\n\nRedis不是万金油\n\n在面试的时候，常被问比较下Redis与Memcache的优缺点，个人觉得这二者并不适合一起比较，一个是非关系型数据库不仅可以做缓存还能干其它事情，一个是仅用做缓存。常常让我们对这二者进行比较，主要也是由于Redis最广泛的应用场景就是Cache。那么Redis到底能干什么？又不能干什么呢？\n\nRedis都可以干什么事儿\n\n缓存，毫无疑问这是Redis当今最为人熟知的使用场景。再提升服务器性能方面非常有效；\n\n排行榜，如果使用传统的关系型数据库来做这个事儿，非常的麻烦，而利用Redis的SortSet数据结构能够非常方便搞定；\n\n计算器/限速器，利用Redis中原子性的自增操作，我们可以统计类似用户点赞数、用户访问数等，这类操作如果用MySQL，频繁的读写会带来相当大的压力；限速器比较典型的使用场景是限制某个用户访问某个API的频率，常用的有抢购时，防止用户疯狂点击带来不必要的压力；\n\n好友关系，利用集合的一些命令，比如求交集、并集、差集等。可以方便搞定一些共同好友、共同爱好之类的功能；\n\n简单消息队列，除了Redis自身的发布/订阅模式，我们也可以利用List来实现一个队列机制，比如：到货通知、邮件发送之类的需求，不需要高可靠，但是会带来非常大的DB压力，完全可以用List来完成异步解耦；\n\nSession共享，以PHP为例，默认Session是保存在服务器的文件中，如果是集群服务，同一个用户过来可能落在不同机器上，这就会导致用户频繁登陆；采用Redis保存Session后，无论用户落在那台机器上都能够获取到对应的Session信息。\n\nRedis不能干什么事儿\n\nRedis感觉能干的事情特别多，但它不是万能的，合适的地方用它事半功倍。如果滥用可能导致系统的不稳定、成本增高等问题。\n\n比如，用Redis去保存用户的基本信息，虽然它能够支持持久化，但是它的持久化方案并不能保证数据绝对的落地，并且还可能带来Redis性能下降，因为持久化太过频繁会增大Redis服务的压力。\n\n简单总结就是数据量太大、数据访问频率非常低的业务都不适合使用Redis，数据太大会增加成本，访问频率太低，保存在内存中纯属浪费资源。\n\n\n选择总需要找个理由\n\n上面说了Redis的一些使用场景，那么这些场景的解决方案也有很多其它选择，比如缓存可以用Memcache，Session共享还能用MySql来实现，消息队列可以用RabbitMQ，我们为什么一定要用Redis呢？\n\n速度快，完全基于内存，使用C语言实现，网络层使用epoll解决高并发问题，单线程模型避免了不必要的上下文切换及竞争条件； 注意：单线程仅仅是说在网络请求这一模块上用一个请求处理客户端的请求，像持久化它就会重开一个线程/进程去进行处理\n\n丰富的数据类型，Redis有8种数据类型，当然常用的主要是 String、Hash、List、Set、 SortSet 这5种类型，他们都是基于键值的方式组织数据。每一种数据类型提供了非常丰富的操作命令，可以满足绝大部分需求，如果有特殊需求还能自己通过 lua 脚本自己创建新的命令（具备原子性）；\n\n\n除了提供的丰富的数据类型，Redis还提供了像慢查询分析、性能测试、Pipeline、事务、Lua自定义命令、Bitmaps、HyperLogLog、发布/订阅、Geo等个性化功能。\n\nRedis的代码开源在GitHub，代码非常简单优雅，任何人都能够吃透它的源码；它的编译安装也是非常的简单，没有任何的系统依赖；有非常活跃的社区，各种客户端的语言支持也是非常完善。另外它还支持事务（没用过）、持久化、主从复制让高可用、分布式成为可能。\n\n\n做为一个开发者，对于我们使用的东西不能让它成为一个黑盒子，我们应该深入进去，对它更了解、更熟悉。今天简单说了下Redis的使用场景，以及为什么选择了Redis而不是其它。\n\n欢迎工作一到五年的Java工程师朋友们加入Java架构开发：860113481\n\n群内提供免费的Java架构学习资料（里面有高可用、高并发、高性能及分布式、Jvm性能调优、Spring源码，MyBatis，Netty,Redis,Kafka,Mysql,Zookeeper,Tomcat,Docker,Dubbo,Nginx等多个知识点的架构资料）合理利用自己每一分每一秒的时间来学习提升自己，不要再用\"没有时间“来掩饰自己思想上的懒惰！趁年轻，使劲拼，给未来的自己一个交代！\n\n作者：Java填坑之路\n链接：https://www.jianshu.com/p/e2c9b5758312\n來源：简书\n简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。', '2', '0', '10', '0', '2018-10-15 11:42:38', '2018-10-26 15:12:51');
INSERT INTO `article` VALUES ('2', 'SpringBoot2.X 工具篇规划', 'http://localhost:8088/images/excerpt.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'SpringBoot2.X 工具篇规划', '2', '0', '11', '0', '2018-10-19 15:38:11', '2018-10-26 15:12:52');
INSERT INTO `article` VALUES ('3', 'test1', 'http://localhost:8088/images/code.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'testsf', '2', '0', '1', '0', '2018-10-23 16:33:29', '2018-10-26 15:12:45');
INSERT INTO `article` VALUES ('4', 'test2', 'http://localhost:8088/images/excerpt.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'tesfsfsfddsf', '2', '0', '1', '0', '2018-10-23 16:33:36', '2018-10-26 15:12:46');
INSERT INTO `article` VALUES ('5', 'test3', 'http://localhost:8088/images/java.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsdfsfddsdf', '2', '0', '1', '0', '2018-10-23 16:33:41', '2018-10-26 15:12:48');
INSERT INTO `article` VALUES ('6', 'test4', 'http://localhost:8088/images/code.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsfsf', '2', '0', '0', '0', '2018-10-23 16:33:49', '2018-10-25 17:08:51');
INSERT INTO `article` VALUES ('7', 'test5', 'http://localhost:8088/images/java.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsfsfd', '2', '0', '0', '0', '2018-10-23 16:33:54', '2018-10-25 17:08:52');
INSERT INTO `article` VALUES ('8', 'test6', 'http://localhost:8088/images/excerpt.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sfdsfdsdf', '2', '0', '0', '0', '2018-10-23 16:34:02', '2018-10-25 17:08:52');
INSERT INTO `article` VALUES ('9', 'test7', 'http://localhost:8088/images/code.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsfsf', '2', '0', '0', '0', '2018-10-23 16:56:17', '2018-10-25 17:08:53');
INSERT INTO `article` VALUES ('10', 'test8', 'http://localhost:8088/images/java.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfdsdfs', '2', '0', '0', '0', '2018-10-23 16:56:24', '2018-10-25 17:08:53');
INSERT INTO `article` VALUES ('11', 'test9', 'http://localhost:8088/images/excerpt.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsdfsf', '2', '1', '0', '0', '2018-10-23 16:56:30', '2018-10-26 15:06:06');
INSERT INTO `article` VALUES ('12', 'test10', 'http://localhost:8088/images/code.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', '12313213', '2', '1', '0', '0', '2018-10-23 16:56:35', '2018-10-26 15:06:05');
INSERT INTO `article` VALUES ('13', 'test11', 'http://localhost:8088/images/java.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsfsf', '2', '1', '0', '0', '2018-10-23 16:56:41', '2018-10-26 15:06:04');
INSERT INTO `article` VALUES ('14', 'test12', 'http://localhost:8088/images/excerpt.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsffd', '2', '1', '0', '0', '2018-10-23 16:56:45', '2018-10-26 15:06:03');
INSERT INTO `article` VALUES ('15', 'test13', 'http://localhost:8088/images/code.jpg', '最近阅读了《Redis开发与运维》，非常不错。这里对书中的知识整理一下，方便自己回顾一下Redis的整个体系，来对相关知识点查漏补缺。为什么要选择Redis：介绍Redis的使用场景与使用Redis的原因；Redis常用命令总结：包括时间复杂度总结与具体数据类型在Redis内部使用的数据结构；', 'sdfsfsf', '2', '1', '0', '0', '2018-10-23 16:56:55', '2018-10-26 15:06:02');

-- ----------------------------
-- Table structure for `diary`
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(500) NOT NULL DEFAULT '' COMMENT '标题',
  `pic` varchar(200) NOT NULL DEFAULT '' COMMENT '封面图',
  `description` varchar(300) NOT NULL DEFAULT '' COMMENT '描述',
  `content` text NOT NULL COMMENT '内容',
  `type` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '类型0：学习相关 1：工作相关 2:生活相关 3：业余兴趣',
  `click` bigint(20) NOT NULL DEFAULT '0' COMMENT '点击数量',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='个人写作（日记)';

-- ----------------------------
-- Records of diary
-- ----------------------------
INSERT INTO `diary` VALUES ('1', 'diary1', 'http://localhost:8088/images/excerpt.jpg', 'sfsdfsdfsdfsfdsfd,sdfsfsfdsfd,是对方是否但是地方。sfsfsfdsfsfs', 'sdfsdfsdf', '2', '1', '0', '2018-10-26 15:43:47', '2018-10-26 15:56:18');
INSERT INTO `diary` VALUES ('2', 'diary2', 'http://localhost:8088/images/java.jpg', 'sfsfdsfsfsdf', 'sdfdsfsdf', '0', '2', '0', '2018-10-26 15:44:20', '2018-10-26 15:56:19');
INSERT INTO `diary` VALUES ('3', 'diary3', 'http://localhost:8088/images/code.jpg', 's12132132', 'dfgdg123123123', '0', '3', '0', '2018-10-26 15:44:35', '2018-10-26 15:56:21');
INSERT INTO `diary` VALUES ('4', 'diary4', 'http://localhost:8088/images/code.jpg', 'sdfsdf', 'sdfdsf', '0', '0', '0', '2018-10-26 16:10:39', '2018-10-26 16:10:48');
INSERT INTO `diary` VALUES ('5', 'diary5', 'http://localhost:8088/images/excerpt.jpg', 'sdfsf', 'sdfsdf', '0', '0', '0', '2018-10-26 16:10:55', '2018-10-26 16:10:59');

-- ----------------------------
-- Table structure for `english`
-- ----------------------------
DROP TABLE IF EXISTS `english`;
CREATE TABLE `english` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `en` varchar(1024) NOT NULL COMMENT '英文句子',
  `cn` varchar(1024) NOT NULL DEFAULT '' COMMENT '中文翻译',
  `date` date NOT NULL DEFAULT '2018-10-01' COMMENT '日期',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除 1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='每日一句英文';

-- ----------------------------
-- Records of english
-- ----------------------------
INSERT INTO `english` VALUES ('1', 'test', '测试', '2018-10-01', '0', '2018-10-19 16:47:51', '2018-10-19 16:47:51');
INSERT INTO `english` VALUES ('2', 'sdfsf', 'sdfsfsf', '2018-10-01', '0', '2018-10-24 12:57:29', '2018-10-24 12:57:29');
INSERT INTO `english` VALUES ('3', 'sdfsf', 'sdfsf', '2018-10-01', '0', '2018-10-24 12:57:32', '2018-10-24 12:57:32');
INSERT INTO `english` VALUES ('4', 'sdfsf', 'sdfsf', '2018-10-01', '0', '2018-10-24 12:57:37', '2018-10-24 12:57:37');
INSERT INTO `english` VALUES ('5', 'ssdfsf', 'sdfsdfsdf', '2018-10-01', '0', '2018-10-24 12:57:40', '2018-10-24 12:57:40');
INSERT INTO `english` VALUES ('6', 'sdfsf', 'sdfsfsf', '2018-10-01', '0', '2018-10-24 12:57:43', '2018-10-24 12:57:43');
INSERT INTO `english` VALUES ('7', 'sdfsf', 'sdfsf', '2018-10-01', '0', '2018-10-24 12:57:46', '2018-10-24 12:57:46');
INSERT INTO `english` VALUES ('8', 'sdfsf', 'sdfsf', '2018-10-01', '0', '2018-10-24 12:57:50', '2018-10-24 12:57:50');
INSERT INTO `english` VALUES ('9', 'sdfsf', 'sdfsfsdf', '2018-10-01', '0', '2018-10-24 12:57:55', '2018-10-24 12:57:55');
INSERT INTO `english` VALUES ('10', 'sdfsf', 'sdfsf', '2018-10-01', '0', '2018-10-24 12:57:57', '2018-10-24 12:57:57');
INSERT INTO `english` VALUES ('11', 'sdfsf', 'sdfdsf', '2018-10-01', '0', '2018-10-24 12:57:59', '2018-10-24 12:57:59');
INSERT INTO `english` VALUES ('12', 'sdfsfsdf', 'sdfsf', '2018-10-01', '0', '2018-10-24 12:58:03', '2018-10-24 12:58:03');

-- ----------------------------
-- Table structure for `link`
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(300) NOT NULL DEFAULT '' COMMENT '域名',
  `title` varchar(300) NOT NULL COMMENT '名称',
  `description` varchar(300) NOT NULL COMMENT '描述',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0收藏 1友情',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='链接表';

-- ----------------------------
-- Records of link
-- ----------------------------
INSERT INTO `link` VALUES ('1', 'http://www.baidu.com', '百度', '搜索引擎', '1', '0', '2018-10-24 13:20:43', '2018-10-24 14:01:41');

-- ----------------------------
-- Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(300) NOT NULL COMMENT '公告标题',
  `content` varchar(1024) NOT NULL COMMENT '公告内容',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '今日无公告', '无公告', '0', '2018-10-22 11:06:54', '2018-10-22 11:06:54');
INSERT INTO `notice` VALUES ('2', '明日无公告', '明日也无公告', '0', '2018-10-22 11:07:15', '2018-10-22 11:07:15');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_uuid` varchar(70) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `role` int(10) NOT NULL,
  `image` varchar(255) NOT NULL,
  `last_ip` varchar(255) NOT NULL,
  `last_time` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表(暂时不用)';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'd242ae49-4734-411e-8c8d-d2b09e87c3c8', 'EalenXie', '$2a$04$petEXpgcLKfdLN4TYFxK0u8ryAzmZDHLASWLX/XXm8hgQar1C892W', 'SSSSS', 'ssssssssss', '1', 'g', '0:0:0:0:0:0:0:1', '2018-07-11 11:26:27', '2018-10-15 11:21:05', '2018-10-15 11:21:25');
