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
| 基础设施 | 楼层、房型、房间、床位、设备 | nursing_ | 已实现 |
| 护理服务 | 护理项目、护理计划、护理等级、项目计划关联 | nursing_ | 已实现 |
| 老人档案 | 老人信息、家属信息、老人家属关联 | nursing_ | 已实现 |
| 入住管理 | 入住申请、入住审核、退住管理 | nursing_ | 已实现 |
| 健康评估 | 评估记录、评分管理、建议等级 | nursing_ | 已实现 |
| 护理排班 | 排班计划、执行记录、状态流转 | nursing_ | 已实现 |
| 告警管理 | 告警规则、告警数据、告警处理 | nursing_ | 已实现 |
| 合同管理 | 合同签订、合同变更、合同终止 | nursing_ | 已实现 |
| 预约管理 | 来访预约、入住预约、参观预约 | nursing_ | 已实现 |
| 仪表盘 | 统计卡片、趋势图、饼图、柱状图、雷达图 | nursing_ | 已实现 |

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
| 14 | nursing_health_assessment | 健康评估表 | 健康评估 | ✅ 代码已实现 |
| 15 | nursing_arrange | 护理排班表 | 护理排班 | ✅ 代码已实现 |
| 16 | nursing_alert_rule | 告警规则表 | 告警管理 | ✅ 代码已实现 |
| 17 | nursing_alert_data | 告警数据表 | 告警管理 | ✅ 代码已实现 |
| 19 | nursing_contract | 合同表 | 合同管理 | ✅ 已实现 |
| 20 | nursing_reservation | 预约表 | 预约管理 | ✅ 已实现 |

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

### 第三阶段：老人档案与入住管理（已完成 ✅）

**目标**: 建立老人完整档案，实现入住全流程管理

**完成内容**:

| 序号 | 功能组件 | 实现方式 | 状态 |
|------|----------|----------|------|
| 1 | 老人信息管理 (NursingElder) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD、列表查询、导出、关联护理等级和床位名称 | ✅ 完成 |
| 2 | 家属信息管理 (NursingFamily) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD、列表查询、导出 | ✅ 完成 |
| 3 | 老人家属关联管理 (NursingElderFamily) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD、列表查询、关联老人和家属姓名 | ✅ 完成 |
| 4 | 入住申请管理 (NursingCheckIn) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD、列表查询、导出、状态管理 | ✅ 完成 |
| 5 | 入住申请详情接口 (DetailVO) | 10表关联查询 + 嵌套结果映射，返回完整关联信息 | ✅ 完成 |
| 6 | 入住申请列表接口 (ListVO) | 独立VO分层设计，支持老人姓名模糊搜索，返回老人详细信息和护理等级 | ✅ 完成 |

**实现方式**:
- 每个功能完整的Controller + Service + Mapper + Domain四层架构
- 继承BaseEntity，统一审计字段（createTime、updateTime等）
- 使用Lombok @Data简化实体类
- Mapper XML编写SQL，支持关联查询（left join显示关联名称）
- 所有Controller继承BaseController，统一响应处理
- 使用Swagger泛型改造（R<T>和TableDataInfo<T>）

**VO分层设计**:

| VO类 | 用途 | 关联表数 | 说明 |
|------|------|----------|------|
| NursingCheckInListVO | 列表接口 | 4表 | 入住申请 + 老人信息 + 床位 + 护理等级 |
| NursingCheckInDetailVO | 详情接口 | 10表 | 全部关联信息（含房间、楼层、房型、护理计划、家属列表） |

**设计原则**:
- **Entity（NursingCheckIn）**：仅数据库字段，用于增删改，保持与表结构一致
- **ListVO**：列表专用，包含必要的展示字段，避免返回冗余数据
- **DetailVO**：详情专用，返回完整关联信息，支持后续扩展（合同等）
- **导出接口**：继续使用Entity，保持Excel导出字段稳定

**详情接口10表关联说明**:

```
nursing_check_in (主表)
├── nursing_elder (老人信息)
├── nursing_bed (床位)
│   └── nursing_room (房间)
│       ├── nursing_floor (楼层)
│       └── nursing_room_type (房型)
├── nursing_level (护理等级)
├── nursing_plan (护理计划)
└── nursing_elder_family (老人家属关联)
    └── nursing_family (家属信息) - 一对多，使用 collection 嵌套映射
```

