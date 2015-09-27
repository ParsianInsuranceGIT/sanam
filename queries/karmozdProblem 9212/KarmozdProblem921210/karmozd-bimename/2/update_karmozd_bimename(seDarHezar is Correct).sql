update tbl_bimename b1
set b1.bimename_karmozd=(select kb.sedarhezar from man_karmozd_bimenamee_92_12_08 kb where kb.bimename_id=b1.bimename_id)
where exists(select * from man_karmozd_bimenamee_92_12_08 kb1 where kb1.bimename_id=b1.bimename_id);