/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-02-15 09:19:05
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of query_statement
-- ----------------------------
INSERT INTO `query_statement` VALUES ('29', '0', null, 'select count(*) from QueryStatement queryStatement', '0', '\0', 'count.operation4QueryStatement.查询配置', null);
INSERT INTO `query_statement` VALUES ('30', '0', null, 'from QueryStatement queryStatement order by keyString', '0', '\0', 'list.operation4QueryStatement.查询配置', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('31', '0', null, 'select count(*) from SystemAttribute systemAttribute where upAttribute is null', '0', '\0', 'count.operation4SystemAttribute.系统属性', null);
INSERT INTO `query_statement` VALUES ('32', '0', null, 'from SystemAttribute systemAttribute where upAttribute is null', '0', '\0', 'list.operation4SystemAttribute.系统属性', 'listSystemAttribute');
INSERT INTO `query_statement` VALUES ('33', '0', null, 'select count(*) from SystemUser systemUser', '0', '\0', 'count.operation4SystemUser.系统用户', null);
INSERT INTO `query_statement` VALUES ('34', '0', null, 'from SystemUser systemUser', '0', '\0', 'list.operation4SystemUser.系统用户', 'listSystemUser');
INSERT INTO `query_statement` VALUES ('35', '0', null, 'select count(*) from SystemMenu systemMenu where upMenuItem is null', '0', '\0', 'count.operation4SystemMenu.系统菜单', null);
INSERT INTO `query_statement` VALUES ('36', '0', null, 'from SystemMenu systemMenu where upMenuItem is null', '0', '\0', 'list.operation4SystemMenu.系统菜单', 'listSystemMenu');
INSERT INTO `query_statement` VALUES ('37', '0', null, 'select count(*) from SystemLog systemLog', '0', '\0', 'count.operation4SystemLog.系统日志', null);
INSERT INTO `query_statement` VALUES ('38', '0', null, 'from SystemLog systemLog', '0', '\0', 'list.operation4SystemLog.系统日志', 'listSystemLog');
INSERT INTO `query_statement` VALUES ('39', '0', null, 'from Teacher teacher', '0', '\0', 'list.operation4Person.教师', 'listTeacher');
INSERT INTO `query_statement` VALUES ('40', '0', null, 'from Student student order by major', '0', '\0', 'list.operation4Person.学生', 'listStudent');
INSERT INTO `query_statement` VALUES ('41', '0', null, 'from Major major order by name', '0', '\0', 'list.operation4Person.专业', 'listMajor');
INSERT INTO `query_statement` VALUES ('42', '0', null, 'select count(*) from Teacher teacher', '0', '\0', 'count.operation4Person.教师', null);
INSERT INTO `query_statement` VALUES ('43', '0', null, 'select count(*) from Student student', '0', '\0', 'count.operation4Person.学生', null);
INSERT INTO `query_statement` VALUES ('44', '0', null, 'select count(*) from Major major', '0', '\0', 'count.operation4Person.专业', null);
INSERT INTO `query_statement` VALUES ('45', '0', null, 'select count(*) from ThingType thingType where upType is null', '0', '\0', 'count.operation4ThingType.项目类型', null);
INSERT INTO `query_statement` VALUES ('46', '0', null, 'select count(*) from PersonTitle personTitle where upTitle is null', '0', '\0', 'count.operation4PersonTitle.人员类型', null);
INSERT INTO `query_statement` VALUES ('47', '0', null, 'from Project project order by name', '0', '\0', 'list.operation4Thing.科研', 'listProject');
INSERT INTO `query_statement` VALUES ('48', '0', null, 'select count(*) from Project project', '0', '\0', 'count.operation4Thing.科研', null);
INSERT INTO `query_statement` VALUES ('49', '0', null, 'from Course course order by name', '0', '\0', 'list.operation4Thing.教学', 'listCourse');
INSERT INTO `query_statement` VALUES ('50', '0', null, 'select count(*) from Course course', '0', '\0', 'count.operation4Thing.教学', null);
