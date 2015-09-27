update tbl_khate_Sanad kh
set  kh.ETEBARREMAINING=( 
                            select remaining_amount_long 
                            from  tbl_credebit
                            where tbl_credebit.id=kh.etebar_credebit_id
                        );                   
                          