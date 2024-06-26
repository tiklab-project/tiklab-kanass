CREATE TABLE pmc_work_role_function(
        id VARCHAR(12) PRIMARY KEY,
        role_id VARCHAR(12) NOT NULL,
        role_type VARCHAR(64) NOT NULL,
        privilege_id VARCHAR(12) NOT NULL,
        function_id VARCHAR(8) NOT NULL,
        function_type VARCHAR(64) NOT NULL,
        type VARCHAR(12) NOT NULL
);

CREATE TABLE pmc_work_function(
        id VARCHAR(8) PRIMARY KEY,
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

CREATE TABLE pmc_work_privilege(
        id VARCHAR(12) PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
        grouper VARCHAR(64) NOT NULL,
        scope VARCHAR(64) NOT NULL,
        work_type_id VARCHAR(12) NOT NULL
);

ALTER TABLE pmc_work_type ADD COLUMN privilege_id VARCHAR(12);
ALTER TABLE pmc_work_type_dm ADD COLUMN privilege_id VARCHAR(12);


INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('14d28df53715', '2', 'role', 'b588f4c3', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('06f6a017794a', '2', 'role', '9ceb88f6', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('45fd1589118d', '2', 'role', 'da027819', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('3481c81b83d3', '2', 'role', '6fb48dae', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('7a1217ecca75', '2', 'role', 'b97418c8', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('43cce6f29ab4', '2', 'role', '44d52196', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('715aec0fb619', '2', 'role', '3b7f0517', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('65e7cfd8d5d6', '2', 'role', '3f6d9de2', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e26e2fa2de4b', '2', 'role', '439c0a4e', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e6a13a549970', '2', 'role', 'd11e8ce7', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('8251c51d1ec0', '2', 'role', '416a168e', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('bbef09a91435', '2', 'role', '4b18b18a', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('196bafe2c83d', '2', 'role', '0f9ff2ce', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('1d2dea89c728', '2', 'role', 'bec9e2d4', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f4b6282a25d7', '2', 'role', '622b36bb', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('d280b04041ca', '2', 'role', 'ca554bdd', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('fe729d7e0f00', '2', 'role', '3b3ac1bf', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('1431353e6b53', '2', 'role', 'f828fa2b', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('ac48182b98b0', '2', 'role', '691deab6', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('d26a1ac94c6e', '2', 'role', 'bd073750', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('a7efce4d75b4', '2', 'role', 'e8787ab9', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('90e76a7d1be0', '2', 'role', '187d7a58', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e9e717551dfa', '2', 'role', 'a34d7fc3', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('8908359371a6', '2', 'role', '5f08e5f0', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('b20449bfdd7d', '2', 'role', '53e66a2d', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('0b1fbd20aed8', '2', 'role', '94521eae', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('7c9b240340c1', '2', 'role', '46670c59', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e2f304e8407a', '2', 'role', 'ac3bfb6f', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('d1db992d1c73', '2', 'role', 'c31c705e', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('ce8092434dfa', '4559d54bc8b7', 'role', 'c31c705e', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('0e2e714ba801', '4559d54bc8b7', 'role', '9ceb88f6', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('3169882bb774', '4559d54bc8b7', 'role', '691deab6', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('291cfa74197d', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'ac3bfb6f', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('bbe190a40138', 'WORK_ITEM_ASSIGNER', 'virtualRole', '691deab6', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('548e5c1a1020', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'f828fa2b', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('428a3082bb04', 'WORK_ITEM_ASSIGNER', 'virtualRole', '53e66a2d', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('74c6ae31867e', 'WORK_ITEM_ASSIGNER', 'virtualRole', '94521eae', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('13732a3feb9d', 'WORK_ITEM_ASSIGNER', 'virtualRole', '46670c59', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('25147b077ebf', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'ac3bfb6f', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('839a4fb038a3', 'WORK_ITEM_ASSIGNER', 'virtualRole', '53e66a2d', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('cfe68d6e5933', 'WORK_ITEM_ASSIGNER', 'virtualRole', '94521eae', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('cd9fef3802a6', 'WORK_ITEM_ASSIGNER', 'virtualRole', '53e66a2d', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f61951090126', 'WORK_ITEM_ASSIGNER', 'virtualRole', '94521eae', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('a77344a6927b', 'WORK_ITEM_ASSIGNER', 'virtualRole', '46670c59', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('33e20b539ab4', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'ac3bfb6f', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f1f46beda59e', 'WORK_ITEM_ASSIGNER', 'virtualRole', '691deab6', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f9aefbfd5a9a', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'b588f4c3', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('1caec3f4248b', 'WORK_ITEM_ASSIGNER', 'virtualRole', '187d7a58', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('a8ad4d59ea36', 'WORK_ITEM_ASSIGNER', 'virtualRole', '691deab6', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('565a074f04ca', 'WORK_ITEM_ASSIGNER', 'virtualRole', '46670c59', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('673513c8dfe0', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'b2565437', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('c74675bb6a29', 'WORK_ITEM_ASSIGNER', 'virtualRole', 'f828fa2b', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('2dfeaeea0910', 'WORK_ITEM_CREATOR', 'virtualRole', '691deab6', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('9de915a3b44a', 'WORK_ITEM_CREATOR', 'virtualRole', 'bd073750', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('2e700b21c9b1', 'WORK_ITEM_CREATOR', 'virtualRole', 'e8787ab9', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('9a2b74f55399', 'WORK_ITEM_CREATOR', 'virtualRole', '187d7a58', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('c37d437d494a', 'WORK_ITEM_CREATOR', 'virtualRole', '0763e387', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('25c06175ec4b', 'WORK_ITEM_CREATOR', 'virtualRole', '42fffc89', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e32d784d6c42', 'WORK_ITEM_CREATOR', 'virtualRole', 'b2565437', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('7a0d11b9b174', 'WORK_ITEM_CREATOR', 'virtualRole', 'c31c705e', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('5322e7217d9e', 'WORK_ITEM_CREATOR', 'virtualRole', '9ceb88f6', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('378acce0e548', 'WORK_ITEM_CREATOR', 'virtualRole', '53e66a2d', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('6d86950ef81b', 'WORK_ITEM_CREATOR', 'virtualRole', '94521eae', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('237a1ceac92c', 'WORK_ITEM_CREATOR', 'virtualRole', '46670c59', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('b29983cb0bcd', 'WORK_ITEM_CREATOR', 'virtualRole', 'ac3bfb6f', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('94b5f7071039', 'WORK_ITEM_CREATOR', 'virtualRole', 'da027819', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f85614c01e4b', 'WORK_ITEM_CREATOR', 'virtualRole', '6fb48dae', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('81856c13f4f9', 'WORK_ITEM_CREATOR', 'virtualRole', 'b97418c8', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('054e632211d0', 'WORK_ITEM_CREATOR', 'virtualRole', '44d52196', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('1f0af4bf353b', 'WORK_ITEM_CREATOR', 'virtualRole', '3b7f0517', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f67124cdb865', 'WORK_ITEM_CREATOR', 'virtualRole', '3f6d9de2', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('56ed83a6e574', 'WORK_ITEM_CREATOR', 'virtualRole', 'd11e8ce7', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('7a45fe1251a3', 'WORK_ITEM_CREATOR', 'virtualRole', '416a168e', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('86e89755bc2f', 'WORK_ITEM_CREATOR', 'virtualRole', '4b18b18a', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('2eccf67344d2', 'WORK_ITEM_CREATOR', 'virtualRole', '0f9ff2ce', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('375a26dda067', 'WORK_ITEM_CREATOR', 'virtualRole', 'bec9e2d4', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('9737bc1398f1', 'WORK_ITEM_CREATOR', 'virtualRole', '622b36bb', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('07c987ace85e', 'WORK_ITEM_CREATOR', 'virtualRole', 'ca554bdd', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e7eaf3eefde9', 'WORK_ITEM_CREATOR', 'virtualRole', '3b3ac1bf', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('a41427e0cf07', 'WORK_ITEM_CREATOR', 'virtualRole', 'f828fa2b', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('16647e52bc80', 'WORK_ITEM_CREATOR', 'virtualRole', '691deab6', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('8fdd87110f00', 'WORK_ITEM_CREATOR', 'virtualRole', 'e8787ab9', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('d3ba5d5061d4', 'WORK_ITEM_CREATOR', 'virtualRole', 'bd073750', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('a6bf4632b631', 'WORK_ITEM_CREATOR', 'virtualRole', '187d7a58', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('417a41f4c489', 'WORK_ITEM_CREATOR', 'virtualRole', 'c4579b11', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e94917c78227', 'WORK_ITEM_CREATOR', 'virtualRole', 'b588f4c3', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('90d351e33dec', 'WORK_ITEM_CREATOR', 'virtualRole', 'c5401366', 'system', '3fe57528d42d', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('c7f2be489cf4', 'WORK_ITEM_CREATOR', 'virtualRole', '439c0a4e', 'system', '3fe57528d42d', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('48728b3c236a', 'WORK_ITEM_CREATOR', 'virtualRole', '53e66a2d', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('4e1a4b7293ff', 'WORK_ITEM_CREATOR', 'virtualRole', '94521eae', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('da10b6ed4c1d', 'WORK_ITEM_CREATOR', 'virtualRole', '46670c59', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('b13b1ad2b0a6', 'WORK_ITEM_CREATOR', 'virtualRole', 'ac3bfb6f', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('0ba989798ee1', 'WORK_ITEM_CREATOR', 'virtualRole', 'c31c705e', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('0671b1c23305', 'WORK_ITEM_CREATOR', 'virtualRole', '9ceb88f6', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('5bf0896b5ba2', 'WORK_ITEM_CREATOR', 'virtualRole', 'da027819', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('bbf1a727bac2', 'WORK_ITEM_CREATOR', 'virtualRole', '6fb48dae', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('10c88c4b7668', 'WORK_ITEM_CREATOR', 'virtualRole', 'b97418c8', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('07e8f150de27', 'WORK_ITEM_CREATOR', 'virtualRole', '44d52196', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('9e9d0bccafa3', 'WORK_ITEM_CREATOR', 'virtualRole', '3b7f0517', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('dd3f0ec865cb', 'WORK_ITEM_CREATOR', 'virtualRole', '3f6d9de2', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('34873a7e1f54', 'WORK_ITEM_CREATOR', 'virtualRole', '439c0a4e', 'system', '5e1a8e32b3d0', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('f92bf9decfff', 'WORK_ITEM_CREATOR', 'virtualRole', 'd11e8ce7', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('cb9f03960afe', 'WORK_ITEM_CREATOR', 'virtualRole', '416a168e', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('8409c1c4398e', 'WORK_ITEM_CREATOR', 'virtualRole', '4b18b18a', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('12cd0d253ded', 'WORK_ITEM_CREATOR', 'virtualRole', '0f9ff2ce', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('2ba7895d6d7d', 'WORK_ITEM_CREATOR', 'virtualRole', 'bec9e2d4', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('ab5846564150', 'WORK_ITEM_CREATOR', 'virtualRole', '622b36bb', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('8dc3e6c3051d', 'WORK_ITEM_CREATOR', 'virtualRole', 'ca554bdd', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('4dfa95e3586c', 'WORK_ITEM_CREATOR', 'virtualRole', '3b3ac1bf', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('9c5fdab3fb81', 'WORK_ITEM_CREATOR', 'virtualRole', 'f828fa2b', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('6a9aa6459935', 'WORK_ITEM_CREATOR', 'virtualRole', '691deab6', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('2abcee7c05ba', 'WORK_ITEM_CREATOR', 'virtualRole', 'bd073750', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('c88fb96084aa', 'WORK_ITEM_CREATOR', 'virtualRole', 'e8787ab9', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('4ad3ef90b1c8', 'WORK_ITEM_CREATOR', 'virtualRole', '187d7a58', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('e636db3dd1b1', 'WORK_ITEM_CREATOR', 'virtualRole', 'a34d7fc3', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('220914cc1fd6', 'WORK_ITEM_CREATOR', 'virtualRole', '5f08e5f0', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('1aa92a0a745a', 'WORK_ITEM_CREATOR', 'virtualRole', 'b588f4c3', 'system', '5e1a8e32b3d0', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('71a980615b70', 'WORK_ITEM_CREATOR', 'virtualRole', '53e66a2d', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('6906381ebca2', 'WORK_ITEM_CREATOR', 'virtualRole', '94521eae', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('b3069027f2e0', 'WORK_ITEM_CREATOR', 'virtualRole', '46670c59', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('234e47a0072d', 'WORK_ITEM_CREATOR', 'virtualRole', 'ac3bfb6f', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('3f53e7ab4744', 'WORK_ITEM_CREATOR', 'virtualRole', 'c31c705e', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('efb7affd74ae', 'WORK_ITEM_CREATOR', 'virtualRole', '9ceb88f6', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('55f30e9d8769', 'WORK_ITEM_CREATOR', 'virtualRole', 'da027819', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('50ea63cbaec3', 'WORK_ITEM_CREATOR', 'virtualRole', '6fb48dae', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('00700aa9b06a', 'WORK_ITEM_CREATOR', 'virtualRole', 'b97418c8', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('a52db366ef85', 'WORK_ITEM_CREATOR', 'virtualRole', '44d52196', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('eecec1f85c3e', 'WORK_ITEM_CREATOR', 'virtualRole', '3b7f0517', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('66e6b20f1c74', 'WORK_ITEM_CREATOR', 'virtualRole', '3f6d9de2', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('bd840c0e0b48', 'WORK_ITEM_CREATOR', 'virtualRole', '439c0a4e', 'system', '63bbe5a38a46', 'function');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('dbca0be92eac', 'WORK_ITEM_CREATOR', 'virtualRole', 'd11e8ce7', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('fd1915e76b28', 'WORK_ITEM_CREATOR', 'virtualRole', '416a168e', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('1e39835b7374', 'WORK_ITEM_CREATOR', 'virtualRole', '4b18b18a', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('89d3430c1047', 'WORK_ITEM_CREATOR', 'virtualRole', '0f9ff2ce', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('3ec1a4f19f4d', 'WORK_ITEM_CREATOR', 'virtualRole', 'bec9e2d4', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('cc2f5d53375d', 'WORK_ITEM_CREATOR', 'virtualRole', '622b36bb', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('b32d3c6acf36', 'WORK_ITEM_CREATOR', 'virtualRole', 'ca554bdd', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('0a5fbbaad1d6', 'WORK_ITEM_CREATOR', 'virtualRole', '3b3ac1bf', 'system', '63bbe5a38a46', 'field');
INSERT INTO pmc_work_role_function (id, role_id, role_type, function_id, type, privilege_id, function_type) VALUES ('951a516e8338', 'WORK_ITEM_CREATOR', 'virtualRole', 'f828fa2b', 'system', '63bbe5a38a46', 'field');