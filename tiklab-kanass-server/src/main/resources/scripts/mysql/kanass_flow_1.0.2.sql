CREATE TABLE pmc_work_transition_history (
     id VARCHAR(12) PRIMARY KEY,
     work_item_id VARCHAR(12) NOT NULL,
     old_node_id VARCHAR(12) NOT NULL,
     new_node_id VARCHAR(12) NOT NULL,
     creater VARCHAR(12) NOT NULL,
     transition_desc TEXT,
     create_time DATETIME,
     INDEX idx_work_item_id (work_item_id),
     INDEX idx_creater (creater)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事项流转历史';