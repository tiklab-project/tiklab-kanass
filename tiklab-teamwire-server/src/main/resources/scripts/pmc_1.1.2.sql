CREATE TABLE pmc_version_status(
        id VARCHAR(32) PRIMARY KEY,
        name VARCHAR(64) ,
        description VARCHAR(64),
        sort integer,
        grouper VARCHAR(32)
);

INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_version_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

UPDATE pmc_version SET version_state = '000000' WHERE version_state = '0';
UPDATE pmc_version SET version_state = '111111' WHERE version_state = '1';
UPDATE pmc_version SET version_state = '222222' WHERE version_state = '2';