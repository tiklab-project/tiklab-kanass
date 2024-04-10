ALTER TABLE pmc_stage ADD COLUMN tree_path TEXT;
ALTER TABLE pmc_stage ADD COLUMN root_id VARCHAR(12);
ALTER TABLE pmc_stage ADD COLUMN deep int;
ALTER TABLE pmc_work_item ADD COLUMN stage_id VARCHAR(12);

UPDATE pcs_foc_field SET grouper = 'syscustom' WHERE code = ('bugType', 'workPriority', 'taskType' , 'demandType');

INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('56035266', '高', '187d7a58', 'high', 3);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('04b440ad', '低', '187d7a58', 'low', 1);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('faaecb3d', '中', '187d7a58', 'medium', 2);



INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('bf5a3c4e', '0c5d4ff9', 'c4579b11', 0, 18);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('5fb54a50', '515f17bd', 'a34d7fc3', 0, 16);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('d661beec', '607f6be6', '0763e387', 0, 16);
