ALTER TABLE pmc_sprint ADD COLUMN rela_start_time timestamp;
ALTER TABLE pmc_sprint ADD COLUMN rela_end_time timestamp;

ALTER TABLE pmc_version ADD COLUMN rela_start_time timestamp;
ALTER TABLE pmc_version RENAME COLUMN publish_date TO publish_time;
ALTER TABLE pmc_version RENAME COLUMN rela_publish_date TO rela_publish_time;



