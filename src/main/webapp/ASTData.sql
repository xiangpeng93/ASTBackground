/*
Navicat MySQL Data Transfer

Source Server         : 10.11.160.101
Source Server Version : 50173
Source Host           : 10.11.160.101:3306
Source Database       : ASTData

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-26 19:01:10
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
  `activeNumber` int(1) DEFAULT '0',
  `activePeopleNumber` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of active_infos
-- ----------------------------
INSERT INTO `active_infos` VALUES ('58', '1', '2017-12-26 16:14:46', 'null', 'img/pic1.jpg', '<p>1313</p>', 'http://127.0.0.1:8080/active?id=58', '2', '1', '3');
INSERT INTO `active_infos` VALUES ('59', '114', '2017-12-26 17:03:30', 'null', 'img/pic1.jpg', '<p>1313<img src=\"http://127.0.0.1:80/img/Desert.jpg\" style=\"max-width: 100%;\"><img src=\"http://127.0.0.1:80/img/Koala.jpg\" style=\"max-width: 100%;\"></p>', 'http://127.0.0.1:8080/active?id=59', '13', '3', '4');

-- ----------------------------
-- Table structure for active_registration
-- ----------------------------
DROP TABLE IF EXISTS `active_registration`;
CREATE TABLE `active_registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activeName` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userAge` varchar(255) DEFAULT NULL,
  `userPhone` varchar(255) DEFAULT NULL,
  `registrationTime` datetime DEFAULT NULL,
  `isConfirm` int(11) DEFAULT '0',
  `activeSession` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of active_registration
-- ----------------------------
INSERT INTO `active_registration` VALUES ('1', '12', '123', '123', '123', '2017-12-26 15:28:14', '0', '13');
INSERT INTO `active_registration` VALUES ('2', '12', '12', '131', '3', '2017-12-26 15:43:52', '0', '1');
INSERT INTO `active_registration` VALUES ('3', '114', '13131', '13', '13', '2017-12-26 16:44:16', '0', '1');

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

-- ----------------------------
-- Records of user_infos
-- ----------------------------
