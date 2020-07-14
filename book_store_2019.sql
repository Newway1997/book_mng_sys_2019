/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : book_store_2019

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 14/07/2020 23:32:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `isbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publisher` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publish_date` datetime(0) NULL DEFAULT NULL,
  `unit_price` decimal(10, 2) NULL DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `all_num` int(11) NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_num` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('9782511360809', 1, '流浪地球', '刘慈欣', '中国华侨出版社', '2016-06-23 16:17:01', 38.00, 's28780375.jpg', '在太阳灭亡的瞬间，一切都平息了，每个人怀揣着恐惧和希望踏上漫长的流浪之旅……', 4, '流浪;科幻', 3, 1);
INSERT INTO `book` VALUES ('9787511360809', 1, '流浪地球', '刘慈欣', '中国华侨出版社', '2016-06-23 16:17:01', 38.00, 's28780375.jpg', '在太阳灭亡的瞬间，一切都平息了，每个人怀揣着恐惧和希望踏上漫长的流浪之旅……', 5, '流浪;科幻', 5, 0);
INSERT INTO `book` VALUES ('97875440878', 1, '解忧杂货店', '[日] 东野圭吾', '南海出版公司', '2014-05-01 10:40:31', 39.50, 's2726411.jpg', '现代人内心流失的东西，这家杂货店能帮你找回——', 2, '治愈', 2, 0);
INSERT INTO `book` VALUES ('9787544087888', 1, '解忧杂货店', '[日] 东野圭吾', '南海出版公司', '2014-05-01 10:40:31', 39.50, 's2726411.jpg', '现代人内心流失的东西，这家杂货店能帮你找回——', 2, '治愈', 2, 0);
INSERT INTO `book` VALUES ('978754420878', 1, '解忧杂货店', '[日] 东野圭吾', '南海出版公司', '2014-05-01 10:40:31', 39.50, 's2726411.jpg', '现代人内心流失的东西，这家杂货店能帮你找回——', 5, '治愈', 2, 0);
INSERT INTO `book` VALUES ('9787544270878', 1, '解忧杂货店', '[日] 东野圭吾', '南海出版公司', '2014-05-01 10:40:31', 39.50, 's2726411.jpg', '现代人内心流失的东西，这家杂货店能帮你找回——', 5, '治愈', 1, 1);

-- ----------------------------
-- Table structure for borrow_book
-- ----------------------------
DROP TABLE IF EXISTS `borrow_book`;
CREATE TABLE `borrow_book`  (
  `id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `isbn` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reader_id` int(11) NULL DEFAULT NULL,
  `borrow_date` datetime(0) NULL DEFAULT NULL,
  `return_date` datetime(0) NULL DEFAULT NULL,
  `fine` decimal(10, 0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow_book
-- ----------------------------
INSERT INTO `borrow_book` VALUES (00000000001, '9787511360809', 1, '2019-04-18 13:57:01', '2019-06-17 13:57:01', 0, 0);
INSERT INTO `borrow_book` VALUES (00000000002, '9787511360809', 1, '2019-04-18 13:59:52', '2019-06-17 13:59:52', 0, 0);
INSERT INTO `borrow_book` VALUES (00000000003, '9787511360809', 1, '2019-04-18 14:00:18', '2019-06-17 14:00:18', 0, 0);
INSERT INTO `borrow_book` VALUES (00000000004, '9787511360809', 1, '2019-04-18 14:00:27', '2019-06-17 14:00:27', 0, 0);
INSERT INTO `borrow_book` VALUES (00000000005, '9787544270878', 1, '2019-06-23 16:05:34', '2019-08-22 16:05:34', 0, 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL,
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '文学小说');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` int(11) NOT NULL,
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regdate` datetime(0) NULL DEFAULT NULL,
  `level` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES (1, '老六', 31, 1, '1234566', '2019-04-03 20:05:07', 1);

-- ----------------------------
-- Table structure for reader_level
-- ----------------------------
DROP TABLE IF EXISTS `reader_level`;
CREATE TABLE `reader_level`  (
  `id` int(11) NOT NULL,
  `max_br_num` int(11) NOT NULL,
  `max_br_day` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0000000001, 'user', '123456', 1, 1);
INSERT INTO `user` VALUES (0000000004, 'admin', '123456', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
