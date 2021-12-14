/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50734
Source Host           : localhost:3306
Source Database       : fd_user

Target Server Type    : MYSQL
Target Server Version : 50734
File Encoding         : 65001

Date: 2021-12-14 22:18:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `sex` char(1) NOT NULL COMMENT '性别',
  `squad` varchar(100) NOT NULL COMMENT '班级',
  `teacher` char(1) NOT NULL COMMENT '1为老师、2为学生',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('24', '邹阳明', '2327972001', '123456', '女', '2135', '1');
INSERT INTO `user` VALUES ('25', '测试', 'admin', 'z20030610', '男', '2135', '0');
