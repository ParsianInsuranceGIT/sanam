update tbl_credebit cu set cu.vosoul_state = 'TAEED_VOSOUL', cu.vosoul_date = 
(
select max(ce.vosoul_date) as evd from tbl_khate_sanad ks 
join tbl_credebit cb on cb.id = ks.bedehi_credebit_id
join tbl_credebit ce on ce.id = ks.etebar_credebit_id
where 
ce.vosoul_state = 'TAEED_VOSOUL'
and cb.vosoul_state = 'TAEED_NASHODE'
and cb.id = cu.id
group by cb.id
)
where cu.id in 
(
select cb.id from tbl_khate_sanad ks 
join tbl_credebit cb on cb.id = ks.bedehi_credebit_id
join tbl_credebit ce on ce.id = ks.etebar_credebit_id
where 
ce.vosoul_state = 'TAEED_VOSOUL'
and cb.vosoul_state = 'TAEED_NASHODE'
);



237969 rows updated