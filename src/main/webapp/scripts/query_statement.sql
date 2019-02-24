/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-02-24 17:08:45
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
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

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
INSERT INTO `query_statement` VALUES ('53', '1', null, 'select count(*) from ThingTypeCircle thingTypeCircle', '0', '\0', 'count.operation4ThingTypeCircle.任务分配', null);
INSERT INTO `query_statement` VALUES ('54', '1', null, 'from ThingTypeCircle thingTypeCircle order by thingType', '0', '\0', 'list.operation4ThingTypeCircle.任务分配', 'listThingTypeCircle');
INSERT INTO `query_statement` VALUES ('64', '1', 'thingTypeList', 'from Thing thing where thing.thingType in :thingTypeList', '0', '\0', 'list.operation4Team.可选题目.thingTypeList', 'listThing');
INSERT INTO `query_statement` VALUES ('65', '1', 'thingTypeList', 'select count(*) from Thing thing where thing.thingType in :thingTypeList', '0', '\0', 'count.operation4Team.可选题目.thingTypeList', null);
INSERT INTO `query_statement` VALUES ('80', '2', null, 'select count(*) from QueryStatement queryStatement where queryStatement.hql is null', '0', '\0', 'count.operation4QueryStatement.filter.查询配置', null);
INSERT INTO `query_statement` VALUES ('81', '2', null, 'from QueryStatement queryStatement where queryStatement.hql is null  order by keyString', '0', '\0', 'list.operation4QueryStatement.filter.查询配置', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('82', '3', 'currentThing', 'from Team team where team.thing=:currentThing', '0', '\0', 'list.operation4Team.currentThing.相关团队', 'listTeam');
INSERT INTO `query_statement` VALUES ('83', '3', 'currentThing', 'select count(*) from Team team where team.thing=:currentThing', '0', '\0', 'count.operation4Team.currentThing.相关团队', null);
INSERT INTO `query_statement` VALUES ('96', '1', 'myself', 'from Team team where team.leader=:myself', '0', '\0', 'list.operation4Progress.我领导的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('97', '2', 'myself', 'select count(*) from Team team where team.leader=:myself', '0', '\0', 'count.operation4Progress.我领导的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('98', '3', 'myself', 'select team_members_id  from team_person where person_id=:myself', '0', '', 'list.operation4Progress.我参与的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('104', '3', 'myself', 'select count(*) from team_person where person_id=:myself', '0', '', 'count.operation4Progress.我参与的.myself', null);
INSERT INTO `query_statement` VALUES ('109', '2', 'currentTeam', 'from Progress progress where progress.team=:currentTeam', '0', '\0', 'list.operation4Progress.currentTeam.进度查看', 'listProgress');
INSERT INTO `query_statement` VALUES ('110', '2', 'currentTeam', 'select count(*) from Progress progress where progress.team=:currentTeam', '0', '\0', 'count.operation4Progress.currentTeam.进度查看', 'listProgress');
INSERT INTO `query_statement` VALUES ('119', '1', 'currentProgress', 'from Evaluate evaluate where evaluate.progress=:currentProgress', '0', '\0', 'list.operation4Progress.currentProgress.反馈信息', 'listEvaluate');
INSERT INTO `query_statement` VALUES ('120', '1', 'currentProgress', 'select count(*) from Evaluate evaluate where evaluate.progress=:currentProgress', '0', '\0', 'count.operation4Progress.currentProgress.反馈信息', null);
INSERT INTO `query_statement` VALUES ('121', '4', null, 'SELECT Count(DISTINCT person.grade_name) FROM person WHERE person.grade_name IS NOT NULL', '0', '', 'count.operation4Person.年级', null);
INSERT INTO `query_statement` VALUES ('122', '5', null, 'SELECT DISTINCT person.grade_name FROM person WHERE person.grade_name IS NOT NULL  LIMIT 0, 10', '0', '', 'list.operation4Person.年级', 'listGradeName');
