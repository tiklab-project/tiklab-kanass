<!--
 * @Author: 袁婕轩
 * @Date: 2024-12-30 16:42:50
 * @LastEditors: 袁婕轩
 * @LastEditTime: 2024-12-30 18:04:50
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
