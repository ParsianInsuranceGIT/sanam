 select
   (
       select ( cr2.amount_long )
         from tbl_credebit cr2
        where cr2.id = calcTable.mincr
   )
   amountMin ,
   calcTable.bimenameId ,
   calcTable.pishnehadID,
   calcTable.tarikhSodur ,
   calcTable.gbid ,
   calcTable.bState ,
   calcTable.gbMajmoo ,
   calcTable.esnahve ,
   calcTable.mincr ,
   calcTable.countghest ,
   calcTable.SepordeAvlie ,
   case
      when calcTable.bState in ( 500 ,510 )
      then sumamoun500
      when calcTable.bState > 510
      then (
         case
            when calcTable.esnahve = 'mah'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) * 12 + SepordeAvlie
            when calcTable.esnahve = '3mah'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) * 4 + SepordeAvlie
            when calcTable.esnahve = '6mah'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) * 2 + SepordeAvlie
            when calcTable.esnahve = 'sal'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) + SepordeAvlie
         end )
   end CorrectMajmoo
   from
   (
       select b.bimename_id bimenameId ,
         p.id pishnehadID ,
         b.bimename_tarikhsodur tarikhSodur ,
         gb.id gbid ,
         b.state_id bState ,
         gb.majmoo_amount gbMajmoo ,
         es.nahve_pardakht_hagh_bime esnahve ,
         min ( cr.id ) mincr ,
         sum ( cr.amount_long ) sumamoun500 ,
         count ( g.id ) countghest ,
         replace ( nvl ( es.mablagh_sepor_ebted_sal ,0 ) ,',' ) SepordeAvlie
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
         gb.majmoo_amount ,
         es.mablagh_sepor_ebted_sal ,
         es.nahve_pardakht_hagh_bime
      having sum ( cr.amount_long ) != gb.majmoo_amount
   )
   calcTable
  where ( calcTable.gbMajmoo - (
   case
      when calcTable.bState in ( 500 ,510 )
      then sumamoun500
      when calcTable.bState > 510
      then (
         case
            when calcTable.esnahve = 'mah'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) * 12 + SepordeAvlie
            when calcTable.esnahve = '3mah'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) * 4 + SepordeAvlie
            when calcTable.esnahve = '6mah'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) * 2 + SepordeAvlie
            when calcTable.esnahve = 'sal'
            then (
               (
                   select ( cr2.amount_long )
                     from tbl_credebit cr2
                    where cr2.id = calcTable.mincr
               )
               - SepordeAvlie ) + SepordeAvlie
         end )
   end ) ) <> 0;
