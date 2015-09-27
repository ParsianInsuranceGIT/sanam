
 update tbl_sequence s
 set seq_count = LPAD(seq_count,6,0)
   where year = 93
   and seq_name = 'SUB_CODE_RAHGIRI_PISHNAHAD'
   and seq_count not like '______'
;

Select s.*,LPAD(seq_count,6,0)
   from tbl_sequence s
  where year = 93
   and seq_name = 'SUB_CODE_RAHGIRI_PISHNAHAD'
   and seq_count not like '______'
;

--------------------------------------------------------------------------------
update tbl_pishnehad p
set p.radif=concat(substr(p.radif,1,10),LPAD(substr(p.radif,11,14),6,0))
  where radif not like '______/__/______'
  and p.created_date='1393/01/17'
;

 Select p.radif,concat(substr(p.radif,1,10),LPAD(substr(p.radif,11,14),6,0))
   from tbl_pishnehad p
  where radif not like '______/__/______'
  and p.created_date='1393/01/17'
order by p.id desc;
--------------------------------------------------------------------------------