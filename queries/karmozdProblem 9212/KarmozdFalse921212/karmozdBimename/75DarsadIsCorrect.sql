select b.bimename_id,p.id pishnehadId,b.bimename_tarikhsodur,(nvl(es.sarmaye_paye_fot_long,0) * 0.03) seDarHezar ,
b.bimename_karmozd, trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0))) * 0.75)) Haftad5DarsadBime 



from tbl_bimename b

join tbl_ghestbandi gb on b.bimename_id = gb.bimename_id

join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id

join tbl_estelam es on es.estelam_id = p.estelam_id

join tbl_ghest gh on gh.ghestbandi_id = gb.id

--left join tbl_darkhast_taghir dt on dt.newpish_id=p.id



where b.bimename_tarikhsodur>='1392/03/01' and gb.salebimei=0 and p.c_valid=1 and b.bimename_ready='yes'

--and (dt.state_id!=9200 and dt.state_id!=9190 and dt.state_id!=9180) or dt.state_id is null

and 

    (nvl(es.sarmaye_paye_fot_long,0) * 0.03)

      >

    (((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0))) * 0.75) 

    and trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) ) * 0.75))<>b.bimename_karmozd

    and trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) ) * 0.75))+1<>b.bimename_karmozd

    and trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0)) ) * 0.75))-1<>b.bimename_karmozd
    
    and b.state_id<=510

group by b.bimename_id,p.id,b.bimename_tarikhsodur,(nvl(es.sarmaye_paye_fot_long,0) * 0.03) ,b.bimename_karmozd, trunc((((nvl(gb.majmoo_amount,0) - nvl(gb.majmoo_maliat,0) - nvl(gb.majmoo_ezafi,0))) * 0.75))       

;



