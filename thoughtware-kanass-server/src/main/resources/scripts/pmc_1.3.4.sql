CREATE TABLE pmc_work_role_function(
        id VARCHAR(12) PRIMARY KEY,
        role_id VARCHAR(12) NOT NULL,
        role_type VARCHAR(64) NOT NULL,
        function_id VARCHAR(12) NOT NULL,
        type VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_work_function(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        code VARCHAR(64) NOT NULL,
        parent_id VARCHAR(12) NOT NULL,
        sort VARCHAR(32)
);

CREATE TABLE pmc_project_vrole(
        id VARCHAR(12) PRIMARY KEY,
        project_id VARCHAR(12) NOT NULL,
        vrole_id VARCHAR(64) NOT NULL
);
