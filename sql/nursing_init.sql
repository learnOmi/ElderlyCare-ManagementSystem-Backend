-- =============================================================
-- 中州养老（ElderlyCare）业务模块初始化脚本
-- 模块：nursing-platform（养老护理综合业务平台）
-- 前缀：nursing_
-- 参考：codeRef 前端完整功能
-- 创建时间：2026-06-26
-- =============================================================

-- =============================================================
-- 一、基础设施模块（楼层、房型、房间、床位、设备）
-- =============================================================

-- ----------------------------
-- 1. 楼层表 (nursing_floor)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_floor`;
CREATE TABLE `nursing_floor` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '楼层ID',
  `name`          VARCHAR(100)    NOT NULL                 COMMENT '楼层名称',
  `floor_no`      INT(11)         NOT NULL                 COMMENT '楼层编号',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='楼层表';

-- ----------------------------
-- 2. 房型表 (nursing_room_type)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_room_type`;
CREATE TABLE `nursing_room_type` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '房型ID',
  `name`          VARCHAR(100)    NOT NULL                 COMMENT '房型名称',
  `bed_count`     INT(11)         DEFAULT '1'              COMMENT '床位数量',
  `price`         DECIMAL(10,2)   DEFAULT NULL             COMMENT '房型价格',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `description`   VARCHAR(500)    DEFAULT NULL             COMMENT '房型描述',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='房型表';

-- ----------------------------
-- 3. 房间表 (nursing_room)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_room`;
CREATE TABLE `nursing_room` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '房间ID',
  `room_no`       VARCHAR(50)     NOT NULL                 COMMENT '房间编号',
  `floor_id`      BIGINT(20)      NOT NULL                 COMMENT '所属楼层ID',
  `room_type_id`  BIGINT(20)      DEFAULT NULL             COMMENT '房型ID',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_floor_id` (`floor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='房间表';

-- ----------------------------
-- 4. 床位表 (nursing_bed)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_bed`;
CREATE TABLE `nursing_bed` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '床位ID',
  `bed_no`        VARCHAR(50)     NOT NULL                 COMMENT '床位编号',
  `room_id`       BIGINT(20)      NOT NULL                 COMMENT '所属房间ID',
  `bed_status`    TINYINT(1)      DEFAULT '0'              COMMENT '床位状态(0:空闲, 1:已入住, 2:维修中, 3:预订)',
  `device_bind`   TINYINT(1)      DEFAULT '0'              COMMENT '设备绑定(0:未绑定, 1:已绑定)',
  `device_no`     VARCHAR(100)    DEFAULT NULL             COMMENT '设备编号',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_room_id` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='床位表';

-- ----------------------------
-- 5. 设备表 (nursing_device)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_device`;
CREATE TABLE `nursing_device` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '设备ID',
  `device_no`     VARCHAR(100)    NOT NULL                 COMMENT '设备编号',
  `device_name`   VARCHAR(100)    NOT NULL                 COMMENT '设备名称',
  `device_type`   TINYINT(1)      DEFAULT '0'              COMMENT '设备类型(0:智能床, 1:呼叫器, 2:监测设备, 3:其他)',
  `bed_id`        BIGINT(20)      DEFAULT NULL             COMMENT '绑定床位ID',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:离线, 1:在线, 2:故障)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_device_no` (`device_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='设备表';

-- =============================================================
-- 二、护理服务模块（项目、计划、等级、项目计划关联）
-- =============================================================

-- ----------------------------
-- 6. 护理项目表 (nursing_project)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_project`;
CREATE TABLE `nursing_project` (
  `id`                    BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '项目ID',
  `name`                  VARCHAR(100)    NOT NULL                 COMMENT '项目名称',
  `order_no`              INT(11)         DEFAULT '0'              COMMENT '排序号',
  `unit`                  VARCHAR(50)     DEFAULT NULL             COMMENT '单位',
  `price`                 DECIMAL(10,2)   DEFAULT NULL             COMMENT '价格',
  `image`                 VARCHAR(500)    DEFAULT NULL             COMMENT '图片',
  `nursing_requirement`   VARCHAR(1000)   DEFAULT NULL             COMMENT '护理要求',
  `status`                TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`             VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`           DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`             VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`           DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`                VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='护理项目表';

-- ----------------------------
-- 7. 护理计划表 (nursing_plan)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_plan`;
CREATE TABLE `nursing_plan` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '计划ID',
  `plan_name`     VARCHAR(100)    NOT NULL                 COMMENT '计划名称',
  `sort_no`       INT(11)         DEFAULT '0'              COMMENT '排序号',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='护理计划表';

-- ----------------------------
-- 8. 护理等级表 (nursing_level)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_level`;
CREATE TABLE `nursing_level` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '等级ID',
  `name`          VARCHAR(100)    NOT NULL                 COMMENT '等级名称',
  `plan_id`       BIGINT(20)      DEFAULT NULL             COMMENT '关联护理计划ID',
  `fee`           DECIMAL(10,2)   DEFAULT NULL             COMMENT '护理费用',
  `description`   VARCHAR(500)    DEFAULT NULL             COMMENT '等级描述',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='护理等级表';

