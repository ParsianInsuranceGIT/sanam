-------------------------------------Report-------------------------------------
--select count(kargst.karmozd_amount),sum(kargst.karmozd_amount),
--       krnama.namayande_id,krnama.amount ,
--       crdbt.amount_long as credebit_amount,krnama.id as karmozd_namayande_id, crdbt.id as credebit_id
--from tbl_karmozd_ghest kargst
--join tbl_ghest gst on kargst.ghest_id = gst.id
--join tbl_karmozd_namayande krnama on krnama.id=kargst.karmozd_namayande_id
--join tbl_credebit crdbt on crdbt.id = krnama.credebit_id
--join tbl_ghestBandi gstbndi on gst.ghestbandi_id = gstbndi.id
--join tbl_bimename b on gstbndi.bimename_id = b.bimename_id
--join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
--where p.options is null and p.namayande_poshtiban_id is null
--group by krnama.namayande_id, krnama.amount, crdbt.amount_long, krnama.id, crdbt.id --and p.namayande_id=515040 

-----------------------------------DELETE---------------------------------------
DELETE FROM tbl_karmozd_ghest;
DELETE FROM tbl_karmozd_namayande;
DELETE FROM tbl_credebit WHERE credebit_type='PARDAKHT_KARMOZD' or credebit_type='BARGASHT_KARMOZD';
--
update tbl_ghest
set karmozd_paid=null 
, karmozd_real=null;
DELETE FROM tbl_karmozd ;
update tbl_bimename set bimename_karmozd=null;