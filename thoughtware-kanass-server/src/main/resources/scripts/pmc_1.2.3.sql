UPDATE pcs_foc_field SET grouper = 'syscustom' WHERE id = '187d7a58';
UPDATE pcs_foc_field SET grouper = 'syscustom' WHERE code in ('bugType', 'taskType' , 'demandType');

INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('56035266', '高', '187d7a58', 'high', 3);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('04b440ad', '低', '187d7a58', 'low', 1);
INSERT INTO pcs_foc_select_item (id, name, field_id, value, sort) VALUES ('faaecb3d', '中', '187d7a58', 'medium', 2);


INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('bf5a3c4e', '0c5d4ff9', 'c4579b11', 0, 18);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('5fb54a50', '515f17bd', 'a34d7fc3', 0, 16);
INSERT INTO pcs_foc_form_field (id, form_id, field_id, required, sort) VALUES ('d661beec', '607f6be6', '0763e387', 0, 16);



INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b67ca7e80fd3', '111111', '63fd58715558');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ca57e43dc9a6', '111111', '2f2548b9de7b');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('49d14805f011', '111111', '77da6376511e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('35ee58756a2b', '111111', '4cdb5348a9ca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d909ab540994', '111111', '0c73e628fd54');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6c91c49f202a', '111111', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('66a7b880a7cd', '111111', 'f569dfa96880');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('cb85094c0fe9', '111111', '90b4e472163a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f08e95feb597', '111111', 'fec1a8ee3f6c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('522fd5372a7f', '111111', '321751dfd3a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3224f95852f0', '111111', '7ff4702ebe64');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0735dd9a8af7', '111111', '1f777ba063f7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('e95f04c04a62', '111111', 'c5af706628c2');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a030da80ecb9', '111111', '49e12c2b8fca');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5bfe4d81850f', '111111', '48717b977129');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f54580bcd692', '111111', '5d5b64c3d172');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('7692874267eb', '111111', 'f9e27dd6f76e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('6d1d0c9d35d0', '111111', '47fb980c2919');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('aeaec72e0d73', '111111', 'a11ca9e4559e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b1838fb9f397', '111111', '746c3becb86f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('ceb8f0dc3c76', '111111', '138654cdc36c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('3ccd2f378bed', '111111', '290accfb27c3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0419a7f4eba4', '111111', '8d33e4945151');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('394b6b9d8d5c', '111111', '7c8652d3e2f0');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c325b87a00da', '111111', '519fc38f6390');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('dac346a16d29', '111111', '063143949cc7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5d4fa3c8f76d', '111111', 'de21752872e9');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a7e83c2b5b6c', '111111', '025e702fc3c4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('51af28f69a9d', '111111', '64bdf62686a4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d70be9f57202', '111111', 'cb6c8c3f4048');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b81416bb41b1', '111111', '4cc4e67319a0');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('56b9f90a2bc4', '111111', 'e5b34be19fab');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b59c9401f8b8', '111111', '4235d2624bdf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('08a15ef655bd', '111111', 'hf43e412151e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a7d3ecbca3f5', '111111', 'oug5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d13f375ffd80', '111111', 'hfg5371be8ec');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('569f891e8010', '111111', '43e7d41decf7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('803f2ab9cea9', '111111', 'wqre9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0109830fb40b', '111111', '585d26bcbdf3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('55c88dbd43bb', '111111', '890e7d41decf');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c5ed6919d310', '111111', '447d9998fc00');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('f696a795dac3', '111111', '925371be8ec6');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('252709b11337', '111111', '043e412151db');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('d53bb53e4070', '111111', '6b61fbe5091a');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('deb5f94291c5', '111111', '325c2503007f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('58409b12b54d', '111111', '9c99b8a096c8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c3053072f172', '111111', 'cb954a7c0be3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('89811f7afdbe', '111111', 'e8bf9843bc9d');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('33f1219173d9', '111111', '5fb7863b09a8');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0bfb55283e28', '111111', '428be660dea3');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('31ffeb47ee87', '111111', '57a3bcd1e5fe');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4b2d8576f523', '111111', 'dd81bdbb52bc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5f42c66d8226', '111111', '9633d9475886');

INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0e9f53a26249', 'pro_111111', 'b80a4416f42f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2139a69cf108', 'pro_111111', '4b982e213c12');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c1d548647274', 'pro_111111', 'fdb16c391ffc');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5b6359199097', 'pro_111111', 'ce9b25e6cac7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('2fd403fb570f', 'pro_111111', 'd7e0583f53e7');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9e0e5fc344e1', 'pro_111111', 'dae13d309463');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8d7d9f9ac17d', 'pro_111111', '9538263abda4');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('a60714124735', 'pro_111111', 'df306d70374c');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('0d92e2116681', 'pro_111111', '66bd7120b0de');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('956207fef4f9', 'pro_111111', '72e68d34870e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('5b7fc1a30034', 'pro_111111', 'a3ee33eba363');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('c4f1c473044c', 'pro_111111', 'd893af6f93ff');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b17d8f414cbb', 'pro_111111', '36bb7d9c537e');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('938d537cc3de', 'pro_111111', 'a13114663695');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('8e12005e95a6', 'pro_111111', '05edc354e9ce');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('dee112b49bae', 'pro_111111', '1843bd5f5a73');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('277e58687e63', 'pro_111111', '40d00b88b805');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('64ea5404f0a2', 'pro_111111', '2a6be8fda76f');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('b52cef768cc3', 'pro_111111', 'abe6fffaae63');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('9dd6cf1979b9', 'pro_111111', 'da52f90b3707');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('596b54c858ba', 'pro_111111', 'd3447a36dd98');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1480c62ad6bd', 'pro_111111', '1ff199504908');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('779d96eb7d94', 'pro_111111', '5386b5add6a5');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('4750ee2ebd63', 'pro_111111', '09a64b79b631');
INSERT INTO pcs_prc_role_function (id, role_id, function_id) VALUES ('1f8499947b5a', 'pro_111111', 'bfbb4a6b97a4');


