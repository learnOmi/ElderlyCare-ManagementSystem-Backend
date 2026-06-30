-- =============================================================
-- 导出权限菜单补齐脚本
-- 说明：为各模块补齐导出(export)权限菜单
-- 执行方式：在数据库中执行此脚本
-- =============================================================

-- ----------------------------
-- 1. 楼层导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '楼层导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:floor:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:floor:list' LIMIT 1;

-- ----------------------------
-- 2. 房型导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '房型导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:roomType:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:roomType:list' LIMIT 1;

-- ----------------------------
-- 3. 房间导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '房间导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:room:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:room:list' LIMIT 1;

-- ----------------------------
-- 4. 床位导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '床位导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:bed:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:bed:list' LIMIT 1;

-- ----------------------------
-- 5. 设备导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '设备导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:device:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:device:list' LIMIT 1;

-- ----------------------------
-- 6. 护理项目导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '项目导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:project:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:project:list' LIMIT 1;

-- ----------------------------
-- 7. 护理计划导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '计划导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:plan:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:plan:list' LIMIT 1;

-- ----------------------------
-- 8. 护理等级导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '等级导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:level:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:level:list' LIMIT 1;

-- ----------------------------
-- 9. 老人档案导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '老人导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:elder:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:elder:list' LIMIT 1;

-- ----------------------------
-- 10. 入住管理导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '入住导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:checkIn:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:checkIn:list' LIMIT 1;

-- ----------------------------
-- 11. 健康评估导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '评估导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:healthAssessment:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:healthAssessment:list' LIMIT 1;

-- ----------------------------
-- 12. 护理排班导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '排班导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:arrange:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:arrange:list' LIMIT 1;

-- ----------------------------
-- 13. 告警数据导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '告警导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:alertData:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:alertData:list' LIMIT 1;

-- ----------------------------
-- 14. 合同管理导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '合同导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:contract:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:contract:list' LIMIT 1;

-- ----------------------------
-- 15. 预约管理导出权限
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '预约导出', menu_id, 5, '', '', 'F', '0', '0', 'nursing:reservation:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` WHERE `perms` = 'nursing:reservation:list' LIMIT 1;
