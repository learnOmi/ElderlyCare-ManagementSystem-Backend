-- =============================================================
-- 中州养老（ElderlyCare）数据库表结构修复脚本
-- 修复 Mapper XML 引用但数据库表缺失的字段
-- 创建时间：2026-07-02
-- =============================================================

-- =============================================================
-- 1. 修复 nursing_alert_data 表 - 添加缺失字段
-- =============================================================
-- 添加 alert_content 字段（告警内容）
ALTER TABLE `nursing_alert_data` 
ADD COLUMN `alert_content` VARCHAR(500) DEFAULT NULL COMMENT '告警内容' AFTER `status`;

-- 添加 monitor_value 字段（监测值）
ALTER TABLE `nursing_alert_data` 
ADD COLUMN `monitor_value` VARCHAR(100) DEFAULT NULL COMMENT '监测值' AFTER `alert_content`;

-- 添加 unit 字段（单位）
ALTER TABLE `nursing_alert_data` 
ADD COLUMN `unit` VARCHAR(50) DEFAULT NULL COMMENT '单位' AFTER `monitor_value`;

-- =============================================================
-- 2. 修复 nursing_device 表 - 添加缺失字段
-- =============================================================
-- 添加 unit 字段（单位）
ALTER TABLE `nursing_device` 
ADD COLUMN `unit` VARCHAR(50) DEFAULT NULL COMMENT '监测单位' AFTER `status`;

-- =============================================================
-- 3. 修复 nursing_level 表 - 添加缺失字段
-- =============================================================
-- 添加 sort_order 字段（排序号）
ALTER TABLE `nursing_level` 
ADD COLUMN `sort_order` INT(11) DEFAULT '0' COMMENT '排序号' AFTER `description`;

-- =============================================================
-- 4. 修复 nursing_floor 表 - 添加缺失字段
-- =============================================================
-- 添加 bed_count 字段（床位数）
ALTER TABLE `nursing_floor` 
ADD COLUMN `bed_count` INT(11) DEFAULT '0' COMMENT '床位数' AFTER `floor_no`;

-- =============================================================
-- 5. 修复 nursing_room 表 - 添加缺失字段
-- =============================================================
-- 添加 bed_count 字段（床位数）
ALTER TABLE `nursing_room` 
ADD COLUMN `bed_count` INT(11) DEFAULT '0' COMMENT '床位数' AFTER `room_type_id`;

-- =============================================================
-- 6. 修复 nursing_elder 表 - 添加缺失字段
-- =============================================================
-- 添加 plan_id 字段（护理计划ID）
ALTER TABLE `nursing_elder` 
ADD COLUMN `plan_id` BIGINT(20) DEFAULT NULL COMMENT '护理计划ID' AFTER `level_id`;

-- =============================================================
-- 6. 更新 nursing_level 表的排序号（初始数据）
-- =============================================================
UPDATE `nursing_level` SET `sort_order` = id WHERE `sort_order` IS NULL OR `sort_order` = 0;

-- =============================================================
-- 7. 更新 nursing_floor 表的床位数（根据实际数据计算）
-- =============================================================
UPDATE `nursing_floor` f 
SET `bed_count` = (
    SELECT COUNT(*) 
    FROM `nursing_bed` b 
    JOIN `nursing_room` r ON b.room_id = r.id 
    WHERE r.floor_id = f.id
);

-- =============================================================
-- 8. 更新 nursing_room 表的床位数（根据实际数据计算）
-- =============================================================
UPDATE `nursing_room` r 
SET `bed_count` = (
    SELECT COUNT(*) 
    FROM `nursing_bed` b 
    WHERE b.room_id = r.id
);

-- =============================================================
-- 9. 确认修复结果
-- =============================================================
SELECT 'nursing_alert_data' as table_name, 'alert_content' as column_name, IF(COLUMN_NAME IS NOT NULL, 'OK', 'MISSING') as status 
FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='nursing_alert_data' AND COLUMN_NAME='alert_content'
UNION ALL
SELECT 'nursing_alert_data' as table_name, 'monitor_value' as column_name, IF(COLUMN_NAME IS NOT NULL, 'OK', 'MISSING') as status 
FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='nursing_alert_data' AND COLUMN_NAME='monitor_value'
UNION ALL
SELECT 'nursing_alert_data' as table_name, 'unit' as column_name, IF(COLUMN_NAME IS NOT NULL, 'OK', 'MISSING') as status 
FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='nursing_alert_data' AND COLUMN_NAME='unit'
UNION ALL
SELECT 'nursing_device' as table_name, 'unit' as column_name, IF(COLUMN_NAME IS NOT NULL, 'OK', 'MISSING') as status 
FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='nursing_device' AND COLUMN_NAME='unit'
UNION ALL
SELECT 'nursing_level' as table_name, 'sort_order' as column_name, IF(COLUMN_NAME IS NOT NULL, 'OK', 'MISSING') as status 
FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='nursing_level' AND COLUMN_NAME='sort_order';
