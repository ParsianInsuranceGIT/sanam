 update tbl_ghestbandi gb
set gb.majmoo_amount =
   (
       SELECT mj.correctmajmoo from man_majmoghalata2 mj where mj.gbid = gb.id
   )
  where exists
   (
       SELECT * from man_majmoghalata2 mj2 where mj2.gbid = gb.id
   ) ;



 update tbl_ghestbandi gb
set gb.majmoo_amount =
   (
       SELECT mj.gbmajmoo from man_majmoghalata2 mj where mj.gbid = gb.id
   )
  where exists
   (
       SELECT mj2.*
         from man_majmoghalata2 mj2
      join tbl_bimename b on b.bimename_id = mj2.bimenameid
        where mj2.gbid = gb.id
         and ( bimename_shomare not like '%412070/91/%' and  bimename_shomare not like '%111116/91/%' and bimename_shomare not like '%/92/%' )
   ) ; 