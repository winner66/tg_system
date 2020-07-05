-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: tg_system
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `backup_info`
--

DROP TABLE IF EXISTS `backup_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `backup_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instance_id` int(11) DEFAULT NULL COMMENT '备份所属实例的id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份方法(物理/逻辑)',
  `size` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份文件大小',
  `strategy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份策略(实例/单库)',
  `time` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份结束时间',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份所在主机',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在端口',
  `backup_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份文件名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份文件url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据库备份还原记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backup_info`
--

LOCK TABLES `backup_info` WRITE;
/*!40000 ALTER TABLE `backup_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `backup_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bu_comtag`
--

DROP TABLE IF EXISTS `bu_comtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bu_comtag` (
  `buComtid` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论投稿任务分类id',
  `buComtname` varchar(64) NOT NULL COMMENT '评论投稿分类名',
  PRIMARY KEY (`buComtid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='评论投稿分类表\r\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bu_comtag`
--

LOCK TABLES `bu_comtag` WRITE;
/*!40000 ALTER TABLE `bu_comtag` DISABLE KEYS */;
INSERT INTO `bu_comtag` VALUES (1,'微博/微信/论坛等'),(2,'微博转评'),(3,'微信转评'),(4,'新闻跟评'),(5,'其他'),(6,'demo');
/*!40000 ALTER TABLE `bu_comtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bu_comtalk`
--

DROP TABLE IF EXISTS `bu_comtalk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bu_comtalk` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `taskid` int(11) NOT NULL DEFAULT '0' COMMENT '任务id',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `name` varchar(32) NOT NULL COMMENT '提交的用户名',
  `fenzu` int(11) NOT NULL DEFAULT '0' COMMENT '所属分组（等彭霞的表结构）',
  `createtime` datetime NOT NULL COMMENT '提交时间',
  `url` varchar(512) DEFAULT NULL COMMENT '链接',
  `filename` varchar(512) DEFAULT NULL COMMENT '附件地址',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态0审核中 1合格 2驳回',
  `majia` varchar(32) DEFAULT NULL COMMENT '马甲',
  `reward` int(11) NOT NULL DEFAULT '0' COMMENT '奖励',
  `reason` varchar(512) DEFAULT NULL COMMENT '拒绝通过理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='评论投稿评论稿件表（这个要和何老师的制作相结合）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bu_comtalk`
--

LOCK TABLES `bu_comtalk` WRITE;
/*!40000 ALTER TABLE `bu_comtalk` DISABLE KEYS */;
INSERT INTO `bu_comtalk` VALUES (1,3,'asds','sa',0,'2020-07-04 21:27:32','dsa','asd',2,'sad',5,'dsad'),(2,5,'sadsa','asd',0,'2020-07-04 21:27:55','asd','asdsad',2,'dsad',0,'是非得失'),(3,3,'536565','dsfds',0,'2020-07-04 21:28:16','asdsa','sdfds',0,'sadsa',20,NULL);
/*!40000 ALTER TABLE `bu_comtalk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bu_comtask`
--

DROP TABLE IF EXISTS `bu_comtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bu_comtask` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bid` int(11) NOT NULL COMMENT '编号',
  `tag` int(255) NOT NULL COMMENT '任务分类 对应bu_comtag.buComtid',
  `countj` int(11) NOT NULL DEFAULT '0' COMMENT '接收人数',
  `countf` int(11) NOT NULL DEFAULT '0' COMMENT '发送人数',
  `counte` int(11) NOT NULL DEFAULT '0' COMMENT '完成量',
  `fabup` varchar(255) NOT NULL COMMENT '发布人',
  `orgtoplimit` int(11) NOT NULL DEFAULT '0' COMMENT '机构任务上限',
  `orgbelimit` int(11) NOT NULL DEFAULT '0' COMMENT '机构任务下限',
  `taskname` varchar(32) NOT NULL COMMENT '任务名称',
  `createtime` datetime NOT NULL COMMENT '发布时间',
  `starttime` datetime NOT NULL COMMENT '开始时间',
  `endtime` datetime NOT NULL COMMENT '结束时间',
  `taskreq` longtext NOT NULL COMMENT '任务要求',
  `pertoplimit` int(11) NOT NULL COMMENT '单人上限',
  `taskbelimit` int(11) NOT NULL COMMENT '任务下限',
  `reward` int(11) NOT NULL COMMENT '奖励设置',
  `filename` varchar(512) DEFAULT NULL COMMENT '附件地址',
  `titlex` int(11) NOT NULL DEFAULT '0' COMMENT '贴文标题是否显示0 不 1要',
  `titleb` int(11) NOT NULL DEFAULT '0' COMMENT '贴文标题是否必填',
  `hrefx` int(11) NOT NULL DEFAULT '0' COMMENT '贴文链接是否显示',
  `hrefb` int(11) NOT NULL DEFAULT '0' COMMENT '贴文链接是否必填',
  `contentx` int(11) NOT NULL DEFAULT '0' COMMENT '贴文内容是否显示',
  `contentb` int(11) NOT NULL DEFAULT '0' COMMENT '贴文内容是否必填',
  `filex` int(11) NOT NULL DEFAULT '0' COMMENT '贴文附件是否显示',
  `fileb` int(11) NOT NULL DEFAULT '0' COMMENT '贴文附件是否必填',
  `majiax` int(11) NOT NULL DEFAULT '0' COMMENT '马甲是否显示',
  `majiab` int(11) NOT NULL DEFAULT '0' COMMENT '马甲是否必填',
  `userIds` int(11) DEFAULT NULL COMMENT '要发送给的用户们',
  `taskstatus` int(11) NOT NULL DEFAULT '0' COMMENT '任务状态0已发布 1已结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='评论投稿任务表（差人员那几项）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bu_comtask`
--

LOCK TABLES `bu_comtask` WRITE;
/*!40000 ALTER TABLE `bu_comtask` DISABLE KEYS */;
INSERT INTO `bu_comtask` VALUES (3,20200003,1,0,10,0,'超级管理员',0,0,'阿萨德撒','2020-07-02 10:43:59','2020-07-02 10:43:59','2020-07-02 10:43:59','阿萨德我去发',0,0,10,'1593757447533.docx,1593756923094.jpg,1593756654077.xlsx,1593756654077.pdf',0,0,1,0,1,0,1,1,0,0,NULL,1),(4,20200004,3,0,10,0,'超级管理员',0,0,'萨菲是否收到阿萨德撒','2020-07-04 01:41:25','2020-07-04 00:00:00','2020-07-24 00:00:00','asdsadas',1,1,3,'1593757447533.docx,1593756923094.jpg,1593756654077.xlsx,1593756654077.pdf,1593798083352.jpg',0,0,1,0,1,0,1,1,0,0,NULL,0),(5,20200005,4,0,10,0,'超级管理员',0,0,'breberf大大','2020-07-04 01:15:11','2020-07-04 00:00:00','2020-07-04 00:00:00','wefdvds',0,0,10,'1593757447533.docx,1593756923094.jpg,1593756654077.xlsx,1593756654077.pdf',0,0,1,0,1,0,1,1,0,0,NULL,0),(8,20200007,2,0,10,0,'超级管理员',0,0,'sdfsd','2020-07-02 16:15:06','2020-07-04 00:00:00','2020-07-04 00:00:00','asfdf',0,0,10,'1593757447533.docx,1593756923094.jpg,1593756654077.xlsx',0,0,1,0,1,0,1,1,1,1,NULL,0),(9,20200009,1,0,10,0,'超级管理员',0,0,'test','2020-07-02 16:15:52','2020-07-02 16:15:52','2020-07-02 16:15:52','more',0,0,10,'1593757447533.docx,1593756923094.jpg,1593756654077.xlsx,1593756654077.pdf',0,0,1,0,1,0,1,1,1,1,NULL,1),(13,20200009,3,0,10,0,'超级管理员',0,0,'test','2020-07-02 16:15:52','2020-07-02 16:15:52','2020-07-02 16:15:52','more',0,0,10,'1593757447533.docx,1593756923094.jpg,1593756654077.xlsx,1593756654077.pdf',0,0,1,0,1,0,1,1,1,1,NULL,0),(14,20200014,3,0,10,0,'超级管理员',0,0,'阿萨德撒','2020-07-04 01:13:27','2020-07-04 00:00:00','2020-07-04 00:00:00','asdsa',0,0,0,'',1,1,1,1,0,0,1,0,0,0,NULL,0),(15,20200015,2,0,10,0,'超级管理员',0,0,'asd','2020-07-03 20:11:09','2020-07-03 00:00:00','2020-07-01 00:00:00','asdsa',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(16,20200015,3,0,10,0,'超级管理员',0,0,'asd','2020-07-03 20:11:09','2020-07-03 00:00:00','2020-07-01 00:00:00','asdsa',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(17,20200017,2,0,10,0,'超级管理员',0,0,'demo','2020-07-03 20:11:54','2020-07-03 00:00:00','2020-07-01 00:00:00','qwewqewqeq',10,10,3,'1593778298423.docx,1593778298423.jpg,',1,1,1,1,0,0,0,0,1,1,NULL,0),(18,20200017,3,0,10,0,'超级管理员',0,0,'demo','2020-07-03 20:11:54','2020-07-03 00:00:00','2020-07-01 00:00:00','qwewqewqeq',10,10,3,'1593778298423.docx,1593778298423.jpg,',1,1,1,1,0,0,0,0,1,1,NULL,0),(19,20200019,1,0,10,0,'超级管理员',0,0,'撒大大说','2020-07-03 20:13:25','2020-07-31 00:00:00','2020-07-17 00:00:00','萨达',3,3,3,'1593778397863.xls,1593778397880.docx,',1,1,0,0,0,0,0,0,0,0,NULL,0),(20,20200019,6,0,10,0,'超级管理员',0,0,'撒大大说','2020-07-03 20:13:25','2020-07-31 00:00:00','2020-07-17 00:00:00','萨达',3,3,3,'1593778397863.xls,1593778397880.docx,',1,1,0,0,0,0,0,0,0,0,NULL,0),(21,20200021,2,0,10,0,'超级管理员',0,0,'发哈弗静安寺借款方','2020-07-03 20:15:40','2020-07-04 00:03:03','2020-07-03 00:00:00','阿斯顿撒旦',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(22,20200021,3,0,10,0,'超级管理员',0,0,'发哈弗静安寺借款方','2020-07-03 20:15:40','2020-07-04 00:03:03','2020-07-03 00:00:00','阿斯顿撒旦',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(23,20200021,4,0,10,0,'超级管理员',0,0,'发哈弗静安寺借款方','2020-07-03 20:15:40','2020-07-04 00:03:03','2020-07-03 00:00:00','阿斯顿撒旦',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(24,20200024,5,0,10,0,'超级管理员',0,0,'多福多寿','2020-07-03 20:27:21','2020-07-17 00:00:00','2020-07-03 00:00:00','的范德萨发',0,0,0,'1593779232498.doc,',0,0,0,0,0,0,0,0,0,0,NULL,0),(25,20200025,4,0,10,0,'超级管理员',0,0,'融入','2020-07-04 01:02:25','2020-07-04 00:00:00','2020-07-03 00:00:00','防守打法',0,0,0,'1593795740453.docx,1593795740461.jpg,',0,0,0,0,0,0,0,0,1,1,NULL,0),(26,20200026,5,0,10,0,'超级管理员',0,0,'融入','2020-07-04 01:02:25','2020-07-04 00:00:00','2020-07-03 00:00:00','防守打法',0,0,0,'1593795740453.docx,1593795740461.jpg,',0,0,0,0,0,0,0,0,1,1,NULL,0),(27,20200027,5,0,10,0,'超级管理员',0,0,'asd','2020-07-04 01:42:09','2020-07-04 00:00:00','2020-07-04 00:00:00','asd',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(28,20200028,2,0,10,0,'超级管理员',0,0,'萨达','2020-07-04 14:48:12','2020-07-04 00:00:00','2020-07-04 00:00:00','说到底',0,0,0,'',0,0,0,0,0,0,0,0,0,0,NULL,0),(29,20200030,5,0,10,0,'超级管理员',0,0,'阿斯顿撒旦的','2020-07-04 14:49:45','2020-07-31 00:00:00','2020-07-03 00:00:00','驱蚊器二无无群二',0,0,0,'1593845375736.jpg,1593845375739.jpg,1593845375741.jpg',1,0,1,1,0,0,1,1,0,0,NULL,1),(30,20200029,4,0,10,0,'超级管理员',0,0,'阿斯顿撒旦的','2020-07-04 14:49:45','2020-07-31 00:00:00','2020-07-03 00:00:00','驱蚊器二无无群二',0,0,0,'1593845375736.jpg,1593845375739.jpg,1593845375741.jpg',1,0,1,1,0,0,1,1,0,0,NULL,0);
/*!40000 ALTER TABLE `bu_comtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bu_sptag`
--

DROP TABLE IF EXISTS `bu_sptag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bu_sptag` (
  `buSptid` int(11) NOT NULL AUTO_INCREMENT COMMENT '特约投稿分类id',
  `buSptname` varchar(64) NOT NULL COMMENT '特约投稿分类名',
  PRIMARY KEY (`buSptid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='特约投稿分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bu_sptag`
--

LOCK TABLES `bu_sptag` WRITE;
/*!40000 ALTER TABLE `bu_sptag` DISABLE KEYS */;
INSERT INTO `bu_sptag` VALUES (1,'日常约稿'),(4,'活动约稿'),(5,'其他约稿'),(6,'test');
/*!40000 ALTER TABLE `bu_sptag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_user`
--

DROP TABLE IF EXISTS `client_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `account` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `status` int(3) DEFAULT NULL COMMENT '状态  1：禁用   0：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `phone` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `groupId` bigint(20) DEFAULT NULL COMMENT '所在分组/机 ',
  `dept` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名称',
  `level` int(8) DEFAULT NULL COMMENT '人员等级',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `bank` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开户银行',
  `bank_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行账号',
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
  `type` int(11) DEFAULT '0' COMMENT '0:人员属于组 1：人员属于机构',
  `realname` varchar(255) DEFAULT '马甲',
  `deptId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='人员基本数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_user`
--

LOCK TABLES `client_user` WRITE;
/*!40000 ALTER TABLE `client_user` DISABLE KEYS */;
INSERT INTO `client_user` VALUES (1,'曹晴朗','123','143b92d687de1c6f64ef769e7a34a03422d70084bb85ce78daa98323bd511ade','2Po3uofh7M00QyQctEV9ICQPhRFa8gHKzGG',1,'2020-06-17 20:12:37','2020-06-17 20:12:40','2020-06-17 20:12:42',NULL,'13567543456',NULL,'成都市',1,'男','1435567543@','qq.comxxxx银行','1234567890998','成都市',0,'马甲',NULL),(2,'demo6',NULL,'c6da24dba51081d2ca42e7e621ded8c0641ae79f7abe73ca5c5d70e4e86e6742','IxFt0tMV6jJIdLXXthOCUatVUTatC5vYoRh',1,'2020-07-03 21:08:21','2020-07-03 21:08:21','2020-07-03 21:08:21','','18990813069',NULL,'',0,'1','','','','',1,'123',0),(3,'demo6',NULL,'a1122d9165ba04f4bfe6be152db4613d2cab76b6a3dcac24e1c7412512a84061','RnZxjxNz5umgiU8B0qhejXqoYy3lorEkO3N',1,'2020-07-03 21:14:31','2020-07-03 21:14:31','2020-07-03 21:14:31','','18990813069',NULL,'',0,'1','','','','',1,'123',0),(4,'admin_sc',NULL,'2e9fef8c28bb453a217f6a932bf06bba5e88c6a651d0477885417edd5121743f','xbeKhG7aL0xJeGNu4PEoQ1mwBQz4hoOs2fk',1,'2020-07-04 20:47:47','2020-07-04 20:47:47','2020-07-04 20:47:47','','',NULL,'',0,'男','','','','',1,'123',0);
/*!40000 ALTER TABLE `client_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `key` (`key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统配置信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_auth`
--

DROP TABLE IF EXISTS `sys_data_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_data_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='数据权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_auth`
--

LOCK TABLES `sys_data_auth` WRITE;
/*!40000 ALTER TABLE `sys_data_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_data_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `parent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上级部门名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `depart_num` int(11) DEFAULT NULL COMMENT '部门编号',
  `is_vip` int(11) NOT NULL DEFAULT '0' COMMENT '0:非会员 1：会员',
  `vip_dealtime` date DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='部门管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,0,'0','四川省',10,0,NULL),(2,1,'四川省','成都市11',1001,0,NULL),(3,1,'四川省','绵阳市',1002,0,NULL),(4,1,'四川省','自贡市',1003,0,NULL),(7,0,'0','云南省',20,0,NULL),(8,7,'云南省','昆明市',2001,0,NULL),(9,2,'成都市','成华区',100101,0,NULL),(10,0,'一级部门','重庆市',30,0,NULL),(11,0,'0','山东省',40,0,NULL),(12,0,'0','北京市',50,0,NULL),(13,12,'北京市','朝阳区',5001,0,NULL),(14,1,'四川省','四川省-骨干组',1099,0,NULL),(15,0,'0','test222',10,0,NULL),(17,1,'1','南充市',90,0,NULL),(20,2,'成都市11','新机构',90,0,NULL),(21,0,'','新机构',90,0,NULL),(22,21,'新机构','新节点',90,0,NULL),(23,10,'重庆市','某区',90,0,NULL),(24,23,'某区','某街道',90,0,NULL),(25,23,'某区','某',90,0,NULL),(26,25,'新机构','新机构',90,0,NULL),(27,4,'自贡市','qw',90,0,NULL),(28,2,'成都市11','新机构',90,0,NULL);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_dict` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(32) DEFAULT NULL COMMENT '分组',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `dict_type` int(11) DEFAULT NULL COMMENT '分类： 0：分组 1：分组成员',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '代码',
  `parent_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父节点名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_group`
--

DROP TABLE IF EXISTS `sys_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '组id',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所在机构',
  `preGroupId` bigint(20) DEFAULT '0' COMMENT '父组',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '''组名''',
  PRIMARY KEY (`group_id`),
  KEY `dept_id` (`dept_id`),
  CONSTRAINT `sys_group_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_group`
--

LOCK TABLES `sys_group` WRITE;
/*!40000 ALTER TABLE `sys_group` DISABLE KEYS */;
INSERT INTO `sys_group` VALUES (11,2,0,'成都市下的组1'),(12,2,0,'成都市下的组2'),(13,2,0,'成都市下的组3'),(14,1,0,'四川省下的组3234');
/*!40000 ALTER TABLE `sys_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (1,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":9,\"parentId\":2,\"name\":\"成华区\",\"parentName\":\"成都市\",\"departNum\":100101}',30,'0:0:0:0:0:0:0:1','2020-06-18 21:41:14'),(2,'admin_sc','保存角色','cn.cwnu.modules.sys.controller.SysRoleController.save()','{\"roleId\":11,\"roleName\":\"四川省\",\"subsidiary\":0,\"remark\":\"四川省-角色\",\"deptId\":1,\"deptName\":\"四川省\",\"menuIdList\":[1,18,33,32,34,35,22,39,41,42,3,10,11,12,13,2,9,6,7,8,36,37,38,44,45,46,60,81,61,99,21,43,75,56,76,77,80,49,19,4,16,14,15,17,24,27,28,29,30,86,87,88,89,90,25,31,23],\"deptIdList\":[1,2,9,3,4,5,6],\"createTime\":\"Jun 18, 2020 9:49:19 PM\"}',312,'0:0:0:0:0:0:0:1','2020-06-18 21:49:20'),(3,'admin_sc','保存角色','cn.cwnu.modules.sys.controller.SysRoleController.save()','{\"roleId\":12,\"roleName\":\"云南省\",\"subsidiary\":0,\"remark\":\"云南省-角色\",\"deptId\":7,\"deptName\":\"云南省\",\"menuIdList\":[20,1,18,33,32,34,35,22,39,41,42,3,10,11,12,13,2,9,6,7,8,36,37,38,44,45,46,60,81,61,99,21,43,75,56,76,77,80,49,19,4,16,14,15,17,24,27,28,29,30,86,87,88,89,90,25,31,23],\"deptIdList\":[7,8],\"createTime\":\"Jun 18, 2020 9:50:17 PM\"}',26,'0:0:0:0:0:0:0:1','2020-06-18 21:50:17'),(4,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":10,\"parentId\":0,\"name\":\"重庆市\",\"parentName\":\"一级部门\",\"departNum\":30}',11,'0:0:0:0:0:0:0:1','2020-06-18 21:51:30'),(5,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":11,\"parentId\":0,\"name\":\"山东省\",\"parentName\":\"0\",\"departNum\":40}',17,'0:0:0:0:0:0:0:1','2020-06-18 22:04:31'),(6,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":12,\"parentId\":0,\"name\":\"北京市\",\"parentName\":\"0\",\"departNum\":50}',11,'0:0:0:0:0:0:0:1','2020-06-18 22:07:37'),(7,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":13,\"parentId\":12,\"name\":\"朝阳区\",\"parentName\":\"北京市\",\"departNum\":5001}',9,'0:0:0:0:0:0:0:1','2020-06-18 22:08:35'),(8,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":14,\"parentId\":1,\"name\":\"四川省-骨干组\",\"parentName\":\"四川省\",\"departNum\":1099}',9,'0:0:0:0:0:0:0:1','2020-06-18 22:10:49'),(9,'admin_sc','保存角色','cn.cwnu.modules.sys.controller.SysRoleController.save()','{\"roleId\":13,\"roleName\":\"成都市\",\"subsidiary\":11,\"remark\":\"成都市角色\",\"menuIdList\":[20,1,18,33,32,34,35,22,39,41,42,3,10,11,12,13,2,9,6,7,8,36,37,38,44,45,46,60,81,61,99,21,43,75,56,76,77,80,49,19,4,16,14,15,17,24,27,28,29,30,86,87,88,89,90,25,31,23],\"deptIdList\":[1,14],\"createTime\":\"Jun 18, 2020 10:13:08 PM\"}',38,'0:0:0:0:0:0:0:1','2020-06-18 22:13:09'),(10,'admin_sc','机构删除','cn.cwnu.modules.sys.controller.SysDeptController.delete()','10',3,'0:0:0:0:0:0:0:1','2020-06-19 14:18:16'),(11,'admin_sc','机构删除','cn.cwnu.modules.sys.controller.SysDeptController.delete()','10',1,'0:0:0:0:0:0:0:1','2020-06-19 14:18:29'),(12,'admin_sc','机构删除','cn.cwnu.modules.sys.controller.SysDeptController.delete()','6',12,'0:0:0:0:0:0:0:1','2020-06-19 14:21:46'),(13,'admin_sc','保存角色','cn.cwnu.modules.sys.controller.SysRoleController.save()','{\"roleId\":14,\"roleName\":\"南充市\",\"subsidiary\":11,\"remark\":\"南充市\",\"deptId\":5,\"deptName\":\"南充市\",\"menuIdList\":[1,18,33,32,34,35,22,39,41,42,3,10,11,12,13,2,9,6,7,8,36,37,38,44,45,46,60,81,61,99,21,43,75,56,76,77,80,49,19,4,16,14,15,17,24,27,28,29,30,86,87,88,89,90,25,31,23],\"deptIdList\":[1,5],\"createTime\":\"Jun 19, 2020 2:25:13 PM\"}',37,'0:0:0:0:0:0:0:1','2020-06-19 14:25:14'),(14,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',22,'0:0:0:0:0:0:0:1','2020-07-02 20:33:58'),(15,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',1,'0:0:0:0:0:0:0:1','2020-07-02 20:34:10'),(16,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',1,'0:0:0:0:0:0:0:1','2020-07-02 20:35:12'),(17,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":3,\"parentId\":1}',3,'0:0:0:0:0:0:0:1','2020-07-02 20:38:20'),(18,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":1,\"parentId\":0}',2,'0:0:0:0:0:0:0:1','2020-07-02 20:42:03'),(19,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',1,'0:0:0:0:0:0:0:1','2020-07-02 20:51:42'),(20,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',3,'0:0:0:0:0:0:0:1','2020-07-02 20:54:28'),(21,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',4,'0:0:0:0:0:0:0:1','2020-07-02 21:03:20'),(22,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',19,'0:0:0:0:0:0:0:1','2020-07-02 21:04:38'),(23,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0}',2,'0:0:0:0:0:0:0:1','2020-07-02 21:04:50'),(24,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":15,\"parentId\":0,\"name\":\"test222\"}',226,'0:0:0:0:0:0:0:1','2020-07-02 21:06:26'),(25,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":2,\"parentId\":1,\"name\":\"成都市11\"}',24,'0:0:0:0:0:0:0:1','2020-07-02 21:08:02'),(26,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":2,\"parentId\":1,\"name\":\"成都市11\"}',4,'0:0:0:0:0:0:0:1','2020-07-02 21:16:55'),(27,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":7,\"realName\":\"test\",\"username\":\"cd_demo\",\"password\":\"793460046eed909071ba283f402cf373e0c04697bbf07772eae9c567020d72e8\",\"salt\":\"U8j1wIQM0v9qT3p64kdFOapw4YpZ4T56WSx\",\"mobile\":\"15626562626\",\"sex\":\"0\",\"deptName\":\"成都市11\",\"deptId\":2,\"status\":1,\"online\":0,\"roleIdList\":[],\"createTime\":\"2020-07-03 08:08:32\",\"identity\":\"后台管理员\",\"identityId\":1}',202,'0:0:0:0:0:0:0:1','2020-07-03 08:08:33'),(28,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":8,\"realName\":\"yn_demo\",\"username\":\"yn_demo\",\"password\":\"7d2126f883f98e7c8ffb47bdde1f078e4f8feda28f72659643974f18817064f8\",\"salt\":\"NyQOjMPJUD79ddmEtu7gaYLHkJedstr2oYA\",\"mobile\":\"18256565656\",\"sex\":\"1\",\"deptName\":\"云南省\",\"deptId\":7,\"status\":1,\"online\":0,\"roleIdList\":[],\"createTime\":\"2020-07-03 08:15:35\",\"identity\":\"普通用户\",\"identityId\":2}',104,'0:0:0:0:0:0:0:1','2020-07-03 08:15:35'),(29,'admin_sc','删除角色','cn.cwnu.modules.sys.controller.SysRoleController.delete()','[14]',987,'0:0:0:0:0:0:0:1','2020-07-03 08:29:25'),(30,'admin_sc','删除角色','cn.cwnu.modules.sys.controller.SysRoleController.delete()','[12]',42,'0:0:0:0:0:0:0:1','2020-07-03 08:29:29'),(31,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":9,\"realName\":\"demo\",\"username\":\"demo\",\"password\":\"ed2bdb28f25c5b370035878360d42f76c7f9462d86700a6633ed6bb123849f54\",\"salt\":\"Z3WnqMvV2q45Wqwd9m0IYgcQK8snAvvw4B0\",\"mobile\":\"18200262656\",\"sex\":\"1\",\"deptName\":\"四川省\",\"deptId\":1,\"status\":1,\"online\":0,\"roleIdList\":[],\"createTime\":\"2020-07-03 08:56:05\",\"identity\":\"后台管理员\",\"identityId\":1}',49,'0:0:0:0:0:0:0:1','2020-07-03 08:56:05'),(32,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":10,\"realName\":\"demo2\",\"username\":\"demo2\",\"password\":\"7304d0cc2bda791effb3a097d1df9c6f012b1f75119447a6b57eaa60af82aeaa\",\"salt\":\"DDVV6nHieQoRu4oV2mREpwf3ph5ubM7Y18p\",\"mobile\":\"18526322356\",\"sex\":\"1\",\"deptName\":\"四川省\",\"deptId\":1,\"status\":1,\"online\":0,\"roleIdList\":[],\"createTime\":\"2020-07-03 09:02:36\",\"identity\":\"后台管理员\",\"identityId\":1}',132,'0:0:0:0:0:0:0:1','2020-07-03 09:02:36'),(33,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":11,\"realName\":\"demo\",\"username\":\"demo3\",\"password\":\"e7ddeb4c7e40cb4799d46a10e4d71003aa8a5f9381dec12c7d79123a2c32db94\",\"salt\":\"BErF4iAxUi8HfAX6dXIN3gELUMiSGYwlYhJ\",\"sex\":\"1\",\"deptName\":\"北京市\",\"deptId\":12,\"status\":1,\"online\":0,\"roleIdList\":[],\"createTime\":\"2020-07-03 09:10:17\",\"identity\":\"后台管理员\",\"identityId\":1}',20,'0:0:0:0:0:0:0:1','2020-07-03 09:10:18'),(34,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":12,\"realName\":\"demo\",\"username\":\"demo4\",\"password\":\"9b94b7e401e345d7396bb04a547b35933d2e8eb2538617874161126d891d9531\",\"salt\":\"MDej3kKGZJW6eVrrgTv1XDkSf3vHgw0QhmE\",\"mobile\":\"18200261556\",\"sex\":\"0\",\"deptName\":\"云南省\",\"deptId\":7,\"status\":1,\"online\":0,\"roleIdList\":[],\"createTime\":\"2020-07-03 09:18:05\",\"identity\":\"后台管理员\",\"identityId\":1}',91,'0:0:0:0:0:0:0:1','2020-07-03 09:18:05'),(35,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":13,\"realName\":\"demo\",\"username\":\"demo5\",\"password\":\"6cab4a13adea3779ce21a59267dd7550431a9c24332626d2b33fc4914913faff\",\"salt\":\"MH8wGaypt8W7gkwegG51vxa1qnWz1YXrGgi\",\"sex\":\"1\",\"deptName\":\"四川省\",\"deptId\":1,\"status\":0,\"online\":0,\"roleIdList\":[2],\"createTime\":\"2020-07-03 09:25:07\",\"identity\":\"普通用户\",\"identityId\":2}',114,'0:0:0:0:0:0:0:1','2020-07-03 09:25:08'),(36,'admin_sc','保存用户','cn.cwnu.modules.sys.controller.SysUserController.save()','{\"id\":14,\"realName\":\"demo\",\"username\":\"demo6\",\"password\":\"0aa24f365d06410909c3348836f053017e09874963cbd59cc69aa7a8a105328b\",\"salt\":\"tofMFfc3fn1hDSTeKJwpVYGIgYblrWr9bYS\",\"mobile\":\"1234\",\"sex\":\"1\",\"deptName\":\"四川省\",\"deptId\":1,\"status\":1,\"online\":0,\"roleIdList\":[2],\"createTime\":\"2020-07-03 09:31:17\",\"identity\":\"普通用户\",\"identityId\":2}',133,'0:0:0:0:0:0:0:1','2020-07-03 09:31:18'),(37,'demo6','机构删除','cn.cwnu.modules.sys.controller.SysDeptController.delete()','5',59,'0:0:0:0:0:0:0:1','2020-07-03 10:45:20'),(38,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"deptId\":2,\"groupId\":17,\"groupName\":\"组1下面的组11\",\"preGroupId\":0}',174,'0:0:0:0:0:0:0:1','2020-07-03 11:55:03'),(39,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"deptId\":0,\"groupId\":14,\"groupName\":\"四川省下的组3\",\"preGroupId\":0}',12,'0:0:0:0:0:0:0:1','2020-07-03 11:56:51'),(40,'demo6','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":17,\"parentId\":1,\"name\":\"新机构\",\"parentName\":\"1\",\"departNum\":90}',198,'0:0:0:0:0:0:0:1','2020-07-03 12:39:57'),(41,'demo6','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":18,\"parentId\":1,\"name\":\"新机构\",\"parentName\":\"1\",\"departNum\":90}',71,'0:0:0:0:0:0:0:1','2020-07-03 12:49:45'),(42,'demo6','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":19,\"parentId\":1,\"name\":\"新机构\",\"parentName\":\"1\",\"departNum\":90}',41,'0:0:0:0:0:0:0:1','2020-07-03 12:49:51'),(43,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"groupId\":21,\"groupName\":\"test\",\"preGroupId\":0}',37,'0:0:0:0:0:0:0:1','2020-07-03 12:51:56'),(44,'demo6','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":18,\"parentId\":1,\"name\":\"新机构\"}',3,'0:0:0:0:0:0:0:1','2020-07-03 12:53:08'),(45,'demo6','机构删除','cn.cwnu.modules.sys.controller.SysDeptController.delete()','18',22,'0:0:0:0:0:0:0:1','2020-07-03 12:53:13'),(46,'demo6','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":17,\"parentId\":1,\"name\":\"南充市\"}',103,'0:0:0:0:0:0:0:1','2020-07-03 12:54:10'),(47,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"groupId\":23,\"groupName\":\"新节点\",\"preGroupId\":0}',21,'0:0:0:0:0:0:0:1','2020-07-03 14:40:55'),(48,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"groupId\":22,\"groupName\":\"新节点11\",\"preGroupId\":0}',103,'0:0:0:0:0:0:0:1','2020-07-03 14:41:06'),(49,'demo6','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":20,\"parentId\":2,\"name\":\"新机构\",\"parentName\":\"成都市11\",\"departNum\":90}',107,'0:0:0:0:0:0:0:1','2020-07-03 14:41:33'),(50,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"groupId\":22,\"groupName\":\"新节点11\",\"preGroupId\":0}',4,'0:0:0:0:0:0:0:1','2020-07-03 14:43:05'),(51,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"groupId\":22,\"groupName\":\"新节点1123\",\"preGroupId\":0}',134,'0:0:0:0:0:0:0:1','2020-07-03 14:43:32'),(52,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"groupId\":26,\"groupName\":\"新节点45;\",\"preGroupId\":0}',12,'0:0:0:0:0:0:0:1','2020-07-03 14:59:32'),(53,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":21,\"parentId\":0,\"name\":\"新机构\",\"parentName\":\"\",\"departNum\":90}',20,'0:0:0:0:0:0:0:1','2020-07-03 15:06:33'),(54,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":22,\"parentId\":21,\"name\":\"新机构\",\"parentName\":\"新机构\",\"departNum\":90}',156,'0:0:0:0:0:0:0:1','2020-07-03 15:06:36'),(55,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":22,\"parentId\":21,\"name\":\"新节点\"}',109,'0:0:0:0:0:0:0:1','2020-07-03 15:07:02'),(56,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":23,\"parentId\":10,\"name\":\"新机构\",\"parentName\":\"重庆市\",\"departNum\":90}',258,'0:0:0:0:0:0:0:1','2020-07-03 15:07:11'),(57,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":23,\"parentId\":10,\"name\":\"某区\"}',7,'0:0:0:0:0:0:0:1','2020-07-03 15:07:27'),(58,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":24,\"parentId\":23,\"name\":\"新机构\",\"parentName\":\"某区\",\"departNum\":90}',11,'0:0:0:0:0:0:0:1','2020-07-03 15:07:34'),(59,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":24,\"parentId\":23,\"name\":\"某街道\"}',8,'0:0:0:0:0:0:0:1','2020-07-03 15:07:56'),(60,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":24,\"parentId\":23,\"name\":\"某街道\"}',2,'0:0:0:0:0:0:0:1','2020-07-03 15:08:17'),(61,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":23,\"parentId\":10,\"name\":\"某区\"}',2,'0:0:0:0:0:0:0:1','2020-07-03 15:08:26'),(62,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":25,\"parentId\":23,\"name\":\"新机构\",\"parentName\":\"某区\",\"departNum\":90}',15,'0:0:0:0:0:0:0:1','2020-07-03 15:08:32'),(63,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":26,\"parentId\":25,\"name\":\"新机构\",\"parentName\":\"新机构\",\"departNum\":90}',105,'0:0:0:0:0:0:0:1','2020-07-03 15:08:37'),(64,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":25,\"parentId\":23,\"name\":\"某\"}',15,'0:0:0:0:0:0:0:1','2020-07-03 15:08:51'),(65,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":27,\"parentId\":4,\"name\":\"新机构\",\"parentName\":\"自贡市\",\"departNum\":90}',10,'0:0:0:0:0:0:0:1','2020-07-03 21:04:42'),(66,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":27,\"parentId\":4,\"name\":\"qw\"}',20,'0:0:0:0:0:0:0:1','2020-07-03 21:05:07'),(67,'admin_sc','机构添加','cn.cwnu.modules.sys.controller.SysDeptController.save()','{\"deptId\":28,\"parentId\":2,\"name\":\"新机构\",\"parentName\":\"成都市11\",\"departNum\":90}',35,'0:0:0:0:0:0:0:1','2020-07-04 20:46:53'),(68,'admin_sc','机构修改','cn.cwnu.modules.sys.controller.SysDeptController.update()','{\"deptId\":19,\"parentId\":1,\"name\":\"新机构1231\"}',141,'0:0:0:0:0:0:0:1','2020-07-04 20:47:02'),(69,'admin_sc','机构删除','cn.cwnu.modules.sys.controller.SysDeptController.delete()','19',106,'0:0:0:0:0:0:0:1','2020-07-04 20:47:16'),(70,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"deptId\":2,\"groupId\":17,\"groupName\":\"组1下面的组1123423\",\"preGroupId\":0}',155,'0:0:0:0:0:0:0:1','2020-07-04 20:48:36'),(71,'demo6','组修改','cn.cwnu.modules.sys.controller.SysGroupController.updataGroup()','{\"deptId\":0,\"groupId\":14,\"groupName\":\"四川省下的组3234\",\"preGroupId\":0}',6,'0:0:0:0:0:0:0:1','2020-07-04 20:49:18');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,'人员权限管理',NULL,NULL,0,'fa fa-deviantart',1),(2,1,'管理员管理','modules/sys/user.html',NULL,1,'fa fa-user',3),(3,1,'角色管理','modules/sys/role.html',NULL,1,'fa fa-user-secret',2),(4,19,'菜单管理','modules/sys/menu.html',NULL,1,'fa fa-th-list',0),(6,2,'查看',NULL,'sys:user:list,sys:user:info',2,NULL,0),(7,2,'新增',NULL,'sys:user:save,sys:role:select',2,NULL,0),(8,2,'修改',NULL,'sys:user:update,sys:role:select',2,NULL,0),(9,2,'删除',NULL,'sys:user:delete',2,NULL,0),(10,3,'查看',NULL,'sys:role:list,sys:role:info',2,NULL,0),(11,3,'新增',NULL,'sys:role:save,sys:menu:perms',2,NULL,0),(12,3,'修改',NULL,'sys:role:update,sys:menu:perms',2,NULL,0),(13,3,'删除',NULL,'sys:role:delete',2,NULL,0),(14,4,'查看',NULL,'sys:menu:list,sys:menu:info',2,NULL,0),(15,4,'新增',NULL,'sys:menu:save,sys:menu:select',2,NULL,0),(16,4,'修改',NULL,'sys:menu:update,sys:menu:select',2,NULL,0),(17,4,'删除',NULL,'sys:menu:delete',2,NULL,0),(18,1,'机构管理','modules/sys/dept.html',NULL,1,'fa fa-cubes',0),(19,0,'系统设置','',NULL,0,'fa fa-cog',10),(20,0,'主页','main.html',NULL,1,'fa fa-arrow-circle-o-up',0),(21,0,'特约投稿',NULL,NULL,0,'fa fa-tumblr',3),(22,1,'人员管理','modules/sys/userManage.html',NULL,1,'fa fa-odnoklassniki',1),(24,19,'类别管理','modules/sys/sysdict.html',NULL,1,'fa fa-book',1),(25,19,'系统日志','modules/sys/log.html','',1,'fa fa-file-text-o',5),(27,24,'查看',NULL,'sys:sysdict:list,sys:clientUser:use,sys:clientUser:ban,sys:sysdict:info,sys:sysdict:select',2,NULL,0),(28,24,'新增',NULL,'sys:sysdict:save,sys:sysdict:select',2,NULL,0),(29,24,'修改',NULL,'sys:sysdict:update,sys:sysdict:select',2,NULL,0),(30,24,'删除',NULL,'sys:sysdict:delete',2,NULL,0),(31,25,'查看',NULL,'sys:log:list',2,NULL,0),(32,18,'查看',NULL,'sys:dept:list,sys:dept:info',2,NULL,0),(33,18,'新增',NULL,'sys:dept:save,sys:dept:select',2,NULL,0),(34,18,'修改',NULL,'sys:dept:update,sys:dept:select',2,NULL,0),(35,18,'删除',NULL,'sys:dept:delete',2,NULL,0),(36,2,'禁用',NULL,'sys:user:ban',2,NULL,0),(37,2,'激活',NULL,'sys:user:use',2,NULL,0),(38,0,'评论投稿',NULL,NULL,0,'fa fa-eyedropper',2),(39,22,'查看',NULL,'sys:clientUser:list,sys:clientUser:info',2,NULL,0),(40,22,'删除',NULL,'sys:clientUser:delete',2,NULL,0),(41,22,'禁用',NULL,'sys:clientUser:ban',2,NULL,0),(42,22,'激活',NULL,'sys:clientUser:use',2,NULL,0),(43,21,'任务列表','modules/business/specialSubmitTask.html',NULL,1,'fa fa-file-o',0),(44,38,'任务列表','modules/business/commentSubmitTask.html',NULL,1,'fa fa-th-list',0),(45,44,'查看',NULL,'business:comtask:tab,business:comtask:search,business:comtag:findname,business:comtask:look,business:comtask:list,business:comtag:tab,business:comtag:list',2,NULL,0),(46,44,'删除',NULL,'business:comtag:deltab,business:comtask:del',2,NULL,0),(49,21,'推稿统计','modules/business/tweetStatist.html',NULL,1,'fa fa-bar-chart',2),(56,43,'查看',NULL,'business:sptag:tab,business:sptag:list',2,NULL,0),(60,38,'评论稿件','modules/business/commentSubmitScript.html',NULL,1,'fa fa-pencil-square-o',1),(75,43,'删除',NULL,'business:sptag:deltab',2,NULL,0),(76,21,'文章稿件','modules/business/articleScript.html',NULL,1,'fa fa-font',1),(77,76,'查看',NULL,'business:articleScript:list',2,NULL,0),(80,76,'删除',NULL,'business:articleScript:delete',2,NULL,0),(86,19,'数据库备份还原','modules/sys/backup.html',NULL,1,'fa fa-database',2),(87,86,'查看',NULL,'sys:backup:list',2,NULL,0),(88,86,'备份',NULL,'sys:backup:export',2,NULL,0),(89,86,'还原',NULL,'sys:backup:reduction',2,NULL,0),(90,86,'删除',NULL,'sys:backup:delete',2,NULL,0),(99,38,'稿件统计','modules/business/manuscriptStatist.html',NULL,1,'fa fa-bar-chart',2),(101,44,'增加',NULL,'business:comtag:addtab,business:comtask:add',2,NULL,0),(102,44,'修改',NULL,'business:comtag:updatetab,business:comtask:stop,business:comtask:update',2,NULL,0),(103,43,'增加',NULL,'business:sptag:addtab',2,NULL,0),(104,43,'修改',NULL,'business:sptag:updatetab',2,NULL,0),(105,60,'查看',NULL,'business:comtalk:maxid,business:comtalk:list,business:comtalk:search,business:comtalk:look',2,NULL,0),(106,60,'修改',NULL,'business:comtalk:reward,business:comtalk:stop,business:comtalk:pass',2,NULL,0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_oss`
--

DROP TABLE IF EXISTS `sys_oss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='文件上传';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_oss`
--

LOCK TABLES `sys_oss` WRITE;
/*!40000 ALTER TABLE `sys_oss` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_oss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `subsidiary` bigint(20) DEFAULT NULL COMMENT '所属一级角色，若不选则为0，该值为一级角色id   0：表示一级角色',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员','一切权限',NULL,'2019-05-01 16:42:33',0),(2,'区域管理员','区域管理者',NULL,'2019-05-01 16:59:54',0),(10,'普通用户','普通用户角色',NULL,'2020-06-17 21:56:33',0),(11,'四川省','四川省-角色',1,'2020-06-18 21:49:19',0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_dept`
--

DROP TABLE IF EXISTS `sys_role_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色与部门对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_dept`
--

LOCK TABLES `sys_role_dept` WRITE;
/*!40000 ALTER TABLE `sys_role_dept` DISABLE KEYS */;
INSERT INTO `sys_role_dept` VALUES (281,1,1),(282,1,2),(283,1,3),(284,1,4),(285,1,5),(286,1,6),(287,1,7),(288,1,8),(289,2,1),(290,2,2),(291,2,3),(292,2,4),(293,2,5),(294,2,6),(295,2,7),(296,2,8),(297,10,1),(298,10,2),(299,10,3),(300,10,4),(301,10,5),(302,10,6),(303,10,7),(304,10,8),(305,11,1),(306,11,2),(307,11,9),(308,11,3),(309,11,4),(310,11,5),(311,11,6),(314,13,1),(315,13,14);
/*!40000 ALTER TABLE `sys_role_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1790 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色与菜单对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
INSERT INTO `sys_role_menu` VALUES (1452,1,20),(1453,1,1),(1454,1,18),(1455,1,32),(1456,1,33),(1457,1,34),(1458,1,35),(1459,1,22),(1460,1,39),(1461,1,41),(1462,1,42),(1463,1,3),(1464,1,10),(1465,1,11),(1466,1,12),(1467,1,13),(1468,1,2),(1469,1,6),(1470,1,7),(1471,1,8),(1472,1,9),(1473,1,36),(1474,1,37),(1475,1,38),(1476,1,44),(1477,1,45),(1478,1,46),(1479,1,60),(1480,1,61),(1481,1,81),(1482,1,99),(1483,1,21),(1484,1,43),(1485,1,56),(1486,1,75),(1487,1,76),(1488,1,77),(1489,1,80),(1490,1,49),(1491,1,19),(1492,1,4),(1493,1,14),(1494,1,15),(1495,1,16),(1496,1,17),(1497,1,24),(1498,1,27),(1499,1,28),(1500,1,29),(1501,1,30),(1502,1,86),(1503,1,87),(1504,1,88),(1505,1,89),(1506,1,90),(1507,1,25),(1508,1,31),(1509,2,20),(1510,2,1),(1511,2,18),(1512,2,32),(1513,2,33),(1514,2,34),(1515,2,35),(1516,2,22),(1517,2,39),(1518,2,41),(1519,2,42),(1520,2,2),(1521,2,6),(1522,2,7),(1523,2,8),(1524,2,9),(1525,2,36),(1526,2,37),(1527,2,38),(1528,2,44),(1529,2,45),(1530,2,46),(1531,2,60),(1532,2,61),(1533,2,81),(1534,2,99),(1535,2,21),(1536,2,43),(1537,2,56),(1538,2,75),(1539,2,76),(1540,2,77),(1541,2,80),(1542,2,49),(1543,10,20),(1544,10,38),(1545,10,44),(1546,10,45),(1547,10,46),(1548,10,60),(1549,10,61),(1550,10,81),(1551,10,99),(1552,10,21),(1553,10,43),(1554,10,56),(1555,10,75),(1556,10,76),(1557,10,77),(1558,10,80),(1559,10,49),(1560,11,1),(1561,11,18),(1562,11,33),(1563,11,32),(1564,11,34),(1565,11,35),(1566,11,22),(1567,11,39),(1568,11,41),(1569,11,42),(1570,11,3),(1571,11,10),(1572,11,11),(1573,11,12),(1574,11,13),(1575,11,2),(1576,11,9),(1577,11,6),(1578,11,7),(1579,11,8),(1580,11,36),(1581,11,37),(1582,11,38),(1583,11,44),(1584,11,45),(1585,11,46),(1586,11,60),(1587,11,81),(1588,11,61),(1589,11,99),(1590,11,21),(1591,11,43),(1592,11,75),(1593,11,56),(1594,11,76),(1595,11,77),(1596,11,80),(1597,11,49),(1598,11,19),(1599,11,4),(1600,11,16),(1601,11,14),(1602,11,15),(1603,11,17),(1604,11,24),(1605,11,27),(1606,11,28),(1607,11,29),(1608,11,30),(1609,11,86),(1610,11,87),(1611,11,88),(1612,11,89),(1613,11,90),(1614,11,25),(1615,11,31),(1616,11,23),(1675,13,20),(1676,13,1),(1677,13,18),(1678,13,33),(1679,13,32),(1680,13,34),(1681,13,35),(1682,13,22),(1683,13,39),(1684,13,41),(1685,13,42),(1686,13,3),(1687,13,10),(1688,13,11),(1689,13,12),(1690,13,13),(1691,13,2),(1692,13,9),(1693,13,6),(1694,13,7),(1695,13,8),(1696,13,36),(1697,13,37),(1698,13,38),(1699,13,44),(1700,13,45),(1701,13,46),(1702,13,60),(1703,13,81),(1704,13,61),(1705,13,99),(1706,13,21),(1707,13,43),(1708,13,75),(1709,13,56),(1710,13,76),(1711,13,77),(1712,13,80),(1713,13,49),(1714,13,19),(1715,13,4),(1716,13,16),(1717,13,14),(1718,13,15),(1719,13,17),(1720,13,24),(1721,13,27),(1722,13,28),(1723,13,29),(1724,13,30),(1725,13,86),(1726,13,87),(1727,13,88),(1728,13,89),(1729,13,90),(1730,13,25),(1731,13,31),(1732,13,23);
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '随机盐',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `work_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '办公号码',
  `email` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `address` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
  `sex` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别',
  `status` int(1) DEFAULT NULL COMMENT '使用状态  0：禁用   1：正常',
  `online` int(1) DEFAULT NULL COMMENT '在线状态  0：离线   1：在线',
  `create_time` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `dept_id` int(11) DEFAULT NULL COMMENT '机构ID',
  `dept_name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '机构名称',
  `identity` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户身份',
  `identity_id` int(1) DEFAULT NULL COMMENT '用户身份id',
  `company` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '单位名称',
  `bank` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '开户银行',
  `bank_num` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '银行账号',
  `remarks` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `realname` varchar(255) DEFAULT '马甲' COMMENT '用户姓名-马甲',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'超级管理员','superAdmin','143b92d687de1c6f64ef769e7a34a03422d70084bb85ce78daa98323bd511ade','2Po3uofh7M00QyQctEV9ICQPhRFa8gHKzGG','13030303055',NULL,NULL,NULL,NULL,1,0,'2020-06-17 17:12:48',0,'','超级管理员',0,NULL,'',NULL,NULL,'马甲'),(2,'张三','admin_sc','143b92d687de1c6f64ef769e7a34a03422d70084bb85ce78daa98323bd511ade','2Po3uofh7M00QyQctEV9ICQPhRFa8gHKzGG','13030303030',NULL,NULL,NULL,NULL,1,0,'2020-06-17 21:16:32',0,'','后台管理员',1,NULL,'',NULL,NULL,'马甲'),(9,'demo','demo','ed2bdb28f25c5b370035878360d42f76c7f9462d86700a6633ed6bb123849f54','Z3WnqMvV2q45Wqwd9m0IYgcQK8snAvvw4B0','18200262656',NULL,NULL,NULL,NULL,1,0,'2020-07-03 08:56:05',1,'四川省','后台管理员',1,NULL,NULL,NULL,NULL,'马甲'),(10,'demo2','demo2','7304d0cc2bda791effb3a097d1df9c6f012b1f75119447a6b57eaa60af82aeaa','DDVV6nHieQoRu4oV2mREpwf3ph5ubM7Y18p','18526322356',NULL,NULL,NULL,NULL,1,0,'2020-07-03 09:02:36',1,'四川省','后台管理员',1,NULL,NULL,NULL,NULL,'马甲'),(11,'demo','demo3','e7ddeb4c7e40cb4799d46a10e4d71003aa8a5f9381dec12c7d79123a2c32db94','BErF4iAxUi8HfAX6dXIN3gELUMiSGYwlYhJ',NULL,NULL,NULL,NULL,NULL,1,0,'2020-07-03 09:10:17',12,'北京市','后台管理员',1,NULL,NULL,NULL,NULL,'马甲'),(12,'demo','demo4','9b94b7e401e345d7396bb04a547b35933d2e8eb2538617874161126d891d9531','MDej3kKGZJW6eVrrgTv1XDkSf3vHgw0QhmE','18200261556',NULL,NULL,NULL,NULL,1,0,'2020-07-03 09:18:05',7,'云南省','后台管理员',1,NULL,NULL,NULL,NULL,'马甲'),(13,'demo','demo5','6cab4a13adea3779ce21a59267dd7550431a9c24332626d2b33fc4914913faff','MH8wGaypt8W7gkwegG51vxa1qnWz1YXrGgi',NULL,NULL,NULL,NULL,NULL,0,0,'2020-07-03 09:25:07',1,'四川省','普通用户',2,NULL,NULL,NULL,NULL,'马甲'),(14,'demo','demo6','0aa24f365d06410909c3348836f053017e09874963cbd59cc69aa7a8a105328b','tofMFfc3fn1hDSTeKJwpVYGIgYblrWr9bYS','1234',NULL,NULL,NULL,NULL,1,0,'2020-07-03 09:31:17',1,'四川省','普通用户',2,NULL,NULL,NULL,NULL,'马甲');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户与角色对应关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (2,2,1),(6,3,2),(16,13,2),(17,14,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_token`
--

DROP TABLE IF EXISTS `sys_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统用户Token';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_token`
--

LOCK TABLES `sys_user_token` WRITE;
/*!40000 ALTER TABLE `sys_user_token` DISABLE KEYS */;
INSERT INTO `sys_user_token` VALUES (1,'0f5a315493dc29d23e09dd856074b4f8','2020-06-19 12:00:21','2020-06-19 11:00:21'),(2,'2a148db4436840eb31c8631734c938b3','2020-07-05 10:20:45','2020-07-05 09:20:45'),(3,'4cd1deaf0b298a766a6c75672117384d','2019-07-09 12:13:47','2019-07-09 11:13:47'),(4,'70283f93e0e78943da9cabb8c133c36d','2019-07-09 12:23:21','2019-07-09 11:23:21'),(5,'8f002274b302a7ee9ce4568f891f07b2','2019-06-30 04:14:16','2019-06-29 16:14:16'),(6,'ba7d940b3fa3cfcc4c2833a7aed861db','2019-06-30 00:15:50','2019-06-29 12:15:50'),(7,'7e5c6f8c14557d302322668ba288a7d8','2020-07-03 09:10:33','2020-07-03 08:10:33'),(9,'1de8c9852c1ba291fe81afedea32099e','2020-07-03 09:56:38','2020-07-03 08:56:38'),(10,'d3971c3f14ac8212106b4a408b9c49ec','2020-07-03 10:02:55','2020-07-03 09:02:55'),(11,'e44b331281e4356b9eafc1ced23679d5','2020-07-03 10:10:41','2020-07-03 09:10:41'),(14,'d5054520dcdb76e5bf7c5da19efabde8','2020-07-05 09:40:51','2020-07-05 08:40:51');
/*!40000 ALTER TABLE `sys_user_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-05  9:34:06
