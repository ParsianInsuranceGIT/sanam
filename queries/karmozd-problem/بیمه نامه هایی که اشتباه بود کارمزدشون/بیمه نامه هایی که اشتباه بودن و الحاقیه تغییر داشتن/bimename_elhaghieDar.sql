select b.* from tbl_ghestbandi gb 
           join tbl_bime_krmzd bk on bk.id_bime=gb.bimename_id 
           join tbl_bimename b on b.bimename_id=gb.bimename_id
           join tbl_pishnehad p on p.bimename_bimename_id=bk.id_bime
           join tbl_estelam e on e.estelam_id=p.estelam_id
           join tbl_elhaghiye elh on elh.bimename_id=bk.id_bime
where p.c_valid=1 and gb.salebimei='0' and e.nahve_pardakht_hagh_bime!='yekja'
and elh.elhaghiye_type='TAGHYIRAT' and elh.state_id=3001
;
