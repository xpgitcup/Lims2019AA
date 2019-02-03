/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-01-31 17:56:04
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `team_person`
-- ----------------------------
DROP TABLE IF EXISTS `team_person`;
CREATE TABLE `team_person` (
  `team_members_id` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  KEY `FK849p1qabcqamcqtn57f2h8v8p` (`person_id`),
  KEY `FKt0ne9eoxa1hptfkq4vo8xhd53` (`team_members_id`),
  CONSTRAINT `FK849p1qabcqamcqtn57f2h8v8p` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKt0ne9eoxa1hptfkq4vo8xhd53` FOREIGN KEY (`team_members_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_person
-- ----------------------------
INSERT INTO `team_person` VALUES ('4', '1');
INSERT INTO `team_person` VALUES ('1', '1');
INSERT INTO `team_person` VALUES ('3', '1');
INSERT INTO `team_person` VALUES ('2', '1');
INSERT INTO `team_person` VALUES ('5', '2');
INSERT INTO `team_person` VALUES ('6', '2');
INSERT INTO `team_person` VALUES ('7', '2');
INSERT INTO `team_person` VALUES ('8', '2');