**关联查询说明**:
- NursingElder: 关联查询护理等级名称(level_name)、床位名称(bed_name)
- NursingElderFamily: 关联查询老人姓名(elder_name)、家属姓名(family_name)
- NursingCheckIn 列表: 关联查询老人姓名(elder_name)、床位名称(bed_name)、护理等级名称(level_name)、老人详细信息（性别、年龄、电话、身份证、入住状态、健康状态）、护理等级费用/描述
- NursingCheckIn 详情: 10表全关联，含家属列表（一对多嵌套映射）、房间/楼层/房型/护理计划

**列表查询增强**:
- 支持按老人姓名模糊搜索（elderName 参数）
- 返回老人完整基础信息（姓名、性别、年龄、电话、身份证、入住状态、健康状态）
- 返回护理等级完整信息（名称、费用、描述）

**Swagger响应泛型改造**:
- 所有Controller返回类型使用R<T>和TableDataInfo<T>
- 遵循第二阶段Swagger改造规范

| Controller | 实体类型 | list返回 | getInfo返回 | detail返回 | add/edit/remove返回 |
|------------|----------|----------|-------------|------------|---------------------|
| NursingElderController | NursingElder | TableDataInfo<NursingElder> | R<NursingElder> | - | R<Void> |
| NursingFamilyController | NursingFamily | TableDataInfo<NursingFamily> | R<NursingFamily> | - | R<Void> |
| NursingElderFamilyController | NursingElderFamily | TableDataInfo<NursingElderFamily> | R<NursingElderFamily> | - | R<Void> |
| NursingCheckInController | NursingCheckIn | TableDataInfo<NursingCheckInListVO> | R<NursingCheckIn> | R<NursingCheckInDetailVO> | R<Void> |

**核心代码示例**:

```java
// 列表接口 - 使用 NursingCheckInListVO
@GetMapping("/list")
public TableDataInfo<NursingCheckInListVO> list(NursingCheckIn nursingCheckIn) {
    startPage();
    List<NursingCheckInListVO> list = nursingCheckInService.selectCheckInList(nursingCheckIn);
    TableDataInfo<NursingCheckInListVO> rspData = new TableDataInfo<>();
    rspData.setCode(HttpStatus.SUCCESS);
    rspData.setMsg("查询成功");
    rspData.setRows(list);
    rspData.setTotal(new PageInfo<>(list).getTotal());
    return rspData;
}

// 详情接口 - 使用 NursingCheckInDetailVO
@GetMapping(value = "/detail/{id}")
public R<NursingCheckInDetailVO> getDetail(@PathVariable("id") Long id) {
    return R.ok(nursingCheckInService.selectCheckInDetailById(id));
}
```

```xml
<!-- MyBatis collection 嵌套映射（一对多家属列表） -->
<collection property="familyList" ofType="com.tong.nursing.vo.NursingCheckInDetailVO$FamilyInfoVO">
    <result property="familyId" column="family_id" />
    <result property="familyName" column="family_name" />
    <result property="relation" column="relation" />
    <result property="isEmergency" column="is_emergency" />
</collection>
```

**第三阶段难点与解决方案**:
1. **实体类臃肿问题** - 采用VO分层设计，列表用ListVO、详情用DetailVO，Entity保持纯净
2. **多表关联查询性能** - 使用LEFT JOIN + 合理索引，避免N+1查询
3. **一对多关系映射** - 使用MyBatis的collection标签实现嵌套结果映射
4. **接口扩展性** - 详情接口预留了合同等未来扩展能力，一次SQL查询搞定
5. **版本兼容** - 保留原有基本信息接口（/{id}），新增详情接口（/detail/{id}），实现平滑过渡

***

### 第四阶段：护理服务与排班（已完成 ✅）

**目标**: 完善护理服务体系，实现健康评估与护理排班

