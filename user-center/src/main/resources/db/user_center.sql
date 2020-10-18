/*
 Navicat Premium Data Transfer

 Source Server         : 47.115.60.46
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.115.60.46:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/10/2020 15:33:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_event_log
-- ----------------------------
DROP TABLE IF EXISTS `bonus_event_log`;
CREATE TABLE `bonus_event_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` int NULL DEFAULT NULL COMMENT 'user.id',
  `value` int NULL DEFAULT NULL COMMENT '积分操作值',
  `event` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发生的事件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_bonus_event_log_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '积分变更记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bonus_event_log
-- ----------------------------
INSERT INTO `bonus_event_log` VALUES (1, 1, 50, 'CONTRIBUTE', '2020-10-07 08:03:40', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (2, 1, 50, 'CONTRIBUTE', '2020-10-07 08:04:20', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (3, 1, 50, 'CONTRIBUTE', '2020-10-07 09:35:30', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (4, 1, 50, 'CONTRIBUTE', '2020-10-08 01:51:08', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (5, 1, 50, 'CONTRIBUTE', '2020-10-08 02:05:23', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (6, 1, 50, 'CONTRIBUTE', '2020-10-08 02:07:22', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (7, 1, 50, 'CONTRIBUTE', '2020-10-08 02:08:12', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (8, 1, 50, 'CONTRIBUTE', '2020-10-08 02:09:33', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (9, 1, 50, 'CONTRIBUTE', '2020-10-08 02:10:16', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (10, 1, 50, 'CONTRIBUTE', '2020-10-08 02:10:50', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (11, 1, 50, 'CONTRIBUTE', '2020-10-08 02:47:07', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (12, 1, 50, 'CONTRIBUTE', '2020-10-08 02:52:09', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (13, 1, 50, 'CONTRIBUTE', '2020-10-08 02:53:14', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (14, 1, 50, 'CONTRIBUTE', '2020-10-08 02:54:37', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (15, 1, 50, 'CONTRIBUTE', '2020-10-08 02:55:42', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (16, 1, 50, 'CONTRIBUTE', '2020-10-08 02:56:13', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (17, 1, 50, 'CONTRIBUTE', '2020-10-08 03:06:27', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (18, 1, 50, 'CONTRIBUTE', '2020-10-08 03:07:18', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (19, 1, 50, 'CONTRIBUTE', '2020-10-08 03:07:51', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (20, 1, 50, 'CONTRIBUTE', '2020-10-08 03:17:31', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (21, 1, 50, 'CONTRIBUTE', '2020-10-08 03:19:24', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (22, 1, 50, 'CONTRIBUTE', '2020-10-08 03:19:53', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (23, 1, 50, 'CONTRIBUTE', '2020-10-08 03:23:26', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (24, 1, 50, 'CONTRIBUTE', '2020-10-08 03:24:51', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (25, 1, 50, 'CONTRIBUTE', '2020-10-08 03:33:38', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (26, 5, -68, 'CONTRIBUTE', '2020-10-14 04:38:09', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (27, 5, -68, 'CONTRIBUTE', '2020-10-14 04:41:00', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (28, 5, -68, 'CONTRIBUTE', '2020-10-14 04:45:16', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (29, 5, -68, 'CONTRIBUTE', '2020-10-14 04:53:24', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (30, 5, 0, 'CONTRIBUTE', '2020-10-14 10:00:55', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (31, 5, 20, 'SIGN_IN', '2020-10-14 11:51:47', '签到加积分');
INSERT INTO `bonus_event_log` VALUES (36, 5, 20, 'SIGN_IN', '2020-10-15 13:46:22', '签到加积分');
INSERT INTO `bonus_event_log` VALUES (39, 5, -99, 'CONTRIBUTE', '2020-10-16 08:06:54', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (40, 5, -99, 'CONTRIBUTE', '2020-10-16 08:08:25', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (41, 5, 0, 'CONTRIBUTE', '2020-10-16 08:13:51', '兑换扣积分');
INSERT INTO `bonus_event_log` VALUES (46, 5, 20, 'SIGN_IN', '2020-10-16 09:19:13', '签到加积分');
INSERT INTO `bonus_event_log` VALUES (47, 1, 50, 'CONTRIBUTE', '2020-10-16 23:15:08', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (48, 1, 50, 'CONTRIBUTE', '2020-10-16 23:18:15', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (49, 5, 50, 'CONTRIBUTE', '2020-10-17 09:44:49', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (50, 5, 50, 'CONTRIBUTE', '2020-10-17 10:02:39', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (51, 5, 20, 'SIGN_IN', '2020-10-17 17:33:59', '签到加积分');
INSERT INTO `bonus_event_log` VALUES (52, 5, 50, 'CONTRIBUTE', '2020-10-17 17:35:09', '投稿加积分');
INSERT INTO `bonus_event_log` VALUES (53, 1, 50, 'CONTRIBUTE', '2020-10-17 17:37:38', '投稿加积分');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `wx_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信id',
  `wx_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信昵称',
  `roles` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `bonus` int NOT NULL DEFAULT 300 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'king991214', '大橙子boy', '学生', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/P3.jpg', '2020-09-24 11:05:55', '2020-09-24 11:05:57', 750);
INSERT INTO `user` VALUES (2, 'xiaoming', '小明', '学生', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/P2.jpg', '2020-09-29 15:23:05', '2020-09-29 15:23:07', 550);
INSERT INTO `user` VALUES (3, 'zhangsan', '张三', 'admin', 'https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg', '2020-09-29 15:24:00', '2020-09-29 15:24:03', 550);
INSERT INTO `user` VALUES (5, 'opYTe4o6by0Dj-TmyLlUC7dK0Y1A', '测试小程序妈的', 'admin', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Woxicngb0LebU1m58cJXz4Eia0xM6VZBb3QgJDBibEQHtTjhkr9QKvYTkX7TmibIkKruicTDxFxnKZcmz6bicWj7ib59w/132', '2020-10-13 05:00:34', '2020-10-13 05:00:34', 10148);
INSERT INTO `user` VALUES (6, 'ocA5c5GnLABZwUaUnJZnHuti7C0g', 'Sunny', 'admin', 'https://thirdwx.qlogo.cn/mmopen/vi_32/RuootsViar69AZ39fu2icWR0vSuzVYM4HKrE2r4iaXeA1C9xz1cRibDia97yuLm3fs2mxePPyn3ccibicDmxXGQgYJN8w/132', '2020-10-16 09:06:26', '2020-10-16 09:06:26', 100);

SET FOREIGN_KEY_CHECKS = 1;
