 select p.id pishnehad_id ,
   gb.salebimei ,
   dt.state_id taghirState ,
   g.id ghestId ,
   g.karmozd_real ,
   trunc ( ( ( b.bimename_karmozd * 0.4 ) * cr.amount_long ) / gb.majmoo_amount ) Correct
   from tbl_ghest g
join tbl_credebit cr             on cr.ghest_id = g.id
join tbl_ghestbandi gb           on gb.id = g.ghestbandi_id
join tbl_bimename b              on b.bimename_id = gb.bimename_id
join tbl_pishnehad p             on p.bimename_bimename_id = b.bimename_id
join tbl_estelam es              on es.estelam_id = p.estelam_id
left join tbl_darkhast_taghir dt on dt.newpish_id = p.id
  where gb.salebimei = 0
   and g.karmozd_real is not null
   and p.c_valid = 1
   and b.bimename_ready = 'yes'
   and es.nahve_pardakht_hagh_bime != 'yekja'
   and gb.type = 'G_BIMENAME'
   and ( ( dt.state_id! = 9200
   and dt.state_id! = 9190
   and dt.state_id! = 9180 )
   or dt.state_id is null )
   and g.karmozd_real <> trunc ( ( ( b.bimename_karmozd * 0.4 ) * cr.amount_long ) / gb.majmoo_amount )
   and g.karmozd_real <> trunc ( ( ( b.bimename_karmozd * 0.4 ) * cr.amount_long ) / gb.majmoo_amount ) + 1
   and g.karmozd_real <> trunc ( ( ( b.bimename_karmozd * 0.4 ) * cr.amount_long ) / gb.majmoo_amount ) - 1
   -- Sadere dar System Jadid
   and ( b.bimename_shomare like '%412070/91/%'
   or b.bimename_shomare like '%111116/91/%'
   or b.bimename_shomare like '%/92/%' )
group by p.id ,
   gb.salebimei ,
   dt.state_id ,
   g.id ,
   g.karmozd_real ,
   trunc ( ( ( b.bimename_karmozd * 0.4 ) * cr.amount_long ) / gb.majmoo_amount ) ;