update tbl_daryafte_fish df
set df.time = (
                        select tbl_credebit.created_time
                        from tbl_credebit
                        where tbl_credebit.daryafte_fish_id=df.id
                     );