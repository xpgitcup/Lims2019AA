/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-02-24 15:53:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `thing_type_circle`
-- ----------------------------
DROP TABLE IF EXISTS `thing_type_circle`;
CREATE TABLE `thing_type_circle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `person_title_id` bigint(20) NOT NULL,
  `thing_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1dbela98vlo3e0iyjuamvulvj` (`person_title_id`),
  KEY `FKssm8med14m6dsxrdrx28o1qfo` (`thing_type_id`),
  CONSTRAINT `FK1dbela98vlo3e0iyjuamvulvj` FOREIGN KEY (`person_title_id`) REFERENCES `person_title` (`id`),
  CONSTRAINT `FKssm8med14m6dsxrdrx28o1qfo` FOREIGN KEY (`thing_type_id`) REFERENCES `thing_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thing_type_circle
-- ----------------------------
INSERT INTO `thing_type_circle` VALUES ('1', '0', '2', '2');
INSERT INTO `thing_type_circle` VALUES ('2', '0', '14', '2');
INSERT INTO `thing_type_circle` VALUES ('3', '0', '15', '11');
INSERT INTO `thing_type_circle` VALUES ('4', '0', '2', '11');
INSERT INTO `thing_type_circle` VALUES ('9', '0', '2', '12');
INSERT INTO `thing_type_circle` VALUES ('10', '0', '2', '14');
INSERT INTO `thing_type_circle` VALUES ('11', '0', '17', '12');
INSERT INTO `thing_type_circle` VALUES ('12', '0', '17', '14');