**已完成**:
- 健康评估管理（多维度评分、综合评分自动计算、建议等级自动推荐）✅
- 护理排班管理（排班编号自动生成、按老人/项目/护理员查询）✅
- 排班执行/取消接口（待执行 → 执行中 → 已执行/已取消，状态流转校验）✅
- 健康评估 VO（含老人姓名、等级名称展示字段）✅
- 护理排班 VO（含老人姓名、项目名称、护理员姓名展示字段）✅
- 补充 13 组字典数据（评估结果、排班状态、合同类型/状态、预约类型/状态等）✅
- 楼层树形查询接口（GET /nursing/floor/{id}/tree，返回楼层→房间→床位三级嵌套）✅
- Generator 模板改造（Lombok @Data + Swagger 注解支持）✅

***

### 第五阶段：告警管理（已完成 ✅）

**目标**: 建立智能告警体系，实现告警规则与告警数据处理

**完成内容**:

| 序号 | 功能组件 | 实现方式 | 状态 |
|------|----------|----------|------|
| 1 | 告警规则管理 (NursingAlertRule) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD、列表查询、导出 | ✅ 完成 |
| 2 | 告警数据管理 (NursingAlertData) | Controller + Service + Mapper + Domain 四层架构，支持 CRUD、列表查询、导出 | ✅ 完成 |
| 3 | 告警数据VO (NursingAlertDataVO) | 包含老人姓名、床位名称、设备名称、规则名称等展示字段 | ✅ 完成 |
| 4 | 告警处理/忽略接口 | 处理告警（状态改为已处理）、忽略告警（状态改为已忽略），含状态流转校验 | ✅ 完成 |
| 5 | 告警编号自动生成 | Service层insert时生成，格式 ALT-yyyyMMdd-001 | ✅ 完成 |
| 6 | 常量补充 | 告警类型（离床/坠床/心率异常/呼吸异常/其他）、通知方式（系统通知/短信/电话） | ✅ 完成 |

**接口清单**:

| 模块 | 接口 | 方法 | 说明 |
|------|------|------|------|
| 告警规则 | /nursing/alertRule/list | GET | 分页查询告警规则列表 |
| 告警规则 | /nursing/alertRule/{id} | GET | 获取告警规则详情 |
| 告警规则 | /nursing/alertRule | POST | 新增告警规则 |
| 告警规则 | /nursing/alertRule | PUT | 修改告警规则 |
| 告警规则 | /nursing/alertRule/{ids} | DELETE | 批量删除告警规则 |
| 告警数据 | /nursing/alertData/list | GET | 分页查询告警数据（含关联信息） |
| 告警数据 | /nursing/alertData/{id} | GET | 获取告警数据详情 |
| 告警数据 | /nursing/alertData | POST | 新增告警数据（编号自动生成） |
| 告警数据 | /nursing/alertData | PUT | 修改告警数据 |
| 告警数据 | /nursing/alertData/handle/{id} | PUT | 处理告警（状态改为已处理） |
| 告警数据 | /nursing/alertData/ignore/{id} | PUT | 忽略告警（状态改为已忽略） |
| 告警数据 | /nursing/alertData/{ids} | DELETE | 批量删除告警数据 |

**新增文件**（13个）：
- Entity: NursingAlertRule.java、NursingAlertData.java
- VO: NursingAlertDataVO.java
- Mapper: NursingAlertRuleMapper.java、NursingAlertDataMapper.java
- XML: NursingAlertRuleMapper.xml、NursingAlertDataMapper.xml
- Service: INursingAlertRuleService.java、INursingAlertDataService.java
- ServiceImpl: NursingAlertRuleServiceImpl.java、NursingAlertDataServiceImpl.java
- Controller: NursingAlertRuleController.java、NursingAlertDataController.java

**第五阶段难点与解决方案**:
1. **VO关联查询** → 告警数据通过LEFT JOIN关联老人、床位、设备、规则表，一次SQL查出所有展示字段
2. **告警编号自动生成** → Service层insert时生成，格式ALT-yyyyMMdd-001
3. **状态流转校验** → 已处理/已忽略的告警不允许重复处理，防止状态不一致

***

### 第六阶段：合同与预约管理（已完成 ✅）

**目标**: 完善合同与预约业务，形成完整的客户管理闭环

