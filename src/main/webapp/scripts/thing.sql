/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-02 17:11:44
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
  `teacher_id` bigint(20) DEFAULT NULL,
  `school_year` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `companya` varchar(255) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9iletimkbhifny4mo0rgy1of0` (`name`),
  KEY `FK3jv5lg4546jticrnr46rl9i6f` (`thing_type_id`),
  KEY `FK5odgcdreoq3kaqqlaypyx7ku6` (`teacher_id`),
  CONSTRAINT `FK3jv5lg4546jticrnr46rl9i6f` FOREIGN KEY (`thing_type_id`) REFERENCES `thing_type` (`id`),
  CONSTRAINT `FK5odgcdreoq3kaqqlaypyx7ku6` FOREIGN KEY (`teacher_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thing
-- ----------------------------
INSERT INTO `thing` VALUES ('1', '0', '本科毕设.2018-2019-2', '16', 'cn.edu.cup.lims.Course', '2', '2018-2019-2', null, null, null);
INSERT INTO `thing` VALUES ('2', '0', '硕士论文.2018-2019-2', '17', 'cn.edu.cup.lims.Course', '2', '2018-2019-2', null, null, null);
INSERT INTO `thing` VALUES ('4', '0', '软件概论.2018-2019-2', '11', 'cn.edu.cup.lims.Course', '2', '2018-2019-2', null, null, null);
INSERT INTO `thing` VALUES ('5', '0', '油气管道SCADA系统与过程控制.2018-2019-2', '12', 'cn.edu.cup.lims.Course', '2', '2018-2019-2', null, null, null);
INSERT INTO `thing` VALUES ('6', '0', '成品油管道设计.2018-2019-2', '13', 'cn.edu.cup.lims.Course', '2', '2018-2019-2', null, null, null);
INSERT INTO `thing` VALUES ('7', '1', '多气合采全开发周期集输及处理工艺', '4', 'cn.edu.cup.lims.Project', null, null, '2016-01-01 00:00:00', '中联煤层气有限责任公司', '5');
INSERT INTO `thing` VALUES ('8', '0', '华南销售项目', '7', 'cn.edu.cup.lims.Project', null, null, '2017-01-01 00:00:00', '中石化销售华南', '2');
INSERT INTO `thing` VALUES ('9', '0', '冲蚀项目', '7', 'cn.edu.cup.lims.Project', null, null, '2018-01-01 00:00:00', '中石油西南分公司', '1');
INSERT INTO `thing` VALUES ('10', '0', '单管水力模拟', '7', 'cn.edu.cup.lims.Project', null, null, '2018-01-01 00:00:00', '华北煤层气', '1');
INSERT INTO `thing` VALUES ('11', '0', '管网水力模拟', '7', 'cn.edu.cup.lims.Project', null, null, null, '华北煤层气', '1');
INSERT INTO `thing` VALUES ('12', '0', '集输管网积液', '7', 'cn.edu.cup.lims.Project', null, null, null, '华北煤层气', '0');
