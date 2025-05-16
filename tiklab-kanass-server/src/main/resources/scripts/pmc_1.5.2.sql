/*迭代和版本新增迁移后的目标id*/
ALTER TABLE "pmc_work_sprint" ADD COLUMN target_sprint_id VARCHAR(12) DEFAULT NULL;
COMMENT ON COLUMN pmc_work_sprint.target_sprint_id IS '迁移目标迭代ID';
ALTER TABLE "pmc_work_version" ADD COLUMN target_version_id VARCHAR(12) DEFAULT NULL;
COMMENT ON COLUMN pmc_work_version.target_version_id IS '迁移目标版本ID';

/*附件表的字段长度设置长一点*/
ALTER TABLE pmc_work_attach
ALTER COLUMN attachmentname TYPE VARCHAR(256);
ALTER TABLE pmc_work_attach
ALTER COLUMN attachmenturl TYPE VARCHAR(256);
ALTER TABLE pmc_work_attach
ALTER COLUMN type TYPE VARCHAR(256);