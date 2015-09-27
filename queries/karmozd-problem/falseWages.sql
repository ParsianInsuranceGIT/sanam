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


select kg.*
from tbl_bimename b
join tbl_ghestbandi gb on b.bimename_id = gb.bimename_id
join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
join tbl_estelam es on es.estelam_id = p.estelam_id
join tbl_ghest gh on gh.ghestbandi_id = gb.id
join tbl_karmozd_ghest kg on kg.ghest_id = gh.id
where kg.karmozd_id in (7947039,8852098,9966129,10327461) and gb.salebimei=0 and p.c_valid=1 and b.bimename_ready='yes' and b.bimename_tarikhsodur>='1392/07/14'  and
      (nvl(es.sarmaye_paye_fot_long,0) * 0.03)>
      (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75) 
      and trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi_test,0)) + replace(nvl(es.mablagh_sepor_ebted_sal,0),',')) * 0.75))<>b.bimename_karmozd
            

;
