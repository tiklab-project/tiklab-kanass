<!--
 * @Author: 袁婕轩
 * @Date: 2024-12-30 16:42:50
 * @LastEditors: 袁婕轩
 * @LastEditTime: 2024-12-31 11:15:34
 * @Description: 
-->

# home
## insight
| 文件        | 说明                         |
|-----------| ---------------------------- |
| InsightFocusServiceImpl   | 仪表盘收藏 |
| InsightServiceImpl | 仪表盘|
| ProjectInsightReportServiceImpl | 仪表盘统计|

## statistic
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ProjectBurnDowmChartServiceImpl | 项目动态数据统计|
| RoadMapServiceImpl | 路线图接口|
| SprintBurnDowmChartServiceImpl | 迭代共同数据统计|
| StageBurnDowmChartServiceImpl | 计划（阶段）的动态统计|
| StatisticWorkItemServiceImpl | 统计接口|
| VersionBurnDowmChartServiceImpl | 版本动态数据统计|
| WorkItemStatServiceImpl | 事项动态数据统计|


# project

## jira 
| 文件        | 说明                         |
|-----------| ---------------------------- |
| InvalidXMLCharFilter | 解析xml|
| JiraImportData94ServiceImpl | 导入线下9.4 版本的jira数据|
| JiraImportDataCloudServiceImpl | cloud 版本jira 数据导入|
| JiraImportDataServiceImpl | jira 数据导入|
| JiraServiceImpl | jira 接口|
| UncompressUtil | 读取压缩文件内容 |

## milestone
| 文件        | 说明                         |
|-----------| ---------------------------- |
| MilestoneServiceImpl | 里程碑接口|

## module
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ModuleServiceImpl | 模块接口|

## project
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ProjectFocusServiceImpl | 项目收藏|
| ProjectServiceImpl | 项目|
| ProjectTypeServiceImpl | 项目类型|
| ProjectVersionServiceImpl | 版本|
| ProjectVirtualUserImpl | 获取项目虚拟角色的成员，用于发送消息，控制流程转换规则指定|

## stage
| 文件        | 说明                         |
|-----------| ---------------------------- |
| StageServiceImpl | 计划（阶段）接口|
| StageWorkItemServiceImpl | 项目阶段事项管理关系|

## test
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ProjectTestRepositoryImpl | 项目用例库关联|
| TestRepositoryServiceImpl | 获取用例库的信息|

## version
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ProjectVersionServiceImpl | 版本接口|
| VersionFocusServiceImpl | 收藏版本的接口|
| VersionStateServiceImpl | 版本状态接口|

## wiki
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ProjectWikiRepositoryImpl | 项目管理的知识库|

## worklog
| 文件        | 说明                         |
|-----------| ---------------------------- |
| WorkLogServiceImpl | 事项工时接口|

# projectset
| 文件        | 说明                         |
|-----------| ---------------------------- |
| ProjectSetFocusServiceImpl | 项目集收藏接口|
| ProjectSetServiceImpl | 项目集接口|

#sprint
| 文件        | 说明                         |
|-----------| ---------------------------- |
| SprintFocusServiceImpl | 收藏迭代的接口|
| SprintServiceImpl | 迭代接口|
| SprintStateServiceImpl | 迭代状态接口|

# workItem
| 文件        | 说明                         |
|-----------| ---------------------------- |
| WikiDocumentServiceImpl | 事项关联的文档接口|
| WorkAttachServiceImpl | 事项的附件|
| WorkCommentServiceImpl | 事项评论接口|
|WorkItemDocumentServiceImpl | 事项文档|
|WorkItemFunctionServiceImpl | 事项权限接口|
|WorkItemRoleFunctionDmServiceImpl | 项目内事项角色的权限接口|
|WorkItemRoleFunctionServiceImpl | 系统的事项角色的权限接口，模版|
|WorkItemServiceImpl | 事项接口|
|WorkRelateServiceImpl | 关联事项接口|
|WorkRepositoryServiceImpl | 项目关联的知识库|
|WorkSprintServiceImpl | 事项与迭代的关联接口|
|WorkStatusServiceImpl | 事项状态|
|WorkTestCaseServiceImpl | 事项与用例的关联接口|
|WorkTypeDmServiceImpl | 项目的事项类型接口|
|WorkTypeServiceImpl | 系统的事项类型接口|
|WorkVersionServiceImpl | 事项与版本的关联关系|


# 数据库

