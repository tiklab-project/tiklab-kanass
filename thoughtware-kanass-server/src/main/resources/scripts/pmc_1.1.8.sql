CREATE TABLE pmc_work_sprint(
    id VARCHAR(12) PRIMARY KEY,
    work_item_id VARCHAR(16) NOT NULL,
    sprint_id VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_work_version(
    id VARCHAR(12) PRIMARY KEY,
    work_item_id VARCHAR(16) NOT NULL,
    version_id VARCHAR(12) NOT NULL
);