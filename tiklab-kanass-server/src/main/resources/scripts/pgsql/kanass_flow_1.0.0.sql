UPDATE pcs_flc_transition_rule_config
SET config_value = REPLACE(config_value, 'WORK_ITEM_AUDITOR', 'WORK_ITEM_ASSIGNER')
WHERE config_value LIKE '%WORK_ITEM_AUDITOR%';