-- ----------------------------
-- 9. 护理项目计划关联表 (nursing_project_plan)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_project_plan`;
CREATE TABLE `nursing_project_plan` (
  `id`                BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '主键ID',
  `plan_id`           BIGINT(20)      NOT NULL                 COMMENT '护理计划ID',
  `project_id`        BIGINT(20)      NOT NULL                 COMMENT '护理项目ID',
  `execute_time`      VARCHAR(50)     DEFAULT NULL             COMMENT '执行时间',
  `execute_cycle`     TINYINT(1)      DEFAULT '0'              COMMENT '执行周期(0:天, 1:周, 2:月)',
  `execute_frequency` INT(11)         DEFAULT '1'              COMMENT '执行频次',
  `create_by`         VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`       DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`         VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`       DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`            VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_project_id` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='护理项目计划关联表';

-- =============================================================
-- 三、老人档案模块（老人信息、家属、老人家属关联）
-- =============================================================

-- ----------------------------
-- 10. 老人信息表 (nursing_elder)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_elder`;
CREATE TABLE `nursing_elder` (
  `id`                BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '老人ID',
  `name`              VARCHAR(50)     NOT NULL                 COMMENT '姓名',
  `gender`            TINYINT(1)      DEFAULT '0'              COMMENT '性别(0:男, 1:女)',
  `birthday`          DATE            DEFAULT NULL             COMMENT '出生日期',
  `age`               INT(11)         DEFAULT NULL             COMMENT '年龄',
  `id_card`           VARCHAR(18)     DEFAULT NULL             COMMENT '身份证号',
  `phone`             VARCHAR(20)     DEFAULT NULL             COMMENT '联系电话',
  `address`           VARCHAR(200)    DEFAULT NULL             COMMENT '家庭住址',
  `avatar`            VARCHAR(500)    DEFAULT NULL             COMMENT '头像',
  `level_id`          BIGINT(20)      DEFAULT NULL             COMMENT '护理等级ID',
  `bed_id`            BIGINT(20)      DEFAULT NULL             COMMENT '入住床位ID',
  `check_in_status`   TINYINT(1)      DEFAULT '0'              COMMENT '入住状态(0:未入住, 1:已入住, 2:已退住)',
  `health_status`     TINYINT(1)      DEFAULT '0'              COMMENT '健康状态(0:自理, 1:半自理, 2:不能自理)',
  `status`            TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`         VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`       DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`         VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`       DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`            VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_bed_id` (`bed_id`),
  KEY `idx_level_id` (`level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='老人信息表';

