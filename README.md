<br />

***

# 中州养老管理系统（ElderlyCare）

## 项目简介

中州养老管理系统是基于RuoYi-Vue框架开发的养老院综合管理平台，旨在为养老机构提供全方位的信息化管理解决方案。系统参考codeRef完整功能设计，涵盖基础设施管理、护理服务管理、老人档案管理、入住管理、健康评估、护理排班、告警管理、合同管理、预约管理等核心业务模块。

## 项目模块结构

```
ElderlyCare (父工程)
├── ElderlyCare-common              // 通用模块（工具类、常量、异常等）
├── ElderlyCare-framework           // 框架模块（安全、配置、拦截器等）
├── ElderlyCare-system              // 系统模块（用户、角色、菜单、字典等）
├── ElderlyCare-quartz              // 定时任务模块
├── ElderlyCare-generator           // 代码生成模块
├── ElderlyCare-admin               // 管理后台模块（Controller、启动类）
├── ElderlyCare-oss                 // 对象存储模块（阿里云OSS）
└── ElderlyCare-nursing-platform    // 护理业务平台模块（单模块，内含所有业务）
```

## 技术栈

- **后端框架**: Spring Boot 2.5.x + Spring Security + JWT
- **持久层**: MyBatis + Druid 数据源 + PageHelper分页
- **缓存**: Redis
- **数据库**: MySQL 8.x
- **工具库**: Lombok 1.18.30、Apache Commons、FastJSON2
- **对象存储**: 阿里云 OSS SDK 3.17.4
- **接口文档**: Swagger3 (SpringFox)
- **代码规范**: 统一注释规范、常量分层管理

## 业务模块总览（基于codeRef）

| 业务域 | 包含功能 | 表前缀 | 状态 |
|--------|----------|--------|------|
| 基础设施 | 楼层、房型、房间、床位、设备 | nursing_ | 设计完成 |
| 护理服务 | 护理项目、护理计划、护理等级、项目计划关联 | nursing_ | 已实现 |
| 老人档案 | 老人信息、家属信息、老人家属关联 | nursing_ | 设计完成 |
| 入住管理 | 入住申请、入住审核、退住管理 | nursing_ | 设计完成 |
| 健康评估 | 评估记录、评分管理、建议等级 | nursing_ | 设计完成 |
| 护理排班 | 排班计划、执行记录、状态流转 | nursing_ | 设计完成 |
| 告警管理 | 告警规则、告警数据、告警处理 | nursing_ | 设计完成 |
| 合同管理 | 合同签订、合同变更、合同终止 | nursing_ | 设计完成 |
| 预约管理 | 来访预约、入住预约、参观预约 | nursing_ | 设计完成 |

## 数据库表清单（nursing_ 前缀，共19张）

| 序号 | 表名 | 说明 | 所属模块 |
|------|------|------|----------|
| 1 | nursing_floor | 楼层表 | 基础设施 |
| 2 | nursing_room_type | 房型表 | 基础设施 |
| 3 | nursing_room | 房间表 | 基础设施 |
| 4 | nursing_bed | 床位表 | 基础设施 |
| 5 | nursing_device | 设备表 | 基础设施 |
| 6 | nursing_project | 护理项目表 | 护理服务 |
| 7 | nursing_plan | 护理计划表 | 护理服务 |
| 8 | nursing_level | 护理等级表 | 护理服务 |
| 9 | nursing_project_plan | 护理项目计划关联表 | 护理服务 |
| 10 | nursing_elder | 老人信息表 | 老人档案 |
| 11 | nursing_family | 家属信息表 | 老人档案 |
| 12 | nursing_elder_family | 老人家属关联表 | 老人档案 |
| 13 | nursing_check_in | 入住申请表 | 入住管理 |
| 14 | nursing_health_assessment | 健康评估表 | 健康评估 |
| 15 | nursing_arrange | 护理排班表 | 护理排班 |
| 16 | nursing_alert_rule | 告警规则表 | 告警管理 |
| 17 | nursing_alert_data | 告警数据表 | 告警管理 |
| 18 | nursing_contract | 合同表 | 合同管理 |
| 19 | nursing_reservation | 预约表 | 预约管理 |

