update tbl_credebit c
set c.rcpt_name = (
                      select concat(concat(s.name,' '),s.name_khanevadegi) 
                      from tbl_shakhs s
                      inner join tbl_bimegozar bg on bg.shakhs_id = s.shakhs_id
                      inner join tbl_pishnehad p on p.bimegozar_id = bg.id
                      where c.pishnehad_id=p.id
                  )
where c.rcpt_name is null;
