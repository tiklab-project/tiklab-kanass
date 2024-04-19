INSERT INTO pcs_foc_form_icon (id, icon_name, icon_url, icon_type) VALUES ('59681749ba26', 'proivilege1.png', '/image/c9ea9a193b36bd29', 'selectItem');
INSERT INTO pcs_foc_form_icon (id, icon_name, icon_url, icon_type) VALUES ('88bd6687b141', 'proivilege2.png', '/image/c8bc16c0c2f5ee55', 'selectItem');
INSERT INTO pcs_foc_form_icon (id, icon_name, icon_url, icon_type) VALUES ('7cc6ce660b1a', 'proivilege3.png', '/image/ec27e7da9237df66', 'selectItem');
INSERT INTO pcs_foc_form_icon (id, icon_name, icon_url, icon_type) VALUES ('a6155e7ae962', 'proivilege4.png', '/image/5e3387601e32bfd4', 'selectItem');
INSERT INTO pcs_foc_form_icon (id, icon_name, icon_url, icon_type) VALUES ('8e42ec5117e5', 'proivilege5.png', '/image/e8de903aaa21e095', 'selectItem');

UPDATE pcs_foc_select_item SET name = '中', field_id = '187d7a58', value = 'medium', sort = 2, icon_url = '/images/proivilege3.png' WHERE id = 'faaecb3d';
UPDATE pcs_foc_select_item SET name = '低', field_id = '187d7a58', value = 'low', sort = 1, icon_url = '/images/proivilege1.png' WHERE id = '04b440ad';
UPDATE pcs_foc_select_item SET name = '高', field_id = '187d7a58', value = 'high', sort = 3, icon_url = '/images/proivilege5.png' WHERE id = '56035266';