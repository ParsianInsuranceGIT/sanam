------------------------Elhaghie_VahedSodor_Update------------------------------
update tbl_elhaghiye el
set el.vahedsodor_id=(
                        select n.id 
                        from tbl_namayande n 
                        join tbl_users u on u.vahed_sodoor_id=n.id
                        where u.user_id=el.user_sader_konande_id
                     )
where el.elhaghiye_type is not null and el.user_sader_konande_id is not null; 

------------------------Bimename_VahedSodor_Update------------------------------
update tbl_bimename b 
set b.vahedSodor_id=(
                      select n.id 
                      from tbl_namayande n where n.kodenamayandekargozar=b.bimename_vahedsodor
                    )
where b.bimename_vahedsodor is not null;                    


