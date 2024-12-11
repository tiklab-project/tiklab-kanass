CREATE TABLE pmc_work_role_function(
        id VARCHAR(12) PRIMARY KEY,
        work_type_id VARCHAR(12) NOT NULL,
        function_id VARCHAR(12) NOT NULL,
        function_type VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_work_role_function_dm(
        id VARCHAR(12) PRIMARY KEY,
        domain_id VARCHAR(12) NOT NULL,
        work_type_id VARCHAR(12) NOT NULL,
        function_id VARCHAR(12) NOT NULL,
        function_type VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_work_item_function(
        id VARCHAR(8) PRIMARY KEY,
        name VARCHAR(12) NOT NULL,
        code VARCHAR(64) NOT NULL
);