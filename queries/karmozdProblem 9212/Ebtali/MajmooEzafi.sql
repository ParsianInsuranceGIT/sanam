 select
   (
       select ( g2.hagh_bime_ezafi_long )
         from tbl_ghest g2
        where g2.id = calcTable.mingb
   )
   amountMin ,
   calcTable.bimenameId ,
   calcTable.pishnehadID ,
   calcTable.tarikhSodur ,
   calcTable.gbid ,
   calcTable.bState ,
   calcTable.majMaliat ,
   calcTable.esnahve ,
   calcTable.mingb ,
   calcTable.countghest ,
   calcTable.SepordeAvlie ,
   case
      when calcTable.bState in ( 500 ,510 )
      then sumMaliat500
      when calcTable.bState > 510
      then 
         case
            when calcTable.esnahve = 'mah'
            then (
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
               ) * 12
            when calcTable.esnahve = '3mah'
            then (
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
              ) * 4 
            when calcTable.esnahve = '6mah'
            then (
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
                  ) * 2
            when calcTable.esnahve = 'sal'
            then (
                     
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb                     
              )  
         end
   end CorrectMajmoo
   from
   (
       select b.bimename_id bimenameId ,
         p.id pishnehadID ,
         b.bimename_tarikhsodur tarikhSodur ,
         gb.id gbid ,
         b.state_id bState ,
         gb.majmoo_ezafi majMaliat ,
         replace ( nvl ( es.mablagh_sepor_ebted_sal ,0 ) ,',' ) SepordeAvlie,
         es.nahve_pardakht_hagh_bime esnahve ,
         min ( gb.id ) mingb ,
         sum ( g.hagh_bime_ezafi_long ) sumMaliat500 ,
         count ( g.id ) countghest
         from tbl_ghestbandi gb
      join tbl_ghest g     on g.ghestBandi_id = gb.id
      join tbl_credebit cr on cr.ghest_id = g.id
      join tbl_bimename b  on b.bimename_id = gb.bimename_id
      join tbl_pishnehad p on p.bimename_bimename_id = b.bimename_id
      join tbl_estelam es  on es.estelam_id = p.estelam_id
        where b.bimename_ready = 'yes'
         and b.bimename_tarikhsodur >= '1390/11/01'
         and p.c_valid = 1
         and es.nahve_pardakht_hagh_bime! = 'yekja'
         --   and es.nahve_pardakht_hagh_bime! = 'sal'
         and gb.salebimei = 0
         and gb.TYPE = 'G_BIMENAME'
         -- Sadere dar System Jadid
         and ( b.bimename_shomare like '%412070/91/%'
         or b.bimename_shomare like '%111116/91/%'
         or b.bimename_shomare like '%/92/%' )
     group by b.bimename_id ,
         p.id ,
         b.bimename_tarikhsodur ,
         gb.id ,
         b.state_id ,
         gb.majmoo_ezafi ,
         es.mablagh_sepor_ebted_sal ,
         es.nahve_pardakht_hagh_bime
      having sum ( g.hagh_bime_ezafi_long ) != gb.majmoo_ezafi
   )
   calcTable
  where ( calcTable.majMaliat - (
   case
      when calcTable.bState in ( 500 ,510 )
      then sumMaliat500
      when calcTable.bState > 510
      then 
         case
            when calcTable.esnahve = 'mah'
            then (
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
               ) * 12
            when calcTable.esnahve = '3mah'
            then (
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
              ) * 4 
            when calcTable.esnahve = '6mah'
            then (
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
                  ) * 2
            when calcTable.esnahve = 'sal'
            then 
                     (
                         select ( g2.hagh_bime_ezafi_long )
                           from tbl_ghest g2
                          where g2.id = calcTable.mingb
                     )
               
         end 
   end )) <> 0 ;