## 开发路线图

### 第一阶段：基础架构搭建（已完成 ✅）

**目标**: 搭建项目基础架构，引入必要的开发工具和基础设施，为后续业务开发做准备

**完成内容**:

| 序号 | 功能组件 | 实现方式 | 状态 |
|------|----------|----------|------|
| 1 | Lombok 集成 | 父pom引入lombok依赖，实体类使用@Data/@Setter/@Getter等注解简化代码 | ✅ 完成 |
| 2 | ElderlyCare-oss 模块 | 创建独立的OSS模块，三层架构：配置属性类 + 文件存储服务 + 自动配置类 | ✅ 完成 |
| 3 | 阿里云OSS集成 | 集成aliyun-sdk-oss 3.17.4，实现文件上传与删除，替换CommonController原本地上传 | ✅ 完成 |
| 4 | ElderlyCare-nursing-platform 模块 | 创建护理业务平台独立模块，迁移护理项目/计划/等级/关联代码 | ✅ 完成 |
| 5 | 护理业务实体改造 | 4个护理实体类全部使用@Data注解简化，保留Swagger注解和Excel注解 | ✅ 完成 |
| 6 | MyBatis Mapper扫描 | 利用@MapperScan("com.tong.**.mapper")通配扫描，自动覆盖所有业务模块 | ✅ 完成 |
| 7 | 数据库初始化脚本 | 编写19张nursing_*业务表 + 完整菜单数据 + 4组字典数据 | ✅ 完成 |
| 8 | 多模块依赖管理 | 父pom dependencyManagement统一管理所有子模块版本，解决版本缺失问题 | ✅ 完成 |

**核心组件说明**:

1. **AliOssConfigProperties** - OSS配置属性类
   - 位置: `ElderlyCare-oss/src/main/java/com/tong/oss/properties/AliOssConfigProperties.java`
   - 功能: 通过`@ConfigurationProperties(prefix = "oss.aliyun")`读取配置
   - 配置项: endpoint、accessKeyId、accessKeySecret、bucketName、domain

2. **OSSAliyunFileStorageService** - OSS文件存储服务
   - 位置: `ElderlyCare-oss/src/main/java/com/tong/oss/client/OSSAliyunFileStorageService.java`
   - 功能: 封装文件上传(`store`)、文件删除(`delete`)
   - 状态管理: 无状态服务，每次调用独立操作OSS

3. **OssAliyunAutoConfig** - OSS客户端自动配置
   - 位置: `ElderlyCare-oss/src/main/java/com/tong/oss/config/OssAliyunAutoConfig.java`
   - 功能: Spring启动时自动创建OSS客户端Bean，检查并创建存储桶
   - 状态管理: Spring单例Bean，应用生命周期内复用同一客户端

**第一阶段难点与解决方案**:
1. **多模块依赖版本问题** - 在父pom的dependencyManagement中统一声明所有子模块版本，子模块无需指定version
2. **模块解耦与依赖方向** - nursing-platform依赖common，admin依赖nursing-platform，方向正确无循环
3. **OSS自动配置设计** - 采用三层架构（配置属性 + 服务类 + 自动配置），符合Spring Boot自动装配理念
4. **业务表命名规范** - 所有业务表统一使用nursing_前缀，避免与系统表(sys_)混淆

***

### 第二阶段：基础数据管理（已完成 ✅）

**目标**: 实现养老院基础设施数据管理，为后续老人入住等业务奠定基础

**完成内容**:

