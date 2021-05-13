/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.2.33-MariaDB : Database - devops_assets_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`devops_assets_manage` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `devops_assets_manage`;

/*Table structure for table `assets_manage_code_comment` */

DROP TABLE IF EXISTS `assets_manage_code_comment`;

CREATE TABLE `assets_manage_code_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `comment` varchar(255) NOT NULL COMMENT '评论内容',
  `score` int(11) DEFAULT NULL COMMENT '分数',
  `type` varchar(255) NOT NULL COMMENT '类型(commit/merge)',
  `ref_id` varchar(255) NOT NULL COMMENT '关联的commitID/mergeID',
  `state` varchar(255) NOT NULL COMMENT '评论状态(展示为READ_ONLY  保存为WRITE_ONLY)',
  `create_user_id` varchar(255) NOT NULL COMMENT '创建者ID',
  `create_user_name` varchar(255) NOT NULL COMMENT '创建者名字',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_update_user_id` varchar(255) NOT NULL COMMENT '最后修改者ID',
  `last_update_user_name` varchar(255) NOT NULL COMMENT '最后修改者名字',
  `last_update_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `assets_manage_code_node` */

DROP TABLE IF EXISTS `assets_manage_code_node`;

CREATE TABLE `assets_manage_code_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '节点ID',
  `name` varchar(255) NOT NULL COMMENT '节点名',
  `address` varchar(255) NOT NULL COMMENT '节点地址',
  `type` varchar(255) NOT NULL COMMENT '节点类型',
  `create_user_id` varchar(255) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_update_user_id` varchar(255) NOT NULL COMMENT '最后修改人',
  `last_update_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `NAME_UNIQUE` (`name`),
  UNIQUE KEY `ADDRESS_UNIQUE` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `assets_manage_code_repository` */

DROP TABLE IF EXISTS `assets_manage_code_repository`;

CREATE TABLE `assets_manage_code_repository` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `ref_Id` varchar(255) NOT NULL COMMENT '外部关联ID',
  `group_id` varchar(255) NOT NULL COMMENT '仓库组ID',
  `group_Name` varchar(255) NOT NULL COMMENT '仓库组名称',
  `repository_id` varchar(255) NOT NULL COMMENT '仓库ID',
  `repository_Name` varchar(255) NOT NULL COMMENT '仓库名称',
  `create_user_id` varchar(255) NOT NULL COMMENT '创建用户ID',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_update_user_id` varchar(255) NOT NULL COMMENT '最后修改用户ID',
  `last_update_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '最后修改时间',
  `fk_code_node` bigint(20) NOT NULL COMMENT '节点ID',
  `fk_code_secret` bigint(20) NOT NULL COMMENT '认证信息',
  PRIMARY KEY (`id`),
  UNIQUE KEY `REF_REPOSITORY` (`ref_Id`),
  KEY `REPO_FK_NODE` (`fk_code_node`),
  KEY `REPO_FK_SECRET` (`fk_code_secret`),
  CONSTRAINT `REPO_FK_NODE` FOREIGN KEY (`fk_code_node`) REFERENCES `assets_manage_code_node` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `REPO_FK_SECRET` FOREIGN KEY (`fk_code_secret`) REFERENCES `assets_manage_code_secret` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `assets_manage_code_secret` */

DROP TABLE IF EXISTS `assets_manage_code_secret`;

CREATE TABLE `assets_manage_code_secret` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账号ID',
  `account` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL DEFAULT 'password' COMMENT '密码',
  `secret` varchar(255) NOT NULL COMMENT '认证密文',
  `state` varchar(255) NOT NULL DEFAULT 'ACTIVED' COMMENT '状态',
  `ref_id` varchar(255) NOT NULL COMMENT '关联ID',
  `create_user_id` varchar(255) NOT NULL COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_update_user_id` varchar(255) NOT NULL COMMENT '最后修改人',
  `last_update_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '最后修改时间',
  `fk_code_node` bigint(20) NOT NULL COMMENT '节点ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `NODE_ACCOUNT_AUTHOR` (`ref_id`,`account`,`create_user_id`,`fk_code_node`),
  KEY `SECRET_FK_NODE` (`fk_code_node`),
  CONSTRAINT `SECRET_FK_NODE` FOREIGN KEY (`fk_code_node`) REFERENCES `assets_manage_code_node` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `assets_manage_manage_hook_event` */

DROP TABLE IF EXISTS `assets_manage_manage_hook_event`;

CREATE TABLE `assets_manage_manage_hook_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `title` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '页面展示的title',
  `devops_value` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '统一属性值',
  `git_value` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL COMMENT 'git平台中的对应的值',
  `type` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '类型,标识是哪个平台',
  `state` varchar(255) COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT 'ACTIVED' COMMENT '状态,默认ACTIVED',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
