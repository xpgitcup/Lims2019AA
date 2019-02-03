/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-01-31 17:55:33
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
  `discription` varchar(255) DEFAULT NULL,
  `hql` varchar(255) DEFAULT NULL,
  `issql` bit(1) DEFAULT NULL,
  `key_string` varchar(255) NOT NULL,
  `view_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iejb2adhrl11w1nanxv8r9hql` (`key_string`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of query_statement
-- ----------------------------
INSERT INTO `query_statement` VALUES ('1', '1', null, '统计查询语句的数量', 'from QueryStatement queryStatement', '\0', 'list.operation4QueryStatement.查询配置', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('2', '1', null, '列表显示当前查询语句', 'select count(*) from QueryStatement queryStatement', '\0', 'count.operation4QueryStatement.查询配置', null);
INSERT INTO `query_statement` VALUES ('3', '0', null, null, 'from Teacher teacher', '\0', 'list.operation4Person.教师', 'listTeacher');
INSERT INTO `query_statement` VALUES ('4', '0', null, null, 'select count(*) from Teacher teacher', '\0', 'count.operation4Person.教师', null);
INSERT INTO `query_statement` VALUES ('5', '0', null, null, 'from Project project', '\0', 'list.operation4Thing.科研', 'listProject');
INSERT INTO `query_statement` VALUES ('6', '0', null, null, 'select count(*) from Project project', '\0', 'count.operation4Thing.科研', null);
INSERT INTO `query_statement` VALUES ('7', '0', null, null, 'from Course course', '\0', 'list.operation4Thing.教学', 'listCourse');
INSERT INTO `query_statement` VALUES ('8', '0', null, null, 'select count(*) from Course course', '\0', 'count.operation4Thing.教学', null);
INSERT INTO `query_statement` VALUES ('9', '0', null, null, 'from Student student', '\0', 'list.operation4Person.学生', 'listStudent');
INSERT INTO `query_statement` VALUES ('10', '0', null, null, 'select count(*) from Student student', '\0', 'count.operation4Person.学生', null);
INSERT INTO `query_statement` VALUES ('11', '0', null, null, 'from Major major', '\0', 'list.operation4Person.专业', 'listMajor');
INSERT INTO `query_statement` VALUES ('12', '0', null, null, 'select count(*) from Major major', '\0', 'count.operation4Person.专业', null);
INSERT INTO `query_statement` VALUES ('13', '0', 'list', '根据项目类型确定可选任务的列表', 'from Thing thing where thing.thingType in :list', '\0', 'list.operation4TeamManageA.可选题目.list', 'listThing');
INSERT INTO `query_statement` VALUES ('14', '0', 'list', '根据项目类型统计可选任务的数量', 'select count(*) from Thing thing where thing.thingType in :list', '\0', 'count.operation4TeamManageA.可选题目.list', null);
INSERT INTO `query_statement` VALUES ('15', '0', 'currentThing', null, 'select count(*) from Team team where team.thing=:currentThing', '\0', 'count.operation4TeamManageA.currentThing.相关团队.list', null);
INSERT INTO `query_statement` VALUES ('16', '0', 'currentThing', null, 'from Team team where team.thing=:currentThing', '\0', 'list.operation4TeamManageA.currentThing.相关团队.list', 'listTeam');
INSERT INTO `query_statement` VALUES ('17', '0', 'currentTeam', null, 'from Team team where team=:currentTeam', '\0', 'list.operation4TeamManageA.currentTeam.相关队员.list', 'listMember');
INSERT INTO `query_statement` VALUES ('18', '0', 'currentTeam', null, 'select count(*) from Team  team where team=:currentTeam', '\0', 'count.operation4TeamManageA.currentTeam.相关队员.list', null);
INSERT INTO `query_statement` VALUES ('19', '0', 'myself', '统计我领导的团队数量--当前用户', 'select count(*) from Team team where team.leader=:myself', '\0', 'count.operation4Progress.我领导的', null);
INSERT INTO `query_statement` VALUES ('20', '0', 'myself', '统计我参与的团队，就要用到SQL语句了, 应该用ID', 'select count(*) from team_person where person=:myself', '\0', 'count.operation4Progress.我参与的', null);
INSERT INTO `query_statement` VALUES ('21', '0', 'currentTeam', '统计当前团队的进度情况，参数是当前团队', 'select count(*) from Progress progress where progress.team=:currentTeam', '\0', 'count.operation4Progress.进度查看', null);
INSERT INTO `query_statement` VALUES ('22', '0', 'currentProgress', '统计当前反馈信息，参数是当前进度', 'select count(*) from Evaluate evaluate where evaluate.progress=:currentProgress', '\0', 'count.operation4Progress.反馈信息', null);
INSERT INTO `query_statement` VALUES ('23', '0', 'myself', '显示我领导的团队', 'from Team team where team.leader=:myself', '\0', 'list.operation4Progress.我领导的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('24', '0', 'myself', '统计我领导的团队，参数是当前用户--自己', 'select count(*) from Team team where team.leader=:myself', '\0', 'count.operation4Progress.我领导的.myself', null);
INSERT INTO `query_statement` VALUES ('25', '2', 'myself', '显示我参与的团队', 'SELECT  team_person.team_members_id FROM team_person WHERE team_person.person_id =:myself', '', 'list.operation4Progress.我参与的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('26', '1', 'myself', '统计我参与的团队', 'select count(*) from team_person where person_id=:myself', '', 'count.operation4Progress.我参与的.myself', null);
INSERT INTO `query_statement` VALUES ('27', '1', 'myself', '显示我参与的团队', 'from Team team where \'in\'(:myself, team.members)', '', 'list.operation4Progress.我参与的', 'listTeam');
