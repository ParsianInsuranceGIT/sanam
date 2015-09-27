select p.id,es.nahve_pardakht_hagh_bime, gb.salebimei, b.bimename_tarikhsodur, gb.id,gb.majmoo_amount,sum(cr.amount_long) 
  from tbl_ghestbandi gb 
  join tbl_ghest g on g.ghestbandi_id=gb.id
  join tbl_credebit cr on cr.ghest_id=g.id
  join tbl_bimename b on b.bimename_id=gb.bimename_id
  join tbl_pishnehad p on p.bimename_bimename_id=b.bimename_id
  join tbl_estelam es on es.estelam_id=p.estelam_id
where 
  p.c_valid=1 and b.bimename_ready='yes' and b.state_id<=510 and es.nahve_pardakht_hagh_bime!='yekja'
  and ( b.bimename_shomare like '%412070/91/%'
   or b.bimename_shomare like '%111116/91/%'
   or b.bimename_shomare like '%/92/%' )
group by p.id, es.nahve_pardakht_hagh_bime, gb.salebimei, b.bimename_tarikhsodur, gb.id, gb.majmoo_amount having gb.majmoo_amount<>sum(cr.amount_long);