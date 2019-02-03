/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-01-31 17:55:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `thing_id` bigint(20) NOT NULL,
  `leader_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKakp3oh8ul8hjcycd0aq8x9836` (`thing_id`),
  KEY `FK4lrv984nrutn7xe5bwap2p1le` (`leader_id`),
  CONSTRAINT `FK4lrv984nrutn7xe5bwap2p1le` FOREIGN KEY (`leader_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKakp3oh8ul8hjcycd0aq8x9836` FOREIGN KEY (`thing_id`) REFERENCES `thing` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('1', '1', '2', '2');
INSERT INTO `team` VALUES ('2', '1', '1', '2');
INSERT INTO `team` VALUES ('3', '1', '3', '2');
INSERT INTO `team` VALUES ('4', '1', '4', '2');
INSERT INTO `team` VALUES ('5', '1', '1', '1');
INSERT INTO `team` VALUES ('6', '1', '2', '1');
INSERT INTO `team` VALUES ('7', '1', '3', '1');
INSERT INTO `team` VALUES ('8', '1', '4', '1');
