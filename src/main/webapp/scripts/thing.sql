/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-01-21 19:14:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `thing`
-- ----------------------------
DROP TABLE IF EXISTS `thing`;
CREATE TABLE `thing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `thing_type_id` bigint(20) NOT NULL,
  `class` varchar(255) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `companya` varchar(255) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `school_year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9iletimkbhifny4mo0rgy1of0` (`name`),
  KEY `FK3jv5lg4546jticrnr46rl9i6f` (`thing_type_id`),
  KEY `FK5odgcdreoq3kaqqlaypyx7ku6` (`teacher_id`),
  CONSTRAINT `FK3jv5lg4546jticrnr46rl9i6f` FOREIGN KEY (`thing_type_id`) REFERENCES `thing_type` (`id`),
  CONSTRAINT `FK5odgcdreoq3kaqqlaypyx7ku6` FOREIGN KEY (`teacher_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thing
-- ----------------------------
INSERT INTO `thing` VALUES ('1', '0', '单管水力模拟', '7', 'cn.edu.cup.lims.Project', '2018-10-01 00:00:00', '华北煤层气', '1', null, null);
INSERT INTO `thing` VALUES ('2', '0', '管网水力学模拟', '7', 'cn.edu.cup.lims.Project', '2018-10-01 00:00:00', '华北煤层气', '1', null, null);
INSERT INTO `thing` VALUES ('3', '0', '多气合采', '4', 'cn.edu.cup.lims.Project', '2016-01-01 00:00:00', '中联煤', '5', null, null);
INSERT INTO `thing` VALUES ('4', '1', '软件实训-课程设计', '11', 'cn.edu.cup.lims.Course', null, null, null, '2', '2018-2019-1');