| 序号 | 功能组件 | 实现方式 | 状态 |
|------|----------|----------|------|
| 1 | 楼层管理 (NursingFloor) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD 和列表查询 | ✅ 完成 |
| 2 | 房型管理 (NursingRoomType) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD 和全部列表 | ✅ 完成 |
| 3 | 房间管理 (NursingRoom) | Controller + Service + Mapper + Domain 四层架构，支持按楼层ID筛选查询 | ✅ 完成 |
| 4 | 床位管理 (NursingBed) | Controller + Service + Mapper + Domain 四层架构，支持按房间ID筛选和状态流转 | ✅ 完成 |
| 5 | 设备管理 (NursingDevice) | Controller + Service + Mapper + Domain 四层架构，支持绑定床位和状态管理 | ✅ 完成 |
| 6 | Swagger响应泛型改造 | Controller返回类型改为R<T>和TableDataInfo<T>，Swagger正确解析响应结构 | ✅ 完成 |
| 7 | 初始化数据脚本 | nursing_init_data.sql 包含19张表的测试数据（3楼层、3房型、18房间、35床位、15设备、10护理项目等） | ✅ 完成 |

**实现方式**:
- 每个功能完整的Controller + Service + Mapper + Domain四层架构
- 继承BaseEntity，统一审计字段（createTime、updateTime等）
- 使用Lombok @Data简化实体类
- Mapper XML编写SQL，支持分页查询
- 所有Controller继承BaseController，统一响应处理

**状态管理**:
- 床位状态：空闲 → 已入住 / 维修中 / 预订（状态机流转）
- 设备状态：离线 / 在线 / 故障

**Swagger响应泛型改造说明**:

| 序号 | 改造要点 | 详细说明 | 状态 |
|------|----------|----------|------|
| 1 | 问题分析 | Controller返回AjaxResult/TableDataInfo时，Swagger无法推断data字段的具体类型，前端查看接口响应部分为空 | ✅ 已解决 |
| 2 | 方案设计 | list方法返回`TableDataInfo<T>`，getInfo方法返回`R<T>`，add/edit/remove方法返回`R<Void>` | ✅ 已完成 |
| 3 | 改造范围 | 仅修改nursing-platform模块下的9个Controller（5个基础设施 + 4个护理服务） | ✅ 已完成 |
| 4 | 继承调整 | 所有Controller继承框架BaseController，移除NursingBaseController（避免方法签名冲突） | ✅ 已完成 |
| 5 | 构造方式 | list方法手动构造泛型TableDataInfo（避免父类非泛型版本导致的类型不匹配） | ✅ 已完成 |
| 6 | 类型匹配 | 修复NursingLevel和NursingProjectPlan的ID参数类型（Integer vs Long） | ✅ 已完成 |

**改造的Controller清单**:

| Controller | 实体类型 | ID类型 | list返回 | getInfo返回 | add/edit/remove返回 |
|------------|----------|--------|----------|-------------|---------------------|
| NursingFloorController | NursingFloor | Long | TableDataInfo<NursingFloor> | R<NursingFloor> | R<Void> |
| NursingRoomTypeController | NursingRoomType | Long | TableDataInfo<NursingRoomType> | R<NursingRoomType> | R<Void> |
| NursingRoomController | NursingRoom | Long | TableDataInfo<NursingRoom> | R<NursingRoom> | R<Void> |
| NursingBedController | NursingBed | Long | TableDataInfo<NursingBed> | R<NursingBed> | R<Void> |
| NursingDeviceController | NursingDevice | Long | TableDataInfo<NursingDevice> | R<NursingDevice> | R<Void> |
| NursingProjectController | NursingProject | Long | TableDataInfo<NursingProject> | R<NursingProject> | R<Void> |
| NursingPlanController | NursingPlan | Long | TableDataInfo<NursingPlan> | R<NursingPlan> | R<Void> |
| NursingLevelController | NursingLevel | Integer | TableDataInfo<NursingLevel> | R<NursingLevel> | R<Void> |
| NursingProjectPlanController | NursingProjectPlan | Integer | TableDataInfo<NursingProjectPlan> | R<NursingProjectPlan> | R<Void> |

