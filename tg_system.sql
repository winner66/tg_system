/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : tg_system

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 18/06/2020 13:05:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for backup_info
-- ----------------------------
DROP TABLE IF EXISTS `backup_info`;
CREATE TABLE `backup_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_id` int(11) NULL DEFAULT NULL COMMENT '备份所属实例的id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份方法(物理/逻辑)',
  `size` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份文件大小',
  `strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份策略(实例/单库)',
  `time` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份结束时间',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份所在主机',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在端口',
  `backup_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份文件名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份文件url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据库备份还原记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for client_user
-- ----------------------------
DROP TABLE IF EXISTS `client_user`;
CREATE TABLE `client_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `account` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `status` int(3) NULL DEFAULT NULL COMMENT '状态  1：禁用   0：正常',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `group` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在分组',
  `dept` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `level` int(8) NULL DEFAULT NULL COMMENT '人员等级',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `bank` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人员基本数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of client_user
-- ----------------------------
INSERT INTO `client_user` VALUES (1, '曹晴朗', '123', '143b92d687de1c6f64ef769e7a34a03422d70084bb85ce78daa98323bd511ade', '2Po3uofh7M00QyQctEV9ICQPhRFa8gHKzGG', 1, '2020-06-17 20:12:37', '2020-06-17 20:12:40', '2020-06-17 20:12:42', NULL, '13567543456', NULL, '成都市', 1, '男', '1435567543@', 'qq.comxxxx银行', '1234567890998', '成都市');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key`(`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_auth`;
CREATE TABLE `sys_data_auth`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据权限' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `parent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级部门名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `depart_num` int(11) NULL DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '0', '四川省', 10);
INSERT INTO `sys_dept` VALUES (2, 1, '四川省', '成都市', 1001);
INSERT INTO `sys_dept` VALUES (3, 1, '四川省', '绵阳市', 1002);
INSERT INTO `sys_dept` VALUES (4, 1, '四川省', '自贡市', 1003);
INSERT INTO `sys_dept` VALUES (5, 1, '四川省', '南充市', 1004);
INSERT INTO `sys_dept` VALUES (6, 1, '四川省', '宜宾市', 1005);
INSERT INTO `sys_dept` VALUES (7, 0, '0', '云南省', 20);
INSERT INTO `sys_dept` VALUES (8, 7, '云南省', '昆明市', 2001);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(32) NULL DEFAULT NULL COMMENT '分组',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `dict_type` int(11) NULL DEFAULT NULL COMMENT '分类： 0：分组 1：分组成员',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代码',
  `parent_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '人员权限管理', NULL, NULL, 0, 'fa fa-deviantart', 1);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员管理', 'modules/sys/user.html', NULL, 1, 'fa fa-user', 3);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'modules/sys/role.html', NULL, 1, 'fa fa-user-secret', 2);
INSERT INTO `sys_menu` VALUES (4, 19, '菜单管理', 'modules/sys/menu.html', NULL, 1, 'fa fa-th-list', 0);
INSERT INTO `sys_menu` VALUES (6, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (7, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, 3, '新增', NULL, 'sys:role:save,sys:menu:perms', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, 3, '修改', NULL, 'sys:role:update,sys:menu:perms', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 1, '机构管理', 'modules/sys/dept.html', NULL, 1, 'fa fa-cubes', 0);
INSERT INTO `sys_menu` VALUES (19, 0, '系统设置', '', NULL, 0, 'fa fa-cog', 10);
INSERT INTO `sys_menu` VALUES (20, 0, '主页', 'main.html', NULL, 1, 'fa fa-arrow-circle-o-up', 0);
INSERT INTO `sys_menu` VALUES (21, 0, '特约投稿', NULL, NULL, 0, 'fa fa-tumblr', 3);
INSERT INTO `sys_menu` VALUES (22, 1, '人员管理', 'modules/sys/userManage.html', NULL, 1, 'fa fa-odnoklassniki', 1);
INSERT INTO `sys_menu` VALUES (24, 19, '类别管理', 'modules/sys/sysdict.html', NULL, 1, 'fa fa-book', 1);
INSERT INTO `sys_menu` VALUES (25, 19, '系统日志', 'modules/sys/log.html', '', 1, 'fa fa-file-text-o', 5);
INSERT INTO `sys_menu` VALUES (27, 24, '查看', NULL, 'sys:sysdict:list,sys:sysdict:info,sys:sysdict:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (28, 24, '新增', NULL, 'sys:sysdict:save,sys:sysdict:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (29, 24, '修改', NULL, 'sys:sysdict:update,sys:sysdict:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (30, 24, '删除', NULL, 'sys:sysdict:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (31, 25, '查看', NULL, 'sys:log:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (32, 18, '查看', NULL, 'sys:dept:list,sys:dept:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (33, 18, '新增', NULL, 'sys:dept:save,sys:dept:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (34, 18, '修改', NULL, 'sys:dept:update,sys:dept:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (35, 18, '删除', NULL, 'sys:dept:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (36, 2, '禁用', NULL, 'sys:user:ban', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (37, 2, '激活', NULL, 'sys:user:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (38, 0, '评论投稿', NULL, NULL, 0, 'fa fa-eyedropper', 2);
INSERT INTO `sys_menu` VALUES (39, 22, '查看', NULL, 'sys:clientUser:list,sys:clientUser:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (40, 22, '删除', NULL, 'sys:clientUser:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (41, 22, '禁用', NULL, 'sys:clientUser:ban', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (42, 22, '激活', NULL, 'sys:clientUser:use', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (43, 21, '任务列表', 'modules/business/specialSubmitTask.html', NULL, 1, 'fa fa-file-o', 0);
INSERT INTO `sys_menu` VALUES (44, 38, '任务列表', 'modules/business/commentSubmitTask.html', NULL, 1, 'fa fa-th-list', 0);
INSERT INTO `sys_menu` VALUES (45, 44, '查看', NULL, 'business:commentSubmitTask:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (46, 44, '删除', NULL, 'business:commentSubmitTask:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (49, 21, '推稿统计', 'modules/business/tweetStatist.html', NULL, 1, 'fa fa-bar-chart', 2);
INSERT INTO `sys_menu` VALUES (56, 43, '查看', NULL, 'business:specialSubmitTask:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (60, 38, '评论稿件', 'modules/business/commentSubmitScript.html', NULL, 1, 'fa fa-pencil-square-o', 1);
INSERT INTO `sys_menu` VALUES (61, 60, '添加', NULL, 'business:commentSubmitScript:add', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (75, 43, '删除', NULL, 'business:specialSubmitTask:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (76, 21, '文章稿件', 'modules/business/articleScript.html', NULL, 1, 'fa fa-font', 1);
INSERT INTO `sys_menu` VALUES (77, 76, '查看', NULL, 'business:articleScript:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (80, 76, '删除', NULL, 'business:articleScript:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (81, 60, '删除', NULL, 'business:commentSubmitScript:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (86, 19, '数据库备份还原', 'modules/sys/backup.html', NULL, 1, 'fa fa-database', 2);
INSERT INTO `sys_menu` VALUES (87, 86, '查看', NULL, 'sys:backup:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (88, 86, '备份', NULL, 'sys:backup:export', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (89, 86, '还原', NULL, 'sys:backup:reduction', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (90, 86, '删除', NULL, 'sys:backup:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (99, 38, '稿件统计', 'modules/business/manuscriptStatist.html', NULL, 1, 'fa fa-bar-chart', 2);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件上传' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `subsidiary` bigint(20) NULL DEFAULT NULL COMMENT '所属一级角色，若不选则为0，该值为一级角色id   0：表示一级角色',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '一切权限', NULL, '2019-05-01 16:42:33', 0);
INSERT INTO `sys_role` VALUES (2, '区域管理员', '区域管理者', NULL, '2019-05-01 16:59:54', 0);
INSERT INTO `sys_role` VALUES (10, '普通用户', '普通用户角色', NULL, '2020-06-17 21:56:33', 0);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 305 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与部门对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (281, 1, 1);
INSERT INTO `sys_role_dept` VALUES (282, 1, 2);
INSERT INTO `sys_role_dept` VALUES (283, 1, 3);
INSERT INTO `sys_role_dept` VALUES (284, 1, 4);
INSERT INTO `sys_role_dept` VALUES (285, 1, 5);
INSERT INTO `sys_role_dept` VALUES (286, 1, 6);
INSERT INTO `sys_role_dept` VALUES (287, 1, 7);
INSERT INTO `sys_role_dept` VALUES (288, 1, 8);
INSERT INTO `sys_role_dept` VALUES (289, 2, 1);
INSERT INTO `sys_role_dept` VALUES (290, 2, 2);
INSERT INTO `sys_role_dept` VALUES (291, 2, 3);
INSERT INTO `sys_role_dept` VALUES (292, 2, 4);
INSERT INTO `sys_role_dept` VALUES (293, 2, 5);
INSERT INTO `sys_role_dept` VALUES (294, 2, 6);
INSERT INTO `sys_role_dept` VALUES (295, 2, 7);
INSERT INTO `sys_role_dept` VALUES (296, 2, 8);
INSERT INTO `sys_role_dept` VALUES (297, 10, 1);
INSERT INTO `sys_role_dept` VALUES (298, 10, 2);
INSERT INTO `sys_role_dept` VALUES (299, 10, 3);
INSERT INTO `sys_role_dept` VALUES (300, 10, 4);
INSERT INTO `sys_role_dept` VALUES (301, 10, 5);
INSERT INTO `sys_role_dept` VALUES (302, 10, 6);
INSERT INTO `sys_role_dept` VALUES (303, 10, 7);
INSERT INTO `sys_role_dept` VALUES (304, 10, 8);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1560 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1452, 1, 20);
INSERT INTO `sys_role_menu` VALUES (1453, 1, 1);
INSERT INTO `sys_role_menu` VALUES (1454, 1, 18);
INSERT INTO `sys_role_menu` VALUES (1455, 1, 32);
INSERT INTO `sys_role_menu` VALUES (1456, 1, 33);
INSERT INTO `sys_role_menu` VALUES (1457, 1, 34);
INSERT INTO `sys_role_menu` VALUES (1458, 1, 35);
INSERT INTO `sys_role_menu` VALUES (1459, 1, 22);
INSERT INTO `sys_role_menu` VALUES (1460, 1, 39);
INSERT INTO `sys_role_menu` VALUES (1461, 1, 41);
INSERT INTO `sys_role_menu` VALUES (1462, 1, 42);
INSERT INTO `sys_role_menu` VALUES (1463, 1, 3);
INSERT INTO `sys_role_menu` VALUES (1464, 1, 10);
INSERT INTO `sys_role_menu` VALUES (1465, 1, 11);
INSERT INTO `sys_role_menu` VALUES (1466, 1, 12);
INSERT INTO `sys_role_menu` VALUES (1467, 1, 13);
INSERT INTO `sys_role_menu` VALUES (1468, 1, 2);
INSERT INTO `sys_role_menu` VALUES (1469, 1, 6);
INSERT INTO `sys_role_menu` VALUES (1470, 1, 7);
INSERT INTO `sys_role_menu` VALUES (1471, 1, 8);
INSERT INTO `sys_role_menu` VALUES (1472, 1, 9);
INSERT INTO `sys_role_menu` VALUES (1473, 1, 36);
INSERT INTO `sys_role_menu` VALUES (1474, 1, 37);
INSERT INTO `sys_role_menu` VALUES (1475, 1, 38);
INSERT INTO `sys_role_menu` VALUES (1476, 1, 44);
INSERT INTO `sys_role_menu` VALUES (1477, 1, 45);
INSERT INTO `sys_role_menu` VALUES (1478, 1, 46);
INSERT INTO `sys_role_menu` VALUES (1479, 1, 60);
INSERT INTO `sys_role_menu` VALUES (1480, 1, 61);
INSERT INTO `sys_role_menu` VALUES (1481, 1, 81);
INSERT INTO `sys_role_menu` VALUES (1482, 1, 99);
INSERT INTO `sys_role_menu` VALUES (1483, 1, 21);
INSERT INTO `sys_role_menu` VALUES (1484, 1, 43);
INSERT INTO `sys_role_menu` VALUES (1485, 1, 56);
INSERT INTO `sys_role_menu` VALUES (1486, 1, 75);
INSERT INTO `sys_role_menu` VALUES (1487, 1, 76);
INSERT INTO `sys_role_menu` VALUES (1488, 1, 77);
INSERT INTO `sys_role_menu` VALUES (1489, 1, 80);
INSERT INTO `sys_role_menu` VALUES (1490, 1, 49);
INSERT INTO `sys_role_menu` VALUES (1491, 1, 19);
INSERT INTO `sys_role_menu` VALUES (1492, 1, 4);
INSERT INTO `sys_role_menu` VALUES (1493, 1, 14);
INSERT INTO `sys_role_menu` VALUES (1494, 1, 15);
INSERT INTO `sys_role_menu` VALUES (1495, 1, 16);
INSERT INTO `sys_role_menu` VALUES (1496, 1, 17);
INSERT INTO `sys_role_menu` VALUES (1497, 1, 24);
INSERT INTO `sys_role_menu` VALUES (1498, 1, 27);
INSERT INTO `sys_role_menu` VALUES (1499, 1, 28);
INSERT INTO `sys_role_menu` VALUES (1500, 1, 29);
INSERT INTO `sys_role_menu` VALUES (1501, 1, 30);
INSERT INTO `sys_role_menu` VALUES (1502, 1, 86);
INSERT INTO `sys_role_menu` VALUES (1503, 1, 87);
INSERT INTO `sys_role_menu` VALUES (1504, 1, 88);
INSERT INTO `sys_role_menu` VALUES (1505, 1, 89);
INSERT INTO `sys_role_menu` VALUES (1506, 1, 90);
INSERT INTO `sys_role_menu` VALUES (1507, 1, 25);
INSERT INTO `sys_role_menu` VALUES (1508, 1, 31);
INSERT INTO `sys_role_menu` VALUES (1509, 2, 20);
INSERT INTO `sys_role_menu` VALUES (1510, 2, 1);
INSERT INTO `sys_role_menu` VALUES (1511, 2, 18);
INSERT INTO `sys_role_menu` VALUES (1512, 2, 32);
INSERT INTO `sys_role_menu` VALUES (1513, 2, 33);
INSERT INTO `sys_role_menu` VALUES (1514, 2, 34);
INSERT INTO `sys_role_menu` VALUES (1515, 2, 35);
INSERT INTO `sys_role_menu` VALUES (1516, 2, 22);
INSERT INTO `sys_role_menu` VALUES (1517, 2, 39);
INSERT INTO `sys_role_menu` VALUES (1518, 2, 41);
INSERT INTO `sys_role_menu` VALUES (1519, 2, 42);
INSERT INTO `sys_role_menu` VALUES (1520, 2, 2);
INSERT INTO `sys_role_menu` VALUES (1521, 2, 6);
INSERT INTO `sys_role_menu` VALUES (1522, 2, 7);
INSERT INTO `sys_role_menu` VALUES (1523, 2, 8);
INSERT INTO `sys_role_menu` VALUES (1524, 2, 9);
INSERT INTO `sys_role_menu` VALUES (1525, 2, 36);
INSERT INTO `sys_role_menu` VALUES (1526, 2, 37);
INSERT INTO `sys_role_menu` VALUES (1527, 2, 38);
INSERT INTO `sys_role_menu` VALUES (1528, 2, 44);
INSERT INTO `sys_role_menu` VALUES (1529, 2, 45);
INSERT INTO `sys_role_menu` VALUES (1530, 2, 46);
INSERT INTO `sys_role_menu` VALUES (1531, 2, 60);
INSERT INTO `sys_role_menu` VALUES (1532, 2, 61);
INSERT INTO `sys_role_menu` VALUES (1533, 2, 81);
INSERT INTO `sys_role_menu` VALUES (1534, 2, 99);
INSERT INTO `sys_role_menu` VALUES (1535, 2, 21);
INSERT INTO `sys_role_menu` VALUES (1536, 2, 43);
INSERT INTO `sys_role_menu` VALUES (1537, 2, 56);
INSERT INTO `sys_role_menu` VALUES (1538, 2, 75);
INSERT INTO `sys_role_menu` VALUES (1539, 2, 76);
INSERT INTO `sys_role_menu` VALUES (1540, 2, 77);
INSERT INTO `sys_role_menu` VALUES (1541, 2, 80);
INSERT INTO `sys_role_menu` VALUES (1542, 2, 49);
INSERT INTO `sys_role_menu` VALUES (1543, 10, 20);
INSERT INTO `sys_role_menu` VALUES (1544, 10, 38);
INSERT INTO `sys_role_menu` VALUES (1545, 10, 44);
INSERT INTO `sys_role_menu` VALUES (1546, 10, 45);
INSERT INTO `sys_role_menu` VALUES (1547, 10, 46);
INSERT INTO `sys_role_menu` VALUES (1548, 10, 60);
INSERT INTO `sys_role_menu` VALUES (1549, 10, 61);
INSERT INTO `sys_role_menu` VALUES (1550, 10, 81);
INSERT INTO `sys_role_menu` VALUES (1551, 10, 99);
INSERT INTO `sys_role_menu` VALUES (1552, 10, 21);
INSERT INTO `sys_role_menu` VALUES (1553, 10, 43);
INSERT INTO `sys_role_menu` VALUES (1554, 10, 56);
INSERT INTO `sys_role_menu` VALUES (1555, 10, 75);
INSERT INTO `sys_role_menu` VALUES (1556, 10, 76);
INSERT INTO `sys_role_menu` VALUES (1557, 10, 77);
INSERT INTO `sys_role_menu` VALUES (1558, 10, 80);
INSERT INTO `sys_role_menu` VALUES (1559, 10, 49);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '随机盐',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `work_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公号码',
  `email` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `status` int(1) NULL DEFAULT NULL COMMENT '使用状态  0：禁用   1：正常',
  `online` int(1) NULL DEFAULT NULL COMMENT '在线状态  0：离线   1：在线',
  `create_time` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '机构ID',
  `dept_name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `identity` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户身份',
  `identity_id` int(1) NULL DEFAULT NULL COMMENT '用户身份id',
  `company` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `bank` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_num` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `remarks` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理者' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'superAdmin', '143b92d687de1c6f64ef769e7a34a03422d70084bb85ce78daa98323bd511ade', '2Po3uofh7M00QyQctEV9ICQPhRFa8gHKzGG', '13030303055', NULL, NULL, NULL, NULL, 1, 0, '2020-06-17 17:12:48', 0, '', '超级管理员', 0, NULL, '', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '张三', 'admin_sc', '143b92d687de1c6f64ef769e7a34a03422d70084bb85ce78daa98323bd511ade', '2Po3uofh7M00QyQctEV9ICQPhRFa8gHKzGG', '13030303030', NULL, NULL, NULL, NULL, 1, 0, '2020-06-17 21:16:32', 0, '', '后台管理员', 1, NULL, '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (2, 2, 1);
INSERT INTO `sys_user_role` VALUES (6, 3, 2);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, '85ce39a440b663f501f4ed65f498791b', '2020-06-18 14:03:44', '2020-06-18 13:03:44');
INSERT INTO `sys_user_token` VALUES (2, '7b055e86fb8752946dcd28fc32af455d', '2020-06-18 13:53:16', '2020-06-18 12:53:16');
INSERT INTO `sys_user_token` VALUES (3, '4cd1deaf0b298a766a6c75672117384d', '2019-07-09 12:13:47', '2019-07-09 11:13:47');
INSERT INTO `sys_user_token` VALUES (4, '70283f93e0e78943da9cabb8c133c36d', '2019-07-09 12:23:21', '2019-07-09 11:23:21');
INSERT INTO `sys_user_token` VALUES (5, '8f002274b302a7ee9ce4568f891f07b2', '2019-06-30 04:14:16', '2019-06-29 16:14:16');
INSERT INTO `sys_user_token` VALUES (6, 'ba7d940b3fa3cfcc4c2833a7aed861db', '2019-06-30 00:15:50', '2019-06-29 12:15:50');

SET FOREIGN_KEY_CHECKS = 1;
