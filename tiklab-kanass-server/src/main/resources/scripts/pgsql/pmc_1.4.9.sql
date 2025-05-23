/* 删除默认的未通过(3b037eb2)、审核(5076fe7e)、已驳回(b5a4ffce)状态节点 */

/* 流程表单字段权限 */
delete from pcs_flc_state_node_role_field pfsnrf where node_id in (select id from pcs_flc_state_node_flow pfsnf where pfsnf.node_id in ('3b037eb2','5076fe7e','b5a4ffce'));

/* 流程中的节点 */
delete from pcs_flc_state_node_flow pfsnf where node_id in ('3b037eb2','5076fe7e','b5a4ffce');

/* 事项上的状态 */
delete from pcs_flc_state_node_relation pfsnr where node_id in ('3b037eb2','5076fe7e','b5a4ffce');

/* 流向 */
delete from pcs_flc_transition pft where (from_node_id in ('3b037eb2','5076fe7e','b5a4ffce') or to_node_id in ('3b037eb2','5076fe7e','b5a4ffce'));

/* 节点本身 */
delete from pcs_flc_state_node pfsn where id in ('3b037eb2','5076fe7e','b5a4ffce');

/* 将事项上这些节点的字段置为default，后续查询的时候再去更新填充 */
update pmc_work_item set work_status_code = 'TODO', work_status_id = 'default', work_status_node_id = 'default' WHERE work_status_node_id in ('3b037eb2','5076fe7e','b5a4ffce');