**完成内容**:
- 合同管理 CRUD（签订、变更、终止）
- 预约管理 CRUD（来访预约、入住预约、参观预约）
- 合同列表 VO 增强（关联老人姓名）
- 字典数据完整（合同类型/状态、预约类型/状态）

***

### 第七阶段：报表统计与仪表盘（已完成 ✅）

**目标**: 数据可视化与系统整体优化完善

**完成内容**:
- 仪表盘统计卡片（4个指标）
- 趋势折线图（最近7天预约/入住趋势）
- 床位状态饼图
- 楼层入住柱状图
- 护理等级雷达图

***

### 第八阶段：系统加固与核心业务完善（已完成 ✅）

**目标**: 修复 CRITICAL 级别问题，完善核心业务流程，补齐缺失接口

**背景**: 基于 2026-06-29 代码完整性分析报告，当前存在 12 个 CRITICAL、14 个 HIGH 级别问题。本阶段优先解决高优先级问题。

**分为三个子阶段**:

#### 子阶段 8.1：数据完整性加固 ✅ 已完成
- 楼层删除校验（检查关联房间）✅
- 房间删除校验（检查关联床位）✅
- 床位删除校验（检查关联老人/设备）✅
- 护理计划级联删除（project_plan）✅
- 老人删除校验（检查入住记录/合同）✅
- 房型删除校验（检查关联房间）✅

#### 子阶段 8.2：核心业务流程完善 ✅ 已完成
- 护理计划项目级联（新增/更新/删除）✅
- 护理计划详情 VO（含项目列表）✅
- 告警处理/忽略接口补全 ✅
- 预约确认/取消接口 ✅
- 预约编号自动生成 ✅
- 护理等级详情 VO ✅

#### 子阶段 8.3：接口补全与优化 ✅ 已完成
- 楼层全量列表接口 listAll ✅
- 房型全量列表接口 listAll ✅
- 护理项目全量列表接口 listAll ✅
- 护理计划全量列表接口 listAll ✅
- 护理等级全量列表接口 listAll ✅
- 按楼层查询房间接口 getRoomsByFloorId ✅
- 导出权限菜单补齐 SQL ✅

***

### 第九阶段：入住流程完善与定时任务（已完成 ✅）

**目标**: 完善入住申请完整流程，实现定时任务自动处理

**完成内容**:

| 序号 | 功能组件 | 实现方式 | 状态 |
|------|----------|----------|------|
| 1 | 入住申请 apply() 流程 | 8步完整入住流程，含事务管理 | ✅ 完成 |
| 2 | CheckInConfig 模块 | 入住配置表 + CRUD 全链路 | ✅ 完成 |
| 3 | 合同状态自动更新 | Quartz 定时任务，每天凌晨2点执行 | ✅ 完成 |
| 4 | 预约过期自动取消 | Quartz 定时任务，每天凌晨1点执行 | ✅ 完成 |
| 5 | 楼层树形查询全量接口 | 返回所有楼层→房间→床位三级结构 | ✅ 完成 |
| 6 | 护理等级 Redis 缓存 | listAll 接口带缓存，增删改时清除缓存 | ✅ 完成 |
| 7 | 健康评估 PDF 上传 | OSS 集成，支持健康评估报告上传 | ✅ 完成 |

**入住申请 8 步流程**:
1. 校验老人是否已入住（身份证号查重）
2. 校验床位是否空闲
3. 更新床位状态为已入住
4. 新增/更新老人信息
5. 新增合同（自动创建）
6. 新增入住记录
7. 新增入住配置
8. 保存家属列表

**定时任务**:
- **ContractStatusJob**: 每天凌晨2点扫描合同表，将到期合同状态更新为已到期
- **ReservationExpireJob**: 每天凌晨1点扫描预约表，将过期预约状态更新为已取消

**新增文件**:
- Entity: NursingCheckInConfig.java
- VO: NursingFloorTreeVO.java
- DTO: CheckInApplyDto.java
- Mapper: NursingCheckInConfigMapper.java、NursingFloorTreeMapper.java
- Service: INursingCheckInConfigService.java、INursingFloorTreeService.java
- Job: ContractStatusJob.java、ReservationExpireJob.java

***

