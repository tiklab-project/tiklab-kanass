/*迭代和版本新增迁移后的目标id*/
ALTER TABLE pmc_work_sprint
    ADD COLUMN target_sprint_id VARCHAR(12) DEFAULT NULL COMMENT '迁移目标迭代ID';

ALTER TABLE pmc_work_version
    ADD COLUMN target_version_id VARCHAR(12) DEFAULT NULL COMMENT '迁移目标版本ID';

/*附件表的字段长度设置长一点*/
ALTER TABLE pmc_work_attach
    MODIFY COLUMN attachmentname VARCHAR(256);

ALTER TABLE pmc_work_attach
    MODIFY COLUMN attachmenturl VARCHAR(256);

ALTER TABLE pmc_work_attach
    MODIFY COLUMN type VARCHAR(256);