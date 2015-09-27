select s.kode_melli_shenasayi,count(distinct s.shakhs_id)
from tbl_shakhs s 
left join tbl_bimeShode bs on bs.shakhs_id=s.shakhs_id 
left join tbl_bimeGozar bg on bg.shakhs_id=s.shakhs_id 
where (bs.id is not null or bg.id is not null) --and s.kode_melli_shenasayi ='0732123240'
group by s.kode_melli_shenasayi having count(distinct s.shakhs_id)>1;

update tbl_bimeshode bs_for_update
set bs_for_update.shakhs_id=(
                              select max(s2.shakhs_id) 
                              from tbl_shakhs s2 
                              where s2.kode_melli_shenasayi=(
                                                              select s1.kode_melli_shenasayi 
                                                              from tbl_shakhs s1 
                                                              where s1.shakhs_id=bs_for_update.shakhs_id
                                                            )
                            )
where exists (
                select s.kode_melli_shenasayi
                from tbl_shakhs s 
                left join tbl_bimeShode bs on bs.shakhs_id=s.shakhs_id 
                left join tbl_bimeGozar bg on bg.shakhs_id=s.shakhs_id 
                where (bs.id is not null or bg.id is not null) and 
                s.kode_melli_shenasayi = (select s3.kode_melli_shenasayi from tbl_shakhs s3 where s3.shakhs_id=bs_for_update.shakhs_id)
                group by s.kode_melli_shenasayi having count(distinct s.shakhs_id)>1                  
             )

;
select b.*
from tbl_bimeshode b
join tbl_shakhs s on s.shakhs_id=b.shakhs_id
where s.kode_melli_shenasayi='0732123240'