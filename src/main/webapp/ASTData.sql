/*
Navicat MySQL Data Transfer

Source Server         : 10.11.160.101
Source Server Version : 50173
Source Host           : 10.11.160.101:3306
Source Database       : ASTData

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-25 17:57:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for active_infos
-- ----------------------------
DROP TABLE IF EXISTS `active_infos`;
CREATE TABLE `active_infos` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `activeName` varchar(1024) NOT NULL,
  `activeTime` varchar(255) NOT NULL,
  `activeAuthor` varchar(255) DEFAULT NULL,
  `activePic` varchar(255) DEFAULT NULL,
  `activeBody` longtext NOT NULL,
  `activeLink` varchar(1024) DEFAULT NULL,
  `activeReadCount` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for active_registration
-- ----------------------------
DROP TABLE IF EXISTS `active_registration`;
CREATE TABLE `active_registration` (
  `id` int(11) NOT NULL,
  `active_name` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userAge` varchar(255) NOT NULL,
  `registrationTime` datetime NOT NULL,
  `isConfirm` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_infos
-- ----------------------------
DROP TABLE IF EXISTS `user_infos`;
CREATE TABLE `user_infos` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) NOT NULL,
  `userPhone` varchar(255) NOT NULL,
  `userAge` varchar(255) NOT NULL,
  `userEmail` varchar(255) NOT NULL,
  `userRegion` int(11) NOT NULL,
  `userPrivalege` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
