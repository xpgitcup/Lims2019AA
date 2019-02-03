/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-01-12 18:02:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `system_user`
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_attribute_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `append_attribute` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_204b9ercidw1baj3s3m9lnr33` (`user_name`),
  KEY `FKaiybevf5q198uok4va3p7b6wb` (`role_attribute_id`),
  CONSTRAINT `FKaiybevf5q198uok4va3p7b6wb` FOREIGN KEY (`role_attribute_id`) REFERENCES `system_attribute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', '0', '641d77dd5271fca28764612a028d9c8e', '1', '李晓平', '');
INSERT INTO `system_user` VALUES ('2', '0', 'ee26fc66b1369c7625333bedafbfcaf6', '1', '宫敬', '');
INSERT INTO `system_user` VALUES ('3', '0', 'f4aa0dd960521e045ae2f20621fb4ee9', '1', '吴海浩', '');
INSERT INTO `system_user` VALUES ('4', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2014314014', 'Student=石国赟');
INSERT INTO `system_user` VALUES ('5', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2014314013', 'Student=丁麟');
INSERT INTO `system_user` VALUES ('6', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2014011043', 'Student=冯志强');
INSERT INTO `system_user` VALUES ('7', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010667', 'Student=沈倩');
INSERT INTO `system_user` VALUES ('8', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010743', 'Student=王铁淞');
INSERT INTO `system_user` VALUES ('9', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010854', 'Student=德吉曲珍');
INSERT INTO `system_user` VALUES ('10', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010855', 'Student=胡瑾');
INSERT INTO `system_user` VALUES ('11', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010856', 'Student=贾羽茜');
INSERT INTO `system_user` VALUES ('12', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010857', 'Student=康雅倩');
INSERT INTO `system_user` VALUES ('13', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010858', 'Student=兰敏');
INSERT INTO `system_user` VALUES ('14', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010859', 'Student=李露露');
INSERT INTO `system_user` VALUES ('15', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010860', 'Student=李玥洁');
INSERT INTO `system_user` VALUES ('16', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010861', 'Student=柳佳丽');
INSERT INTO `system_user` VALUES ('17', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010862', 'Student=史安丁');
INSERT INTO `system_user` VALUES ('18', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010863', 'Student=张怡铭');
INSERT INTO `system_user` VALUES ('19', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010864', 'Student=艾斯卡尔·坎买尔');
INSERT INTO `system_user` VALUES ('20', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010865', 'Student=白宗翰');
INSERT INTO `system_user` VALUES ('21', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010866', 'Student=董佳鑫');
INSERT INTO `system_user` VALUES ('22', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010867', 'Student=付顺康');
INSERT INTO `system_user` VALUES ('23', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010868', 'Student=高经华');
INSERT INTO `system_user` VALUES ('24', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010869', 'Student=顾鹏程');
INSERT INTO `system_user` VALUES ('25', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010870', 'Student=贺国晏');
INSERT INTO `system_user` VALUES ('26', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010871', 'Student=侯富恒');
INSERT INTO `system_user` VALUES ('27', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010872', 'Student=姜铖');
INSERT INTO `system_user` VALUES ('28', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010873', 'Student=李致远');
INSERT INTO `system_user` VALUES ('29', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010875', 'Student=刘禹良');
INSERT INTO `system_user` VALUES ('30', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010876', 'Student=骆新斗');
INSERT INTO `system_user` VALUES ('31', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010877', 'Student=马克');
INSERT INTO `system_user` VALUES ('32', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010878', 'Student=马有财');
INSERT INTO `system_user` VALUES ('33', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010879', 'Student=邵文宏');
INSERT INTO `system_user` VALUES ('34', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010880', 'Student=王饕');
INSERT INTO `system_user` VALUES ('35', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010881', 'Student=伍彦松');
INSERT INTO `system_user` VALUES ('36', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010882', 'Student=熊泽华');
INSERT INTO `system_user` VALUES ('37', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010883', 'Student=严腾飞');
INSERT INTO `system_user` VALUES ('38', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010884', 'Student=杨宏慧');
INSERT INTO `system_user` VALUES ('39', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010885', 'Student=殷雄');
INSERT INTO `system_user` VALUES ('40', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010886', 'Student=张猛');
INSERT INTO `system_user` VALUES ('41', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015010887', 'Student=张雄凯');
INSERT INTO `system_user` VALUES ('42', '0', '25d55ad283aa400af464c76d713c07ad', '9', '2015011149', 'Student=张悦');
INSERT INTO `system_user` VALUES ('43', '0', '25d55ad283aa400af464c76d713c07ad', '8', '1552', 'Teacher=宫敬');
INSERT INTO `system_user` VALUES ('44', '0', '25d55ad283aa400af464c76d713c07ad', '8', '2151', 'Teacher=李晓平');
INSERT INTO `system_user` VALUES ('45', '0', '25d55ad283aa400af464c76d713c07ad', '8', '2152', 'Teacher=邓道明');
INSERT INTO `system_user` VALUES ('46', '0', '25d55ad283aa400af464c76d713c07ad', '8', '2333', 'Teacher=左丽丽');
INSERT INTO `system_user` VALUES ('47', '0', '25d55ad283aa400af464c76d713c07ad', '8', '2013880073', 'Teacher=史博会');
INSERT INTO `system_user` VALUES ('48', '0', '25d55ad283aa400af464c76d713c07ad', '8', '2012880012', 'Teacher=温凯');