## 表 pmc_icon 上传的图标，用于项目头像
| 字段                    | 说明    |
|-----------------------|-------|
| id                    | 唯一标识  |
| icon_name | 图标名字 |
| icon_url | 图标地址 |
| icon_type | 图标类型 |

## 表 pmc_insight 仪表盘
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| insight_name | 仪表盘名称 |
| insight_group | 仪表盘分组 |
| master | 创建人 |
| createdTime | 创建时间 |
| data | 内容，仪表盘的类型，坐标、大小 |

## pmc_insight_focus 仪表盘收藏
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| insight_id | 仪表盘id |
| master_id | 用户id |
| sort | 排序 |

## pmc_milestone 里程碑
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_id | 项目id |
| name | 里程碑名称 |
| milestone_time | 里程碑时间 |
| master | 负责人 |
| milestone_status | 里程碑状态 |

## pmc_module 模块
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| module_name | 模块名称 |
| description | 描述 |
| parent_id | 上级id |
| project_id | 项目id |

## pmc_plan 计划 （弃用）
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| plan_name | 项目id |

## pmc_plan_work_item 计划与事项的关联 (弃用)
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| plan_id | 计划id |
| work_item_id | 事项id |

## pmc_project 项目
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
|  project_key | 项目key，唯一，用于事项id|
| project_name | 项目名称 |
| project_type_id | 项目类型 |
| project_status | 项目状态 |
| project_limits | 可见范围， 0：公共 1 ：私有 |
| creator | 创建人 |
| master | 负责人 |
| description | 描述 |
| project_set_id | 关联的项目集 |
| project_state | 项目状态 1：未开始 2：进行中 3：已完成 |
| start_time | 项目开始时间 |
| end_time | 项目结束时间 |
| icon_url | 项目icon |

## pmc_project_burndowm 项目的事项动态记录
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_id | 项目id |
| remain_workitem_count | 剩余事项数量 |
| total_workitem_count | 全部事项数量 |
| end_workitem_count | 完成事项数量 |
| progress_workitem_count | 进行中事项数量 |
| nostart_workitem_count | 未开始事项数量 |
| remain_bug_count | 剩余缺陷数量 |
| total_bug_count | 全部缺陷数量 |
| end_bug_count | 完成缺陷数量 |
| progress_bug_count | 进行中缺陷数量 |
| nostart_bug_count | 未开始缺陷数量 |
| remain_demand_count | 剩余需求数量 |
| total_demand_count | 全部需求数量 |
| end_demand_count | 完成需求数量 |
| progress_demand_count | 进行中需求数量 |
| nostart_demand_count | 未开始需求数量 |
| remain_demand_count | 剩余任务数量 |
| total_demand_count | 全部任务数量 |
| end_demand_count | 完成任务数量 |
| progress_demand_count | 进行中任务数量 |
| nostart_demand_count | 未开始任务数量 |

## pmc_project_focus 项目收藏
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_id | 项目id |
| master_id | 用户id |
| sort | 排序 |

## pmc_project_set 项目集
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 项目集名称 |
| start_time | 开始时间 |
| end_time | 结束时间 |
| creator | 创建者 |
| project_set_limits | 项目集可见范围 0：公共 1：私有 |
| remark | 没用 |
| sort | 排序 |
| master | 负责人 |
| is_delete | 是否删除 |
| icon_url | 项目集icon |
| creat_time | 创建时间 |
| update_time | 更新时间 |
| color | 颜色，用于头像颜色设置|

## pmc_project_set_focus 项目集收
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_set_id | 项目集id |
| master_id | 用户id |
| sort | 排序 |

## pmc_project_test_repository 项目管理的用例库
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_id | 项目id |
| test_repository_id | 用例库id |
| sort | 用例库名称 |

## pmc_project_type 项目类型， 目前两种
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| type | 类型编码 |
| name | 类型名称 |
| icon_url | 图标 |
| description | 描述 |

## pmc_project_wiki_repository 项目管理的知识库
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_id | 项目id |
| wiki_repository_id | 知识库id |
| sort | 排序 |


## pmc_recent 最近访问
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 名称 |
| model | 关联的模型，事项（workItem)，项目(project)，迭代(sprint)，版本(version)，项目集(projectSet) |
| model_id | 关联的模型id |
| master_id | 用户id |
| project_id | 项目id |
| recent_time | 点击时间 |
| icon_url | 图标 |
| project_type_id | 项目类型id |

## pmc_report 报表(弃用)
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |

