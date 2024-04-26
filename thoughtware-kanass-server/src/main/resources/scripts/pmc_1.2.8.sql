ALTER TABLE pmc_work_item ADD COLUMN code VARCHAR(32) DEFAULT 0;
UPDATE pmc_work_item SET code = id;

