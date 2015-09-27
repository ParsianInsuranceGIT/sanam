select g.id,g.karmozd_real,g.karmozd_paid,trunc((cr.amount_long*gb.karmozdyear)/gb.majmoo_amount) from tbl_ghest g 
         join tbl_credebit cr on cr.ghest_id=g.id 
         join tbl_ghestbandi gb on gb.id=g.ghestbandi_id 
where g.karmozd_real is not null and gb.karmozdyear is not null;
------------------------------------------------------------------------------
 update tbl_ghest g 
set g.karmozd_real=(
                      select trunc((cr.amount_long*gb.karmozdyear)/gb.majmoo_amount) 
                      from tbl_ghest g1
                      join tbl_credebit cr on cr.ghest_id=g1.id 
                      join tbl_ghestbandi gb on gb.id=g1.ghestbandi_id      
                      where g1.id=g.id
                   )
 where g.karmozd_real is not null and exists(select gb1.id from tbl_ghestbandi gb1 where gb1.karmozdyear is not null and gb1.id=g.ghestbandi_id);
 