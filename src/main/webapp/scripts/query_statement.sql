/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-02-14 15:46:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `query_statement`
-- ----------------------------
DROP TABLE IF EXISTS `query_statement`;
CREATE TABLE `query_statement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `params_list` varchar(255) DEFAULT NULL,
  `hql` varchar(255) DEFAULT NULL,
  `ref_count` int(11) NOT NULL,
  `issql` bit(1) DEFAULT NULL,
  `key_string` varchar(255) NOT NULL,
  `view_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iejb2adhrl11w1nanxv8r9hql` (`key_string`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of query_statement
-- ----------------------------
INSERT INTO `query_statement` VALUES ('9', '0', null, 'select count(*) from QueryStatement queryStatement', '0', '\0', 'count.operation4QueryStatement.查询配置', null);
INSERT INTO `query_statement` VALUES ('10', '0', null, 'from QueryStatement queryStatement order by keyString', '0', '\0', 'list.operation4QueryStatement.查询配置', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('11', '0', null, 'select count(*) from SystemAttribute systemAttribute where upAttribute is null', '0', '\0', 'count.operation4SystemAttribute.系统属性', null);
INSERT INTO `query_statement` VALUES ('12', '0', null, 'from SystemAttribute systemAttribute where upAttribute is null', '0', '\0', 'list.operation4SystemAttribute.系统属性', 'listSystemAttribute');
INSERT INTO `query_statement` VALUES ('13', '0', null, 'select count(*) from SystemUser systemUser', '0', '\0', 'count.operation4SystemUser.系统用户', null);
INSERT INTO `query_statement` VALUES ('14', '0', null, 'from SystemUser systemUser', '0', '\0', 'list.operation4SystemUser.系统用户', 'listSystemUser');
INSERT INTO `query_statement` VALUES ('15', '0', null, 'select count(*) from SystemMenu systemMenu where upMenuItem is null', '0', '\0', 'count.operation4SystemMenu.系统菜单', null);
INSERT INTO `query_statement` VALUES ('16', '0', null, 'from SystemMenu systemMenu where upMenuItem is null', '0', '\0', 'list.operation4SystemMenu.系统菜单', 'listSystemMenu');
INSERT INTO `query_statement` VALUES ('17', '0', null, 'select count(*) from SystemLog systemLog', '0', '\0', 'count.operation4SystemLog.系统日志', null);
INSERT INTO `query_statement` VALUES ('18', '0', null, 'from SystemLog systemLog', '0', '\0', 'list.operation4SystemLog.系统日志', 'listSystemLog');
INSERT INTO `query_statement` VALUES ('19', '1', null, 'from Teacher teacher', '0', '\0', 'list.operation4Person.教师', 'listTeacher');
INSERT INTO `query_statement` VALUES ('20', '1', null, 'from Student student order by major', '0', '\0', 'list.operation4Person.学生', 'listStudent');
INSERT INTO `query_statement` VALUES ('21', '2', null, 'from Major major order by name', '0', '\0', 'list.operation4Person.专业', 'listMajor');
INSERT INTO `query_statement` VALUES ('22', '1', null, 'select count(*) from Teacher teacher', '0', '\0', 'count.operation4Person.教师', null);
INSERT INTO `query_statement` VALUES ('23', '1', null, 'select count(*) from Student student', '0', '\0', 'count.operation4Person.学生', null);
INSERT INTO `query_statement` VALUES ('24', '1', null, 'select count(*) from Major major', '0', '\0', 'count.operation4Person.专业', null);
INSERT INTO `query_statement` VALUES ('25', '2', null, 'select count(*) from ThingType thingType where upType is null', '0', '\0', 'count.operation4ThingType.项目类型', null);
INSERT INTO `query_statement` VALUES ('26', '1', null, 'select count(*) from PersonTitle personTitle where upTitle is null', '0', '\0', 'count.operation4PersonTitle.人员类型', null);
