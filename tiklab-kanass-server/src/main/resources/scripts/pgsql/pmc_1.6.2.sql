-- 迭代新增创建时间、更新时间
update pmc_sprint set builder = '111111' where builder is null;

ALTER TABLE "pmc_sprint" ADD COLUMN create_time timestamp;
COMMENT ON COLUMN pmc_sprint.create_time IS '创建时间';
ALTER TABLE "pmc_sprint" ADD COLUMN update_time timestamp;
COMMENT ON COLUMN pmc_sprint.update_time IS '更新时间';

update pmc_sprint set create_time = start_time where create_time is null;
update pmc_sprint set update_time = start_time where update_time is null;

-- 版本新增创建时间、更新时间
ALTER TABLE "pmc_version" ADD COLUMN create_time timestamp;
COMMENT ON COLUMN pmc_version.create_time IS '创建时间';
ALTER TABLE "pmc_version" ADD COLUMN update_time timestamp;
COMMENT ON COLUMN pmc_version.update_time IS '更新时间';

update pmc_version set create_time = start_time where create_time is null;
update pmc_version set update_time = start_time where update_time is null;

-- 版本新增更新时间
ALTER TABLE "pmc_project" ADD COLUMN update_time timestamp;
COMMENT ON COLUMN pmc_project.update_time IS '创建时间';

update pmc_project set update_time = create_time where update_time is null;