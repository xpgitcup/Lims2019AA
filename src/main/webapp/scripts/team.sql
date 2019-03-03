/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-03 14:48:35
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('3', '3', '10', '2');
INSERT INTO `team` VALUES ('4', '4', '11', '2');
INSERT INTO `team` VALUES ('5', '3', '7', '1');
INSERT INTO `team` VALUES ('6', '8', '8', '1');
INSERT INTO `team` VALUES ('7', '5', '9', '1');
INSERT INTO `team` VALUES ('8', '2', '12', '2');
INSERT INTO `team` VALUES ('9', '1', '13', '2');
INSERT INTO `team` VALUES ('10', '1', '14', '2');
INSERT INTO `team` VALUES ('11', '1', '15', '2');
INSERT INTO `team` VALUES ('12', '1', '16', '2');
INSERT INTO `team` VALUES ('13', '1', '17', '2');
INSERT INTO `team` VALUES ('14', '1', '18', '2');
INSERT INTO `team` VALUES ('15', '1', '19', '2');
