update tbl_karmozd_ghest kg
set kg.type_karmozd = 'CODE_MOVAGHAT'                                            
where 
      kg.type_karmozd='ADI' 
      and (
            select p.options from tbl_ghest g 
                             inner join tbl_ghestbandi gb on gb.id = g.ghestbandi_id
                             inner join tbl_bimename b on b.bimename_id = gb.bimename_id
                             inner join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
            where g.id=kg.ghest_id and p.c_valid=1 
          ) = 'CODE_MOVAGHAT' ;
          
--------------------------------------------------------------------------------
update tbl_karmozd_ghest kg
set kg.type_karmozd = 'CODE_MOVAGHAT'                                            
where 
      kg.type_karmozd='ADI'
      and (
            select p.namayande_poshtiban_id from tbl_ghest g 
                                            inner join tbl_ghestbandi gb on gb.id = g.ghestbandi_id
                                            inner join tbl_bimename b on b.bimename_id = gb.bimename_id
                                            inner join tbl_elhaghiye e on e.bimename_id=b.bimename_id
                                            inner join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
            where g.id=kg.ghest_id and p.c_valid=1 and e.tarikh_created > '1392/05/15' and rownum<=1
          ) is not null ;
--------------------------------------------------------------------------------          
 update tbl_karmozd_ghest kg
set kg.type_karmozd = 'CODE_MOVAGHAT'                                            
where 
      kg.type_karmozd is null
      and (
            select p.options from tbl_ghest g 
                             inner join tbl_ghestbandi gb on gb.id = g.ghestbandi_id
                             inner join tbl_bimename b on b.bimename_id = gb.bimename_id
                             inner join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
            where g.id=kg.ghest_id and p.c_valid=1 
          ) = 'CODE_MOVAGHAT' ;
--------------------------------------------------------------------------------
update tbl_karmozd_ghest kg
set kg.type_karmozd = 'CODE_MOVAGHAT'                                            
where 
      kg.type_karmozd is null
      and (
            select p.namayande_poshtiban_id from tbl_ghest g 
                                            inner join tbl_ghestbandi gb on gb.id = g.ghestbandi_id
                                            inner join tbl_bimename b on b.bimename_id = gb.bimename_id
                                            inner join tbl_elhaghiye e on e.bimename_id=b.bimename_id
                                            inner join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
            where g.id=kg.ghest_id and p.c_valid=1 and e.tarikh_created > '1392/05/15'
          ) is not null ;          
--------------------------------------------------------------------------------
update tbl_karmozd_ghest kg
set kg.type_karmozd = 'ADI'                                            
where 
      kg.type_karmozd is null;
	  
--------------------------------------------------------------------------------	  
update tbl_karmozd_namayande
set credebit_id = null;

update tbl_karmozd_namayande
set state='ELAM_BE_MALI_NASHODE';

delete tbl_credebit cr
where cr.credebit_type='PARDAKHT_KARMOZD';

update tbl_ghest
set karmozd_paid=null;

--------------------------------------------------------------------------------	  

--- pardakht 2proje