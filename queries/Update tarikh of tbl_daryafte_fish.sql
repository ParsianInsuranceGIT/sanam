update tbl_daryafte_fish df
set df.tarikh = (
                        select tbl_credebit.created_date
                        from tbl_credebit
                        where tbl_credebit.daryafte_fish_id=df.id
                     );