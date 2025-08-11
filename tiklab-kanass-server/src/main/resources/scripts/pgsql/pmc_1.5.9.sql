ALTER TABLE "pmc_project" ADD COLUMN color int DEFAULT NULL;
COMMENT ON COLUMN pmc_project.color IS '背景色';