select es.nahve_pardakht_hagh_bime, b.bimename_id,b.bimename_shomare, b.bimename_karmozd,gb.majmoo_ezafi,gb.majmoo_ezafi_test,sum(gh.hagh_bime_ezafi_long),
(((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75) doroste
,b.bimename_karmozd - (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75) ekhtelaf
from tbl_bimename b
join tbl_ghestbandi gb on b.bimename_id = gb.bimename_id
join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
join tbl_estelam es on es.estelam_id = p.estelam_id
join tbl_ghest gh on gh.ghestbandi_id = gb.id
where gb.salebimei=0 and p.c_valid=1 and b.bimename_ready='yes' and b.bimename_tarikhsodur>='1392/07/14'  and
      (nvl(es.sarmaye_paye_fot_long,0) * 0.03)>
      (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75) 
      and trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75))<>b.bimename_karmozd
      
      group by es.nahve_pardakht_hagh_bime, b.bimename_id, b.bimename_shomare, b.bimename_karmozd, gb.majmoo_ezafi, gb.majmoo_ezafi_test, (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75), b.bimename_karmozd - (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75)

;

select distinct(b.bimename_id)
--,b.bimename_karmozd
,trunc(nvl(es.sarmaye_paye_fot_long,0) * 0.03) seHezarSarma
,trunc(((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75) haghBime
from tbl_bimename b
join tbl_ghestbandi gb on b.bimename_id = gb.bimename_id
join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
join tbl_estelam es on es.estelam_id = p.estelam_id
join tbl_ghest gh on gh.ghestbandi_id = gb.id
--join tbl_karmozd_ghest kg on kg.ghest_id = gh.id
where gb.salebimei=0 and p.c_valid=1 and b.bimename_ready='yes' and b.bimename_tarikhsodur>='1392/03/01'  and 
      
        (nvl(es.sarmaye_paye_fot_long,0) * 0.03)>
        (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75) 
        and 
        trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75))<>b.bimename_karmozd       
        and
        trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75))+1<>b.bimename_karmozd                         
        and        
        trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75))-1<>b.bimename_karmozd 
      ;

update tbl_bimename b
set b.bimename_karmozd= (
                          select 
                            CASE WHEN bk.haftad_panj_haghbime < bk.sehezar_sarma then bk.haftad_panj_haghbime
                            else bk.sehezar_sarma
                            end 
                          from tbl_bime_krmzd bk where bk.id_bime=b.bimename_id
                        )
where exists (select bk1.* from tbl_bime_krmzd bk1 where b.bimename_id=bk1.id_bime );

select count(g.id),gb.id,bk.id_bime,g.karmozd_real,gb.salebimei from tbl_bime_krmzd bk 
            join tbl_ghestbandi gb on gb.bimename_id=bk.id_bime 
            join tbl_ghest g on g.ghestbandi_id=gb.id
            join tbl_pishnehad p on p.bimename_bimename_id=bk.id_bime            
            where g.karmozd_real is not null and p.c_valid=1 and gb.salebimei='0' group by gb.id, bk.id_bime, g.karmozd_real, gb.salebimei;
            
select count(gb.id),gb.id,gb.salebimei from tbl_ghestbandi gb join tbl_ghest g on g.ghestbandi_id=gb.id
where exists(select * from tbl_bime_krmzd bk where bk.id_bime=gb.bimename_id) and g.karmozd_real is not null group by gb.id, gb.salebimei;
            

select gb.id,b.bimename_karmozd,trunc(b.bimename_karmozd*0.4),e.nahve_pardakht_hagh_bime from tbl_ghestbandi gb 
           join tbl_bime_krmzd bk on bk.id_bime=gb.bimename_id 
           join tbl_bimename b on b.bimename_id=gb.bimename_id
           join tbl_pishnehad p on p.bimename_bimename_id=bk.id_bime
           join tbl_estelam e on e.estelam_id=p.estelam_id
--           join tbl_elhaghiye elh on elh.bimename_id=bk.id_bime
where p.c_valid=1 and gb.salebimei='0' and e.nahve_pardakht_hagh_bime!='yekja'
--and elh.elhaghiye_type='TAGHYIRAT' and elh.state_id=3001
;

update tbl_ghestbandi gb 
set gb.karmozdyear=(select trunc(b.bimename_karmozd*0.15) from tbl_bimename b where b.bimename_id=gb.bimename_id)
where exists (select bk.* from tbl_bime_krmzd bk where bk.id_bime=gb.bimename_id) and gb.salebimei!='0'
;  

select g.id,g.karmozd_real,g.karmozd_paid,trunc((cr.amount_long*gb.karmozdyear)/gb.majmoo_amount) from tbl_ghest g 
         join tbl_credebit cr on cr.ghest_id=g.id 
         join tbl_ghestbandi gb on gb.id=g.ghestbandi_id 
where g.karmozd_real is not null and gb.karmozdyear is not null;

update tbl_ghest g 
set g.karmozd_real=(
                      select trunc((cr.amount_long*gb.karmozdyear)/gb.majmoo_amount) 
                      from tbl_ghest g1
                      join tbl_credebit cr on cr.ghest_id=g1.id 
                      join tbl_ghestbandi gb on gb.id=g1.ghestbandi_id      
                      where g1.id=g.id
                   )
 where g.karmozd_real is not null and exists(select gb1.id from tbl_ghestbandi gb1 where gb1.karmozdyear is not null and gb1.id=g.ghestbandi_id);
 
