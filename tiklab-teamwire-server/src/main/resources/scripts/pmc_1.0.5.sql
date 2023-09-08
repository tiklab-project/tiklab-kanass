
CREATE TABLE pmc_backups_info(
        id VARCHAR(8) PRIMARY KEY,
        task_state VARCHAR(64),
        last_result VARCHAR(64),
        exec_time VARCHAR(64)
);

INSERT INTO pmc_backups_info (id, task_state, last_result, exec_time) VALUES ('backups', 'false', '', '');

