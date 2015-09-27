
update tbl_bimegozar bg
set bg.userkartabl_user_id = (
                                select b.userkartabl_user_id from tbl_pishnehad p
                                                             join tbl_bimename b on p.bimename_bimename_id=b.bimename_id                             
                                where b.userkartabl_user_id is not null and bg.id = p.bimegozar_id
                             );
---------------------------------------------------------------------------------------------------------------------
update tbl_bimegozar bg
set bg.userkartabl_user_id =(
                                select bg1.userkartabl_user_id from tbl_bimegozar bg1 
                                                               join tbl_shakhs s on bg1.shakhs_id=s.shakhs_id
                                where s.kode_melli_shenasayi = (
                                                                  select s1.kode_melli_shenasayi from tbl_bimegozar bg2 
                                                                                                 join tbl_shakhs s1 on bg2.shakhs_id=s1.shakhs_id
                                                                  where bg2.id=bg.id                                                                                                 
                                                                                                 
                                                               )
                                and bg1.userkartabl_user_id is not null  and rownum<=1                          
						    ) 
where bg.userkartabl_user_id is null		;					
							 