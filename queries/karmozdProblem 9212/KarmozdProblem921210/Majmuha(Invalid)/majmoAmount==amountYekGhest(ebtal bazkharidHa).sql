select es.nahve_pardakht_hagh_bime,b.bimename_id,b.state_id,  gb.id,gb.majmoo_amount,es.mablagh_sepor_ebted_sal,sum(cr.amount_long),count(g.id)
  from tbl_ghestbandi gb 
  join tbl_ghest g on g.ghestBandi_id=gb.id 
  join tbl_credebit cr on cr.ghest_id=g.id
  join tbl_bimename b on b.bimename_id=gb.bimename_id
  join tbl_pishnehad p on p.bimename_bimename_id=b.bimename_id
  join tbl_estelam es on es.estelam_id = p.estelam_id  
where b.bimename_ready='yes' and b.bimename_tarikhsodur>='1392/03/01' and p.c_valid=1 and b.state_id>=540 and es.nahve_pardakht_hagh_bime!='yekja'
      and es.nahve_pardakht_hagh_bime!='sal' and gb.salebimei=0
group by es.nahve_pardakht_hagh_bime, b.bimename_id, b.state_id, gb.id, gb.majmoo_amount, es.mablagh_sepor_ebted_sal
having sum(cr.amount_long)=gb.majmoo_amount;
