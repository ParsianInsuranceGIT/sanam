select gb.id,gb.majmoo_amount,sum(cr.amount_long) from tbl_ghestbandi gb
                        join tbl_ghest g on g.ghestbandi_id=gb.id
                        join tbl_credebit cr on cr.ghest_id=g.id 
                        join tbl_bimename b on b.bimename_id=gb.bimename_id
where b.bimename_ready='yes' and cr.credebit_type='GHEST'                        
group by gb.id, gb.majmoo_amount having sum(cr.amount_long)<>gb.majmoo_amount;



update tbl_ghestbandi gb_u 
set gb_u.majmoo_amount = (select sum(c.amount_long) from tbl_credebit c 
                                                    inner join tbl_ghest g on c.ghest_id=g.id
                          where g.ghestbandi_id = gb_u.id)
                          where gb_u.id in(select gb.id
                                                                                    from tbl_ghestbandi gb
                                                                                                            join tbl_ghest g on g.ghestbandi_id=gb.id
                                                                                                            join tbl_credebit cr on cr.ghest_id=g.id 
                                                                                                            join tbl_bimename b on b.bimename_id=gb.bimename_id
                                                                                    where cr.credebit_type='GHEST'                        
                                                                                    group by gb.id, gb.majmoo_amount having sum(cr.amount_long)<>gb.majmoo_amount);