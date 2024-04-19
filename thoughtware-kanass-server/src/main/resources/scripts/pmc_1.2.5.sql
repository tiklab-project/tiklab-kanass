ALTER TABLE pmc_work_item RENAME COLUMN plan_takeup_time TO estimate_time;

ALTER TABLE pmc_insight ALTER COLUMN data type TEXT;