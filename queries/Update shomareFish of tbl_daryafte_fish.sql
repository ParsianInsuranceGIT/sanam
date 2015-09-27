update tbl_daryafte_fish df
set df.shomareFish = (
                        select tbl_credebit.shomare_moshtari
                        from tbl_credebit
                        where tbl_credebit.daryafte_fish_id=df.id
                     );