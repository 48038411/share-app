/*
 Navicat Premium Data Transfer

 Source Server         : 47.115.60.46
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.115.60.46:3306
 Source Schema         : content_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/10/2020 15:33:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mid_user_share
-- ----------------------------
DROP TABLE IF EXISTS `mid_user_share`;
CREATE TABLE `mid_user_share`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `share_id` int NOT NULL COMMENT 'share.id',
  `user_id` int NOT NULL COMMENT 'user.id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mid_user_share_share1_idx`(`share_id`) USING BTREE,
  INDEX `fk_mid_user_share_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-分享中间表【描述用户购买的分享】' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
INSERT INTO `mid_user_share` VALUES (1, 1, 1);
INSERT INTO `mid_user_share` VALUES (2, 2, 1);
INSERT INTO `mid_user_share` VALUES (3, 4, 5);
INSERT INTO `mid_user_share` VALUES (8, 3, 5);
INSERT INTO `mid_user_share` VALUES (9, 2, 5);
INSERT INTO `mid_user_share` VALUES (10, 5, 5);
INSERT INTO `mid_user_share` VALUES (11, 6, 5);
INSERT INTO `mid_user_share` VALUES (12, 1, 5);
INSERT INTO `mid_user_share` VALUES (14, 2, 1);
INSERT INTO `mid_user_share` VALUES (16, 7, 5);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '注意，这里是负载均衡测试', 1, '2020-09-27 11:39:09');
INSERT INTO `notice` VALUES (2, '负载均衡测试1', 1, '2020-09-27 11:39:24');
INSERT INTO `notice` VALUES (3, '负载均衡测试2', 1, '2020-09-27 11:39:34');
INSERT INTO `notice` VALUES (4, '最新通知', 1, '2020-10-16 23:24:53');
INSERT INTO `notice` VALUES (5, '这是最新的', 1, '2020-10-16 23:25:59');
INSERT INTO `notice` VALUES (6, '公告公告', 1, '2020-10-16 23:26:41');
INSERT INTO `notice` VALUES (7, '还有很多bug', 1, '2020-10-16 23:28:38');
INSERT INTO `notice` VALUES (8, '最新公告', 1, '2020-10-17 04:35:23');
INSERT INTO `notice` VALUES (9, '最后一版', 1, '2020-10-17 04:38:22');

-- ----------------------------
-- Table structure for rocketmq_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `rocketmq_transaction_log`;
CREATE TABLE `rocketmq_transaction_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `transaction_Id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '事务id',
  `log` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'RocketMQ事务日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rocketmq_transaction_log
-- ----------------------------

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL DEFAULT 0 COMMENT '发布人id',
  `title` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_original` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否原创 0:否 1:是',
  `author` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作者',
  `cover` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `summary` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '概要信息',
  `price` int NOT NULL DEFAULT 0 COMMENT '价格（需要的积分）',
  `download_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '下载地址',
  `buy_count` int NOT NULL DEFAULT 0 COMMENT '下载数 ',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `audit_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '审核不通过原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分享表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 1, '测试吧', '2020-09-30 08:15:25', '2020-09-30 08:15:27', 0, '郭瑞昌', 'https://img.mukewang.com/szimg/5f583a2609dc33b412000676-360-202.jpg', '', 0, '1', 0, 1, 'PASS', '');
INSERT INTO `share` VALUES (2, 1, '哈哈哈', '2020-10-05 10:24:26', '2020-10-05 10:24:29', 0, '郭瑞昌', 'https://profile.csdnimg.cn/2/6/8/2_weixin_43250266', '', 0, '1', 0, 1, 'PASS', '');
INSERT INTO `share` VALUES (3, 1, 'Java', '2020-10-07 01:14:55', '2020-10-07 01:14:55', 1, '郭瑞昌', 'https://profile.csdnimg.cn/2/6/8/2_weixin_43250266', '没', 68, 'http://www.baidu.com', 0, 1, 'PASS', NULL);
INSERT INTO `share` VALUES (4, 5, '测试', '2020-10-07 01:32:22', '2020-10-07 01:32:22', 1, '郭瑞昌', 'https://profile.csdnimg.cn/2/6/8/2_weixin_43250266', '无', 0, 'wu', 0, 1, 'PASS', NULL);
INSERT INTO `share` VALUES (5, 5, '这个真不戳', '2020-10-14 21:28:18', '2020-10-14 21:28:18', 1, '郭瑞昌', 'https://profile.csdnimg.cn/2/6/8/2_weixin_43250266', '这个真的真的不错', 99, '木有', 0, 1, 'PASS', NULL);
INSERT INTO `share` VALUES (6, 5, '标题1', '2020-10-15 18:44:45', '2020-10-15 18:44:45', 0, '郭瑞昌', 'https://profile.csdnimg.cn/2/6/8/2_weixin_43250266', '没有', 99, '111', 0, 1, 'PASS', NULL);
INSERT INTO `share` VALUES (7, 5, '1', '2020-10-16 20:55:16', '2020-10-16 20:55:16', 0, '1', 'https://profile.csdnimg.cn/2/6/8/2_weixin_43250266', '1', 1, '1', 0, 1, 'PASS', NULL);

SET FOREIGN_KEY_CHECKS = 1;
