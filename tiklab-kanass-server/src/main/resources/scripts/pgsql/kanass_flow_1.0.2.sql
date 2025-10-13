CREATE TABLE pmc_work_transition_history (
     id VARCHAR(12) PRIMARY KEY,
     work_item_id VARCHAR(12) NOT NULL,
     old_node_id VARCHAR(12) NOT NULL,
     new_node_id VARCHAR(12) NOT NULL,
     creater VARCHAR(12) NOT NULL,
     transition_desc text,
     create_time TIMESTAMP
);

COMMENT ON TABLE pmc_work_transition_history IS '事项流转历史';
COMMENT ON COLUMN pmc_work_transition_history.id IS '主键ID';
COMMENT ON COLUMN pmc_work_transition_history.work_item_id IS '评审id';
COMMENT ON COLUMN pmc_work_transition_history.old_node_id IS '旧节点id';
COMMENT ON COLUMN pmc_work_transition_history.new_node_id IS '新节点id';
COMMENT ON COLUMN pmc_work_transition_history.creater IS '创建人id';
COMMENT ON COLUMN pmc_work_transition_history.transition_desc IS '流转描述';
COMMENT ON COLUMN pmc_work_transition_history.create_time IS '创建日期';