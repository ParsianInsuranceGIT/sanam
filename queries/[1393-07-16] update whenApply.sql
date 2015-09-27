update tbl_darkhast_taghir dt_o set dt_o.whenapply =
(
select get_bimeyear(e.tarikh_asar, b.bimename_tarikhshorou)
from tbl_elhaghiye e join tbl_bimename b on e.bimename_id = b.bimename_id join tbl_darkhast d on e.darkhast_id = d.id
join tbl_darkhast_taghir dt on d.darkhast_taghirat = dt.dartaghir_id
where e.state_id = 3001 and e.elhaghiye_type='TAGHYIRAT' and e.tarikh_asar is not null
and get_bimeyear(e.tarikh_asar, b.bimename_tarikhshorou) >= 0
and get_bimeyear(e.tarikh_asar, b.bimename_tarikhshorou) != dt.whenapply
and dt.dartaghir_id = dt_o.dartaghir_id
)
where exists
(
select get_bimeyear(e.tarikh_asar, b.bimename_tarikhshorou)
from tbl_elhaghiye e join tbl_bimename b on e.bimename_id = b.bimename_id join tbl_darkhast d on e.darkhast_id = d.id
join tbl_darkhast_taghir dt on d.darkhast_taghirat = dt.dartaghir_id
where e.state_id = 3001 and e.elhaghiye_type='TAGHYIRAT' and e.tarikh_asar is not null
and get_bimeyear(e.tarikh_asar, b.bimename_tarikhshorou)  >= 0
and get_bimeyear(e.tarikh_asar, b.bimename_tarikhshorou) != dt.whenapply
and dt.dartaghir_id = dt_o.dartaghir_id
);