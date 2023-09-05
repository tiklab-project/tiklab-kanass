
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('2acdb67b', '下拉框', 'select', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('428bb346', '多选', 'checkbox', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('59eb5a88', '日期范围', 'dateRange', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('77198981', '单选', 'radio', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('b3c54c49', '开关', 'switch', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('cabda5be', '输入框', 'input', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('ddb4490b', '数字', 'number', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('defabc6d', '日期', 'date', 'system', NULL);
INSERT INTO pcs_foc_field_type (id, name, code, grouper, sort) VALUES ('eaf425a9', '文本框', 'textarea', 'system', NULL);

INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('0763e387', '缺陷类型', 'bugType', '2acdb67b', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('0f9ff2ce', '所属迭代', 'sprint', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('187d7a58', '优先级', 'workPriority', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('20a664a5', '任务描述', 'taskDesc', 'eaf425a9', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('3b3ac1bf', '前置事项', 'predependworkitem', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('416a168e', '负责人', 'assigner', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('42fffc89', '缺陷点', 'bugnum', 'ddb4490b', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('4b18b18a', '报告人', 'reporter', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('5f08e5f0', '审核日期', 'examineDate', 'defabc6d', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('622b36bb', '计划开始日期', 'planBeginTime', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('691deab6', '描述', 'desc', 'eaf425a9', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('7c43a85f', '所属小组', 'groups', '428bb346', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('a34d7fc3', '任务类型', 'taskType', '2acdb67b', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('b2565437', '简述', 'resume', 'eaf425a9', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('b588f4c3', '测试日期', 'testDate', '59eb5a88', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('bd073750', '事项类型', 'workType', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('bec9e2d4', '所属模块', 'module', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('c4579b11', '需求类型', 'demandType', '77198981', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('c5401366', '是否审查', 'examin', '77198981', 'custom', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('ca554bdd', '计划结束日期', 'planEndTime', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('d11e8ce7', '标题', 'title', 'cabda5be', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('e8787ab9', '所属项目', 'project', '2acdb67b', 'system', NULL, 0);
INSERT INTO pcs_foc_field (id, name, code, field_type, grouper, sort, is_edit_col) VALUES ('f828fa2b', '计划用时', 'planTakeupTime', '2acdb67b', 'system', NULL, 0);


INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('0c5d4ff9', '需求表单', '事项表单', 'system', 0, 1);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('0e425461', '需求表单', '事项表单', 'system', 0, 2);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('12267b6d', '任务表单', '任务表单', 'system', 0, 2);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('515f17bd', '任务表单', '任务表单', 'system', 0, 1);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('607f6be6', '缺陷表单', '事项表单', 'system', 0, 1);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('9a42061f', '事项表单', '事项表单', 'system', 2, 1);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('9c338860', '任务表单', '任务表单', 'system', 0, 2);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('c323f12b', '缺陷表单', '事项表单', 'system', 0, 2);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('c430e1ad', '缺陷表单', '事项表单', 'system', 0, 2);
INSERT INTO pcs_foc_form (id, name, description, grouper, defaults, scope) VALUES ('cd46de1f', '需求表单', '事项表单', 'system', 0, 2);

INSERT INTO pcs_foc_dm_form (id, domain_id, form_id, global_form_id) VALUES ('0b519804', 'f3fdc893ff25', '9c338860', '515f17bd');
INSERT INTO pcs_foc_dm_form (id, domain_id, form_id, global_form_id) VALUES ('126f8bae', 'f3fdc893ff25', 'c430e1ad', '607f6be6');
INSERT INTO pcs_foc_dm_form (id, domain_id, form_id, global_form_id) VALUES ('6022cb00', 'f3fdc893ff25', '0e425461', '0c5d4ff9');
INSERT INTO pcs_foc_dm_form (id, domain_id, form_id, global_form_id) VALUES ('a80e38c9', '4cf6d4dec7d0', '12267b6d', '515f17bd');
INSERT INTO pcs_foc_dm_form (id, domain_id, form_id, global_form_id) VALUES ('d3097c90', '4cf6d4dec7d0', 'cd46de1f', '0c5d4ff9');
INSERT INTO pcs_foc_dm_form (id, domain_id, form_id, global_form_id) VALUES ('e376ef8e', '4cf6d4dec7d0', 'c323f12b', '607f6be6');


INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('00e0c40d', '0c5d4ff9', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('03beced7', '515f17bd', 'ca554bdd', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('05f4bfba', '9a42061f', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('06a64f70', 'c430e1ad', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('07078af5', '0e425461', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('0723cd92', '0c5d4ff9', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('0e1c702b', '0c5d4ff9', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('0ec43f9d', '0e425461', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('0fa83899', 'cd46de1f', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('115c8e28', '9c338860', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1372dead', '607f6be6', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('13bec32d', '12267b6d', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1434bc98', '0e425461', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1551cb8d', '9c338860', '5f08e5f0', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('17a8db05', '0e425461', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1a0f38c2', '12267b6d', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1a389400', '607f6be6', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1d3d1898', 'c323f12b', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('1daacdbb', '9c338860', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('2293f026', 'c430e1ad', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('22afc333', '0c5d4ff9', '0f9ff2ce', 17, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('22db201b', '607f6be6', '42fffc89', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('24290871', 'c430e1ad', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('245fa038', '9a42061f', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('247309cb', '9c338860', 'b588f4c3', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('24e8d5f3', '0c5d4ff9', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('28907a80', '12267b6d', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('29c0e76b', '0c5d4ff9', 'b588f4c3', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('2df23e36', '0e425461', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('33075cc1', 'c323f12b', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('3445149c', 'c430e1ad', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('3508f00b', '9c338860', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('372a842c', '9c338860', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('37a364b9', '9a42061f', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('3809f229', 'c323f12b', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('3cc725be', '9a42061f', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('3df241f2', '12267b6d', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('40775f6e', '9c338860', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('40cf9e4a', 'c323f12b', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('48b46471', '515f17bd', '416a168e', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('4904ecfd', '9c338860', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('49265a70', 'c323f12b', '42fffc89', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('49ed271d', '515f17bd', 'bd073750', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('4f4ba698', '9c338860', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('4f8ac43e', '515f17bd', 'b588f4c3', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('4fe5668f', '0e425461', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('50e37a6b', '0e425461', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('51ce3b98', '515f17bd', '3b3ac1bf', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('596858ec', 'c323f12b', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('5b41ab62', '9a42061f', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('5bfd45a9', '515f17bd', 'bec9e2d4', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('5d5ace74', 'cd46de1f', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('61ba2bd0', 'c430e1ad', '42fffc89', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('638bbc15', '0e425461', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('63a17241', '607f6be6', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('63cc3600', '607f6be6', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('640db276', 'c430e1ad', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('64d8236c', '607f6be6', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('654e0751', 'cd46de1f', 'bd073750', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('683f4bcf', 'c430e1ad', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('6a4c87d6', '607f6be6', 'b2565437', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('6e780e05', '0c5d4ff9', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('6ea915cb', '9c338860', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('706e650b', 'c430e1ad', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('72d0e1c4', '9c338860', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('73777c17', '515f17bd', '622b36bb', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('74184059', '12267b6d', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('7447bf8e', '12267b6d', 'b588f4c3', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('746a5d65', '607f6be6', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('77b0ef39', '0e425461', 'bd073750', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('78b2281a', '12267b6d', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('79721ac5', '0e425461', '0f9ff2ce', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('7bc7b743', '12267b6d', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('7cdd1ba3', '12267b6d', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('7e506285', '0c5d4ff9', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('7f4a2140', 'c323f12b', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('805f79e1', '9a42061f', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('814825ac', 'c323f12b', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('84203a14', '9a42061f', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('85cd90cb', 'c430e1ad', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('88000423', '12267b6d', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('89f6151d', 'c430e1ad', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('8c0baa53', '9a42061f', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('8d6eae7b', 'cd46de1f', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('8d99e995', '0c5d4ff9', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('8e85e8f9', '607f6be6', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('91f9038e', '0e425461', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('92e59a4b', '0e425461', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('95958440', '9a42061f', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('95b4edda', 'c323f12b', 'b2565437', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('9663daaf', '12267b6d', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('96d7622a', '607f6be6', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('97101e11', '12267b6d', '5f08e5f0', 14, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('97d1c981', '0e425461', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('984b1739', '0c5d4ff9', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('9975e500', 'c430e1ad', 'b2565437', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('99e31890', '607f6be6', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('9f70718d', 'cd46de1f', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a05e3670', 'cd46de1f', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a123a4cc', 'c323f12b', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a137742c', '515f17bd', '5f08e5f0', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a3e30e17', '515f17bd', '187d7a58', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a402388b', '607f6be6', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a540b8e2', 'cd46de1f', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a5775a3d', '607f6be6', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a59cdb3a', 'c430e1ad', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('a91fb4cd', '12267b6d', '416a168e', 2, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('ab1ea8ab', '9a42061f', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('ab2cc2fd', '0c5d4ff9', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('ae647ad0', 'cd46de1f', 'c5401366', 17, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b4a9bdd0', '9c338860', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b4b83345', '0c5d4ff9', 'bd073750', 16, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b52ff41e', 'cd46de1f', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b7071259', 'c430e1ad', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b74105c1', '0c5d4ff9', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b75df895', 'cd46de1f', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b779277a', 'cd46de1f', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('b79ff96f', '0e425461', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('bc21cf07', 'cd46de1f', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c043443e', 'cd46de1f', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c0df6fe0', '9c338860', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c1bcb984', 'c430e1ad', 'd11e8ce7', 1, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c2270280', 'c323f12b', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c3bf509a', '9a42061f', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c3c99145', '9c338860', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c4c777f7', '0c5d4ff9', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c4ed498c', 'c323f12b', 'e8787ab9', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c63ba537', 'cd46de1f', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('c834a221', '9a42061f', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('cd743f29', 'c323f12b', '622b36bb', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('d1e5ded1', '515f17bd', '0f9ff2ce', 7, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('d3d39189', '9a42061f', '0f9ff2ce', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('d3ee7c5e', 'cd46de1f', '0f9ff2ce', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('d7249469', 'c323f12b', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('d8ad603a', '9c338860', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('da6eb35b', '0c5d4ff9', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('db88d4f7', '0e425461', 'c5401366', 17, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('dcf24c56', '607f6be6', '691deab6', 11, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('df658d25', '515f17bd', 'f828fa2b', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('e53b1c59', '515f17bd', 'e8787ab9', 15, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('e71e6b51', 'cd46de1f', 'b588f4c3', 16, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('e8290d1c', '607f6be6', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('e867bc91', '12267b6d', '187d7a58', 4, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('e8c55fbf', '515f17bd', '691deab6', 13, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('eb0512ea', '0e425461', 'b588f4c3', 16, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('edd746fc', 'c430e1ad', '3b3ac1bf', 9, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('edf80bcc', '12267b6d', '4b18b18a', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('ee7a4da4', 'cd46de1f', 'bd073750', 12, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('f53b5710', '515f17bd', 'd11e8ce7', 3, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('f85a0205', '0c5d4ff9', 'bec9e2d4', 6, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('f9e08395', '515f17bd', '4b18b18a', 5, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('fd1c9469', '0e425461', 'f828fa2b', 10, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('fec0799a', 'c323f12b', 'ca554bdd', 8, 0);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, sort, required) VALUES ('feda0326', '0c5d4ff9', 'c5401366', 15, 0);

INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('039bc15d', '易用性问题', '0763e387', 'easeOfUse', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('03da940d', '优化', 'a34d7fc3', 'optimization', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('11d6ea1f', '用户反馈', 'c4579b11', 'userFeedback', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('37cf210e', '安全问题', '0763e387', 'security', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('42cf3280', '竞品调研', 'c4579b11', 'competitorsSurvey ', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('4922a294', 'UI问题', '0763e387', 'ui', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('62a0df93', '性能问题', '0763e387', 'performance', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('6c1d0472', '产品规划', 'c4579b11', 'projectPlan', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('6ee78fbc', '新增功能', 'a34d7fc3', 'addfuncation', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('8581ea12', '其他', 'c4579b11', 'other', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('8b2bc535', '是', 'c5401366', 'true', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('8da39b77', '兼容性问题', '0763e387', 'compatibility', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('aa4f0af2', '项目组2', '7c43a85f', 'group2', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('adf72e9f', '内部需求', 'c4579b11', ' internalDemand', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('caf3ed46', '项目组1', '7c43a85f', 'group1', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('e6666586', '接口问题', '0763e387', 'api', NULL);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('e7d7d4ca', '功能问题', '0763e387', 'function', NULL);


INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('025e702fc3c4', '流程列表', 'SysFlowList', '063143949cc7', 42, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('05edc354e9ce', '添加报表', 'ReportAdd', '36bb7d9c537e', 29, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('063143949cc7', '流程中心', 'SysFlow', NULL, NULL, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('09a64b79b631', '添加迭代', 'SpintAdd', '1ff199504908', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('0c73e628fd54', '插件', 'SysPlugin', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('138654cdc36c', '消息发送方式', 'SysMessageSendType', '47fb980c2919', 41, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('1843bd5f5a73', '模块', 'module', NULL, 37, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('1f777ba063f7', '系统角色管理', 'SysRoleSys', '63fd58715558', 48, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('1ff199504908', '迭代', 'Sprint', NULL, 4, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('290accfb27c3', '表单中心', 'SysForm', NULL, NULL, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('2a6be8fda76f', '里程碑', 'Milestone', NULL, 5, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('2f2548b9de7b', '添加项目集', 'ProgramAdd', NULL, 32, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('321751dfd3a5', '项目角色管理', 'SysRoleProject', '63fd58715558', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('36bb7d9c537e', '报表', 'Report', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('3d513cb49e30', '添加版本', 'VersionAdd', '7d9b12620349', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('3e642d400783', '日志模板', 'SysLogTemplate', '5d2518f49057', 50, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('40d00b88b805', '删除报表', 'ReportDelete', '36bb7d9c537e', 30, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('47fb980c2919', '消息中心', 'SysMessage', NULL, NULL, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('48717b977129', '待办', 'SysTodo', NULL, 52, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('49e12c2b8fca', '权限', 'SysPermission', '63fd58715558', 47, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('4b982e213c12', '编辑模块', 'ModuleEdit', '1843bd5f5a73', 39, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('4cdb5348a9ca', '添加项目', 'ProjectAdd', NULL, 26, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('4f3307200787', '操作日志', 'SysLogList', '5d2518f49057', 45, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('519fc38f6390', '字段', 'SysField', '290accfb27c3', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('5386b5add6a5', '编辑迭代', 'SprintEdit', '1ff199504908', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('556157bb8449', '添加版本事项', 'VersionWorkAdd', '7d9b12620349', 25, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('5d5b64c3d172', '待办类型', 'SysTodoList', '48717b977129', 54, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('63fd58715558', '权限中心', 'SysPriviliege', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('66bd7120b0de', '关联项目', 'RelationProject', 'dae13d309463', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('72e68d34870e', '项目', 'Project', NULL, 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('746c3becb86f', '消息类型', 'SysMessageType', '47fb980c2919', 49, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('77da6376511e', '项目类型', 'SysProjectType', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('7c8652d3e2f0', '字段类型', 'SysFieldType', '290accfb27c3', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('7ff4702ebe64', '项目功能管理', 'SysFeatrueProject', '63fd58715558', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('86db94ef62a6', '用户管理', 'SysUser', '1465dc3ae3c4', NULL, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('8d33e4945151', '表单', 'SysFormList', '290accfb27c3', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('90b4e472163a', '事项类型', 'SysWorkType', 'fdb16c391ffc', 0, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('9538263abda4', '编辑项目集', 'ProjectSetEdit', 'dae13d309463', 36, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('97c93ffe00c9', '组织管理', 'SysOrga', '1465dc3ae3c4', NULL, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('a11ca9e4559e', '消息通知方案', 'SysMessageNotice', '47fb980c2919', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('a13114663695', '编辑报表', 'ReportEdit', '36bb7d9c537e', 31, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('a3ee33eba363', '编辑项目', 'ProjectEdit', '72e68d34870e', 27, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('aa2bd97ffce7', '删除版本', 'VersionDelete', '7d9b12620349', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('abe6fffaae63', '编辑里程碑', 'MilestoneEdit', '2a6be8fda76f', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('b80a4416f42f', '添加模块', 'ModuleAdd', '1843bd5f5a73', 38, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('bfbb4a6b97a4', '删除迭代', 'SprintDelete', '1ff199504908', 28, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('c5af706628c2', '系统功能管理', 'SysFeatrueSys', '63fd58715558', 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('c9efe0b7e166', '日志类型', 'SysLogType', '5d2518f49057', 51, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('ce9b25e6cac7', '添加事项', 'WorkAdd', 'fdb16c391ffc', 0, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('d3447a36dd98', '删除里程碑', 'MilestoneDele', '2a6be8fda76f', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('d7e0583f53e7', '删除事项', 'WorkDelete', 'fdb16c391ffc', 0, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('d893af6f93ff', '删除项目', 'ProjectDelete', '72e68d34870e', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('da52f90b3707', '添加里程碑', 'MilestoneAdd', '2a6be8fda76f', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('dae13d309463', '项目集', 'ProjectSet', NULL, 35, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('de21752872e9', '流程状态', 'SysFlowNode', '063143949cc7', 43, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('df306d70374c', '删除项目集', 'ProjectSetDelete', 'dae13d309463', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('f4f16a0df8e4', '编辑版本', 'VersionEdit', '7d9b12620349', 1, '2');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('f9e27dd6f76e', '待办管理', 'SysSetting', '48717b977129', 53, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('fdb16c391ffc', '事项', 'SysWork', NULL, 1, '1');
INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('fec1a8ee3f6c', '事项优先级', 'SysWorkPriority', 'fdb16c391ffc', 0, '1');


INSERT INTO pcs_prc_function (id, name, code, parent_function_id, sort, type) VALUES ('f569dfa96880', '版本与许可证', 'SysVersion', 'fdb16c391ffc', 0, '1');

INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ba2c6ec37566', '77f512ab7c53', 'f569dfa96880');


INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('1', 'admin', '管理员', 'system', '1', 1, 1);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('2', 'admin', '管理员', 'system', '2', 1, 1);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('323810f03653', '普通用户', '初始化', 'system', '2', 2, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('32521b1d228f', 'admin', '管理员', 'system', '2', 2, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('4559d54bc8b7', '普通用户', '初始化', 'system', '2', 1, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('77f512ab7c53', '普通用户', '初始化', 'custom', '1', 1, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('7fd9ce1c7be1', '普通用户', '初始化', 'system', '2', 2, 0);
INSERT INTO pcs_prc_role (id, name, description, grouper, type, scope, business_type) VALUES ('f899d7406414', 'admin', '管理员', 'system', '2', 2, 0);

INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0384b19fb96f', '323810f03653', '556157bb8449');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('06cbccb937e0', '2', '3d513cb49e30');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('07a90ef59e7e', 'f899d7406414', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0867737efbce', '32521b1d228f', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0af6cf341338', 'f899d7406414', '7d9b12620349');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0e2818de51a6', '2', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0e6787594fe2', '7fd9ce1c7be1', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0eaef912aa8e', 'f899d7406414', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('11638bbe4623', '1', '290accfb27c3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('13cfa8a07d95', '77f512ab7c53', '8d33e4945151');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1509ab54a573', '323810f03653', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('16bc10074ad0', '2', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1728682f6d0e', '7fd9ce1c7be1', '7d9b12620349');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('18d6173f4007', '1', '2f2548b9de7b');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('18dde3eac131', '323810f03653', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1a66aa6f47ce', '323810f03653', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1af1c1ad0e37', 'f899d7406414', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1b154c7bd658', '77f512ab7c53', '57a3bcd1e5fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1b23e606c6eb', '2', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1c4f37558ba0', '1', '9c99b8a096c8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1d1705dcd760', '4559d54bc8b7', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1f0df69963c9', '4559d54bc8b7', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1f9bceac059b', '77f512ab7c53', '9314739a13fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2071edcc3257', '4559d54bc8b7', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('225a7137358b', 'f899d7406414', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('249f5dffa66c', '2', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('24af8dc2a546', '32521b1d228f', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('251b3b6c9300', '32521b1d228f', 'aa2bd97ffce7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('259566e6cbe5', '2', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('25d618897805', '4559d54bc8b7', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('271aee47153c', '7fd9ce1c7be1', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('27b395a81a62', '7fd9ce1c7be1', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('27f117b7bfdc', '323810f03653', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2adef967afe9', '7fd9ce1c7be1', 'f4f16a0df8e4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2b311cfdb319', '32521b1d228f', '7d9b12620349');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2bfe48c18e48', '1', '9314739a13fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2d372f44f334', '32521b1d228f', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2ebe5a6a9215', 'f899d7406414', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3253c2d87e9e', '77f512ab7c53', 'fec1a8ee3f6c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3286360e6323', '32521b1d228f', '556157bb8449');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('330a15f5cc3c', '77f512ab7c53', '7c8652d3e2f0');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('336240b887bf', '32521b1d228f', 'd893af6f93ff');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('33e5c035badf', '1', '325c2503007f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('350862f7a6bf', 'f899d7406414', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('356492740083', '4559d54bc8b7', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3580a96d516d', '1', '57a3bcd1e5fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('362a5557badc', '2', 'd893af6f93ff');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('373283c79ded', 'f899d7406414', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('380179f52eb6', '32521b1d228f', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('38affaa0f5fe', '32521b1d228f', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('39cf725aa2ff', '4559d54bc8b7', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3a2d85c9a1e4', '32521b1d228f', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3b695bad5297', 'f899d7406414', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3cd334e08d99', '4559d54bc8b7', 'aa2bd97ffce7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3f3a112b1644', '4559d54bc8b7', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3f430cf439a6', '1', 'fec1a8ee3f6c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4112ed0893a1', '4559d54bc8b7', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('411f9d02d0fc', '1', 'cb954a7c0be3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('41242b417e8f', '7fd9ce1c7be1', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4131746d467d', '1', '428be660dea3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('433403b40e88', '2', 'f4f16a0df8e4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('43d3d4459278', '323810f03653', '3d513cb49e30');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('445c5ad97bb2', '2', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('44ed21c1f1f0', '7fd9ce1c7be1', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('474316296751', '32521b1d228f', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('477dded4bfb4', '32521b1d228f', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('48a09cee38bb', '323810f03653', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('48df1259343a', 'f899d7406414', 'bfbb4a6b97a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4a0955e7cda8', '77f512ab7c53', '5fb7863b09a8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4c74be36c9bc', '7fd9ce1c7be1', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4e02777c4d29', '1', '0c73e628fd54');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4f0af087f0fd', '7fd9ce1c7be1', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4f18196200e3', '2', 'aa2bd97ffce7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4f2b2ed4262e', '4559d54bc8b7', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4fc0599fcdcd', 'f899d7406414', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5127ebfd8d97', '1', 'edb60bf65e42');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('513b415006d8', '323810f03653', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5211887eb9d2', '32521b1d228f', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('54604b672aa7', '2', 'bfbb4a6b97a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('572a1c7e1bce', '4559d54bc8b7', '7d9b12620349');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('57fe6a3ef1c4', '32521b1d228f', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5b07c6225563', '1', '890e7d41decf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5b666c00bbf1', '77f512ab7c53', 'cb954a7c0be3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('60a6265952fb', '7fd9ce1c7be1', 'bfbb4a6b97a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('627f6b354040', '2', '7d9b12620349');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('67308d98da7b', '2', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('69367b9eb826', '32521b1d228f', '3d513cb49e30');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('698e686acae7', '2', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('69bfba35685f', '77f512ab7c53', '447d9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6a4684c6779f', '2', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6bbe5a2558f4', '1', 'f6f51f944133');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6bc07e6604a3', '4559d54bc8b7', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6dbb1bfe6c8f', '32521b1d228f', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6f70073e0d99', '77f512ab7c53', '925371be8ec6');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7116bd8459f6', '1', '063143949cc7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('72d6661836c3', '7fd9ce1c7be1', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('741c66428809', '323810f03653', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7869284f8a88', '1', '8d33e4945151');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7937b0c7cdb7', '1', 'e8bf9843bc9d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7961a24de0d1', '7fd9ce1c7be1', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7a0b8cfad444', '77f512ab7c53', '063143949cc7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7c3ce8efb864', '323810f03653', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7c4ff01e1b7c', '323810f03653', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7e1c40bce22f', 'f899d7406414', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7ef21ec46d62', '323810f03653', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('807166b0aa7d', '4559d54bc8b7', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('81e1f15c7714', '77f512ab7c53', '043e412151db');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('829e304a4f2f', '4559d54bc8b7', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8423ec5b1371', 'f899d7406414', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8445e3e4a249', '32521b1d228f', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('845848ec9783', '77f512ab7c53', '519fc38f6390');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('85f68840c769', '77f512ab7c53', '325c2503007f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('882dcf55f35a', '2', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8840d3009342', '1', '5fb7863b09a8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('89705349bae1', '7fd9ce1c7be1', '556157bb8449');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8a7096cf8541', 'f899d7406414', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8a7da0653fc2', '7fd9ce1c7be1', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8bb880b9433a', '1', '447d9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8cb537cd6ff9', '2', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8cfd2483bae7', '1', '4cdb5348a9ca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8e21b03b0c5b', '4559d54bc8b7', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8e8381438e32', '4559d54bc8b7', 'bfbb4a6b97a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8ebf982cd1f3', '32521b1d228f', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8ec8775a955c', '77f512ab7c53', '6b61fbe5091a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8f44c97d21e8', '2', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('90ea06611801', '1', '925371be8ec6');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9138b123e072', '77f512ab7c53', '290accfb27c3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('91e202a790a4', 'f899d7406414', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('92abacceed2a', 'f899d7406414', 'f4f16a0df8e4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9313181d6146', '4559d54bc8b7', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9af8f2428c6b', '77f512ab7c53', '428be660dea3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9be1c91c0212', '323810f03653', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9ffdeacc1ef7', '4559d54bc8b7', '556157bb8449');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a0706f9eb64b', '2', '556157bb8449');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a0c9b5bb00d9', '77f512ab7c53', '890e7d41decf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a2000ec2826d', '323810f03653', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a28a0225bacc', 'f899d7406414', '3d513cb49e30');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a2bd75e1be35', '323810f03653', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a47b64d9c83c', '4559d54bc8b7', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a4dc27cd42c6', '7fd9ce1c7be1', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a54f7dc3c503', '323810f03653', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a80f6360cea2', 'f899d7406414', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a8a88e66a138', '323810f03653', 'aa2bd97ffce7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ac1e84f2c5b9', '77f512ab7c53', '585d26bcbdf3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b0a071648bfd', '77f512ab7c53', '77da6376511e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b24fc3356a97', '323810f03653', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b2ba90bbdd70', '7fd9ce1c7be1', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b3d6603a7064', '7fd9ce1c7be1', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b49a67f49e37', '7fd9ce1c7be1', '3d513cb49e30');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b4f72e449dbe', '77f512ab7c53', '2f2548b9de7b');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b56a5709b0e6', 'f899d7406414', '556157bb8449');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b67a5e4543a2', '32521b1d228f', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b75a5cfcd434', '1', '90b4e472163a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b8249548ac7f', '1', '585d26bcbdf3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('bb08fa8647f0', '32521b1d228f', 'f4f16a0df8e4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('bd5cc43663f3', '7fd9ce1c7be1', 'aa2bd97ffce7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('bda657ee5e7b', '7fd9ce1c7be1', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('be29861f2ed8', '4559d54bc8b7', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('bfd39a686968', '4559d54bc8b7', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c1447ab95337', '323810f03653', 'f4f16a0df8e4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c201276fbbbc', '1', '7c8652d3e2f0');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c26bdeb81fb7', 'f899d7406414', 'd893af6f93ff');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c36f76282dda', '77f512ab7c53', '0c73e628fd54');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c6677ccef6ce', '4559d54bc8b7', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c9affb20f471', '2', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cd4edb5d23bb', 'f899d7406414', 'aa2bd97ffce7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cd8f800952d4', '32521b1d228f', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d01e0f47de62', '77f512ab7c53', '90b4e472163a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d19ec7f07cdf', '2', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d1a80aaea185', '32521b1d228f', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d1b412baeeb4', '7fd9ce1c7be1', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d3fe6d7a91cd', '77f512ab7c53', '4cdb5348a9ca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d4e6de264709', '32521b1d228f', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d5d3ad9ba8de', '7fd9ce1c7be1', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d78cdc7a4b74', '2', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d7a525ec06c1', '4559d54bc8b7', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d7ea547dfa4b', '77f512ab7c53', '9633d9475886');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('db2770819324', '323810f03653', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('db64e4d09379', '323810f03653', '7d9b12620349');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('dc1e9a63876a', '1', '77da6376511e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('dded1b3af81c', '77f512ab7c53', '9c99b8a096c8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('de8fb602d11f', 'f899d7406414', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('df2509f2b01a', 'f899d7406414', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('df77366fb4ae', '32521b1d228f', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e04a38df1bac', '323810f03653', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e0c8c10e7796', '1', 'dd81bdbb52bc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e15420999065', '77f512ab7c53', 'edb60bf65e42');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e248f0c8c761', '323810f03653', 'bfbb4a6b97a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e255dc72fbdd', '1', '519fc38f6390');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e35ed5dfac6c', '7fd9ce1c7be1', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e38d27b687a3', '323810f03653', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e47613a1c60d', '2', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e4dc3a9f88b5', '77f512ab7c53', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e573d58c51ba', '2', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e5a49ae15ee3', 'f899d7406414', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e5a98ff91465', '77f512ab7c53', 'f6f51f944133');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e6216820335c', '77f512ab7c53', '47fb980c2919');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e829c3648f58', '4559d54bc8b7', 'f4f16a0df8e4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e90ef4935ffb', '1', '043e412151db');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e9520e6c5112', '2', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ea1c42df716e', '2', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('eb73e860e5ad', '323810f03653', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('eb893c87f5ae', 'f899d7406414', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ece2f601edcf', '323810f03653', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ed6ee8d87b12', '1', '47fb980c2919');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ede3985c08fa', '4559d54bc8b7', '3d513cb49e30');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ee7400639e3d', '32521b1d228f', 'bfbb4a6b97a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('eec085569052', '7fd9ce1c7be1', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ef1ff7edc992', '32521b1d228f', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f05572ba4052', '1', '6b61fbe5091a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f174891a8987', 'f899d7406414', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f24c37768750', '4559d54bc8b7', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f2fe741a6803', 'f899d7406414', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f3844533c0c0', '7fd9ce1c7be1', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f78bbee5deea', '32521b1d228f', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f99cc87b3484', '1', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f9ae73e72f8d', '2', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f9f10a9b6c6d', '7fd9ce1c7be1', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('81f4befbcc5a', '2', 'b80a4416f42f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e131a682a210', '2', '4b982e213c12');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('65f83cc98f66', '2', '1843bd5f5a73');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c7123f86ff31', '32521b1d228f', '4b982e213c12');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5d9474d33f88', '32521b1d228f', 'b80a4416f42f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('96ddd0674769', '32521b1d228f', '1843bd5f5a73');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('658385a1ee03', 'f899d7406414', '1843bd5f5a73');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cfd0212bbbe3', 'f899d7406414', 'b80a4416f42f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d200c77ff787', 'f899d7406414', '4b982e213c12');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ff2b707db9c1', '1', 'oug5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b02cb492e16f', '1', 'wqre9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('54002045ce64', '1', 'hf43e412151e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('60834b5b4e43', '1', '4235d2624bdf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1160ea8402ce', '1', 'hfg5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5744a31320a9', '1', '43e7d41decf7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c011e6aecd23', '1', 'a11ca9e4559e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4959e7511d36', '1', '746c3becb86f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5a4ed8eb0b69', '1', '138654cdc36c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4cfe0106dd6a', '1', 'de21752872e9');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2cfa9eaeb209', '1', '7ff4702ebe64');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d6f8fe78676b', '1', '1f777ba063f7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('981d2694ff80', '1', '025e702fc3c4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2fba63dffebf', '1', '5d5b64c3d172');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('47c930cd8013', '1', 'c5af706628c2');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4d6d6d498f4d', '1', '48717b977129');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6bdefbe3f29f', '1', '63fd58715558');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1d0a3776d396', '1', 'f9e27dd6f76e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ca5636f06db8', '1', '49e12c2b8fca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5cde17f1b39c', '1', '321751dfd3a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1a08cd5778cb', '1', '9633d9475886');



INSERT INTO pcs_prc_dm_role (id, domain_id, role_id) VALUES ('05a4fd811427', 'f3fdc893ff25', '323810f03653');
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id) VALUES ('86912b2f65de', '4cf6d4dec7d0', '7fd9ce1c7be1');
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id) VALUES ('d34704c82822', 'f3fdc893ff25', 'f899d7406414');
INSERT INTO pcs_prc_dm_role (id, domain_id, role_id) VALUES ('f1968b10de30', '4cf6d4dec7d0', '32521b1d228f');

INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('177715b8d071', 'd34704c82822', 'f3fdc893ff25', '111111');
INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('639f1838c88e', '05a4fd811427', 'f3fdc893ff25', '111111');
INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('8061e9feec9f', '86912b2f65de', '4cf6d4dec7d0', '111111');
INSERT INTO pcs_prc_dm_role_user (id, dmRole_id, domain_id, user_id) VALUES ('b882c6171f34', 'f1968b10de30', '4cf6d4dec7d0', '111111');

INSERT INTO pcs_prc_role_user (id, role_id, user_id) VALUES ('38c0375b6adf', '32521b1d228f', '111111');
INSERT INTO pcs_prc_role_user (id, role_id, user_id) VALUES ('4df7c0dae8b5', '7dc47e805306', '111111');
INSERT INTO pcs_prc_role_user (id, role_id, user_id) VALUES ('78ddd5263ed6', 'f899d7406414', '111111');
INSERT INTO pcs_prc_role_user (id, role_id, user_id) VALUES ('be3d8a44ee5c', '958339ea47a6', '111111');
INSERT INTO pcs_prc_role_user (id, role_id, user_id) VALUES ('c9b6e4449057', '45937fbdea22', '111111');

INSERT INTO pcs_ucc_dm_user (id, domain_id, user_id, type) VALUES ('401b9f477890', 'f3fdc893ff25', '111111', 0);
INSERT INTO pcs_ucc_dm_user (id, domain_id, user_id, type) VALUES ('5dc8b55815ae', '4cf6d4dec7d0', '111111', 0);

INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('1bba4e45931f', '事项更新', 'teamwire');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('a1f2cb478fad', '事项添加', 'teamwire');
INSERT INTO pcs_op_log_type (id, name, bgroup) VALUES ('ff0a79e4fc61', '添加项目', 'teamwire');

INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('31d39e6f981a', '事项状态修改', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div>
                    <span style="padding-right: 10px;"> ${master.nickname} </span> 更新了
                    <span style="color: #5d70ea;font-size: 13px; font-weight: 500;"> ${workItemTitle} </span> 状态
                </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;">
                    ${oldValue. name}
                    </div> ——— <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;">
                     ${newValue. name}
                    </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'teamwire', '${master.nickname}更新了状态');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('c5613dbc2726', '项目添加日志', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div> <span style="padding-right: 10px;"> ${master} </span> 创建了项目 </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right:10px;">
                    <img src=${projectIcon} alt="" width="16px" height="16px"> </div>
                    <div style="color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px;border-radius: 5px;"> ${projectName} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${creatTime} </div>
    </div>', '/index/${projectType}/${projectId}/survey', 'teamwire', '${master}添加项目');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('4c4d9114ddc0', '事项负责人修改', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div>
                    <span style="padding-right: 10px;"> ${master.nickname} </span> 更新了
                    <span style="color: #5d70ea;font-size: 13px; font-weight: 500;"> ${workItemTitle} </span> 负责人 </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;">
                    ${oldValue.nickname}
                    </div> ———
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;"> ${newValue.nickname} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'teamwire', '${master.nickname}更换负责人');
INSERT INTO pcs_op_log_template (id, title, content, link, bgroup, abstract_content) VALUES ('f621925c6e63', '事项添加', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div
                style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;">
                ${createUserIcon}
            </div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div> <span style="padding-right: 10px;"> ${master} </span> 创建了${workItemTypeName} </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div
                        style="font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right: 10px;">
                        <img src=${workItemIcon} alt=width=16px height=16px>
                    </div>
                    <div
                        style="color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px;">
                        ${workItemTitle} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${createTime} </div>
    </div>', 'index/work/workone/${workItemId}', 'teamwire', '${master}添加事项');

INSERT INTO pcs_todo_task_type (id, name, bgroup) VALUES ('c30aac12c4be', '事项待办', 'teamwire');
INSERT INTO pcs_todo_task_template (id, title, link, bgroup, content) VALUES ('ff0a79e4fc61', '待办事项', 'index/work/workone/${workItemId}', 'teamwire', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div> <span style="font-weight: 600;"> ${createUser.name} </span> 向您分配了事项 </div>
                <div style="line-height: 30px; display: flex; align-items: center; height: 30px;">
                <img src=${workTypeIcon} alt="" width="16px" height="16px">
                    <div style="color: #5d70ea; margin-left: 10px;">${workItemTitle}</div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>');

INSERT INTO pmc_work_priority (id, name, description, sort, grouper,icon_url) VALUES ('1', '最低', NULL, NULL, 'system', 'proivilege1.png');
INSERT INTO pmc_work_priority (id, name, description, sort, grouper, icon_url) VALUES ('2', '低', NULL, NULL, 'system', 'proivilege2.png');
INSERT INTO pmc_work_priority (id, name, description, sort, grouper, icon_url) VALUES ('3', '中', NULL, NULL, 'system', 'proivilege3.png');
INSERT INTO pmc_work_priority (id, name, description, sort, grouper, icon_url) VALUES ('4', '高', NULL, NULL, 'system', 'proivilege4.png');
INSERT INTO pmc_work_priority (id, name, description, sort, grouper, icon_url) VALUES ('5', '最高', NULL, NULL, 'system', 'proivilege5.png');


INSERT INTO pmc_work_type (id, name, description, code, sort, form_id, flow_id, icon_url, grouper, scope) VALUES ('7055ebc6', '需求', '需求', 'demand', 1, '0c5d4ff9', '4d040c6d', 'workType1.png', 'system', 0);
INSERT INTO pmc_work_type (id, name, description, code, sort, form_id, flow_id, icon_url, grouper, scope) VALUES ('778222e0', '任务', '任务', 'task', 2, '515f17bd', '4d040c6d', 'workType2.png', 'system', 0);
INSERT INTO pmc_work_type (id, name, description, code, sort, form_id, flow_id, icon_url, grouper, scope) VALUES ('98121701', '缺陷', '缺陷', 'defect', 3, '607f6be6', '4d040c6d', 'workType3.png', 'system', 0);

INSERT INTO pmc_project_type (id, type, name, description, icon_url) VALUES ('5a46432a', 'scrum', '敏捷式项目', '敏捷式项目', 'project1.png');
INSERT INTO pmc_project_type (id, type, name, description, icon_url) VALUES ('ea782c6d', 'nomal', '瀑布研发项目', '瀑布研发项目', 'project2.png');

INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('000000', '未开始', '未开始', 1, 'system');
INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('111111', '进行中', '进行中', 2, 'system');
INSERT INTO pmc_sprint_status (id, name, description, sort, grouper) VALUES ('222222', '已完成', '已完成', 3, 'system');

INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id) VALUES ('7fcf0ff78d62', 'c36a8358f8e2', 1, 'teamwire', 'dingding,email,qywechat,site');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id) VALUES ('d616bdf8fbc1', 'a12523a2d65b', 1, 'teamwire', 'dingding,email,qywechat,site');
INSERT INTO pcs_mec_message_notice (id, message_type_id, type, bgroup, message_send_type_id) VALUES ('fb0c8fe5cae9', 'f5f8a45722db', 1, 'teamwire', 'dingding,email,qywechat,site');

INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('1f90ca2b7420', 'c36a8358f8e2', 'dingding', '事项状态变更-钉钉', '**事项状态变更通知**${createUser.nickname} 更新了 <font color=info> ${workItemTitle}</font>状态![图片](${workTypeIcon})${oldValue. name} -> ${newValue. name}', '/index/work/workone/${workItemId}', 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('65135e38383d', 'a12523a2d65b', 'dingding', '项目添加-钉钉', '**项目添加通知**${master} 添加了项目 <font color=info> ${projectName}</font>状态![图片](${projectIcon})', NULL, 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('8a9808957cf4', 'f5f8a45722db', 'site', '任务待办-站内信', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 10px; width: 250px">
                <div> <span style="font-weight: 600;"> ${createUser.name} </span> 向您分配了事项 </div>
                <div style="line-height: 30px; display: flex; align-items: center; height: 30px;"> <img
                        src="${workTypeIcon}" alt="" width="16px" height="16px">
                    <div style="color: #5d70ea; margin-left: 10px;text-overflow: ellipsis;white-space: nowrap;height: 30px;overflow: hidden;">${workItemTitle}</div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>', '/index/work/workone/${workItemId}', 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('0588213d423b', 'f5f8a45722db', 'email', '任务待办-邮箱', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 10px; width: 250px">
                <div> <span style="font-weight: 600;"> ${createUser.name} </span> 向您分配了事项 </div>
                <div style="line-height: 30px; display: flex; align-items: center; height: 30px;"> <img
                        src="${workTypeIcon}" alt="" width="16px" height="16px">
                    <div style="color: #5d70ea; margin-left: 10px;text-overflow: ellipsis;white-space: nowrap;height: 30px;overflow: hidden;">${workItemTitle}</div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>', NULL, 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('6800f0954b41', 'c36a8358f8e2', 'email', '事项状态变更-邮箱', '<div
        style="display: flex;  align-items: center;  font-size: 14px;  justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px;height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;">
                ${createUserIcon}</div>
            <div style="display: flex;  flex-direction: column;  padding: 0 20px;">
                <div> <span style="padding-right: 10px;"> ${master.nickname} </span> 更新了
                <span style="color: #5d70ea;font-size: 13px; font-weight: 500;"> ${workItemTitle} </span> 状态 </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;">
                        ${oldValue. name}
                    </div> ———
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;"> ${newValue. name} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>', NULL, 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('c386e011802a', 'f5f8a45722db', 'qywechat', '任务待办-企业微信', '**事项待办通知**${createUser.nickname} 向您分配了事项 <font color=info> ${workItemTitle}</font>![图片](${workTypeIcon})', '', 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('fc20ed1a0aca', 'c36a8358f8e2', 'qywechat', '事项状态变更-企业微信', '**事项状态变更通知**${createUser.nickname} 更新了 <font color=info> ${workItemTitle}</font>状态![图片](${workTypeIcon})${oldValue. name} -> ${newValue. name}', NULL, 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('fc30b7434a89', 'a12523a2d65b', 'qywechat', NULL, '**项目添加通知**${master} 添加了项目 <font color=info> ${projectName}</font>状态![图片](${projectIcon})', NULL, 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('ffe7cb993f47', 'f5f8a45722db', 'dingding', '任务通知-钉钉', '**事项待办通知**${createUser.nickname} 向您分配了事项 <font color=info> ${workItemTitle}</font>![图片](${workTypeIcon})</div>', '/index/prodetail/{id}/{project.id}', 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('142f281b3dd9', 'c36a8358f8e2', 'site', '事项状态变更-站内信', '<div
        style="display: flex;  align-items: center;  font-size: 14px;  justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div
                style="width: 25px;height: 25px; line-height: 25px; background-color: #ccc; border-radius: 32px; text-align: center; color: #fff;">
                ${createUserIcon}</div>
            <div style="display: flex;  flex-direction: column;  padding: 0 20px;">
                <div>
                    <span style="padding-right: 10px;"> ${master.nickname} </span> 更新了
                    <span style="color: #5d70ea;font-size: 13px; font-weight: 500;"> ${workItemTitle} </span> 状态
                </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px;border-radius: 5px; margin-right: 10px;">
                        ${oldValue. name}
                    </div> ———
                    <div style="padding: 5px 10px; background-color: #F4F5F7; font-size: 12px; line-height: 15px; border-radius: 5px;margin-left: 10px;"> ${newValue. name} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${receiveTime} </div>
    </div>', '/index/work/workone/${workItemId}', 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('9fd5b40149ea', 'a12523a2d65b', 'site', '项目添加消息', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div> <span style="padding-right: 10px;"> ${master} </span> 创建了项目 </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right:10px;">
                    <img src=${projectIcon} alt="" width="16px" height="16px"> </div>
                    <div style="color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px;border-radius: 5px;"> ${projectName} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${sendTime} </div>
    </div>', '/index/${projectType}/${projectId}/survey', 'teamwire', NULL);
INSERT INTO pcs_mec_message_template (id, msg_type_id, msg_send_type_id, title, content, link, bgroup, link_params) VALUES ('9f71be8a74e0', 'a12523a2d65b', 'email', '项目添加-邮箱', '<div style="display: flex; align-items: center; font-size: 14px; justify-content: space-between;">
        <div style="display: flex;align-items: center;">
            <div style="width: 25px; height: 25px; line-height: 25px; background-color: #ccc; border-radius: 25px;text-align: center; color: #fff;">${createUserIcon}</div>
            <div style="display: flex; flex-direction: column; padding: 0 20px;">
                <div> <span style="padding-right: 10px;"> ${master} </span> 创建了项目 </div>
                <div style="line-height: 40px; display: flex; align-items: center; height: 40px;">
                    <div style="font-size: 12px; height: 15px; line-height: 15px; border-radius: 5px; margin-right:10px;">
                    <img src=${projectIcon} alt="" width="16px" height="16px"> </div>
                    <div style="color: #5d70ea; cursor: pointer; font-size: 12px; height: 15px; line-height: 15px;border-radius: 5px;"> ${projectName} </div>
                </div>
            </div>
        </div>
        <div style="font-size: 13px;"> ${sendTime} </div>
    </div>', NULL, 'teamwire', NULL);

INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('a12523a2d65b', '项目添加', '项目添加', 'teamwire');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('c36a8358f8e2', '事项变更', '事项变更', 'teamwire');
INSERT INTO pcs_mec_message_type (id, name, description, bgroup) VALUES ('f5f8a45722db', '任务通知', '任务通知', 'teamwire');

INSERT INTO pcs_mec_webhook (id, name, type, hook, secret, url, product, project, send_type, params, description) VALUES ('61f8b3bba61a', '企业微信', 2, 'https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=f82568d4-2a86-45e6-9b55-c42df09a60f2', NULL, 'http://project.dev.doublekit.io', NULL, NULL, 1, 'text', NULL);
INSERT INTO pcs_mec_webhook (id, name, type, hook, secret, url, product, project, send_type, params, description) VALUES ('95444abff27f', '钉钉', 1, 'https://oapi.dingtalk.com/robot/send?access_token=49886270bff587fbd2da563e194667b5f669acf4b71751729ba9c24a084237ac', 'SEC72979f777deef91fdeeb4faad8ac70a4730168ee3b04dacbf52757279209e4a8', 'http://project.dev.doublekit.io', NULL, NULL, 1, 'text', NULL);

INSERT INTO pmc_project (id, project_key, project_name, project_type_id, project_limits, master,  creator, description, project_set_id, project_state, start_time, end_time, icon_url) VALUES ('4cf6d4dec7d0', 'apis', '接口关联软件开发', '5a46432a', '0', '111111', '111111', 'asa', NULL, '1', '2022-12-13 00:00:00', '2022-12-31 00:00:00', 'project1.png');
INSERT INTO pmc_project (id, project_key, project_name, project_type_id, project_limits, master, creator, description, project_set_id, project_state, start_time, end_time, icon_url) VALUES ('f3fdc893ff25', 'matflow', '流水线项目', 'ea782c6d', '0', '111111', '111111', NULL, NULL, '1', '2022-12-13 00:00:00', '2023-01-31 00:00:00', 'project1.png');

--CREATE INDEX name_index ON pmc_project USING gin (project_name GIN_TRGM_OPS);

INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, plan_takeup_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type) VALUES ('apis-1', 2, 'apis-1', '接口设计', '[{"type":"paragraph","children":[{"text":""}]}]', NULL, NULL, '4cf6d4dec7d0', 'b81aef3b', NULL, 'a0f286c2', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2022-12-13 00:00:00', '2022-12-13 00:00:00', NULL, NULL, NULL, NULL, '{}', '2022-12-13 08:16:00', 'TODO', 'demand', NULL, '7055ebc6', 'todo', '6c1d0472');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, plan_takeup_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type) VALUES ('apis-3', 3, 'apis-3', '接口开发', '[{"type":"paragraph","children":[{"text":""}]}]', NULL, NULL, '4cf6d4dec7d0', 'd3ef6551', NULL, 'a0f286c2', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2022-12-13 00:00:00', '2022-12-13 00:00:00', NULL, NULL, NULL, NULL, '{}', '2022-12-13 08:16:24', 'TODO', 'task', NULL, '778222e0', 'todo', '03da940d');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, plan_takeup_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type) VALUES ('apis-4', 4, 'apis-4', '接受数据失败', '[{"type":"paragraph","children":[{"text":""}]}]', NULL, NULL, '4cf6d4dec7d0', 'a64e8050', NULL, 'a0f286c2', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2022-12-13 00:00:00', '2022-12-13 00:00:00', NULL, NULL, NULL, NULL, '{}', '2022-12-13 08:16:47', 'TODO', 'defect', NULL, '98121701', 'todo', '4922a294');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, plan_takeup_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type) VALUES ('matflow-1', 2, 'matflow-1', '流水线运行', '[{"type":"paragraph","children":[{"text":""}]}]', NULL, NULL, 'f3fdc893ff25', 'ef23b25f', NULL, 'eedd26da', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2022-12-13 00:00:00', '2022-12-13 00:00:00', NULL, NULL, NULL, NULL, '{}', '2022-12-13 08:19:18', 'TODO', 'demand', NULL, '7055ebc6', 'todo', '6c1d0472');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, plan_takeup_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type) VALUES ('matflow-3', 3, 'matflow-3', '开发流水线项目', '[{"type":"paragraph","children":[{"text":""}]}]', NULL, NULL, 'f3fdc893ff25', 'c9e59337', NULL, 'eedd26da', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2022-12-13 00:00:00', '2022-12-13 00:00:00', NULL, NULL, NULL, NULL, '{}', '2022-12-13 08:19:33', 'TODO', 'task', NULL, '778222e0', 'todo', '6ee78fbc');
INSERT INTO pmc_work_item (id, order_num, root_id, title, description, parent_id, tree_path, project_id, work_type_id, work_priority_id, work_status_id, module_id, sprint_id, version_id, pre_depend_id, builder_id, assigner_id, reporter_id, plan_begin_time, plan_end_time, actual_begin_time, actual_end_time, percent, plan_takeup_time, ext_data, build_time, work_status_code, work_type_code, surplus_time, work_type_sys_id, work_status_node_id, each_type) VALUES ('matflow-4', 4, 'matflow-4', '流水线启动失败', '[{"type":"paragraph","children":[{"text":""}]}]', NULL, NULL, 'f3fdc893ff25', 'dcbf7211', NULL, 'eedd26da', NULL, NULL, NULL, NULL, '111111', '111111', NULL, '2022-12-13 00:00:00', '2022-12-13 00:00:00', NULL, NULL, NULL, NULL, '{}', '2022-12-13 08:19:54', 'TODO', 'defect', NULL, '98121701', 'todo', '8da39b77');

--CREATE INDEX title_index ON pmc_work_item USING gin (title GIN_TRGM_OPS);


INSERT INTO pmc_sprint (id, sprint_name, description, project_id, master, sprint_state_id, start_time, end_time) VALUES ('0410da30425b', '迭代1', NULL, '4cf6d4dec7d0', '111111', '000000', '2022-12-13 00:00:00', '2023-01-31 00:00:00');
INSERT INTO pmc_sprint (id, sprint_name, description, project_id, master, sprint_state_id, start_time, end_time) VALUES ('caa684dfa094', '迭代2', NULL, '4cf6d4dec7d0', '111111', '000000', '2022-12-13 00:00:00', '2023-01-31 00:00:00');

INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time) VALUES ('2f39cc971151', '接口关联软件开发', 'project', '4cf6d4dec7d0', '111111', '4cf6d4dec7d0', '2022-12-13 12:11:23');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time) VALUES ('55094b6bd7d6', '接受数据失败', 'workItem', 'apis-4', '111111', '4cf6d4dec7d0', '2022-12-13 12:16:49');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time) VALUES ('7d38780e3e67', '流水线项目', 'project', 'f3fdc893ff25', '111111', 'f3fdc893ff25', '2022-12-13 12:18:45');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time) VALUES ('8f67fade691e', '接口设计', 'workItem', 'apis-1', '111111', '4cf6d4dec7d0', '2022-12-13 12:16:03');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time) VALUES ('927c00b24d48', '开发流水线项目', 'workItem', 'matflow-3', '111111', 'f3fdc893ff25', '2022-12-13 12:19:57');
INSERT INTO pmc_recent (id, name, model, model_id, master_id, project_id, recent_time) VALUES ('e281dc36d76e', '流水线启动失败', 'workItem', 'matflow-4', '111111', 'f3fdc893ff25', '2022-12-13 12:19:56');


INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('a64e8050', '4cf6d4dec7d0', '98121701', 'c323f12b', '22751ea5');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('b81aef3b', '4cf6d4dec7d0', '7055ebc6', 'cd46de1f', '22751ea5');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('c9e59337', 'f3fdc893ff25', '778222e0', '9c338860', '024227ab');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('d3ef6551', '4cf6d4dec7d0', '778222e0', '12267b6d', '22751ea5');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('dcbf7211', 'f3fdc893ff25', '98121701', 'c430e1ad', '024227ab');
INSERT INTO pmc_work_type_dm (id, project_id, work_type_id, form_id, flow_id) VALUES ('ef23b25f', 'f3fdc893ff25', '7055ebc6', '0e425461', '024227ab');


INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope) VALUES ('024227ab', '事项流程', '事项流程', 'custom', 0, 2);
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope) VALUES ('22751ea5', '事项流程', '事项流程', 'custom', 0, 2);
INSERT INTO pcs_flc_flow (id, name, description, grouper, defaults, scope) VALUES ('4d040c6d', '事项流程', '事项流程', 'custom', NULL, 1);

INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('0b53c698', 'done', '22751ea5', 690, 300, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('0e1eccfe', 'todo', '4d040c6d', 250, 300, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('72697a07', 'start', '024227ab', 100, 290, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('73cb56f4', '7db3a0d1', '4d040c6d', 430, 300, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('78258edf', 'start', '4d040c6d', 100, 290, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('a0f286c2', 'todo', '22751ea5', 250, 300, 40, 100, 2, 'TODO');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('ad2d4449', '7db3a0d1', '22751ea5', 430, 300, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('b4c86536', 'start', '22751ea5', 100, 290, 60, 60, 1, 'START');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('cd764abf', '7db3a0d1', '024227ab', 430, 300, 40, 180, 2, 'PROGRESS');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('d5cdecdd', 'done', '4d040c6d', 690, 300, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('eecad17a', 'done', '024227ab', 690, 300, 40, 100, 2, 'DONE');
INSERT INTO pcs_flc_state_node_flow (id, node_id, flow_id, X, Y, height, width, type, node_status) VALUES ('eedd26da', 'todo', '024227ab', 250, 300, 40, 100, 2, 'TODO');

INSERT INTO pcs_flc_state_node (id, name) VALUES ('7db3a0d1', '进行中');

INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('19dc5e5e', '开始', '22751ea5', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('1e76c11d', '创建', '4d040c6d', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('36b0afc7', '开始', '4d040c6d', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('64dbd6fa', '创建', '024227ab', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('755a166b', '创建', '22751ea5', 'start', 'todo', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('8a464239', '完成', '4d040c6d', '7db3a0d1', 'done', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('aa7bc2e6', '完成', '024227ab', '7db3a0d1', 'done', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('e15c8797', '开始', '024227ab', 'todo', '7db3a0d1', 'right1', 'left1');
INSERT INTO pcs_flc_transition (id, name, flow_id, from_node_id, to_node_id, from_port_id, to_port_id) VALUES ('fef23ab8', '完成', '22751ea5', '7db3a0d1', 'done', 'right1', 'left1');

INSERT INTO pcs_flc_dm_flow (id, domain_id, flow_id, global_flow_id) VALUES ('9bcc1be0', '4cf6d4dec7d0', '22751ea5', '4d040c6d');
INSERT INTO pcs_flc_dm_flow (id, domain_id, flow_id, global_flow_id) VALUES ('b422fc1b', 'f3fdc893ff25', '024227ab', '4d040c6d');
