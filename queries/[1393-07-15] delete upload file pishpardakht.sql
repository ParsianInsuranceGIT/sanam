-- check beshe ke sanad nakhorde bashan
-- id ro az rooye yek dune nemoone mishe peyda kard

update tbl_credebit c_o set c_o.status = 'TO_DELETE', c_o.daryafte_fish_id = null where c_o.id in
(
select c.id from tbl_bankinfo bi join tbl_credebit c on bi.credebit_id = c.id
where bi.id = 40198850
)
;

delete from tbl_daryafte_fish df_o where df_o.bank_info_id in
(
select bi.main_id from tbl_bankinfo bi join tbl_credebit c on bi.credebit_id = c.id
where bi.id = 40198850
)
;

delete from tbl_bankinfo bi_o where bi_o.main_id in
(
select bi.main_id from tbl_bankinfo bi join tbl_credebit c on bi.credebit_id = c.id
where bi.id = 40198850
)
;

delete from tbl_credebit c where c.status = 'TO_DELETE';