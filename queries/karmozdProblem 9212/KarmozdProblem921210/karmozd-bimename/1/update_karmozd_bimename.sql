update tbl_bimename b1
set b1.bimename_karmozd=(select kb.haftad5darsadbime from tbl_karmozd_bimename_92_12_08 kb where kb.bimename_id=b1.bimename_id)
where exists(select * from tbl_karmozd_bimename_92_12_08 kb1 where kb1.bimename_id=b1.bimename_id);