**核心代码示例**:

```java
// list方法 - 手动构造泛型TableDataInfo
@GetMapping("/list")
public TableDataInfo<NursingFloor> list(NursingFloor nursingFloor) {
    startPage();
    List<NursingFloor> list = nursingFloorService.selectNursingFloorList(nursingFloor);
    // 手动构造泛型 TableDataInfo，避免父类非泛型版本导致的类型不匹配
    TableDataInfo<NursingFloor> rspData = new TableDataInfo<>();
    rspData.setCode(HttpStatus.SUCCESS);
    rspData.setMsg("查询成功");
    rspData.setRows(list);
    rspData.setTotal(new PageInfo<>(list).getTotal());
    return rspData;
}

// getInfo方法 - 使用R.ok()
@GetMapping(value = "/{id}")
public R<NursingFloor> getInfo(@PathVariable("id") Long id) {
    return R.ok(nursingFloorService.selectNursingFloorById(id));
}

// add方法 - 使用R.ok()/R.fail()
@PostMapping
public R<Void> add(@RequestBody NursingFloor nursingFloor) {
    int rows = nursingFloorService.insertNursingFloor(nursingFloor);
    return rows > 0 ? R.ok() : R.fail();
}
```

**Swagger改造难点与解决方案**:
1. **方法签名冲突** - 最初尝试创建NursingBaseController继承BaseController并提供泛型方法，但Java不支持仅通过返回类型不同的方法重写，改用直接继承BaseController并手动构造响应对象
2. **ID类型不一致** - NursingLevel和NursingProjectPlan实体的ID字段为Integer类型，而其他实体为Long类型，需分别处理避免编译错误

**第二阶段难点与解决方案**:
1. **Swagger响应类型不明确** - 使用泛型R<T>和TableDataInfo<T>替代非泛型版本，使Swagger正确解析响应结构
2. **楼层-房间-床位三级层级关系** - 通过外键约束和业务逻辑校验确保数据一致性
3. **床位状态流转规则** - 在Service层实现状态机校验，已入住床位需先退住才能删除

***

### 第三阶段：老人档案与入住管理（规划中）

**目标**: 建立老人完整档案，实现入住全流程管理

**计划实现**:
- 老人信息管理（基本信息、护理等级、床位关联）
- 家属信息管理（多家属、紧急联系人）
- 老人家属关联管理
- 入住申请、审核、入住、退住全流程
- 入住状态流转（待审核 → 已审核 → 已入住 → 已退住）

***

### 第四阶段：护理服务与排班（规划中）

**目标**: 完善护理服务体系，实现护理排班与执行跟踪

**计划实现**:
- 健康评估管理（多维度评分、建议护理等级）
- 护理排班管理（按老人/项目/护理员排班）
- 排班执行记录（待执行 → 执行中 → 已执行/已取消）
- 护理项目与计划的关联配置（已具备基础）

***

### 第五阶段：告警与设备管理（规划中）

**目标**: 建立智能告警体系，实现设备数据接入与告警

**计划实现**:
- 告警规则配置（多种告警类型、阈值设置、通知方式）
- 告警数据管理（告警产生、处理流程、处理记录）
- 设备管理与绑定
- 智能床位数据接入预留

***

### 第六阶段：合同与预约管理（规划中）

**目标**: 完善合同与预约业务，形成完整的客户管理闭环

**计划实现**:
- 合同管理（签订、变更、终止、到期提醒）
- 预约管理（来访预约、入住预约、参观预约）
- 预约状态流转与确认机制

***

### 第七阶段：报表统计与系统完善（规划中）

**目标**: 数据可视化与系统整体优化完善

**计划实现**:
- 入住率统计报表
- 护理工作量统计
- 告警数据分析
- 仪表盘数据整合
- 系统性能优化

***

