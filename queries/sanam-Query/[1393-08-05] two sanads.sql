select c.id, sum(replace(ksb.amount, ',', '')) as sanad, c.amount_long, c.remaining_amount_long, c.subsystem_name, c.created_date, c.subsystem_identifier, c.credebit_type
from tbl_credebit c join tbl_khate_sanad ksb on ksb.bedehi_credebit_id = c.id
where c.subsystem_name = 'VEHICLE'
group by c.id, c.amount_long, c.remaining_amount_long, c.subsystem_name, c.created_date, c.subsystem_identifier, c.credebit_type
having sum(replace(ksb.amount, ',', '')) > c.amount_long - c.remaining_amount_long and count(ksb.bedehi_credebit_id) > 1
order by c.created_date desc;

select c.id, sum(replace(kse.amount, ',', '')) as sanad, c.amount_long, c.remaining_amount_long, c.subsystem_name, c.created_date, c.subsystem_identifier, c.credebit_type
from tbl_credebit c join tbl_khate_sanad kse on kse.etebar_credebit_id = c.id
where c.subsystem_name = 'VEHICLE'
group by c.id, c.amount_long, c.remaining_amount_long, c.subsystem_name, c.created_date, c.subsystem_identifier, c.credebit_type
having sum(replace(kse.amount, ',', '')) > c.amount_long - c.remaining_amount_long and count(kse.bedehi_credebit_id) > 1
order by c.created_date desc;