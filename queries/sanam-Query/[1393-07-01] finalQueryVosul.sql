select  
c.subsystem_identifier,
                        c.shenase_credebit,
                        c.amount_long,
                        to_number(replace(k.amount ,',' ,'')) mablagh_tasviye_shode,
                        k.amount mablagh_tasviye_shode,
                       c.remaining_amount_long,
                       ec.shomare_moshtari,
                       c.credebit_type,
                        c.uniquecode,
                       c.rcpt_name,
                       c.sarresid_date
          from tbl_credebit c
          join 
               (
               select credebit1_.id crid
                     from tbl_credebit credebit1_
                      inner join tbl_khate_sanad khatesanad2_
                         on credebit1_.id = khatesanad2_.bedehi_credebit_id
                      inner join tbl_credebit credebit3_
                         on khatesanad2_.etebar_credebit_id = credebit3_.id
                      where 
-- Bedehihaii ke kamel pardakht shode and                      
                        credebit1_.remaining_amount_long = 0
                       and credebit3_.credebit_type <> 'VEHICLE_HAGHBIME_ELECTRONICI'
                   and credebit3_.credebit_type <> 'VEHICLE_HAGHBIME_BARGASHTI'
                        and credebit3_.credebit_type <> 'AZ_MAHALLE_TABLIGHAT'
                        and khatesanad2_.bedehi_credebit_id not in (select max(s1.bedehi_credebit_id) from tbl_credebit c3 join tbl_khate_sanad s1 on s1.bedehi_credebit_id=c3.id join tbl_credebit c4 on c4.id=s1.etebar_credebit_id
group by s1.bedehi_credebit_id,c4.vosoul_state having c4.vosoul_state='TAEED_NASHODE')
                      group by credebit1_.id, credebit3_.vosoul_state
                     -- having credebit3_.vosoul_state = 'TAEED_VOSOUL'
                      /* max(credebit3_.vosoul_state) = 'TAEED_VOSOUL' and min(credebit3_.vosoul_state) = 'TAEED_VOSOUL'*/
                  ) tv   on tv.crid = c.id
          left join tbl_khate_sanad k
            on c.id = k.bedehi_credebit_id
          left join tbl_credebit ec
            on ec.id = k.etebar_credebit_id
          left join tbl_bankinfo b
            on b.credebit_id = ec.id
         where c.subsystem_name = 'VEHICLE'  
       --and c.subsystem_identifier='1110/297011/92/000740'
--         and c.rcpt_name ='ÔÑßÊ ÎÔ ÇáÈÑÒ (ÓåÇãí ÚÇã)'
--         and c.vahedesodor_id =210110
         and
         case
                 when(ec.credebit_type = 'DARYAFTE_ELECTRONICI') then
                  ec.created_date
				 when (length(b.taarikh) = 10) then
                  b.taarikh
                 else
                  b.created_date
               end >= '1393/01/01'
               and
               case
                 when(ec.credebit_type = 'DARYAFTE_ELECTRONICI') then
                  ec.created_date
				 when (length(b.taarikh) = 10) then
                  b.taarikh
                 else
                  b.created_date
               end <= '1393/06/31'

        union
        select distinct c.subsystem_identifier,
                        c.shenase_credebit,
                       c.amount_long,
                       c.amount_long mablagh_tasviye_shode,
                        '',
                       c.remaining_amount_long,
                        '',
                       c.credebit_type,
                        c.uniquecode,
                       c.rcpt_name,
                       c.sarresid_date
                  
          from tbl_credebit c
         where 
         (
               c.credebit_type like 'VEHICLE_HAGHBIME_ELECTRONICI'
               or
               c.credebit_type like 'VEHICLE_HAGHBIME_BARGASHTI'
          )
       -- and c.subsystem_identifier = '1110/297011/92/000740'
--         and c.rcpt_name ='ÔÑßÊ ÎÔ ÇáÈÑÒ (ÓåÇãí ÚÇã)'
--         and c.vahedesodor_id =210110
           and c.sarresid_date >= '1393/01/01' and c.sarresid_date <= '1393/06/31'
           
  Union
  
       select    
                          c.subsystem_identifier,
                        c.shenase_credebit,
                        c.amount_long,
                        to_number(replace(k.amount ,',' ,'')) mablagh_tasviye_shode,
                      k.amount mablagh_tasviye_shode,
                        c.remaining_amount_long,
                       ec.shomare_moshtari,
                       c.credebit_type,
                        c.uniquecode,
                        c.rcpt_name,
                        c.sarresid_date
          from tbl_credebit c
          join 
               (
               select credebit1_.id crid
                     from tbl_credebit credebit1_
                      inner join tbl_khate_sanad khatesanad2_
                         on credebit1_.id = khatesanad2_.bedehi_credebit_id
                      inner join tbl_credebit credebit3_
                         on khatesanad2_.etebar_credebit_id = credebit3_.id
                      where 
-- Bedehihaii ke kamel pardakht shode and                      
                        credebit1_.remaining_amount_long = 0
                       and credebit3_.credebit_type <> 'VEHICLE_HAGHBIME_ELECTRONICI'
                       and credebit3_.credebit_type = 'VEHICLE_HAGHBIME_BARGASHTI'
                       and credebit3_.credebit_type <> 'PARDAKHT_SHENASEDAR'
                      and credebit3_.credebit_type <> 'AZ_MAHALLE_TABLIGHAT'
                      and khatesanad2_.bedehi_credebit_id not in (select max(s1.bedehi_credebit_id) from tbl_credebit c3 join tbl_khate_sanad s1 on s1.bedehi_credebit_id=c3.id join tbl_credebit c4 on c4.id=s1.etebar_credebit_id
group by s1.bedehi_credebit_id,c4.vosoul_state having c4.vosoul_state='TAEED_NASHODE')
                      group by credebit1_.id, credebit3_.vosoul_state
                    --  having credebit3_.vosoul_state = 'TAEED_VOSOUL'
                      /*max(credebit3_.vosoul_state) = 'TAEED_VOSOUL' and min(credebit3_.vosoul_state) = 'TAEED_VOSOUL'*/
                  ) tv   on tv.crid = c.id
          left join tbl_khate_sanad k
            on c.id = k.bedehi_credebit_id
          left join tbl_credebit ec
            on ec.id = k.etebar_credebit_id
   --       left join tbl_bankinfo b
     --       on b.credebit_id = ec.id
         where c.subsystem_name = 'VEHICLE' 
           and c.sarresid_date >= '1393/01/01' and c.sarresid_date <= '1393/06/31'
           
