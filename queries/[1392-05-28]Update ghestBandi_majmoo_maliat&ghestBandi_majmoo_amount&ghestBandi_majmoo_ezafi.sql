update tbl_ghestbandi gb_u 
set gb_u.majmoo_amount = (select sum(c.amount_long) from tbl_credebit c 
                                                    inner join tbl_ghest g on c.ghest_id=g.id
                          where g.ghestbandi_id = gb_u.id)
                          where gb_u.majmoo_amount is null;
--------------------------------------------------------------------------------
update tbl_ghestbandi gb_u 
set gb_u.majmoo_maliat = (select sum(g.maliat_long) from tbl_ghest g where g.ghestbandi_id = gb_u.id) where gb_u.majmoo_maliat is null;
--------------------------------------------------------------------------------
update tbl_ghestbandi gb_u
set gb_u.majmoo_ezafi = (select sum(g.hagh_bime_ezafi_long) from tbl_ghest g where g.ghestbandi_id = gb_u.id) where gb_u.majmoo_ezafi is null;
--------------------------------------------------------------------------------

                          