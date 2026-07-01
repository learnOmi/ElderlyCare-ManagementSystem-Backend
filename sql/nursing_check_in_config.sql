-- =============================================================
-- 入住配置表 nursing_check_in_config
-- =============================================================
DROP TABLE IF EXISTS `nursing_check_in_config`;
CREATE TABLE `nursing_check_in_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `check_in_id` bigint DEFAULT NULL COMMENT '入住表ID',
  `nursing_level_id` bigint DEFAULT NULL COMMENT '护理等级ID',
  `nursing_level_name` varchar(100) DEFAULT NULL COMMENT '护理等级名称',
  `fee_start_date` date DEFAULT NULL COMMENT '费用开始时间',
  `fee_end_date` date DEFAULT NULL COMMENT '费用结束时间',
  `deposit` decimal(10,2) DEFAULT NULL COMMENT '押金（元）',
  `nursing_fee` decimal(10,2) DEFAULT NULL COMMENT '护理费用（元/月）',
  `bed_fee` decimal(10,2) DEFAULT NULL COMMENT '床位费用（元/月）',
  `insurance_payment` decimal(10,2) DEFAULT NULL COMMENT '医保支付（元/月）',
  `government_subsidy` decimal(10,2) DEFAULT NULL COMMENT '政府补贴（元/月）',
  `other_fees` decimal(10,2) DEFAULT NULL COMMENT '其他费用（元/月）',
  `sort_order` int DEFAULT NULL COMMENT '排序编号',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_check_in_id` (`check_in_id`),
  KEY `idx_nursing_level_id` (`nursing_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='入住配置表';

-- =============================================================
-- 入住配置菜单权限数据
-- =============================================================
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '入住配置', m.menu_id, 6, 'nursing/checkInConfig', 'nursing/checkInConfig/index', 'C', '0', '0', 'nursing:checkInConfig:list', '#', 'admin', NOW(), '', NULL, '入住配置管理'
FROM `sys_menu` m WHERE m.menu_name = '入住管理' LIMIT 1;

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '查询', c.menu_id, 1, '', '', 'F', '0', '0', 'nursing:checkInConfig:list', '#', 'admin', NOW(), '', NULL, '查询权限'
FROM `sys_menu` c WHERE c.perms = 'nursing:checkInConfig:list' LIMIT 1;

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '新增', c.menu_id, 2, '', '', 'F', '0', '0', 'nursing:checkInConfig:add', '#', 'admin', NOW(), '', NULL, '新增权限'
FROM `sys_menu` c WHERE c.perms = 'nursing:checkInConfig:list' LIMIT 1;

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '修改', c.menu_id, 3, '', '', 'F', '0', '0', 'nursing:checkInConfig:edit', '#', 'admin', NOW(), '', NULL, '修改权限'
FROM `sys_menu` c WHERE c.perms = 'nursing:checkInConfig:list' LIMIT 1;

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '删除', c.menu_id, 4, '', '', 'F', '0', '0', 'nursing:checkInConfig:remove', '#', 'admin', NOW(), '', NULL, '删除权限'
FROM `sys_menu` c WHERE c.perms = 'nursing:checkInConfig:list' LIMIT 1;

INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
SELECT '导出', c.menu_id, 5, '', '', 'F', '0', '0', 'nursing:checkInConfig:export', '#', 'admin', NOW(), '', NULL, '导出权限'
FROM `sys_menu` c WHERE c.perms = 'nursing:checkInConfig:list' LIMIT 1;
