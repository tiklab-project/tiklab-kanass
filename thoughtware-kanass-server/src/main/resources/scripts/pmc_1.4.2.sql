ALTER TABLE pmc_work_item_document ADD COLUMN project_id VARCHAR(12);
ALTER TABLE pmc_work_test_case ADD COLUMN project_id VARCHAR(12);

ALTER TABLE pmc_sprint ADD COLUMN color INT;
ALTER TABLE pmc_stage ADD COLUMN color INT;
ALTER TABLE pmc_version ADD COLUMN color INT;

UPDATE pmc_sprint SET color = FLOOR(RANDOM() * 4) + 1;
UPDATE pmc_stage SET color = FLOOR(RANDOM() * 4) + 1;
UPDATE pmc_version SET color = FLOOR(RANDOM() * 4) + 1;

UPDATE pmc_project SET icon_url = 'project' ||  floor(random() * 4 + 1)::int || '.png';



UPDATE pmc_work_type SET name = '需求', description = '需求', code = 'demand', form_id = '0c5d4ff9', flow_id = 'a96cf9c9', icon_url = 'workType1.png', grouper = 'system', sort = '1', scope = 0 WHERE id = '7055ebc6';
UPDATE pmc_work_type SET name = '任务', description = '任务', code = 'task', form_id = '515f17bd', flow_id = '4d040c6d', icon_url = 'workType2.png', grouper = 'system', sort = '2', scope = 0 WHERE id = '778222e0';
UPDATE pmc_work_type SET name = '缺陷', description = '缺陷', code = 'defect', form_id = '607f6be6', flow_id = '3d879830', icon_url = 'workType3.png', grouper = 'system', sort = '3', scope = 0 WHERE id = '98121701';

DELETE FROM  pmc_icon WHERE icon_type = 'project';
DELETE FROM  pmc_icon WHERE icon_type = 'workType';
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('7f63d59a4f3a', 'project1.png', 'project1.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('1566ca2f8ea6', 'project2.png', 'project2.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('8fe41d811db8', 'project3.png', 'project3.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c35cb5b60b2d', 'project4.png', 'project4.png', 'project');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('f6f63073d412', 'workType1.png', 'workType1.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('24e8be1867b8', 'workType2.png', 'workType2.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('abeb5a0e194a', 'workType3.png', 'workType3.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('070287809b9a', 'workType4.png', 'workType4.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('ff54eafd3756', 'workType5.png', 'workType5.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('2f950b04363c', 'workType6.png', 'workType6.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('5ce7cab32924', 'workType7.png', 'workType7.png', 'workType');
INSERT INTO pmc_icon (id, icon_name, icon_url, icon_type) VALUES ('c3ee42e730ac', 'workType8.png', 'workType8.png', 'workType');