## pmc_sprint 迭代
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| sprint_name | 迭代名称 |
| description | 描述 |
| project_id | 项目id |
| master | 负责人 |
| sprint_state_id | 状态id |
| start_time | 开始时间 |
| end_time | 结束时间 |
| builder | 创建人 |
| rela_start_time | 真正开始时间 |
| rela_end_time | 真正结束时间 |
| color | 颜色， 图标颜色 |

## pmc_sprint_burndowm 迭代的事项动态记录
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| sprint_id | 迭代id |
| remain_workitem_count | 剩余事项数量 |
| total_workitem_count | 全部事项数量 |
| end_workitem_count | 完成事项数量 |
| progress_workitem_count | 进行中事项数量 |
| nostart_workitem_count | 未开始事项数量 |
| remain_bug_count | 剩余缺陷数量 |
| total_bug_count | 全部缺陷数量 |
| end_bug_count | 完成缺陷数量 |
| progress_bug_count | 进行中缺陷数量 |
| nostart_bug_count | 未开始缺陷数量 |
| remain_demand_count | 剩余需求数量 |
| total_demand_count | 全部需求数量 |
| end_demand_count | 完成需求数量 |
| progress_demand_count | 进行中需求数量 |
| nostart_demand_count | 未开始需求数量 |
| remain_demand_count | 剩余任务数量 |
| total_demand_count | 全部任务数量 |
| end_demand_count | 完成任务数量 |
| progress_demand_count | 进行中任务数量 |
| nostart_demand_count | 未开始任务数量 |


## pmc_sprint_focus 迭代收藏
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| sprint_id | 迭代id |
| master_id | 用户id |
| project_id | 项目id |
| sort | 排序 |

## pmc_sprint_state 迭代状态
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识, 000000 未开始; 111111 进行中; 222222 已完成  |
| description | 描述 |
| sort | 排序 |
| grouper | 分组, 没用 |

## pmc_stage 计划（阶段）
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| stage_name | 计划名称 |
| parent_id | 上级id |
| status | 状态 |
| progress | 进度 |
| master | 负责人 |
| description | 描述 |
| project_id | 项目id |
| start_time | 开始时间 |
| end_time | 结束时间 |
| tree_path | 所有上级节点数组 |
| root_id | 根节点 |
| deep | 深度 |
| color | 头像颜色 |

## pmc_stage_burndowm 计划的事项动态记录
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| stage_id | 计划id |
| remain_workitem_count | 剩余事项数量 |
| total_workitem_count | 全部事项数量 |
| end_workitem_count | 完成事项数量 |
| progress_workitem_count | 进行中事项数量 |
| nostart_workitem_count | 未开始事项数量 |
| remain_bug_count | 剩余缺陷数量 |
| total_bug_count | 全部缺陷数量 |
| end_bug_count | 完成缺陷数量 |
| progress_bug_count | 进行中缺陷数量 |
| nostart_bug_count | 未开始缺陷数量 |
| remain_demand_count | 剩余需求数量 |
| total_demand_count | 全部需求数量 |
| end_demand_count | 完成需求数量 |
| progress_demand_count | 进行中需求数量 |
| nostart_demand_count | 未开始需求数量 |
| remain_demand_count | 剩余任务数量 |
| total_demand_count | 全部任务数量 |
| end_demand_count | 完成任务数量 |
| progress_demand_count | 进行中任务数量 |
| nostart_demand_count | 未开始任务数量 |


## pmc_stage_work_item 计划与事项的关联
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| stage_id | 计划id |
| work_item_id | 事项id |

## pmc_system_url 系统集成的url
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 名称 |
| system_url | 系统url |
| web_url | 前端url |

## pmc_version 版本
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 版本名称 |
| project_id | 项目id |
| master| 负责人 |
| start_time | 开始时间 |
| publish_time | 计划发布时间 |
| version_state | 版本状态 |
| rela_publish_time | 真正的发布时间 |
| rela_start_time | 真正的开始时间 |
| builder | 创建人 |
| color | 颜色， 图标颜色 |


