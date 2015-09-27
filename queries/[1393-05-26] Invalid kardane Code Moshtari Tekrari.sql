
update tbl_credebit bedehi
set bedehi.shomare_moshtari=bedehi.shomare_moshtari || '_INVALID'
where exists(
              select cr.shomare_moshtari
              from tbl_credebit cr
              where cr.credebit_type='GHEST' and cr.shomare_moshtari=bedehi.shomare_moshtari
              group by cr.shomare_moshtari having count(cr.id)>1
            );
---------Ghesthaye Sanad nakhorde ke code moshtari tekrari darand---------------
select bedehi.* from tbl_credebit bedehi
left join tbl_khate_sanad kh on kh.bedehi_credebit_id=bedehi.id
where kh.id is null and 
      bedehi.credebit_type='GHEST' and 
      exists(
              select cr.shomare_moshtari
              from tbl_credebit cr
              where cr.credebit_type='GHEST' and cr.shomare_moshtari=bedehi.shomare_moshtari
              group by cr.shomare_moshtari having count(cr.id)>1
            );
-----------------------------Code Moshtari haye Tekrari-------------------------
select cr.shomare_moshtari
from tbl_credebit cr
where cr.credebit_type='GHEST' 
group by cr.shomare_moshtari having count(cr.id)>1;
--------------------------------------------------------------------------------

select * from tbl_credebit cr
where cr.shomare_moshtari='99999390012949812';