/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : bms_demo

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-05-23 22:31:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型：0-菜单 1-子菜单 2-按钮',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0否，1是)',
  PRIMARY KEY (`id`),
  KEY `menu_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '系统管理', '10000', null, null, '0', 'system', null, '2017-09-20 15:52:54', 'admin', '2017-09-20 15:52:54', null, '0');
INSERT INTO `menu` VALUES ('2', '1', '用户管理', '10100', null, null, '1', 'system:user', null, '2017-09-20 15:55:34', 'admin', '2017-09-20 15:55:34', null, '0');
INSERT INTO `menu` VALUES ('3', '2', '编辑用户', '10101', null, null, '2', 'system:user:edit', null, '2018-05-14 22:45:54', null, '2018-05-14 22:45:54', null, '0');
INSERT INTO `menu` VALUES ('4', '2', '删除用户', '10102', null, null, '2', 'system:user:delete', null, '2018-05-14 22:47:25', null, '2018-05-14 22:47:25', null, '0');
INSERT INTO `menu` VALUES ('5', '2', '分配角色', '10103', null, null, '2', 'system:user:grant', null, '2018-05-14 22:48:04', null, '2018-05-14 22:48:04', null, '0');
INSERT INTO `menu` VALUES ('6', '2', '添加用户', '10104', null, null, '2', 'system:user:add', null, '2018-05-14 22:49:16', null, '2018-05-14 22:49:16', null, '0');
INSERT INTO `menu` VALUES ('7', '1', '角色管理', '10200', null, null, '1', 'system:role', null, '2018-05-19 22:03:19', null, '2018-05-19 22:03:19', null, '0');
INSERT INTO `menu` VALUES ('8', '7', '添加角色', '10201', null, null, '2', 'system:role:add', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('9', '7', '编辑角色', '10202', null, null, '2', 'system:role:edit', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('10', '7', '删除角色', '10203', null, null, '2', 'system:role:delete', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('11', '7', '分配资源', '10204', null, null, '2', 'system:role:grant', null, '2018-05-19 23:08:02', null, '2018-05-19 23:08:02', null, '0');
INSERT INTO `menu` VALUES ('12', '1', '资源管理', '10300', null, null, '1', 'system:menu', null, '2018-05-20 22:16:39', null, '2018-05-20 22:16:39', null, '0');
INSERT INTO `menu` VALUES ('13', '12', '添加资源', '10301', null, null, '2', 'system:menu:add', null, '2018-05-20 22:17:11', null, '2018-05-20 22:17:11', null, '0');
INSERT INTO `menu` VALUES ('14', '12', '编辑资源', '10302', null, null, '2', 'system:menu:edit', null, '2018-05-20 22:17:47', null, '2018-05-20 22:17:47', null, '0');
INSERT INTO `menu` VALUES ('15', '12', '删除资源', '10303', null, null, '2', 'system:menu:delete', null, '2018-05-20 22:18:16', null, '2018-05-20 22:18:16', null, '0');
INSERT INTO `menu` VALUES ('48', null, 'test3', '30101', '', null, '1', 'system:test3', null, '2017-11-06 20:39:01', null, '2017-11-06 20:39:01', '', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `useable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `role_del_flag` (`del_flag`),
  KEY `role_en_name` (`enname`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', 'admin', '1', null, '2017-08-25 11:26:36', null, '2017-08-25 11:26:36', null, '0');
INSERT INTO `role` VALUES ('2', '运营test', 'operator', '1', null, '2017-08-25 11:26:36', null, '2017-08-25 11:26:36', null, '0');
INSERT INTO `role` VALUES ('3', '人力', 'hr', '1', null, '2017-08-25 11:26:36', null, '2017-08-25 11:26:36', null, '0');
INSERT INTO `role` VALUES ('4', '产品', 'product', '1', null, '2017-08-25 11:26:37', null, '2017-08-25 11:26:37', null, '0');
INSERT INTO `role` VALUES ('5', '财务', 'finance', '1', null, '2017-08-25 11:26:37', null, '2017-08-25 11:26:37', null, '0');
INSERT INTO `role` VALUES ('6', '超级管理员', 'superadmin', '1', null, '2017-09-26 12:46:12', null, '2017-09-26 12:46:12', null, '0');
INSERT INTO `role` VALUES ('7', 'test3', null, '1', null, '2018-05-20 14:15:34', null, '2018-05-20 14:15:34', '吃了2', '0');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '6');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '15');
INSERT INTO `role_menu` VALUES ('1', '48');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `photo` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记(0否，1是)',
  PRIMARY KEY (`id`),
  KEY `idx_user_login_name` (`login_name`),
  KEY `idx_user_update_date` (`update_date`),
  KEY `idx_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '管理员', null, null, null, null, null, null, null, '2017-09-21 10:08:53', null, '2017-09-21 10:08:53', null, '0');
INSERT INTO `user` VALUES ('2', 'superadmin', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '超级管理员', null, null, null, null, null, null, null, '2017-09-21 10:08:53', null, '2017-09-21 10:08:53', null, '0');
INSERT INTO `user` VALUES ('3', 'test2', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '测试2', 'test@mail.cn', '13100131000', null, null, null, null, null, '2017-11-05 23:14:22', null, '2017-11-05 23:14:22', null, '0');
INSERT INTO `user` VALUES ('4', 'test1', 'f4cc399f0effd13c888e310ea2cf5399', '1', null, 'test1', 'tw@mail.com', '13800138000', null, null, null, null, null, '2018-05-26 00:00:45', null, '2018-05-16 23:27:53', '123eee', '0');
INSERT INTO `user` VALUES ('5', 'test1', 'e358efa489f58062f10dd7316b65649e', '1', null, 'test', '123@main.com', '123', null, null, null, null, null, '2018-05-19 20:13:03', null, '2018-05-19 20:13:19', '123', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '6');
INSERT INTO `user_role` VALUES ('3', '1');
INSERT INTO `user_role` VALUES ('3', '2');
INSERT INTO `user_role` VALUES ('4', '1');
INSERT INTO `user_role` VALUES ('4', '7');
