update tbl_credebit
set bank_name = ' Ã«—  «·ò —Ê‰Ìò - „Ì—œ«„«œ ‘—ﬁÌ (0201136462000)'
where credebit_type = 'DARYAFTE_ELECTRONICI';
--------------------------------------------------------------------------------
update tbl_credebit
set bank_name = 'Å«—”Ì«‰ - »·Ê«— ò‘«Ê—“ (0200234164006)'
where credebit_type = 'PISHPARDAKHT';
--------------------------------------------------------------------------------
update tbl_credebit c
set c.bank_name = 'Å«—”Ì«‰ - Ê‰ò (81011989)'
where 
      c.credebit_type = 'DARYAFTE_FISH' 
  and c.daryafte_fish_id in
                            (
                                select df.id from tbl_daryafte_fish df where df.bank_info_id in
                                (
                                    select bi.main_id from tbl_bankinfo bi where bi.id in 
                                    (
                                        select b.id from tbl_bargozarandeh b where b.shomare_hesab = '81011989'
                                    )
                                )
                            );
--------------------------------------------------------------------------------
update tbl_credebit c
set c.bank_name = ' Ã«—  - ”ÅÂ»œ ﬁ—‰Ì (17038494)'
where 
      c.credebit_type = 'DARYAFTE_FISH' 
  and c.daryafte_fish_id in
                            (
                                select df.id from tbl_daryafte_fish df where df.bank_info_id in
                                (
                                    select bi.main_id from tbl_bankinfo bi where bi.id in 
                                    (
                                        select b.id from tbl_bargozarandeh b where b.shomare_hesab = '17038494'
                                    )
                                )
                            )     ;   
--------------------------------------------------------------------------------
update tbl_credebit
set bank_name = ' Ã«—  «·ò —Ê‰Ìò - „Ì—œ«„«œ ‘—ﬁÌ (0201136462000)'
where format='230';
                            