-- ----------------------------
-- 11. 家属信息表 (nursing_family)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_family`;
CREATE TABLE `nursing_family` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '家属ID',
  `name`          VARCHAR(50)     NOT NULL                 COMMENT '姓名',
  `gender`        TINYINT(1)      DEFAULT '0'              COMMENT '性别(0:男, 1:女)',
  `phone`         VARCHAR(20)     DEFAULT NULL             COMMENT '联系电话',
  `id_card`       VARCHAR(18)     DEFAULT NULL             COMMENT '身份证号',
  `address`       VARCHAR(200)    DEFAULT NULL             COMMENT '住址',
  `relation`      VARCHAR(50)     DEFAULT NULL             COMMENT '与老人关系',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='家属信息表';

-- ----------------------------
-- 12. 老人家属关联表 (nursing_elder_family)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_elder_family`;
CREATE TABLE `nursing_elder_family` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '主键ID',
  `elder_id`      BIGINT(20)      NOT NULL                 COMMENT '老人ID',
  `family_id`     BIGINT(20)      NOT NULL                 COMMENT '家属ID',
  `relation`      VARCHAR(50)     DEFAULT NULL             COMMENT '亲属关系',
  `is_emergency`  TINYINT(1)      DEFAULT '0'              COMMENT '是否紧急联系人(0:否, 1:是)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_elder_id` (`elder_id`),
  KEY `idx_family_id` (`family_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='老人家属关联表';

-- =============================================================
-- 四、入住管理模块
-- =============================================================

-- ----------------------------
-- 13. 入住申请表 (nursing_check_in)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_check_in`;
CREATE TABLE `nursing_check_in` (
  `id`                BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '入住ID',
  `elder_id`          BIGINT(20)      NOT NULL                 COMMENT '老人ID',
  `bed_id`            BIGINT(20)      DEFAULT NULL             COMMENT '入住床位ID',
  `level_id`          BIGINT(20)      DEFAULT NULL             COMMENT '护理等级ID',
  `plan_id`           BIGINT(20)      DEFAULT NULL             COMMENT '护理计划ID',
  `apply_date`        DATE            DEFAULT NULL             COMMENT '申请日期',
  `check_in_date`     DATE            DEFAULT NULL             COMMENT '入住日期',
  `expected_check_out_date` DATE      DEFAULT NULL             COMMENT '预计退住日期',
  `check_out_date`    DATE            DEFAULT NULL             COMMENT '实际退住日期',
  `status`            TINYINT(1)      DEFAULT '0'              COMMENT '状态(0:待审核, 1:已审核, 2:已入住, 3:已退住, 4:已拒绝)',
  `audit_by`          VARCHAR(64)     DEFAULT ''               COMMENT '审核人',
  `audit_time`        DATETIME        DEFAULT NULL             COMMENT '审核时间',
  `audit_remark`      VARCHAR(500)    DEFAULT NULL             COMMENT '审核意见',
  `create_by`         VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`       DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`         VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`       DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`            VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_elder_id` (`elder_id`),
  KEY `idx_bed_id` (`bed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='入住申请表';

-- =============================================================
-- 五、健康评估模块
-- =============================================================

-- ----------------------------
-- 14. 健康评估表 (nursing_health_assessment)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_health_assessment`;
CREATE TABLE `nursing_health_assessment` (
  `id`                BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '评估ID',
  `elder_id`          BIGINT(20)      NOT NULL                 COMMENT '老人ID',
  `assessment_date`   DATE            DEFAULT NULL             COMMENT '评估日期',
  `assessor`          VARCHAR(64)     DEFAULT NULL             COMMENT '评估人',
  `physical_score`    INT(11)         DEFAULT '0'              COMMENT '身体状况评分',
  `mental_score`      INT(11)         DEFAULT '0'              COMMENT '精神状况评分',
  `daily_score`       INT(11)         DEFAULT '0'              COMMENT '日常生活能力评分',
  `social_score`      INT(11)         DEFAULT '0'              COMMENT '社会适应能力评分',
  `total_score`       INT(11)         DEFAULT '0'              COMMENT '综合评分',
  `suggested_level`   BIGINT(20)      DEFAULT NULL             COMMENT '建议护理等级',
  `assessment_result` VARCHAR(2000)   DEFAULT NULL             COMMENT '评估结果',
  `status`            TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`         VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`       DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`         VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`       DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`            VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_elder_id` (`elder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='健康评估表';

-- =============================================================
-- 六、护理排班模块
-- =============================================================

-- ----------------------------
-- 15. 护理排班表 (nursing_arrange)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_arrange`;
CREATE TABLE `nursing_arrange` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '排班ID',
  `arrange_no`    VARCHAR(50)     DEFAULT NULL             COMMENT '排班编号',
  `elder_id`      BIGINT(20)      DEFAULT NULL             COMMENT '老人ID',
  `project_id`    BIGINT(20)      DEFAULT NULL             COMMENT '护理项目ID',
  `nurse_id`      BIGINT(20)      DEFAULT NULL             COMMENT '护理员ID',
  `plan_date`     DATE            DEFAULT NULL             COMMENT '计划日期',
  `plan_time`     VARCHAR(20)     DEFAULT NULL             COMMENT '计划时间',
  `execute_time`  DATETIME        DEFAULT NULL             COMMENT '实际执行时间',
  `execute_by`    VARCHAR(64)     DEFAULT ''               COMMENT '执行人',
  `status`        TINYINT(1)      DEFAULT '0'              COMMENT '状态(0:待执行, 1:已执行, 2:已取消, 3:执行中)',
  `cancel_reason` VARCHAR(500)    DEFAULT NULL             COMMENT '取消原因',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_elder_id` (`elder_id`),
  KEY `idx_plan_date` (`plan_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='护理排班表';

-- =============================================================
-- 七、告警管理模块
-- =============================================================

-- ----------------------------
-- 16. 告警规则表 (nursing_alert_rule)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_alert_rule`;
CREATE TABLE `nursing_alert_rule` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '规则ID',
  `rule_name`     VARCHAR(100)    NOT NULL                 COMMENT '规则名称',
  `alert_type`    TINYINT(1)      DEFAULT '0'              COMMENT '告警类型(0:离床, 1:坠床, 2:心率异常, 3:呼吸异常, 4:其他)',
  `threshold`     VARCHAR(100)    DEFAULT NULL             COMMENT '告警阈值',
  `level`         TINYINT(1)      DEFAULT '1'              COMMENT '告警级别(0:一般, 1:重要, 2:紧急)',
  `notify_type`   TINYINT(1)      DEFAULT '0'              COMMENT '通知方式(0:系统通知, 1:短信, 2:电话)',
  `status`        TINYINT(1)      DEFAULT '1'              COMMENT '状态(0:禁用, 1:启用)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='告警规则表';

-- ----------------------------
-- 17. 告警数据表 (nursing_alert_data)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_alert_data`;
CREATE TABLE `nursing_alert_data` (
  `id`            BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '告警ID',
  `alert_no`      VARCHAR(50)     DEFAULT NULL             COMMENT '告警编号',
  `elder_id`      BIGINT(20)      DEFAULT NULL             COMMENT '老人ID',
  `bed_id`        BIGINT(20)      DEFAULT NULL             COMMENT '床位ID',
  `device_id`     BIGINT(20)      DEFAULT NULL             COMMENT '设备ID',
  `rule_id`       BIGINT(20)      DEFAULT NULL             COMMENT '告警规则ID',
  `alert_type`    TINYINT(1)      DEFAULT '0'              COMMENT '告警类型(0:离床, 1:坠床, 2:心率异常, 3:呼吸异常, 4:其他)',
  `level`         TINYINT(1)      DEFAULT '1'              COMMENT '告警级别(0:一般, 1:重要, 2:紧急)',
  `alert_value`   VARCHAR(100)    DEFAULT NULL             COMMENT '告警值',
  `alert_time`    DATETIME        DEFAULT NULL             COMMENT '告警时间',
  `handle_time`   DATETIME        DEFAULT NULL             COMMENT '处理时间',
  `handle_by`     VARCHAR(64)     DEFAULT ''               COMMENT '处理人',
  `handle_result` VARCHAR(500)    DEFAULT NULL             COMMENT '处理结果',
  `status`        TINYINT(1)      DEFAULT '0'              COMMENT '状态(0:未处理, 1:处理中, 2:已处理, 3:已忽略)',
  `create_by`     VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`   DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`     VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`   DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`        VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_elder_id` (`elder_id`),
  KEY `idx_alert_time` (`alert_time`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='告警数据表';

-- =============================================================
-- 八、合同管理模块
-- =============================================================

-- ----------------------------
-- 18. 合同表 (nursing_contract)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_contract`;
CREATE TABLE `nursing_contract` (
  `id`                BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '合同ID',
  `contract_no`       VARCHAR(50)     NOT NULL                 COMMENT '合同编号',
  `elder_id`          BIGINT(20)      NOT NULL                 COMMENT '老人ID',
  `check_in_id`       BIGINT(20)      DEFAULT NULL             COMMENT '入住ID',
  `contract_type`     TINYINT(1)      DEFAULT '0'              COMMENT '合同类型(0:入住合同, 1:服务合同, 2:其他)',
  `sign_date`         DATE            DEFAULT NULL             COMMENT '签订日期',
  `start_date`        DATE            DEFAULT NULL             COMMENT '开始日期',
  `end_date`          DATE            DEFAULT NULL             COMMENT '结束日期',
  `total_amount`      DECIMAL(10,2)   DEFAULT NULL             COMMENT '合同总金额',
  `status`            TINYINT(1)      DEFAULT '0'              COMMENT '状态(0:草稿, 1:已签订, 2:已生效, 3:已到期, 4:已终止)',
  `terminate_reason`  VARCHAR(500)    DEFAULT NULL             COMMENT '终止原因',
  `terminate_date`    DATE            DEFAULT NULL             COMMENT '终止日期',
  `create_by`         VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`       DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`         VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`       DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`            VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_contract_no` (`contract_no`),
  KEY `idx_elder_id` (`elder_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='合同表';

-- =============================================================
-- 九、预约管理模块
-- =============================================================

-- ----------------------------
-- 19. 预约表 (nursing_reservation)
-- ----------------------------
DROP TABLE IF EXISTS `nursing_reservation`;
CREATE TABLE `nursing_reservation` (
  `id`                BIGINT(20)      NOT NULL AUTO_INCREMENT  COMMENT '预约ID',
  `reservation_no`    VARCHAR(50)     DEFAULT NULL             COMMENT '预约编号',
  `reservation_type`  TINYINT(1)      DEFAULT '0'              COMMENT '预约类型(0:来访预约, 1:入住预约, 2:参观预约)',
  `name`              VARCHAR(50)     NOT NULL                 COMMENT '预约人姓名',
  `phone`             VARCHAR(20)     DEFAULT NULL             COMMENT '联系电话',
  `elder_name`        VARCHAR(50)     DEFAULT NULL             COMMENT '老人姓名',
  `reservation_date`  DATE            DEFAULT NULL             COMMENT '预约日期',
  `reservation_time`  VARCHAR(20)     DEFAULT NULL             COMMENT '预约时间',
  `visitor_count`     INT(11)         DEFAULT '1'              COMMENT '来访人数',
  `status`            TINYINT(1)      DEFAULT '0'              COMMENT '状态(0:待确认, 1:已确认, 2:已完成, 3:已取消)',
  `cancel_reason`     VARCHAR(500)    DEFAULT NULL             COMMENT '取消原因',
  `create_by`         VARCHAR(64)     DEFAULT ''               COMMENT '创建者',
  `create_time`       DATETIME        DEFAULT NULL             COMMENT '创建时间',
  `update_by`         VARCHAR(64)     DEFAULT ''               COMMENT '更新者',
  `update_time`       DATETIME        DEFAULT NULL             COMMENT '更新时间',
  `remark`            VARCHAR(500)    DEFAULT NULL             COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_reservation_date` (`reservation_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='预约表';

-- =============================================================
-- 十、菜单数据（养老护理一级菜单 + 二级子菜单）
-- 说明：menu_type — M:目录 C:菜单 F:按钮
--       perms 格式：nursing:xxx:操作
-- =============================================================

-- ----------------------------
-- 一级菜单：养老护理
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('养老护理', 0, 6, 'nursing', NULL, 'M', '0', '0', '', 'nursing', 'admin', NOW(), '', NULL, '养老护理目录');

SET @parentId_nursing = (SELECT MAX(menu_id) FROM sys_menu WHERE parent_id = 0);

-- ----------------------------
-- 二级菜单：基础数据
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('基础数据', @parentId_nursing, 1, 'base', NULL, 'M', '0', '0', '', 'tree', 'admin', NOW(), '', NULL, '基础数据目录');

SET @parentId_base = LAST_INSERT_ID();

-- 楼层管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('楼层管理', @parentId_base, 1, 'floor', 'nursing/floor/index', 'C', '0', '0', 'nursing:floor:list', 'build', 'admin', NOW(), '', NULL, '楼层管理菜单');
SET @menuFloor = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('楼层查询', @menuFloor, 1, '', '', 'F', '0', '0', 'nursing:floor:query', '#', 'admin', NOW(), '', NULL, ''),
('楼层新增', @menuFloor, 2, '', '', 'F', '0', '0', 'nursing:floor:add', '#', 'admin', NOW(), '', NULL, ''),
('楼层修改', @menuFloor, 3, '', '', 'F', '0', '0', 'nursing:floor:edit', '#', 'admin', NOW(), '', NULL, ''),
('楼层删除', @menuFloor, 4, '', '', 'F', '0', '0', 'nursing:floor:remove', '#', 'admin', NOW(), '', NULL, '');

-- 房型管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('房型管理', @parentId_base, 2, 'roomType', 'nursing/roomType/index', 'C', '0', '0', 'nursing:roomType:list', 'table', 'admin', NOW(), '', NULL, '房型管理菜单');
SET @menuRoomType = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('房型查询', @menuRoomType, 1, '', '', 'F', '0', '0', 'nursing:roomType:query', '#', 'admin', NOW(), '', NULL, ''),
('房型新增', @menuRoomType, 2, '', '', 'F', '0', '0', 'nursing:roomType:add', '#', 'admin', NOW(), '', NULL, ''),
('房型修改', @menuRoomType, 3, '', '', 'F', '0', '0', 'nursing:roomType:edit', '#', 'admin', NOW(), '', NULL, ''),
('房型删除', @menuRoomType, 4, '', '', 'F', '0', '0', 'nursing:roomType:remove', '#', 'admin', NOW(), '', NULL, '');

-- 房间管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('房间管理', @parentId_base, 3, 'room', 'nursing/room/index', 'C', '0', '0', 'nursing:room:list', 'chart', 'admin', NOW(), '', NULL, '房间管理菜单');
SET @menuRoom = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('房间查询', @menuRoom, 1, '', '', 'F', '0', '0', 'nursing:room:query', '#', 'admin', NOW(), '', NULL, ''),
('房间新增', @menuRoom, 2, '', '', 'F', '0', '0', 'nursing:room:add', '#', 'admin', NOW(), '', NULL, ''),
('房间修改', @menuRoom, 3, '', '', 'F', '0', '0', 'nursing:room:edit', '#', 'admin', NOW(), '', NULL, ''),
('房间删除', @menuRoom, 4, '', '', 'F', '0', '0', 'nursing:room:remove', '#', 'admin', NOW(), '', NULL, '');

-- 床位管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('床位管理', @parentId_base, 4, 'bed', 'nursing/bed/index', 'C', '0', '0', 'nursing:bed:list', 'sleep', 'admin', NOW(), '', NULL, '床位管理菜单');
SET @menuBed = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('床位查询', @menuBed, 1, '', '', 'F', '0', '0', 'nursing:bed:query', '#', 'admin', NOW(), '', NULL, ''),
('床位新增', @menuBed, 2, '', '', 'F', '0', '0', 'nursing:bed:add', '#', 'admin', NOW(), '', NULL, ''),
('床位修改', @menuBed, 3, '', '', 'F', '0', '0', 'nursing:bed:edit', '#', 'admin', NOW(), '', NULL, ''),
('床位删除', @menuBed, 4, '', '', 'F', '0', '0', 'nursing:bed:remove', '#', 'admin', NOW(), '', NULL, '');

-- 设备管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('设备管理', @parentId_base, 5, 'device', 'nursing/device/index', 'C', '0', '0', 'nursing:device:list', 'server', 'admin', NOW(), '', NULL, '设备管理菜单');
SET @menuDevice = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('设备查询', @menuDevice, 1, '', '', 'F', '0', '0', 'nursing:device:query', '#', 'admin', NOW(), '', NULL, ''),
('设备新增', @menuDevice, 2, '', '', 'F', '0', '0', 'nursing:device:add', '#', 'admin', NOW(), '', NULL, ''),
('设备修改', @menuDevice, 3, '', '', 'F', '0', '0', 'nursing:device:edit', '#', 'admin', NOW(), '', NULL, ''),
('设备删除', @menuDevice, 4, '', '', 'F', '0', '0', 'nursing:device:remove', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 二级菜单：护理服务
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('护理服务', @parentId_nursing, 2, 'serve', NULL, 'M', '0', '0', '', 'skill', 'admin', NOW(), '', NULL, '护理服务目录');

SET @parentId_serve = LAST_INSERT_ID();

-- 护理项目
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('护理项目', @parentId_serve, 1, 'project', 'nursing/project/index', 'C', '0', '0', 'nursing:project:list', 'example', 'admin', NOW(), '', NULL, '护理项目菜单');
SET @menuProject = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('项目查询', @menuProject, 1, '', '', 'F', '0', '0', 'nursing:project:query', '#', 'admin', NOW(), '', NULL, ''),
('项目新增', @menuProject, 2, '', '', 'F', '0', '0', 'nursing:project:add', '#', 'admin', NOW(), '', NULL, ''),
('项目修改', @menuProject, 3, '', '', 'F', '0', '0', 'nursing:project:edit', '#', 'admin', NOW(), '', NULL, ''),
('项目删除', @menuProject, 4, '', '', 'F', '0', '0', 'nursing:project:remove', '#', 'admin', NOW(), '', NULL, '');

-- 护理计划
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('护理计划', @parentId_serve, 2, 'plan', 'nursing/plan/index', 'C', '0', '0', 'nursing:plan:list', 'form', 'admin', NOW(), '', NULL, '护理计划菜单');
SET @menuPlan = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('计划查询', @menuPlan, 1, '', '', 'F', '0', '0', 'nursing:plan:query', '#', 'admin', NOW(), '', NULL, ''),
('计划新增', @menuPlan, 2, '', '', 'F', '0', '0', 'nursing:plan:add', '#', 'admin', NOW(), '', NULL, ''),
('计划修改', @menuPlan, 3, '', '', 'F', '0', '0', 'nursing:plan:edit', '#', 'admin', NOW(), '', NULL, ''),
('计划删除', @menuPlan, 4, '', '', 'F', '0', '0', 'nursing:plan:remove', '#', 'admin', NOW(), '', NULL, '');

-- 护理等级
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('护理等级', @parentId_serve, 3, 'level', 'nursing/level/index', 'C', '0', '0', 'nursing:level:list', 'chart', 'admin', NOW(), '', NULL, '护理等级菜单');
SET @menuLevel = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('等级查询', @menuLevel, 1, '', '', 'F', '0', '0', 'nursing:level:query', '#', 'admin', NOW(), '', NULL, ''),
('等级新增', @menuLevel, 2, '', '', 'F', '0', '0', 'nursing:level:add', '#', 'admin', NOW(), '', NULL, ''),
('等级修改', @menuLevel, 3, '', '', 'F', '0', '0', 'nursing:level:edit', '#', 'admin', NOW(), '', NULL, ''),
('等级删除', @menuLevel, 4, '', '', 'F', '0', '0', 'nursing:level:remove', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 二级菜单：老人管理
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('老人管理', @parentId_nursing, 3, 'elder', NULL, 'M', '0', '0', '', 'user', 'admin', NOW(), '', NULL, '老人管理目录');

SET @parentId_elder = LAST_INSERT_ID();

-- 老人档案
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('老人档案', @parentId_elder, 1, 'elder', 'nursing/elder/index', 'C', '0', '0', 'nursing:elder:list', 'peoples', 'admin', NOW(), '', NULL, '老人档案菜单');
SET @menuElder = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('老人查询', @menuElder, 1, '', '', 'F', '0', '0', 'nursing:elder:query', '#', 'admin', NOW(), '', NULL, ''),
('老人新增', @menuElder, 2, '', '', 'F', '0', '0', 'nursing:elder:add', '#', 'admin', NOW(), '', NULL, ''),
('老人修改', @menuElder, 3, '', '', 'F', '0', '0', 'nursing:elder:edit', '#', 'admin', NOW(), '', NULL, ''),
('老人删除', @menuElder, 4, '', '', 'F', '0', '0', 'nursing:elder:remove', '#', 'admin', NOW(), '', NULL, '');

-- 入住管理
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('入住管理', @parentId_elder, 2, 'checkIn', 'nursing/checkIn/index', 'C', '0', '0', 'nursing:checkIn:list', 'logininfor', 'admin', NOW(), '', NULL, '入住管理菜单');
SET @menuCheckIn = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('入住查询', @menuCheckIn, 1, '', '', 'F', '0', '0', 'nursing:checkIn:query', '#', 'admin', NOW(), '', NULL, ''),
('入住申请', @menuCheckIn, 2, '', '', 'F', '0', '0', 'nursing:checkIn:add', '#', 'admin', NOW(), '', NULL, ''),
('入住审核', @menuCheckIn, 3, '', '', 'F', '0', '0', 'nursing:checkIn:audit', '#', 'admin', NOW(), '', NULL, ''),
('入住修改', @menuCheckIn, 4, '', '', 'F', '0', '0', 'nursing:checkIn:edit', '#', 'admin', NOW(), '', NULL, '');

-- 健康评估
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('健康评估', @parentId_elder, 3, 'healthAssessment', 'nursing/healthAssessment/index', 'C', '0', '0', 'nursing:healthAssessment:list', 'edit', 'admin', NOW(), '', NULL, '健康评估菜单');
SET @menuHealth = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('评估查询', @menuHealth, 1, '', '', 'F', '0', '0', 'nursing:healthAssessment:query', '#', 'admin', NOW(), '', NULL, ''),
('评估新增', @menuHealth, 2, '', '', 'F', '0', '0', 'nursing:healthAssessment:add', '#', 'admin', NOW(), '', NULL, ''),
('评估修改', @menuHealth, 3, '', '', 'F', '0', '0', 'nursing:healthAssessment:edit', '#', 'admin', NOW(), '', NULL, ''),
('评估删除', @menuHealth, 4, '', '', 'F', '0', '0', 'nursing:healthAssessment:remove', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 二级菜单：护理排班
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('护理排班', @parentId_nursing, 4, 'arrange', 'nursing/arrange/index', 'C', '0', '0', 'nursing:arrange:list', 'date', 'admin', NOW(), '', NULL, '护理排班菜单');
SET @menuArrange = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('排班查询', @menuArrange, 1, '', '', 'F', '0', '0', 'nursing:arrange:query', '#', 'admin', NOW(), '', NULL, ''),
('排班新增', @menuArrange, 2, '', '', 'F', '0', '0', 'nursing:arrange:add', '#', 'admin', NOW(), '', NULL, ''),
('排班修改', @menuArrange, 3, '', '', 'F', '0', '0', 'nursing:arrange:edit', '#', 'admin', NOW(), '', NULL, ''),
('排班删除', @menuArrange, 4, '', '', 'F', '0', '0', 'nursing:arrange:remove', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 二级菜单：告警管理
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('告警管理', @parentId_nursing, 5, 'alert', NULL, 'M', '0', '0', '', 'message', 'admin', NOW(), '', NULL, '告警管理目录');

SET @parentId_alert = LAST_INSERT_ID();

-- 告警数据
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('告警数据', @parentId_alert, 1, 'alertData', 'nursing/alertData/index', 'C', '0', '0', 'nursing:alertData:list', 'email', 'admin', NOW(), '', NULL, '告警数据菜单');
SET @menuAlertData = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('告警查询', @menuAlertData, 1, '', '', 'F', '0', '0', 'nursing:alertData:query', '#', 'admin', NOW(), '', NULL, ''),
('告警处理', @menuAlertData, 2, '', '', 'F', '0', '0', 'nursing:alertData:handle', '#', 'admin', NOW(), '', NULL, '');

-- 告警规则
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('告警规则', @parentId_alert, 2, 'alertRule', 'nursing/alertRule/index', 'C', '0', '0', 'nursing:alertRule:list', 'monitor', 'admin', NOW(), '', NULL, '告警规则菜单');
SET @menuAlertRule = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('规则查询', @menuAlertRule, 1, '', '', 'F', '0', '0', 'nursing:alertRule:query', '#', 'admin', NOW(), '', NULL, ''),
('规则新增', @menuAlertRule, 2, '', '', 'F', '0', '0', 'nursing:alertRule:add', '#', 'admin', NOW(), '', NULL, ''),
('规则修改', @menuAlertRule, 3, '', '', 'F', '0', '0', 'nursing:alertRule:edit', '#', 'admin', NOW(), '', NULL, ''),
('规则删除', @menuAlertRule, 4, '', '', 'F', '0', '0', 'nursing:alertRule:remove', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 二级菜单：合同管理
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('合同管理', @parentId_nursing, 6, 'contract', 'nursing/contract/index', 'C', '0', '0', 'nursing:contract:list', 'documentation', 'admin', NOW(), '', NULL, '合同管理菜单');
SET @menuContract = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('合同查询', @menuContract, 1, '', '', 'F', '0', '0', 'nursing:contract:query', '#', 'admin', NOW(), '', NULL, ''),
('合同新增', @menuContract, 2, '', '', 'F', '0', '0', 'nursing:contract:add', '#', 'admin', NOW(), '', NULL, ''),
('合同修改', @menuContract, 3, '', '', 'F', '0', '0', 'nursing:contract:edit', '#', 'admin', NOW(), '', NULL, ''),
('合同删除', @menuContract, 4, '', '', 'F', '0', '0', 'nursing:contract:remove', '#', 'admin', NOW(), '', NULL, '');

-- ----------------------------
-- 二级菜单：预约管理
-- ----------------------------
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('预约管理', @parentId_nursing, 7, 'reservation', 'nursing/reservation/index', 'C', '0', '0', 'nursing:reservation:list', 'form', 'admin', NOW(), '', NULL, '预约管理菜单');
SET @menuReservation = LAST_INSERT_ID();
INSERT INTO `sys_menu` (`menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
VALUES ('预约查询', @menuReservation, 1, '', '', 'F', '0', '0', 'nursing:reservation:query', '#', 'admin', NOW(), '', NULL, ''),
('预约新增', @menuReservation, 2, '', '', 'F', '0', '0', 'nursing:reservation:add', '#', 'admin', NOW(), '', NULL, ''),
('预约修改', @menuReservation, 3, '', '', 'F', '0', '0', 'nursing:reservation:edit', '#', 'admin', NOW(), '', NULL, ''),
('预约取消', @menuReservation, 4, '', '', 'F', '0', '0', 'nursing:reservation:cancel', '#', 'admin', NOW(), '', NULL, '');

-- =============================================================
-- 十一、字典数据
-- =============================================================

-- 床位状态字典
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `remark`)
VALUES ('床位状态', 'nursing_bed_status', '0', 'admin', NOW(), '床位状态列表');

SET @dictBedStatus = LAST_INSERT_ID();

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `remark`)
VALUES (1, '空闲', '0', 'nursing_bed_status', '', 'success', 'Y', '0', 'admin', NOW(), ''),
(2, '已入住', '1', 'nursing_bed_status', '', 'primary', 'N', '0', 'admin', NOW(), ''),
(3, '维修中', '2', 'nursing_bed_status', '', 'warning', 'N', '0', 'admin', NOW(), ''),
(4, '预订', '3', 'nursing_bed_status', '', 'info', 'N', '0', 'admin', NOW(), '');

-- 入住状态字典
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `remark`)
VALUES ('入住状态', 'nursing_check_in_status', '0', 'admin', NOW(), '入住状态列表');

SET @dictCheckInStatus = LAST_INSERT_ID();

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `remark`)
VALUES (1, '待审核', '0', 'nursing_check_in_status', '', 'warning', 'Y', '0', 'admin', NOW(), ''),
(2, '已审核', '1', 'nursing_check_in_status', '', 'primary', 'N', '0', 'admin', NOW(), ''),
(3, '已入住', '2', 'nursing_check_in_status', '', 'success', 'N', '0', 'admin', NOW(), ''),
(4, '已退住', '3', 'nursing_check_in_status', '', 'info', 'N', '0', 'admin', NOW(), ''),
(5, '已拒绝', '4', 'nursing_check_in_status', '', 'danger', 'N', '0', 'admin', NOW(), '');

-- 告警级别字典
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `remark`)
VALUES ('告警级别', 'nursing_alert_level', '0', 'admin', NOW(), '告警级别列表');

SET @dictAlertLevel = LAST_INSERT_ID();

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `remark`)
VALUES (1, '一般', '0', 'nursing_alert_level', '', 'info', 'Y', '0', 'admin', NOW(), ''),
(2, '重要', '1', 'nursing_alert_level', '', 'warning', 'N', '0', 'admin', NOW(), ''),
(3, '紧急', '2', 'nursing_alert_level', '', 'danger', 'N', '0', 'admin', NOW(), '');

-- 执行周期字典
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `remark`)
VALUES ('执行周期', 'nursing_execute_cycle', '0', 'admin', NOW(), '护理执行周期');

SET @dictExecuteCycle = LAST_INSERT_ID();

INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `remark`)
VALUES (1, '天', '0', 'nursing_execute_cycle', '', 'primary', 'Y', '0', 'admin', NOW(), ''),
(2, '周', '1', 'nursing_execute_cycle', '', 'success', 'N', '0', 'admin', NOW(), ''),
(3, '月', '2', 'nursing_execute_cycle', '', 'warning', 'N', '0', 'admin', NOW(), '');

-- =============================================================
-- 脚本结束
-- =============================================================