## pmc_version_burndowm 版本的事项动态记录
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| version_id | 版本id |
| remain_workitem_count | 剩余事项数量 |
| total_workitem_count | 全部事项数量 |
| end_workitem_count | 完成事项数量 |
| progress_workitem_count | 进行中事项数量 |
| nostart_workitem_count | 未开始事项数量 |
| remain_bug_count | 剩余缺陷数量 |
| total_bug_count | 全部缺陷数量 |
| end_bug_count | 完成缺陷数量 |
| progress_bug_count | 进行中缺陷数量 |
| nostart_bug_count | 未开始缺陷数量 |
| remain_demand_count | 剩余需求数量 |
| total_demand_count | 全部需求数量 |
| end_demand_count | 完成需求数量 |
| progress_demand_count | 进行中需求数量 |
| nostart_demand_count | 未开始需求数量 |
| remain_demand_count | 剩余任务数量 |
| total_demand_count | 全部任务数量 |
| end_demand_count | 完成任务数量 |
| progress_demand_count | 进行中任务数量 |
| nostart_demand_count | 未开始任务数量 |

## pmc_version_focus 版本收藏
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| version_id | 版本id |
| master_id | 用户id |
| project_id | 项目id |
| sort | 排序 |

## pmc_version_state 版本状态
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识, 000000 未开始; 111111 进行中; 222222 已完成  |
| description | 描述 |
| sort | 排序 |
| grouper | 分组, 没用 |

## pmc_work_attach 事项附件
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| attachmentName | 附件名称 |
| attachmentUrl | 附件地址 |
| type | 附件类型 |
| sort | 排序 |

## pmc_work_comment 事项评论
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| details | 详细内容 |
| user_id | 评论人 |
| create_time | 评论时间 |
| update_time | 更新时间 |

## pmc_work_item 事项
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| order_num | 事项序号 |
| root_id | 根节点id |
| title  | 事项标题 |
| description | 描述 |
| parent_id | 上级id |
| tree_path | 上级节点路径 |
| project_id | 项目id |
| work_type_id | 事项类型id |
| work_item_priority | 事项优先级 |
| work_status_id | 事项状态 |
| module_id | 模块id |
| sprint_id | 迭代id |
| version_id | 版本id |
| pre_depend_id | 前置事项id |
| builder_id | 创建者 |
| assigner_id | 负责人 |
| reporter_id | 报告人 |
| plan_begin_time | 计划开始时间 |
| plan_end_time | 计划结束时间 |
| actual_begin_time | 实际开始时间 |
| actual_end_time | 实际结束时间 |
| percent | 进度 |
| estimate_time | 计划用时 |
| ext_data | 扩展数据，自定义表单字段的数据 |
| build_time | 创建时间 |
| work_status_code | 状态code |
| surplus_time | 剩余时间 |
| work_type_sys_id | 系统的事项类型id |
| work_status_node_id | 事项状态节点id |
| each_type | 缺陷，需求，任务类型id |
| update_time | 更新时间 |
| code | 事项code |
| stage_id | 计划id |

## pmc_work_item_document 事项文档
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| document_id | 文档id |
| repository_id | 知识库id |
| sort   | 排序 |
| project_id | 项目id |

## pmc_work_item_function 事项权限的功能点
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 名称 |
| code | 编码 |


## pmc_work_log 事项工时
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| worker | 记录者 |
| work_date | 记录时间 |
| takeup_time | 用时 |
| work_content | 内容 |
| creat_time | 创建时间 |
| update_time | 更新时间 | 
| project_id | 项目id |

## pmc_work_priority 事项优先级(弃用)
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |

## pmc_work_relate 事项关联
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| relate_item_id | 关联事项id |
| create_time | 创建时间 |

## pmc_work_role_function 系统的事项角色的权限
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_type_id | 事项类型id |
| function_id | 功能点id |
| function_type | 功能点类型 function 功能点， field 字段 |
| role_id | 角色id |

## pmc_work_role_function_dm 事项角色的权限
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 名称 |
| code | 编码 |
| description | 描述 |
| sort | 排序 |

## pmc_work_sprint 事项与迭代的关联
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| sprint_id | 迭代id |

## pmc_work_status 事项状态 (弃用)
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |

## pmc_work_type 系统事项类型
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| name | 名称 |
| code | 编码 |
| description | 描述 |
| form_id | 管理的表单id |
| flow_id | 流程id |
| icon_url | 图标 |
| grouper | 分组 system 系统， custom 自定义 |
| sort | 排序 |

## pmc_work_type_dm 项目内的事项类型
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| project_id | 项目id |
| work_type_id | 系统事项类型id |
| form_id | 管理的表单id |
| flow_id | 流程id |

## pmc_work_version 事项与版本的关联
| 字段    | 说明    |
|------------|-------|
| id       | 唯一标识  |
| work_item_id | 事项id |
| version_id | 版本id |
