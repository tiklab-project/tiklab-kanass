ALTER TABLE pmc_stage ADD COLUMN tree_path TEXT;
ALTER TABLE pmc_stage ADD COLUMN root_id VARCHAR(12);
ALTER TABLE pmc_work_item ADD COLUMN stage_id VARCHAR(12);