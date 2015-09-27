delete from tbl_elhaghiye e where e.elhaghiye_id =
(
  select e2.elhaghiye_id from tbl_darkhast d join tbl_darkhast_taghir dt on dt.dartaghir_id = d.darkhast_taghirat
  join tbl_elhaghiye e2 on d.id = e2.darkhast_id
  and dt.dartaghir_id = 38124654
);

delete from tbl_darkhast d where d.id =
(
  select d2.id from tbl_darkhast d2 join tbl_darkhast_taghir dt on dt.dartaghir_id = d2.darkhast_taghirat  
  and dt.dartaghir_id = 38124654
);

delete from tbl_transition_log tl where tl.transition_log_id in
(
  select tl2.transition_log_id from tbl_transition_log tl2 join tbl_darkhast_taghir dt on dt.dartaghir_id = tl2.darkhasttaghirat_id  
  and dt.dartaghir_id = 38124654
);

delete from tbl_darkhast_taghir dt where dt.dartaghir_id = 38124654;